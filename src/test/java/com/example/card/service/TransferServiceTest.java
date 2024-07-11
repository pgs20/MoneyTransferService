package com.example.card.service;

import com.example.card.repository.ConfirmOperationRequest;
import com.example.card.repository.TransferRepository;
import com.example.card.repository.TransferRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.concurrent.ConcurrentHashMap;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransferServiceTest {
    @Mock
    private TransferRepository transferRepository;

    @InjectMocks
    private TransferService transferService;

    @Test
    public void testTransfer() {
        TransferRequest transferRequest = new TransferRequest(
                "1234 5678 9012 3456",
                "12/24",
                "123",
                "9876 5432 1098 7654",
                new TransferRequest.Amount(100, "RUB"));

        when(transferRepository.getRepository()).thenReturn(new ConcurrentHashMap<>());

        ResponseEntity responseEntity = transferService.transfer(transferRequest);

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testConfirmOperation() {
        ConfirmOperationRequest confirmOperationRequest = new ConfirmOperationRequest("5435", "4524");

        when(transferRepository.getRepository()).thenReturn(new ConcurrentHashMap<>());

        ResponseEntity responseEntity = transferService.confirmOperation(confirmOperationRequest);

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
