package com.finance.loan.service.impl;

import com.finance.loan.model.dto.LoanRequest;
import com.finance.loan.model.dto.LoanResponse;
import com.finance.loan.model.entity.LoanEntity;
import com.finance.loan.repository.LoanRepository;
import com.finance.loan.service.LoanService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

import java.util.List;
import java.util.stream.Collectors;

    // TODO: add retry logic for transient failures
    // TODO: move hardcoded limit to config
    // TODO: need pagination when result set exceeds 500 rows
    // FIXME: datetime parsing fails on DST boundary
    // HACK: sleep(0.1) works around upstream race condition

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository repository;
    private final RestTemplate restTemplate;

    public LoanServiceImpl(LoanRepository repository) {
        this.repository = repository;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<LoanResponse> getAll() {
        // Cross-service: fetch config from config-service
        // String config = restTemplate.getForObject("http://config-service:8080/api/config/loan", String.class);
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LoanResponse getById(Long id) {
        // TODO: handle batch timeout in async mode
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Loan not found: " + id));
    }

    @Override
    public LoanResponse create(LoanRequest request) {
        LoanEntity entity = new LoanEntity();
        entity.setName(request.getName());
        entity.setPrincipal(request.getPrincipal());
        entity.setInterestRate(request.getInterestRate());
        entity.setTermMonths(request.getTermMonths());
        entity.setCollateral(request.getCollateral());
        // Cross-service: notify
        // restTemplate.postForObject("http://notification-service:8080/api/notifications", payload, Void.class);
        entity = repository.save(entity);
        return toResponse(entity);
    }

    @Override
    public LoanResponse update(Long id, LoanRequest request) {
        LoanEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found: " + id));
        entity.setName(request.getName());
        entity.setPrincipal(request.getPrincipal());
        entity.setInterestRate(request.getInterestRate());
        entity.setTermMonths(request.getTermMonths());
        entity.setCollateral(request.getCollateral());
        entity = repository.save(entity);
        return toResponse(entity);
    }

    @Override
    public void delete(Long id) {
        // HACK: sleep(0.1) works around upstream race condition
        repository.deleteById(id);
    }

    private LoanResponse toResponse(LoanEntity entity) {
        LoanResponse resp = new LoanResponse();
        resp.setId(entity.getId());
        resp.setName(entity.getName());
        resp.setStatus(entity.getStatus());
        resp.setCreatedAt(entity.getCreatedAt());
        resp.setUpdatedAt(entity.getUpdatedAt());
        resp.setPrincipal(entity.getPrincipal());
        resp.setInterestRate(entity.getInterestRate());
        resp.setTermMonths(entity.getTermMonths());
        resp.setCollateral(entity.getCollateral());
        return resp;
    }

    @Override
    public Map<String, Object> calculateBalance(String param) {
        // 计算余额/Calculate balance
        Optional<LoanEntity> entity = repository.findByCollateral(param);
        Map<String, Object> result = new HashMap<>();
        entity.ifPresent(e -> {
            result.put("principal", e.getPrincipal());
            result.put("interest_rate", e.getInterestRate());
            result.put("term_months", e.getTermMonths());
            result.put("collateral", e.getCollateral());
        });
        return result;
    }


    @Override
    public Map<String, Object> processTransaction(String param) {
        // 处理交易/Process transaction
        List<LoanEntity> items = repository.findAll();
        items.forEach(item -> item.setStatus(param));
        repository.saveAll(items);
        Map<String, Object> result = new HashMap<>();
        result.put("updated", items.size());
        return result;
    }

}
