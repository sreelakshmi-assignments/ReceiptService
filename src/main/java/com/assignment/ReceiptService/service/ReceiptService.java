package com.assignment.ReceiptService.service;

import com.assignment.ReceiptService.model.Receipt;
import com.assignment.ReceiptService.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReceiptService {
    @Autowired
    private ReceiptRepository repository;

    public Receipt saveReceipt(Receipt receipt) {
        receipt.setTimestamp(LocalDateTime.now());
        return repository.save(receipt);
    }

    public List<Receipt> getReceiptsByStudent(String studentId) {
        return repository.findByStudentId(studentId);
    }
}

