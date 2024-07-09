package com.example.card.controller;

import com.example.card.repository.ConfirmOperationRequest;
import com.example.card.repository.TransferRequest;
import com.example.card.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://serp-ya.github.io")
@RestController
public class TransferController {
    @Autowired
    private TransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferRequest transferRequest) {
        return transferService.transfer(transferRequest);
    }

    @PostMapping("/confirmOperation")
    public ResponseEntity<?> confirmOperation(@RequestBody ConfirmOperationRequest confirmOperationRequest) {
        return transferService.confirmOperation(confirmOperationRequest);
    }
}
