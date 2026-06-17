package com.exam.idcard.service;

import com.exam.idcard.model.Profile;
import com.exam.idcard.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class BatchService {

    private final ProfileRepository repository;
    private final PdfService pdfService;

    public BatchService(ProfileRepository repository,
                        PdfService pdfService) {

        this.repository = repository;
        this.pdfService = pdfService;
    }

    public void generateAllCards() {

        for (Profile profile : repository.findAll()) {

            pdfService.generatePdf(profile);
        }
    }
}