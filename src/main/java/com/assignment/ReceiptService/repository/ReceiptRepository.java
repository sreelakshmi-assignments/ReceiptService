package com.assignment.ReceiptService.repository;


import com.assignment.ReceiptService.model.Receipt;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReceiptRepository extends MongoRepository<Receipt, String> {
    List<Receipt> findByStudentId(String studentId);
}
