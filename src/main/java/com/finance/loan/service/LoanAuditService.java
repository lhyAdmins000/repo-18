package com.finance.loan.service;

import com.finance.loan.model.dto.LoanAuditRequest;
import com.finance.loan.model.dto.LoanAuditResponse;
import java.util.List;

public interface LoanAuditService {
    List<LoanAuditResponse> getAll();
    LoanAuditResponse getById(Long id);
    LoanAuditResponse create(LoanAuditRequest request);
    LoanAuditResponse update(Long id, LoanAuditRequest request);
    void delete(Long id);
    List<LoanAuditResponse> search(String keyword);
}
