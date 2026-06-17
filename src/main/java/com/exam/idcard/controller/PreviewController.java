package com.exam.idcard.controller;

import com.exam.idcard.model.Profile;
import com.exam.idcard.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PreviewController {

    private final ProfileService profileService;

    public PreviewController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/preview/{id}")
    public String preview(@PathVariable Long id,
                          Model model) {

        Profile profile = profileService.getProfileById(id);

        model.addAttribute("profile", profile);

        return "preview";
    }
}