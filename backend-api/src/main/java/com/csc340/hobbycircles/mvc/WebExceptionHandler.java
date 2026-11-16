package com.csc340.hobbycircles.mvc;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csc340.hobbycircles.service.BadRequestException;
import com.csc340.hobbycircles.service.ConflictException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackageClasses = AppController.class)
public class WebExceptionHandler {
    
    @ExceptionHandler({ BadRequestException.class, ConflictException.class })
    public String handleServiceError(RuntimeException ex, HttpServletRequest request, RedirectAttributes redirect) {
        return backWithError(ex.getMessage(), request, redirect);
    }

    @ExceptionHandler(BindException.class)
    public String handleValidation(BindException ex, HttpServletRequest request, RedirectAttributes redirect) {
        String message = ex.getFieldErrors().stream()
                .findFirst()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .orElse("Please check the form and try again.");
        return backWithError(message, request, redirect);
    }

    private String backWithError(String message, HttpServletRequest request, RedirectAttributes redirect) {
        redirect.addFlashAttribute("error", message);
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/login");
    }

}
