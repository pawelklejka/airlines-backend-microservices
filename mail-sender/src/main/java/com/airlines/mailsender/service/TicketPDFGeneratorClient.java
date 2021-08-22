package com.airlines.mailsender.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("/ticketPDFgenerator-service")
@Service
public interface TicketPDFGeneratorClient {
    @GetMapping("/download-ticket")
    byte[] getTicketPDF();
}
