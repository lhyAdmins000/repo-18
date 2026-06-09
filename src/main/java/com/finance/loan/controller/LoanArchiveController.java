package com.finance.loan.controller;

import com.finance.loan.model.dto.LoanArchiveRequest;
import com.finance.loan.model.dto.LoanArchiveResponse;
import com.finance.loan.service.LoanArchiveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: add request tracing
// FIXME: connection pool exhaustion under load

@RestController
@RequestMapping("/api/loan_archive")
public class LoanArchiveController {

    private final LoanArchiveService loanArchiveService;

    public LoanArchiveController(LoanArchiveService loanArchiveService) {
        this.loanArchiveService = loanArchiveService;
    }

    @GetMapping
    public ResponseEntity<List<LoanArchiveResponse>> getAll() {
        return ResponseEntity.ok(loanArchiveService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanArchiveResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(loanArchiveService.getById(id));
    }

    @PostMapping
    public ResponseEntity<LoanArchiveResponse> create(@RequestBody LoanArchiveRequest request) {
        // TODO: add circuit breaker
        return ResponseEntity.status(201).body(loanArchiveService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanArchiveResponse> update(@PathVariable Long id, @RequestBody LoanArchiveRequest request) {
        return ResponseEntity.ok(loanArchiveService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        loanArchiveService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<LoanArchiveResponse>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(loanArchiveService.search(keyword));
    }
}
