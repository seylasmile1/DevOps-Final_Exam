package com.exam.idcard.controller;

import com.exam.idcard.model.Profile;
import com.exam.idcard.service.ProfileService;
import com.exam.idcard.service.QrCodeService;

import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/qr")
public class QrController {

    private final QrCodeService qrService;
    private final ProfileService profileService;

    public QrController(
            QrCodeService qrService,
            ProfileService profileService) {

        this.qrService = qrService;
        this.profileService = profileService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> generate(@PathVariable Long id) throws Exception {
        Profile profile = profileService.getProfileById(id);

        String fileName = qrService.generate(profile);

        Path path = Paths.get(fileName);
        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }
}