package com.exam.idcard.service;

import com.exam.idcard.model.Profile;
import com.exam.idcard.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository repository;

    public List<Profile> getAllProfiles() {
        return repository.findAll();
    }

    public Profile saveProfile(Profile profile) {
        return repository.save(profile);
    }

    public void deleteProfile(Long id) {
        repository.deleteById(id);
    }

    public Profile getProfileById(Long id) {
        return repository.findById(id).orElse(null);
    }
}