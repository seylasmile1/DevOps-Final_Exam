package com.exam.idcard.controller;

import com.exam.idcard.model.Profile;
import com.exam.idcard.service.PdfService;
import com.exam.idcard.service.ProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    private final PdfService pdfService;
    private final ProfileService profileService;

    public PdfController(PdfService pdfService,
                         ProfileService profileService) {

        this.pdfService = pdfService;
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    public String generatePdf(@PathVariable Long id) {

        Profile profile = profileService.getProfileById(id);

        pdfService.generatePdf(profile);

        return "PDF generated successfully";
    }
}