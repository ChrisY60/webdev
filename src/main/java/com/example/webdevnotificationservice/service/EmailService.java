package com.example.webdevnotificationservice.service;

public interface EmailService {
    void sendEmail(String to, String subject, String text);
}
