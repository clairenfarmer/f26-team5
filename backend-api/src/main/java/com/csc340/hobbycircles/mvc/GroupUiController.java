package com.csc340.hobbycircles.mvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csc340.hobbycircles.dto.EventRequest;
import com.csc340.hobbycircles.dto.EventResponse;
import com.csc340.hobbycircles.dto.EventUpdateRequest;
import com.csc340.hobbycircles.dto.HobbyGroupResponse;
import com.csc340.hobbycircles.service.BadRequestException;
import com.csc340.hobbycircles.service.CommentService;
import com.csc340.hobbycircles.service.EventService;
import com.csc340.hobbycircles.service.HobbyGroupService;
import com.csc340.hobbycircles.service.RsvpService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/hobbygroups")
public class GroupUiController {
    private final HobbyGroupService hobbyGroupService;
    private final EventService eventService;
    private final CommentService commentService;
    private final RsvpService rsvpService;

    public GroupUiController(HobbyGroupService hobbyGroupService, EventService eventService,
            CommentService commentService, RsvpService rsvpService) {
        this.hobbyGroupService = hobbyGroupService;
        this.eventService = eventService;
        this.commentService = commentService;
        this.rsvpService = rsvpService;
    }

    // view a single event: stats, details, and who has RSVP'd
    @GetMapping("/events/{eventId}")
    public String viewEvent(@PathVariable Long eventId, HttpSession session, Model model) {
        Long groupId = UiSession.requireId(session, "group");
        EventResponse event = eventService.getEvent(eventId);
        if (!event.hobbygroupId().equals(groupId)) {
            throw new BadRequestException("Event does not belong to this hobby group.");
        }
        long daysUntil = ChronoUnit.DAYS.between(LocalDate.now(), LocalDateTime.parse(event.time()).toLocalDate());
        model.addAttribute("event", event);
        model.addAttribute("daysUntil", daysUntil);
        model.addAttribute("rsvps", rsvpService.getRsvps(groupId, eventId));
        return "group/event";
    }

    // edit an event: show the form prefilled with the current values
    @GetMapping("/events/{eventId}/edit")
    public String showEditForm(@PathVariable Long eventId, HttpSession session, Model model) {
        Long groupId = UiSession.requireId(session, "group");
        EventResponse event = findOwnedEvent(groupId, eventId);
        model.addAttribute("name", event.name());
        model.addAttribute("time", event.time().length() > 16 ? event.time().substring(0, 16) : event.time());
        model.addAttribute("location", event.location());
        model.addAttribute("description", event.description());
        addEditFormAttributes(model, eventId);
        return "group/event-form";
    }

    // edit an event: handle the submitted form
    @PostMapping("/events/{eventId}/edit")
    public String updateEvent(
            @PathVariable Long eventId,
            @RequestParam String name,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime time,
            @RequestParam String location,
            @RequestParam(required = false) String description,
            HttpSession session,
            Model model) {
        Long groupId = UiSession.requireId(session, "group");
        EventResponse existing = findOwnedEvent(groupId, eventId);

        String cleanName = name.trim();
        String cleanLocation = location.trim();
        String cleanDescription = description == null ? null : description.trim();

        String error = validateEventFields(cleanName, cleanLocation, cleanDescription);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("name", cleanName);
            model.addAttribute("time", time.toString());
            model.addAttribute("location", cleanLocation);
            model.addAttribute("description", cleanDescription);
            addEditFormAttributes(model, eventId);
            return "group/event-form";
        }

        // the form has no interest or status fields, so keep the event's existing values
        eventService.updateEvent(groupId, eventId, new EventUpdateRequest(
                cleanName, cleanDescription, existing.Interest(), cleanLocation, time, existing.status()));
        return "redirect:/hobbygroups/events/" + eventId;
    }

    private EventResponse findOwnedEvent(Long groupId, Long eventId) {
        EventResponse event = eventService.getEvent(eventId);
        if (!event.hobbygroupId().equals(groupId)) {
            throw new BadRequestException("Event does not belong to this hobby group.");
        }
        return event;
    }

    private void addEditFormAttributes(Model model, Long eventId) {
        model.addAttribute("heading", "Edit Event");
        model.addAttribute("formAction", "/hobbygroups/events/" + eventId + "/edit");
    }

    private String validateEventFields(String name, String location, String description) {
        if (name.isEmpty() || name.length() > 150) {
            return "Event name is required and must be 150 characters or fewer.";
        }
        if (location.isEmpty() || location.length() > 255) {
            return "Location is required and must be 255 characters or fewer.";
        }
        if (description != null && description.length() > 2000) {
            return "Description must be 2000 characters or fewer.";
        }
        return null;
    }

    // group dashboard: welcome header, the group's events, and questions/comments
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Long groupId = UiSession.requireId(session, "group");
        model.addAttribute("group", hobbyGroupService.getProfile(groupId));
        model.addAttribute("events", eventService.browse(groupId, null));
        model.addAttribute("comments", commentService.listForHobbyGroup(groupId));
        return "group/dashboard";
    }

    // create an event: show the form
    @GetMapping("/event-form")
    public String showEventForm(HttpSession session) {
        UiSession.requireId(session, "group");
        return "group/event-form";
    }

    // create an event: handle the submitted form
    @PostMapping("/events")
    public String createEvent(
            @RequestParam String name,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime time,
            @RequestParam String location,
            @RequestParam(required = false) String description,
            HttpSession session,
            Model model) {
        Long groupId = UiSession.requireId(session, "group");

        String cleanName = name.trim();
        String cleanLocation = location.trim();
        String cleanDescription = description == null ? null : description.trim();

        String error = null;
        if (cleanName.isEmpty() || cleanName.length() > 150) {
            error = "Event name is required and must be 150 characters or fewer.";
        } else if (cleanLocation.isEmpty() || cleanLocation.length() > 255) {
            error = "Location is required and must be 255 characters or fewer.";
        } else if (cleanDescription != null && cleanDescription.length() > 2000) {
            error = "Description must be 2000 characters or fewer.";
        }
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("name", cleanName);
            model.addAttribute("time", time.toString());
            model.addAttribute("location", cleanLocation);
            model.addAttribute("description", cleanDescription);
            return "group/event-form";
        }

        // the form has no interest or status fields: events use the group's interest and start as PUBLISHED
        HobbyGroupResponse group = hobbyGroupService.getProfile(groupId);
        eventService.create(groupId, new EventRequest(
                cleanName, cleanDescription, group.interest(), cleanLocation, time, "PUBLISHED"));
        return "redirect:/hobbygroups/dashboard";
    }

    // cancel (delete) an event; the dashboard asks for confirmation before submitting
    @PostMapping("/events/{eventId}/cancel")
    public String cancelEvent(@PathVariable Long eventId, HttpSession session,
            RedirectAttributes redirectAttributes) {
        Long groupId = UiSession.requireId(session, "group");
        String name = eventService.delete(groupId, eventId);
        // flash attributes survive exactly one redirect, so the pop-up shows only once
        redirectAttributes.addFlashAttribute("popup", "\"" + name + "\" has been cancelled and deleted.");
        return "redirect:/hobbygroups/dashboard";
    }
}
