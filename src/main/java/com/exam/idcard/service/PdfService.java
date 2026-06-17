package com.exam.idcard.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.exam.idcard.model.Profile;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

@Service
public class PdfService {

    public void generatePdf(Profile profile) {

        try {

            Document document = new Document();

            PdfWriter.getInstance(
                    document,
                    new FileOutputStream(
                            "uploads/" + profile.getRegistrationNumber() + ".pdf"));

            document.open();

            document.add(new Paragraph("Student ID Card"));
            document.add(new Paragraph("Name: " + profile.getFullName()));
            document.add(new Paragraph(
                    "Reg Number: "
                            + profile.getRegistrationNumber()));

            document.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}