package com.exam.idcard.controller;

import com.exam.idcard.model.Profile;
import com.exam.idcard.model.ProfileType;
import com.exam.idcard.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService service;

    @GetMapping
    public List<Profile> getAll() {
        return service.getAllProfiles();
    }

    @PostMapping
    public Profile create(@RequestBody Profile profile) {
        if (profile.getType() == null) {
            profile.setType(ProfileType.USER);
        }
        return service.saveProfile(profile);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteProfile(id);
    }
}