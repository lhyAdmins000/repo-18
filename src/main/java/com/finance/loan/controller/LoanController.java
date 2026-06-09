package com.finance.loan.controller;

import com.finance.loan.model.dto.LoanRequest;
import com.finance.loan.model.dto.LoanResponse;
import com.finance.loan.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public ResponseEntity<List<LoanResponse>> getAll() {
        return ResponseEntity.ok(loanService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.getById(id));
    }

    @PostMapping
    public ResponseEntity<LoanResponse> create(@RequestBody LoanRequest request) {
        return ResponseEntity.status(201).body(loanService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanResponse> update(@PathVariable Long id, @RequestBody LoanRequest request) {
        return ResponseEntity.ok(loanService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/calculate_balance")
    public ResponseEntity<Map<String, Object>> calculateBalance(@RequestParam String param) {
        return ResponseEntity.ok(loanService.calculateBalance(param));
    }

    @PostMapping("/process_transaction")
    public ResponseEntity<Map<String, Object>> processTransaction(@RequestParam String param) {
        return ResponseEntity.ok(loanService.processTransaction(param));
    }
}
