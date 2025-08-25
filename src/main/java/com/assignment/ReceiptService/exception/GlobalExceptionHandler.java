package com.assignment.ReceiptService.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ReceiptNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ReceiptNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(ReceiptProcessingException.class)
    public ResponseEntity<String> handleProcessingError(ReceiptProcessingException ex) {
        return ResponseEntity.status(500).body(ex.getMessage());
    }

}
