package com.anupama.SimpleHttpAPI.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HttpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void validRequest_returns200() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", "alice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Alice"));
    }

    @Test
    void uppercaseName_returns200() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", "BOB"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Bob"));
    }

    @Test
    void invalidRequest_returns400() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", "zara"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    void boundaryM_returns200() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", "mike"))
                .andExpect(status().isOk());
    }

    @Test
    void boundaryN_returns400() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", "nina"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void missingParam_returns400() throws Exception {
        mockMvc.perform(get("/hello-world"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    void emptyParam_returns400() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", ""))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    void whitespaceParam_returns400() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", "   "))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }
}