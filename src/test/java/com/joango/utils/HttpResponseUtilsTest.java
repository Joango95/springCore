package com.joango.utils;

import com.joango.model.Category;
import com.joango.model.DTO.TicketDTO;
import com.lowagie.text.DocumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class HttpResponseUtilsTest {

    @Test
    @DisplayName("Should generate http response for pdf")
    void generateHttpResponseForPdfTest() throws DocumentException, IOException {
        TicketDTO ticketDto = new TicketDTO(
            1l,
            1l,
            Category.PREMIUM,
            1
        );
        String html = HtmlUtils.generateTicketHtml(List.of(ticketDto));
        byte[] pdfGenerated = PDFUtils.generatePdfFromHtml(html).toByteArray();
        HttpEntity<byte []> response = HttpResponseUtils.generateHttpResponseForPdf(pdfGenerated);

        assertEquals(response.getHeaders().getContentType(), MediaType.APPLICATION_PDF);
        assertEquals(response.getHeaders().getContentLength(), 2030);
        assertEquals(response.getHeaders().getContentDisposition(), ContentDisposition.attachment().filename("tickets.pdf").build());
    }
}
