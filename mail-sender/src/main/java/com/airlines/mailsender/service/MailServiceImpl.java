package com.airlines.mailsender.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final TicketPDFGeneratorClient ticketPDFGeneratorClient;
    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(String to, String subject, String text, boolean isHtmlContent) throws MessagingException, IOException {
        byte[] ticketBytes = ticketPDFGeneratorClient.getTicketPDF();
        File ticket = File.createTempFile("ticket", ".pdf");
        FileUtils.writeByteArrayToFile(ticket, ticketBytes);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, isHtmlContent);
        mimeMessageHelper.addAttachment("ticket.pdf", ticket);
        javaMailSender.send(mimeMessage);
    }
}