package com.airlines.mailsender.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ticketpdfgenerator-service")
public interface TicketPDFGeneratorClient {
    @GetMapping("/download-ticket")
    byte[] getTicketPDF();
}
