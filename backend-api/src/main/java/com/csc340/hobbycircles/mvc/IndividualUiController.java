package com.csc340.hobbycircles.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csc340.hobbycircles.dto.IndividualUpdateRequest;
import com.csc340.hobbycircles.service.CommentService;
import com.csc340.hobbycircles.service.IndividualService;
import com.csc340.hobbycircles.service.RsvpService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller 
@RequestMapping("/individuals")
public class IndividualUiController {

    private final IndividualService individualService;
    private final RsvpService rsvpService;
    private final CommentService commentService;

    public IndividualUiController(
                IndividualService individualService,
                RsvpService rsvpService,
                CommentService commentService){
            this.individualService = individualService;
            this.rsvpService = rsvpService;
            this.commentService = commentService;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Long individualId = UiSession.requireId(session, "individual");
        model.addAttribute("individual", individualService.getProfile(individualId));
        model.addAttribute("rsvps", rsvpService.listForIndividual(individualId));
        model.addAttribute("comments", commentService.listForIndividual(individualId));
        return "individuals/dashboard";
    }

    @GetMapping("/edit")
    public String editDetails(HttpSession session, Model model) {
        model.addAttribute("individual",
                individualService.getProfile(UiSession.requireId(session, "individual")));
        return "individuals/edit";
    }

    @PostMapping("/edit")
    public String updateDetails(@Valid IndividualUpdateRequest request, HttpSession session) {
        individualService.updateProfile(UiSession.requireId(session, "individual"), request);
        return "redirect:/individuals/dashboard";
    }

    
}
