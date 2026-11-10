package com.csc340.hobbycircles.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csc340.hobbycircles.dto.HobbyGroupResponse;
import com.csc340.hobbycircles.dto.IndividualResponse;
import com.csc340.hobbycircles.service.BadRequestException;
import com.csc340.hobbycircles.service.HobbyGroupService;
import com.csc340.hobbycircles.service.IndividualService;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;


@Controller 
public class AppController {
    private final IndividualService individualService;
    private final HobbyGroupService hobbyGroupService;

    public AppController(IndividualService individualService, HobbyGroupService hobbyGroupService) {
        this.individualService = individualService;
        this.hobbyGroupService = hobbyGroupService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("groups", hobbyGroupService.browse(null));
        return "public/index";
    }

    @GetMapping("/login")
    public String login() {
        return "public/login";
    }

    @PostMapping("/login")
    public String authenticate(
            @RequestParam String role,
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session) {
        if ("group".equalsIgnoreCase(role)) {
            HobbyGroupResponse hobbyGroup = hobbyGroupService.authenticate(email, password);
            session.setAttribute("userId", hobbyGroup.id());
            session.setAttribute("userRole", "group");
            session.setAttribute("userName", hobbyGroup.name());
            return "redirect:/hobbygroups/dashboard";
        }
        if (!"individual".equalsIgnoreCase(role)) {
            throw new BadRequestException("Choose a valid account type.");
        }
        IndividualResponse individual = individualService.authenticate(email, password);
        session.setAttribute("userId", individual.id());
        session.setAttribute("userRole", "individual");
        session.setAttribute("userName", individual.name());
        return "redirect:/individuals/dashboard";
    }

    // register as individual 




    //register as group



    
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    //used for registration endpoints 
    private void setSession(HttpSession session, Long id, String role, String name) {
        session.setAttribute("userId", id);
        session.setAttribute("userRole", role);
        session.setAttribute("userName", name);
    }


}
