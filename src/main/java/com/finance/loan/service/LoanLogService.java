package com.finance.loan.service;

import com.finance.loan.model.dto.LoanLogRequest;
import com.finance.loan.model.dto.LoanLogResponse;
import java.util.List;

public interface LoanLogService {
    List<LoanLogResponse> getAll();
    LoanLogResponse getById(Long id);
    LoanLogResponse create(LoanLogRequest request);
    LoanLogResponse update(Long id, LoanLogRequest request);
    void delete(Long id);
    List<LoanLogResponse> search(String keyword);
}
