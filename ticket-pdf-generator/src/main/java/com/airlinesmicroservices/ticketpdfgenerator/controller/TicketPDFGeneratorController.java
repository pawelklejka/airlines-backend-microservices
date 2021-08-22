package com.airlinesmicroservices.ticketpdfgenerator.controller;

import com.airlinesmicroservices.ticketpdfgenerator.service.TicketPDFGeneratorService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class TicketPDFGeneratorController {
    TicketPDFGeneratorService ticketPDFGeneratorService;

    @GetMapping("/download-ticket")
    public ResponseEntity<?> getPDF(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ticket.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(ticketPDFGeneratorService.generatePDF(id, request, response));

    }

}
