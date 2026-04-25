package com.anupama.SimpleHttpAPI.dto;

import lombok.Getter;

@Getter
public class SuccessResponse {
    private final String message;

    public SuccessResponse(String message) {
        this.message = message;
    }

}