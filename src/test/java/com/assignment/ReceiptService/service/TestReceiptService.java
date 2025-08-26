package com.assignment.ReceiptService.service;
import com.assignment.ReceiptService.model.Receipt;
import com.assignment.ReceiptService.repository.ReceiptRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestReceiptService {
    @InjectMocks
    private ReceiptService receiptService;
    @Mock
    private ReceiptRepository receiptRepository;

    @Test
    void testSaveReceipt() {
        Receipt receipt = new Receipt();
        receipt.setStudentId("STU1");
        receipt.setAmount(200.00);

        Receipt savedReceipt = new Receipt();
        savedReceipt.setId("R001");
        savedReceipt.setStudentId("S123");
        savedReceipt.setAmount(250.0);
        savedReceipt.setTimestamp(LocalDateTime.now());

        Mockito.when(receiptRepository.save(Mockito.any(Receipt.class))).thenReturn(savedReceipt);
        Receipt result=receiptService.saveReceipt(receipt);
        Assertions.assertNotNull(savedReceipt);
        Assertions.assertEquals(savedReceipt.getId(),result.getId());
        Assertions.assertEquals(savedReceipt.getAmount(),result.getAmount());
        Assertions.assertNotNull(result.getTimestamp());
        Mockito.verify(receiptRepository, Mockito.times(1)).save(Mockito.any(Receipt.class));
    }

    @Test
    void testFindReceiptById() {
        String studentId = "S123";
        Receipt r1 = new Receipt();
        r1.setId("R001");
        r1.setStudentId(studentId);
        r1.setAmount(100.0);

        Receipt r2 = new Receipt();
        r2.setId("R002");
        r2.setStudentId(studentId);
        r2.setAmount(150.0);

        List<Receipt> mockList = List.of(r1, r2);

        Mockito.when(receiptRepository.findByStudentId(studentId)).thenReturn(mockList);
        List<Receipt> result = receiptService.getReceiptsByStudent(studentId);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("R001", result.get(0).getId());
        Assertions.assertEquals("R002", result.get(1).getId());
        Mockito.verify(receiptRepository, Mockito.times(1)).findByStudentId(studentId);
    }

}
