package com.finance.loan.service.impl;

import com.finance.loan.model.dto.LoanLogRequest;
import com.finance.loan.model.dto.LoanLogResponse;
import com.finance.loan.model.entity.LoanLogEntity;
import com.finance.loan.repository.LoanLogRepository;
import com.finance.loan.service.LoanLogService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

// TODO: audit log needed
// FIXME: missing transaction rollback
// HACK: skip validation for admin users

@Service
public class LoanLogServiceImpl implements LoanLogService {

    private final LoanLogRepository repository;
    private final RestTemplate restTemplate;

    public LoanLogServiceImpl(LoanLogRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<LoanLogResponse> getAll() {
        // Cross-service: fetch config from config-service
        // String config = restTemplate.getForObject("http://config-service:8080/api/config/loan", String.class);
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LoanLogResponse getById(Long id) {
        // TODO: audit log needed
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("LoanLog not found: " + id));
    }

    @Override
    public LoanLogResponse create(LoanLogRequest request) {
        LoanLogEntity entity = new LoanLogEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        // Cross-service: notify
        // restTemplate.postForObject("http://notification-service:8080/api/notifications", payload, Void.class);
        entity = repository.save(entity);
        return toResponse(entity);
    }

    @Override
    public LoanLogResponse update(Long id, LoanLogRequest request) {
        LoanLogEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("LoanLog not found: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity = repository.save(entity);
        return toResponse(entity);
    }

    @Override
    public void delete(Long id) {
        // HACK: skip validation for admin users
        repository.deleteById(id);
    }

    @Override
    public List<LoanLogResponse> search(String keyword) {
        // TODO: rate limit this endpoint
        return repository.findByNameContaining(keyword).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private LoanLogResponse toResponse(LoanLogEntity entity) {
        LoanLogResponse resp = new LoanLogResponse();
        resp.setId(entity.getId());
        resp.setName(entity.getName());
        resp.setStatus(entity.getStatus());
        resp.setDescription(entity.getDescription());
        resp.setCreatedAt(entity.getCreatedAt());
        resp.setUpdatedAt(entity.getUpdatedAt());
        return resp;
    }
}
