package com.airlinesmicroservices.ticketpdfgenerator.controller;

import com.airlinesmicroservices.ticketpdfgenerator.service.TicketPDFGeneratorService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketPDFGeneratorController {
    TicketPDFGeneratorService ticketPDFGeneratorService;

//    @GetMapping("/download-ticket")
//    public ResponseEntity<?> getPDF(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ticket.pdf")
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(ticketPDFGeneratorService.generatePDF(id, request, response));
//
//    }

}
