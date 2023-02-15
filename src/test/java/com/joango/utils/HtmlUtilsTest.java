package com.joango.utils;

import com.joango.model.Category;
import com.joango.model.DTO.TicketDTO;
import com.lowagie.text.DocumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HtmlUtilsTest {

    @Test
    @DisplayName("Should generate tickets html")
    void generateTicketHtmlTest() throws DocumentException, IOException {
        TicketDTO ticketDto = new TicketDTO(
            1l,
            1l,
            Category.PREMIUM,
            1
        );
        String html = HtmlUtils.generateTicketHtml(List.of(ticketDto));

        assertTrue(html.contains("<!DOCTYPE html>"));
        assertTrue(html.contains("<h1>Tickets</h1>"));
        assertTrue(html.contains("<td>1</td>"));
    }
    @Test
    @DisplayName("Should generate custom model and view for single value")
    void createTemplateCustomSingleValue(){
        String variableName = "tickets";
        Object value = new TicketDTO(
            1l,
            1l,
            Category.PREMIUM,
            1
        );
        String templateName = "tickets";

        ModelAndView mav = HtmlUtils.createTemplateCustomSingleValue(
            variableName,
            value,
            templateName
        );

        assertFalse(mav.isEmpty());
        assertTrue(mav.hasView());
        assertSame("tickets", mav.getViewName());
    }

    @Test
    @DisplayName("Should generate custom model and view for single value")
    void createErrorHandlingTemplateTest() {
        //given
        Throwable error = new Throwable("error");
        String templateName = "operationError";

        //when
        ModelAndView mav = HtmlUtils.createErrorHandlingTemplate(error, templateName);

        //should
        assertFalse(mav.isEmpty());
        assertTrue(mav.hasView());
        assertSame("operationError", mav.getViewName());
    }


}
