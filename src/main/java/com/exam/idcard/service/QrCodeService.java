package com.exam.idcard.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import com.exam.idcard.model.Profile;

import org.springframework.stereotype.Service;

import java.nio.file.Paths;

@Service
public class QrCodeService {

    public String generate(Profile profile)
            throws Exception {

        String data =
                "Name: " + profile.getFullName()
                        + "\nReg: "
                        + profile.getRegistrationNumber()
                        + "\nType: "
                        + profile.getType();

        String fileName =
                "uploads/qr_"
                        + profile.getRegistrationNumber()
                        + ".png";

        QRCodeWriter writer =
                new QRCodeWriter();

        BitMatrix matrix =
                writer.encode(
                        data,
                        BarcodeFormat.QR_CODE,
                        250,
                        250);

        MatrixToImageWriter.writeToPath(
                matrix,
                "PNG",
                Paths.get(fileName));

        return fileName;
    }
}