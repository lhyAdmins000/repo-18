package com.finance.loan.service;

import com.finance.loan.model.dto.LoanRequest;
import com.finance.loan.model.dto.LoanResponse;

import java.util.Map;

import java.util.List;

public interface LoanService {
    List<LoanResponse> getAll();
    LoanResponse getById(Long id);
    LoanResponse create(LoanRequest request);
    LoanResponse update(Long id, LoanRequest request);
    void delete(Long id);

    Map<String, Object> calculateBalance(String param);
    Map<String, Object> processTransaction(String param);
}
