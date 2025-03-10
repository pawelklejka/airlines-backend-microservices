package com.airlinesmicroservices.ticketpdfgenerator.service;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface TicketPDFGeneratorService {
    byte[] generatePDF(Long touristId, HttpServletRequest request, HttpServletResponse response) throws IOException;

}
