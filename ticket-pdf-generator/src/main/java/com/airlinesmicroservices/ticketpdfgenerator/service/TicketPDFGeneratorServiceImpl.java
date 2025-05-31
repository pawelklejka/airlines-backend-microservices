package com.airlinesmicroservices.ticketpdfgenerator.service;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.io.IOException;

@Service
public class TicketPDFGeneratorServiceImpl implements TicketPDFGeneratorService {
    private final TicketServiceClient ticketService;
    private final ServletContext servletContext;
    private final TemplateEngine templateEngine;

    public TicketPDFGeneratorServiceImpl(TicketServiceClient ticketService, ServletContext servletContext, TemplateEngine templateEngine) {
        this.ticketService = ticketService;
        this.servletContext = servletContext;
        this.templateEngine = templateEngine;
    }


    @Override
    public byte[] generatePDF(Long ticketId, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Optional<Ticket> ticket = ticketService.getTicket(ticketId);
//
//        System.out.println(ticket.get());
//
//        /* Create HTML using Thymeleaf template Engine */
//
//        Locale locale = request.getLocale();
//
//        WebContext context = new WebContext(request, response, servletContext, locale);
//        context.setVariable("ticket", ticket.get());
//        String orderHtml = templateEngine.process("ticket", context);
//
//        /* Setup Source and target I/O streams */
//
//        ByteArrayOutputStream target = new ByteArrayOutputStream();
//
//        /*Setup converter properties. */
//        ConverterProperties converterProperties = new ConverterProperties();
//        converterProperties.setBaseUri("http://localhost:8081");
//
//        /* Call convert method */
//        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);
//
//        /* extract output as bytes */
//        byte[] bytes = target.toByteArray();
        //cos nie zapisuje
//        Files.write(bytes, new File("resources/template/ticket.pdf"));


        return null;
    }
}