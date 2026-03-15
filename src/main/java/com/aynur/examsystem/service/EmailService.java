package com.aynur.examsystem.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendQr(String email, String qrFilePath) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email);

            if (qrFilePath == null) {
                helper.setSubject("First Exam Result");
                String htmlMsg = "<h3>You did not pass the first exam.</h3>";
                helper.setText(htmlMsg, true);
            } else {
                helper.setSubject("Second Exam QR Code");
                String htmlMsg = "<h3>Your QR Code:</h3>" +
                        "<img src='cid:qrImage'/>";
                helper.setText(htmlMsg, true);

                FileSystemResource file = new FileSystemResource(qrFilePath);
                helper.addInline("qrImage", file);
            }
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}