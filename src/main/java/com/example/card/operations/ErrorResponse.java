package com.example.card.operations;

public class ErrorResponse {
    private String message;
    private int id = 0;

    public ErrorResponse(String message) {
        this.message = message;
        this.id += 1;
    }
}
