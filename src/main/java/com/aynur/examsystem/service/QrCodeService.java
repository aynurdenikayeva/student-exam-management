package com.aynur.examsystem.service;

import com.aynur.examsystem.util.QrCodeGenerator;
import org.springframework.stereotype.Service;

@Service
public class QrCodeService {
    public String generateQr(String email) {
        String content = "SECOND_EXAM:" + email;
        return QrCodeGenerator.generate(content);
    }
}