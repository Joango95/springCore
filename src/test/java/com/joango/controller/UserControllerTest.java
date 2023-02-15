package com.joango.controller;

import com.joango.model.DTO.EventDTO;
import com.joango.model.DTO.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetUserById() throws Exception {
        mockMvc.perform(get("/user/{id}", 4))
            .andExpect(status().is2xxSuccessful())
            .andExpect(model().attribute("users", instanceOf(UserDTO.class)))
            .andExpect(view().name("users"));
    }

    @Test
    void testGetUserByEmail() throws Exception {
        mockMvc.perform(get("/user/email/{email}", "enim.consequat@hotmail.edu"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(model().attribute("users", instanceOf(UserDTO.class)))
            .andExpect(view().name("users"));
    }

    @Test
    void testGetUsersByName() throws Exception {
        mockMvc.perform(get("/user/name/{name}", "Lewis Burton"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(model().attribute("users", hasSize(2)))
            .andExpect(view().name("users"));
    }


}
