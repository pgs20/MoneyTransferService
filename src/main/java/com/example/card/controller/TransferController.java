package com.example.card.controller;

import com.example.card.exception.BadRequestException;
import com.example.card.exception.ConfirmationException;
import com.example.card.exception.ErrorResponse;
import com.example.card.exception.TransferException;
import com.example.card.repository.ConfirmOperationRequest;
import com.example.card.repository.TransferRequest;
import com.example.card.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class TransferController {
    @Autowired
    private TransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferRequest transferRequest) {
        try {
            String operationId = transferService.transfer(transferRequest);
            return ResponseEntity.ok().body(Collections.singletonMap("operationId", operationId));
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Error input data"));
        } catch (TransferException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Error transfer"));
        }
    }

    @PostMapping("/confirmOperation")
    public ResponseEntity<?> confirmOperation(@RequestBody ConfirmOperationRequest confirmOperationRequest) {
        try {
            String operationId = transferService.confirmOperation(confirmOperationRequest);
            return ResponseEntity.ok().body(Collections.singletonMap("operationId", operationId));
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Error input data"));
        } catch (ConfirmationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Error confirmation"));
        }
    }
}
