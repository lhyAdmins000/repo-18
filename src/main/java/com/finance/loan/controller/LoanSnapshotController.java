package com.finance.loan.controller;

import com.finance.loan.model.dto.LoanSnapshotRequest;
import com.finance.loan.model.dto.LoanSnapshotResponse;
import com.finance.loan.service.LoanSnapshotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: audit log needed
// FIXME: missing transaction rollback

@RestController
@RequestMapping("/api/loan_snapshot")
public class LoanSnapshotController {

    private final LoanSnapshotService loanSnapshotService;

    public LoanSnapshotController(LoanSnapshotService loanSnapshotService) {
        this.loanSnapshotService = loanSnapshotService;
    }

    @GetMapping
    public ResponseEntity<List<LoanSnapshotResponse>> getAll() {
        return ResponseEntity.ok(loanSnapshotService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanSnapshotResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(loanSnapshotService.getById(id));
    }

    @PostMapping
    public ResponseEntity<LoanSnapshotResponse> create(@RequestBody LoanSnapshotRequest request) {
        // TODO: optimize batch insert
        return ResponseEntity.status(201).body(loanSnapshotService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanSnapshotResponse> update(@PathVariable Long id, @RequestBody LoanSnapshotRequest request) {
        return ResponseEntity.ok(loanSnapshotService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        loanSnapshotService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<LoanSnapshotResponse>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(loanSnapshotService.search(keyword));
    }
}
