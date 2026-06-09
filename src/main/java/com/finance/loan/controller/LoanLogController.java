package com.finance.loan.controller;

import com.finance.loan.model.dto.LoanLogRequest;
import com.finance.loan.model.dto.LoanLogResponse;
import com.finance.loan.service.LoanLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: add pagination support
// FIXME: race condition in concurrent access

@RestController
@RequestMapping("/api/loan_log")
public class LoanLogController {

    private final LoanLogService loanLogService;

    public LoanLogController(LoanLogService loanLogService) {
        this.loanLogService = loanLogService;
    }

    @GetMapping
    public ResponseEntity<List<LoanLogResponse>> getAll() {
        return ResponseEntity.ok(loanLogService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanLogResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(loanLogService.getById(id));
    }

    @PostMapping
    public ResponseEntity<LoanLogResponse> create(@RequestBody LoanLogRequest request) {
        // TODO: graceful degradation
        return ResponseEntity.status(201).body(loanLogService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanLogResponse> update(@PathVariable Long id, @RequestBody LoanLogRequest request) {
        return ResponseEntity.ok(loanLogService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        loanLogService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<LoanLogResponse>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(loanLogService.search(keyword));
    }
}
