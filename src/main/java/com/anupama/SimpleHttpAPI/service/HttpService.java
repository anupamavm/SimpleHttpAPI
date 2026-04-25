package com.anupama.SimpleHttpAPI.service;

import com.anupama.SimpleHttpAPI.dto.ErrorResponse;
import com.anupama.SimpleHttpAPI.dto.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HttpService {

    public ResponseEntity<?> processHello(String name) {

        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Invalid Input"));
        }

        String trimmed = name.trim();
        char firstChar = Character.toLowerCase(trimmed.charAt(0));

        if (firstChar < 'a' || firstChar > 'm') {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Invalid Input"));
        }

        String formattedName = formatName(trimmed);

        return ResponseEntity.ok(
                new SuccessResponse("Hello " + formattedName)
        );
    }

    private String formatName(String name) {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
    }
}