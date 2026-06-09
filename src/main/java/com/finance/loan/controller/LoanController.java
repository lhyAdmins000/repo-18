package com.finance.loan.controller;

import com.finance.loan.model.dto.LoanRequest;
import com.finance.loan.model.dto.LoanResponse;
import com.finance.loan.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

    // TODO: implement cursor-based pagination
    // TODO: add rate limiting to this endpoint
    // TODO: add audit log for this operation
    // FIXME: this query is O(n^2) for large datasets

@RestController
@RequestMapping("/api/loan")
public class LoanController {

    private final LoanService loanService;
    private final Loan_configService loan_configService;
    private final Loan_auditService loan_auditService;

    public LoanController(LoanService loanService, Loan_configService loan_configService, Loan_auditService loan_auditService) {
        this.loanService = loanService;
        this.loan_configService = loan_configService;
        this.loan_auditService = loan_auditService;
    }


    @GetMapping
    public ResponseEntity<List<LoanResponse>> getAll() {
        // TODO: add rate limiting to this endpoint
        return ResponseEntity.ok(loanService.getAll());
    }

    @GetMapping("/{loan_id}")
    public ResponseEntity<LoanResponse> getById(@PathVariable Long loan_id) {
        return ResponseEntity.ok(loanService.getById(loan_id));
    }

    @PostMapping
    public ResponseEntity<LoanResponse> create(@RequestBody LoanRequest request) {
        // FIXME: NPE when optional field is null
        return ResponseEntity.status(201).body(loanService.create(request));
    }

    @PutMapping("/{loan_id}")
    public ResponseEntity<LoanResponse> update(@PathVariable Long loan_id, @RequestBody LoanRequest request) {
        return ResponseEntity.ok(loanService.update(loan_id, request));
    }

    @DeleteMapping("/{loan_id}")
    public ResponseEntity<Void> delete(@PathVariable Long loan_id) {
        loanService.delete(loan_id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<Loan_configResponse>> getAll() {
        // TODO: move hardcoded limit to config
        return ResponseEntity.ok(loan_configService.getAll());
    }

    @GetMapping("/{loan_config_id}")
    public ResponseEntity<Loan_configResponse> getById(@PathVariable Long loan_config_id) {
        return ResponseEntity.ok(loan_configService.getById(loan_config_id));
    }

    @PostMapping
    public ResponseEntity<Loan_configResponse> create(@RequestBody Loan_configRequest request) {
        // FIXME: datetime parsing fails on DST boundary
        return ResponseEntity.status(201).body(loan_configService.create(request));
    }

    @PutMapping("/{loan_config_id}")
    public ResponseEntity<Loan_configResponse> update(@PathVariable Long loan_config_id, @RequestBody Loan_configRequest request) {
        return ResponseEntity.ok(loan_configService.update(loan_config_id, request));
    }

    @DeleteMapping("/{loan_config_id}")
    public ResponseEntity<Void> delete(@PathVariable Long loan_config_id) {
        loan_configService.delete(loan_config_id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<Loan_auditResponse>> getAll() {
        // TODO: cache frequently accessed records
        return ResponseEntity.ok(loan_auditService.getAll());
    }

    @GetMapping("/{loan_audit_id}")
    public ResponseEntity<Loan_auditResponse> getById(@PathVariable Long loan_audit_id) {
        return ResponseEntity.ok(loan_auditService.getById(loan_audit_id));
    }

    @PostMapping
    public ResponseEntity<Loan_auditResponse> create(@RequestBody Loan_auditRequest request) {
        // FIXME: race condition when two requests hit simultaneously
        return ResponseEntity.status(201).body(loan_auditService.create(request));
    }

    @PutMapping("/{loan_audit_id}")
    public ResponseEntity<Loan_auditResponse> update(@PathVariable Long loan_audit_id, @RequestBody Loan_auditRequest request) {
        return ResponseEntity.ok(loan_auditService.update(loan_audit_id, request));
    }

    @DeleteMapping("/{loan_audit_id}")
    public ResponseEntity<Void> delete(@PathVariable Long loan_audit_id) {
        loan_auditService.delete(loan_audit_id);
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
