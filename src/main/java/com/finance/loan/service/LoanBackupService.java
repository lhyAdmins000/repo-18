package com.finance.loan.service;

import com.finance.loan.model.dto.LoanBackupRequest;
import com.finance.loan.model.dto.LoanBackupResponse;
import java.util.List;

public interface LoanBackupService {
    List<LoanBackupResponse> getAll();
    LoanBackupResponse getById(Long id);
    LoanBackupResponse create(LoanBackupRequest request);
    LoanBackupResponse update(Long id, LoanBackupRequest request);
    void delete(Long id);
    List<LoanBackupResponse> search(String keyword);
}
