package com.bitwarden.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    private final JavaMailSender mailSender;

    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPasswordHintEmail(String email, String hint) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Your Master Password Hint");
            message.setText("Your master password hint is: " + hint);
            
            mailSender.send(message);
            logger.info("Password hint email sent to: {}", email);
        } catch (Exception e) {
            logger.error("Failed to send password hint email to: {}", email, e);
        }
    }

    public void sendNoPasswordHintEmail(String email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Your Master Password Hint");
            message.setText("You have not set a master password hint for your account.");
            
            mailSender.send(message);
            logger.info("No password hint email sent to: {}", email);
        } catch (Exception e) {
            logger.error("Failed to send no password hint email to: {}", email, e);
        }
    }

    public void sendVerificationEmail(String email, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Verify Your Email Address");
            message.setText("Please verify your email address using this token: " + token);
            
            mailSender.send(message);
            logger.info("Verification email sent to: {}", email);
        } catch (Exception e) {
            logger.error("Failed to send verification email to: {}", email, e);
        }
    }

    public void sendTwoFactorEmail(String email, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Your Two-Factor Authentication Code");
            message.setText("Your two-factor authentication code is: " + token);
            
            mailSender.send(message);
            logger.info("Two-factor email sent to: {}", email);
        } catch (Exception e) {
            logger.error("Failed to send two-factor email to: {}", email, e);
        }
    }
}
