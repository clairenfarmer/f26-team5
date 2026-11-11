package com.csc340.hobbycircles.mvc;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csc340.hobbycircles.dto.EventRequest;
import com.csc340.hobbycircles.dto.HobbyGroupResponse;
import com.csc340.hobbycircles.service.CommentService;
import com.csc340.hobbycircles.service.EventService;
import com.csc340.hobbycircles.service.HobbyGroupService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/hobbygroups")
public class GroupUiController {
    private final HobbyGroupService hobbyGroupService;
    private final EventService eventService;
    private final CommentService commentService;

    public GroupUiController(HobbyGroupService hobbyGroupService, EventService eventService,
            CommentService commentService) {
        this.hobbyGroupService = hobbyGroupService;
        this.eventService = eventService;
        this.commentService = commentService;
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
}
