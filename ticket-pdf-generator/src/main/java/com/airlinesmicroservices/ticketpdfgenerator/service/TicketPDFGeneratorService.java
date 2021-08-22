package com.airlinesmicroservices.ticketpdfgenerator.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface TicketPDFGeneratorService {
    byte[] generatePDF(Long touristId, HttpServletRequest request, HttpServletResponse response) throws IOException;

}
