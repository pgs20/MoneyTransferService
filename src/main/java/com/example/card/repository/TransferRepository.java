package com.example.card.repository;

import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransferRepository {
    private ConcurrentHashMap<String, TransferRequest> repository = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String, TransferRequest> getRepository() {
        return repository;
    }
}