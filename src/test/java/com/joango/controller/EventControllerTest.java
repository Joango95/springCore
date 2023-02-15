package com.joango.controller;

import com.joango.model.DTO.EventDTO;
import jdk.jfr.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetEventById() throws Exception {
        mockMvc.perform(get("/event/{id}", 4))
            .andExpect(status().is2xxSuccessful())
            .andExpect(model().attribute("events", instanceOf(EventDTO.class)))
            .andExpect(view().name("events"));
    }

    @Test
    void testGetEventsByTitle() throws Exception {
        mockMvc.perform(get("/event/title/{title}","posuere, enim"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(model().attribute("events", hasSize(2)))
            .andExpect(view().name("events"));
    }

    @Test
    void testGetEventsByDay() throws Exception {
        mockMvc.perform(get("/event/day/{day}", 1691643600000L))
            .andExpect(status().is2xxSuccessful())
            .andExpect(model().attribute("events", hasSize(2)))
            .andExpect(view().name("events"));
    }

    @Test
    void testGetEventByInvalidId() throws Exception {
        mockMvc.perform(get("/event/{id}", 9999))
            .andExpect(status().is4xxClientError())
            .andExpect(content().contentType("text/html;charset=UTF-8"));
    }
}
