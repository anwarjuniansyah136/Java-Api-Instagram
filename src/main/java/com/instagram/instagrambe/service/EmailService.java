package com.instagram.instagrambe.service;

public interface EmailService {
    void sendEmail(String to);

    void sendSimpleMessage(String to, String subject, String text);

    void sendHtmlMessage(String to, String subject, String htmlContent);

    void sendMailTest();

    void sendOTPEmail(String userName, String OTP);

    void sendHtmlMessageWithAttachment(String to, String subject, String text, String attachmentName,
            byte[] attachment);
}
