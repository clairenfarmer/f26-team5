package com.csc340.hobbycircles.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
