package com.joango.utils;

import com.joango.model.Category;
import com.joango.model.DTO.TicketDTO;
import com.lowagie.text.DocumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class PDFUtilsTest {

    @Test
    @DisplayName("Should generate a pdf in byteArray using html")
    void generatePdfFromHtmlTest() throws DocumentException, IOException {
        TicketDTO ticketDto = new TicketDTO(
            1l,
            1l,
            Category.PREMIUM,
            1
        );
        String html = HtmlUtils.generateTicketHtml(List.of(ticketDto));

        ByteArrayOutputStream pdfGenerated = PDFUtils.generatePdfFromHtml(html);
        assertEquals(pdfGenerated.size(), 2030);
    }

}
