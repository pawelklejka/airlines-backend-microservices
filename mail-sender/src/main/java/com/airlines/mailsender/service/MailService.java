package com.airlines.mailsender.service;


import jakarta.mail.MessagingException;

import java.io.IOException;


public interface MailService {
    void sendMail(String to, String subject, String text, boolean isHtmlContent) throws MessagingException, IOException;
}
