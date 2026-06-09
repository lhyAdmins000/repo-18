package com.finance.loan.service;

import com.finance.loan.model.dto.LoanArchiveRequest;
import com.finance.loan.model.dto.LoanArchiveResponse;
import java.util.List;

public interface LoanArchiveService {
    List<LoanArchiveResponse> getAll();
    LoanArchiveResponse getById(Long id);
    LoanArchiveResponse create(LoanArchiveRequest request);
    LoanArchiveResponse update(Long id, LoanArchiveRequest request);
    void delete(Long id);
    List<LoanArchiveResponse> search(String keyword);
}
