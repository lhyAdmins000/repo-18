package com.finance.loan.controller;

import com.finance.loan.model.dto.LoanBackupRequest;
import com.finance.loan.model.dto.LoanBackupResponse;
import com.finance.loan.service.LoanBackupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: optimize batch insert
// FIXME: race condition in concurrent access

@RestController
@RequestMapping("/api/loan_backup")
public class LoanBackupController {

    private final LoanBackupService loanBackupService;

    public LoanBackupController(LoanBackupService loanBackupService) {
        this.loanBackupService = loanBackupService;
    }

    @GetMapping
    public ResponseEntity<List<LoanBackupResponse>> getAll() {
        return ResponseEntity.ok(loanBackupService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanBackupResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(loanBackupService.getById(id));
    }

    @PostMapping
    public ResponseEntity<LoanBackupResponse> create(@RequestBody LoanBackupRequest request) {
        // TODO: audit log needed
        return ResponseEntity.status(201).body(loanBackupService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanBackupResponse> update(@PathVariable Long id, @RequestBody LoanBackupRequest request) {
        return ResponseEntity.ok(loanBackupService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        loanBackupService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<LoanBackupResponse>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(loanBackupService.search(keyword));
    }
}
