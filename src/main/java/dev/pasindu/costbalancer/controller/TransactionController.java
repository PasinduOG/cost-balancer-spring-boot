package dev.pasindu.costbalancer.controller;

import dev.pasindu.costbalancer.entity.Transaction;
import dev.pasindu.costbalancer.service.TransactionService;
import io.github.og4dev.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @PostMapping("/add")
    public ResponseEntity<?> addTransaction(@RequestBody Transaction transaction) {
        boolean saved = service.addTransaction(transaction);
        if (saved) {
            return ApiResponse.success("Transaction added successfully", true);
        } else {
            return ApiResponse.error("Failed to add transaction", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/history/{familyId}")
    public ResponseEntity<?> getHistory(@PathVariable Integer familyId) {
        List<Map<String, Object>> history = service.getTransactionHistory(familyId);
        return ApiResponse.success("History fetched successfully", history);
    }

    @GetMapping("/balance/{familyId}")
    public BigDecimal getBalance(@PathVariable Integer familyId) {
        return service.calculateBalance(familyId);
    }
}