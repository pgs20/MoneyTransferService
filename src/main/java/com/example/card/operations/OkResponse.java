package com.example.card.operations;

import java.util.Random;

public class OkResponse {
    private String operationId;

    public OkResponse() {
        this.operationId = String.valueOf(new Random().nextInt(1000));
    }

    public String getOperationId() {
        return operationId;
    }
}
