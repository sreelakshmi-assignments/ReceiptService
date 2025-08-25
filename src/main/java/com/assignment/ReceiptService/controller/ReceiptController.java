package com.assignment.ReceiptService.controller;

import com.assignment.ReceiptService.model.Receipt;
import com.assignment.ReceiptService.service.ReceiptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receipts")
@Tag(name = "Receipts", description = "Operations related to receipt generation")
public class ReceiptController {
    @Autowired
    private ReceiptService service;


    @Operation(summary = "Create a new receipt", description = "Generates and stores a receipt for a payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receipt created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Receipt> create(@RequestBody Receipt receipt) {
        return ResponseEntity.ok(service.saveReceipt(receipt));
    }

    @Operation(summary = "Get receipt by ID")
    @GetMapping("/{studentId}")
    public ResponseEntity<List<Receipt>> getByStudent(@PathVariable String studentId) {
        return ResponseEntity.ok(service.getReceiptsByStudent(studentId));
    }
}

