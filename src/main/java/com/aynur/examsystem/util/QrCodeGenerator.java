package com.aynur.examsystem.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class QrCodeGenerator {
    public static String generate(String text) {
        try {
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, 250, 250);
            String filePath = "qrcodes/" + text.hashCode() + ".png";
            Path path = FileSystems.getDefault().getPath(filePath);
            if (Files.notExists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            MatrixToImageWriter.writeToPath(matrix, "PNG", path);
            return filePath;

        } catch (Exception e) {
            throw new RuntimeException("QR generation failed", e);
        }
    }
}