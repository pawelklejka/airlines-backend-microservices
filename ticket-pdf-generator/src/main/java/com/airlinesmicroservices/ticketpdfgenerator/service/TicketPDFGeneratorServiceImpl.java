package com.airlinesmicroservices.ticketpdfgenerator.service;
import com.airlinesmicroservices.ticketpdfgenerator.model.Ticket;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

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
        Optional<Ticket> ticket = ticketService.getTicket(ticketId);

        System.out.println(ticket.get().toString());

        /* Create HTML using Thymeleaf template Engine */

        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("ticket", ticket.get());
        String orderHtml = templateEngine.process("ticket", context);

        /* Setup Source and target I/O streams */

        ByteArrayOutputStream target = new ByteArrayOutputStream();

        /*Setup converter properties. */
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8081");

        /* Call convert method */
        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

        /* extract output as bytes */
        byte[] bytes = target.toByteArray();
        //cos nie zapisuje
//        Files.write(bytes, new File("resources/template/ticket.pdf"));


        return bytes;
    }
}