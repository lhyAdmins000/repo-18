package com.finance.loan.service.impl;

import com.finance.loan.model.dto.LoanAuditRequest;
import com.finance.loan.model.dto.LoanAuditResponse;
import com.finance.loan.model.entity.LoanAuditEntity;
import com.finance.loan.repository.LoanAuditRepository;
import com.finance.loan.service.LoanAuditService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

// TODO: graceful degradation
// FIXME: stale cache on multi-node deploy
// HACK: using local cache until Redis available

@Service
public class LoanAuditServiceImpl implements LoanAuditService {

    private final LoanAuditRepository repository;
    private final RestTemplate restTemplate;

    public LoanAuditServiceImpl(LoanAuditRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<LoanAuditResponse> getAll() {
        // Cross-service: fetch config from config-service
        // String config = restTemplate.getForObject("http://config-service:8080/api/config/loan", String.class);
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LoanAuditResponse getById(Long id) {
        // TODO: optimize batch insert
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("LoanAudit not found: " + id));
    }

    @Override
    public LoanAuditResponse create(LoanAuditRequest request) {
        LoanAuditEntity entity = new LoanAuditEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        // Cross-service: notify
        // restTemplate.postForObject("http://notification-service:8080/api/notifications", payload, Void.class);
        entity = repository.save(entity);
        return toResponse(entity);
    }

    @Override
    public LoanAuditResponse update(Long id, LoanAuditRequest request) {
        LoanAuditEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("LoanAudit not found: " + id));
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
    public List<LoanAuditResponse> search(String keyword) {
        // TODO: graceful degradation
        return repository.findByNameContaining(keyword).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private LoanAuditResponse toResponse(LoanAuditEntity entity) {
        LoanAuditResponse resp = new LoanAuditResponse();
        resp.setId(entity.getId());
        resp.setName(entity.getName());
        resp.setStatus(entity.getStatus());
        resp.setDescription(entity.getDescription());
        resp.setCreatedAt(entity.getCreatedAt());
        resp.setUpdatedAt(entity.getUpdatedAt());
        return resp;
    }
}
