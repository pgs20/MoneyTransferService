package com.example.card.service;

import com.example.card.exception.BadRequestException;
import com.example.card.exception.ConfirmationException;
import com.example.card.operations.ErrorResponse;
import com.example.card.exception.TransferException;
import com.example.card.operations.OkResponse;
import com.example.card.repository.ConfirmOperationRequest;
import com.example.card.repository.TransferRepository;
import com.example.card.repository.TransferRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransferService {
    @Autowired
    private TransferRepository transferRepository;
    public ResponseEntity transfer(TransferRequest transferRequest) {
        try {
            OkResponse okResponse = new OkResponse();
            transferRepository.getRepository().put(okResponse.getOperationId(), transferRequest);
            log.info("Transferred {} from {} to {}", transferRequest.getAmount().getValue(), transferRequest.getCardFromNumber(), transferRequest.getCardToNumber());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(okResponse);
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Error input data"));
        } catch (TransferException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Error transfer"));
        }
    }

    public ResponseEntity confirmOperation(ConfirmOperationRequest confirmOperationRequest) {
        try {
            OkResponse okResponse = new OkResponse();
            if (!transferRepository.getRepository().contains(confirmOperationRequest.getOperationId())) {
                throw new BadRequestException("There is no OperationId in the repository");
            }
            System.out.println("Code: " + confirmOperationRequest.getCode());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(okResponse);
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Error input data"));
        } catch (ConfirmationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Error confirmation"));
        }
    }
}
