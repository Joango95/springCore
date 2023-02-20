package com.joango.controller;

import com.joango.model.Category;
import com.joango.model.DTO.EventDTO;
import com.joango.model.DTO.TicketDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import javax.print.attribute.standard.Media;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetTicketById() throws Exception {
        mockMvc.perform(get("/ticket/{id}", 4))
            .andExpect(status().is2xxSuccessful())
            .andExpect(model().attribute("tickets", instanceOf(TicketDTO.class)))
            .andExpect(view().name("tickets"));
    }

    @Test
    void testBookTicket() throws Exception {
        mockMvc.perform(post("/ticket")
                .param("userId", "4")
                .param("eventId", "3")
                .param("category", "PREMIUM")
                .param("place", "43")
            )
            .andExpect(status().is2xxSuccessful())
            .andExpect(model().attribute("tickets", instanceOf(TicketDTO.class)))
            .andExpect(view().name("tickets"));
    }

    @Test
    void testBookTicketsBatch() throws Exception {
        mockMvc.perform(post("/ticket/batch")
                .content("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<tickets>\n" +
                    "    <ticket eventId=\"1\" userId=\"4\" category=\"PREMIUM\" place=\"1\"/>\n" +
                    "    <ticket eventId=\"1\" userId=\"5\" category=\"PREMIUM\" place=\"2\"/>\n" +
                    "    <ticket eventId=\"1\" userId=\"6\" category=\"PREMIUM\" place=\"3\"/>\n" +
                    "</tickets>")
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML)
            )
            .andExpect(status().is2xxSuccessful())
            .andExpect(model().attribute("tickets", hasSize(3)))
            .andExpect(view().name("tickets"));
    }

    @Test
    void testBookTicketsBatchShouldTransactionallyFailCorrectly() throws Exception {
        mockMvc.perform(post("/ticket/batch")
                .content("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<tickets>\n" +
                    "    <ticket eventId=\"1\" userId=\"4\" category=\"PREMIUM\" place=\"1\"/>\n" +
                    "    <ticket eventId=\"1\" userId=\"5\" category=\"PREMIUM\" place=\"2\"/>\n" +
                    "    <ticket eventId=\"123123234\" userId=\"1324132434\" category=\"PREMIUM\" place=\"3\"/>\n" +
                    "</tickets>")
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML)
            )
            .andExpect(status().is4xxClientError());

        mockMvc.perform(get("/ticket/userId/{id}", 4))
            .andExpect(model().attribute("tickets", not(hasItem(new TicketDTO(1L, 4L, Category.PREMIUM, 1)))));
    }

    @Test
    void testGetBookedTicketsByUserId() throws Exception {
        mockMvc.perform(get("/ticket/userId/{id}", 12))
            .andExpect(status().is2xxSuccessful())
            .andExpect(model().attribute("tickets", hasSize(4)))
            .andExpect(view().name("tickets"));
    }

    @Test
    void testGetBookedTicketsByEventId() throws Exception {
        mockMvc.perform(get("/ticket/eventId/{id}", 3))
            .andExpect(status().is2xxSuccessful())
            .andExpect(model().attribute("tickets", hasSize(8)))
            .andExpect(view().name("tickets"));
    }

}
