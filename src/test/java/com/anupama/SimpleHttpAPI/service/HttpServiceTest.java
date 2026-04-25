package com.anupama.SimpleHttpAPI.service;

import com.anupama.SimpleHttpAPI.dto.ErrorResponse;
import com.anupama.SimpleHttpAPI.dto.SuccessResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class HttpServiceTest {

    private final HttpService service = new HttpService();

    @Test
    void validName_firstHalfAlphabet_returns200() {
        ResponseEntity<?> response = service.processHello("alice");

        assertEquals(200, response.getStatusCode().value());
        assertInstanceOf(SuccessResponse.class, response.getBody());

        SuccessResponse body = (SuccessResponse) response.getBody();
        assertEquals("Hello Alice", body.getMessage());
    }

    @Test
    void validName_withUppercase_formatsCorrectly() {
        ResponseEntity<?> response = service.processHello("BOB");

        assertEquals(200, response.getStatusCode().value());
        assertInstanceOf(SuccessResponse.class, response.getBody());

        SuccessResponse body = (SuccessResponse) response.getBody();
        assertEquals("Hello Bob", body.getMessage());
    }

    @Test
    void invalidName_secondHalfAlphabet_returns400() {
        ResponseEntity<?> response = service.processHello("zara");

        assertEquals(400, response.getStatusCode().value());
        assertInstanceOf(ErrorResponse.class, response.getBody());

        ErrorResponse body = (ErrorResponse) response.getBody();
        assertEquals("Invalid Input", body.getError());
    }

    @Test
    void nullName_returns400() {
        ResponseEntity<?> response = service.processHello(null);

        assertEquals(400, response.getStatusCode().value());
        assertInstanceOf(ErrorResponse.class, response.getBody());
    }

    @Test
    void emptyName_returns400() {
        ResponseEntity<?> response = service.processHello("");

        assertEquals(400, response.getStatusCode().value());
        assertInstanceOf(ErrorResponse.class, response.getBody());
    }

    @Test
    void whitespaceName_returns400() {
        ResponseEntity<?> response = service.processHello("   ");

        assertEquals(400, response.getStatusCode().value());
        assertInstanceOf(ErrorResponse.class, response.getBody());
    }
}