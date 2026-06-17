package com.exam.idcard.controller;

import com.exam.idcard.service.BatchService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batch")
public class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @GetMapping
    public String generateAll() {

        batchService.generateAllCards();

        return "Batch generation completed";
    }
}