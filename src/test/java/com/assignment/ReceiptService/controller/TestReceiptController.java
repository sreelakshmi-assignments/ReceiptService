package com.assignment.ReceiptService.controller;
import com.assignment.ReceiptService.model.Receipt;
import com.assignment.ReceiptService.service.ReceiptService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class TestReceiptController {
    @InjectMocks
    private ReceiptController receiptController;

    @Mock
    private ReceiptService receiptService;

    @Test
    void testGetByStudent() {
        String studentId = "12345";

        Receipt receipt1 = new Receipt();
        receipt1.setId("r1");
        receipt1.setStudentId(studentId);
        receipt1.setAmount(100.0);

        Receipt receipt2 = new Receipt();
        receipt2.setId("r2");
        receipt2.setStudentId(studentId);
        receipt2.setAmount(150.0);

        List<Receipt> mockReceipts = List.of(receipt1, receipt2);
        Mockito.when(receiptService.getReceiptsByStudent(studentId)).thenReturn(mockReceipts);
        ResponseEntity<List<Receipt>> response = receiptController.getByStudent(studentId);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertEquals("r1", response.getBody().get(0).getId());
        Mockito.verify(receiptService, Mockito.times(1)).getReceiptsByStudent(studentId);
    }

    @Test
    void testCreate() {
        Receipt receipt = new Receipt();
        receipt.setStudentId("12345");
        receipt.setAmount(200.0);

        Receipt savedReceipt = new Receipt();
        savedReceipt.setId("r100");
        savedReceipt.setStudentId("12345");
        savedReceipt.setAmount(200.0);

        Mockito.when(receiptService.saveReceipt(receipt)).thenReturn(savedReceipt);
        ResponseEntity<Receipt> response = receiptController.create(receipt);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("r100", response.getBody().getId());
        Assertions.assertEquals(200.0, response.getBody().getAmount());
        Mockito.verify(receiptService, Mockito.times(1)).saveReceipt(receipt);
    }

}
