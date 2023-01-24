package com.joango.controller;

import com.joango.facade.BookingFacade;
import com.joango.model.DTO.TicketDTO;
import com.joango.utils.HtmlUtils;
import com.joango.utils.HttpResponseUtils;
import com.joango.utils.PDFUtils;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/pdf")
public class PDFController {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping(value = "/userId/{userId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public HttpEntity<byte[]> getBookedTicketsByUserId(
        @PathVariable(name = "userId") long userId
    ) throws DocumentException, IOException {

        List<TicketDTO> ticketsDto = bookingFacade.getBookedTicketsByUserId(userId);

        String ticketsHtml = HtmlUtils.generateTicketHtml(ticketsDto);

        byte[] ticketsPdf = PDFUtils.generatePdfFromHtml(ticketsHtml).toByteArray();

        return HttpResponseUtils.generateHttpResponseForPdf(ticketsPdf);

    }
}
