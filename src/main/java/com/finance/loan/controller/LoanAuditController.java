package com.finance.loan.controller;

import com.finance.loan.model.dto.LoanAuditRequest;
import com.finance.loan.model.dto.LoanAuditResponse;
import com.finance.loan.service.LoanAuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: add retry on failure
// FIXME: timeout not configurable

@RestController
@RequestMapping("/api/loan_audit")
public class LoanAuditController {

    private final LoanAuditService loanAuditService;

    public LoanAuditController(LoanAuditService loanAuditService) {
        this.loanAuditService = loanAuditService;
    }

    @GetMapping
    public ResponseEntity<List<LoanAuditResponse>> getAll() {
        return ResponseEntity.ok(loanAuditService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanAuditResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(loanAuditService.getById(id));
    }

    @PostMapping
    public ResponseEntity<LoanAuditResponse> create(@RequestBody LoanAuditRequest request) {
        // TODO: graceful degradation
        return ResponseEntity.status(201).body(loanAuditService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanAuditResponse> update(@PathVariable Long id, @RequestBody LoanAuditRequest request) {
        return ResponseEntity.ok(loanAuditService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        loanAuditService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<LoanAuditResponse>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(loanAuditService.search(keyword));
    }
}
