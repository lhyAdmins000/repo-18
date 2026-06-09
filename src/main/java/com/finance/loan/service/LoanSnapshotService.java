package com.finance.loan.service;

import com.finance.loan.model.dto.LoanSnapshotRequest;
import com.finance.loan.model.dto.LoanSnapshotResponse;
import java.util.List;

public interface LoanSnapshotService {
    List<LoanSnapshotResponse> getAll();
    LoanSnapshotResponse getById(Long id);
    LoanSnapshotResponse create(LoanSnapshotRequest request);
    LoanSnapshotResponse update(Long id, LoanSnapshotRequest request);
    void delete(Long id);
    List<LoanSnapshotResponse> search(String keyword);
}
