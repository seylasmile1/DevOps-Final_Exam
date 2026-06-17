package com.exam.idcard.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;

@RestController
@RequestMapping("/files")
public class FileController {

    private final String UPLOAD_DIR = System.getProperty("user.dir") + File.separator + "uploads" + File.separator;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(MultipartHttpServletRequest request) throws IOException {

        MultipartFile file = request.getFileMap().values().stream().findFirst().orElse(null);

        if (file == null) {
            return ResponseEntity.badRequest().body("Required file part is not present.");
        }

        // Check file type
        if (!"image/jpeg".equals(file.getContentType())
            && !"image/png".equals(file.getContentType())) {

            return ResponseEntity.badRequest()
                    .body("Only JPG and PNG are allowed.");
        }

        // Check size (5MB)
        if (file.getSize() > 5 * 1024 * 1024) {

            return ResponseEntity.badRequest()
                    .body("File too large.");
        }

        File directory = new File(UPLOAD_DIR);

        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created && !directory.exists()) {
                return ResponseEntity.status(500).body("Failed to create upload directory: " + directory.getAbsolutePath());
            }
        }

        String rawName = file.getOriginalFilename();
        if (rawName == null || rawName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Uploaded file must have a name.");
        }

        final String fileName;
        try {
            fileName = Paths.get(rawName).getFileName().toString();
        } catch (InvalidPathException ex) {
            return ResponseEntity.badRequest().body("Invalid file name.");
        }

        File dest = new File(directory, fileName);
        try {
            file.transferTo(dest);
        } catch (IOException ex) {
            return ResponseEntity.status(500).body("Failed to save file: " + ex.getMessage());
        }

        return ResponseEntity.ok("Uploaded successfully.");
    }
}