package com.instagram.instagrambe.service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    JavaMailSender emailSender;

    @Autowired
    ThymeleafService thymeleafService;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage messsage = new SimpleMailMessage();
            messsage.setFrom("noreply@dapa.com");
            messsage.setTo(to);
            messsage.setSubject(subject);
            messsage.setText(text);
            emailSender.send(messsage);
        } catch (MailException me) {
            me.printStackTrace();
        }
    }

    @Override
    public void sendMailTest() {

        try {
            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(
                    message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            helper.setFrom("anwarjuniansyah5@gmail.com", "Admin DAPA STORE");
            helper.setTo("anwarjuniansyah136@gmail.com");

            Map<String, Object> variables = new HashMap<>();

            variables.put("name", "DAPA");
            helper.setText(thymeleafService.createContext("send-mail-test.html", variables), true);
            helper.setSubject("Mail Test");
            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmail(String to) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                    message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            helper.setFrom("anwarjuniansyah5@gmail.com", "Admin DAPA STORE");
            helper.setTo(to);
            helper.setSubject("Customer Registration");

            String htmlContent = thymeleafService.createContext("register-mail.html", null);
            helper.setText(htmlContent, true);

            emailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendHtmlMessage(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("noreply@dapa.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            emailSender.send(message);
        } catch (MessagingException | MailException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void sendOTPEmail(String userName, String OTP) {
        try {

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                    message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            helper.setFrom("anwarjuniansyah5@gmail.com", "Admin Instagram");
            helper.setTo(userName);

            Map<String, Object> variables = new HashMap<>();

            variables.put("email", userName);
            variables.put("otp", OTP);
            // helper.setText(html, true);
            helper.setText(thymeleafService.createContext("email-reset-pw.html", variables), true);
            helper.setSubject("Reset Password | OTP");
            emailSender.send(message);
        } catch (Exception e) {

        }
    }

    @Override
    public void sendHtmlMessageWithAttachment(String to, String subject, String text, String attachmentName,
            byte[] attachment) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("noreply@dapa.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            helper.addAttachment(attachmentName, new ByteArrayResource(attachment));
            emailSender.send(message);
        } catch (MessagingException | MailException ex) {
            ex.printStackTrace();
        }
    }
}
