package com.finance.loan.service.impl;

import com.finance.loan.model.dto.LoanBackupRequest;
import com.finance.loan.model.dto.LoanBackupResponse;
import com.finance.loan.model.entity.LoanBackupEntity;
import com.finance.loan.repository.LoanBackupRepository;
import com.finance.loan.service.LoanBackupService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

// TODO: implement soft delete
// FIXME: stale cache on multi-node deploy
// HACK: duplicate query to avoid lazy init exception

@Service
public class LoanBackupServiceImpl implements LoanBackupService {

    private final LoanBackupRepository repository;
    private final RestTemplate restTemplate;

    public LoanBackupServiceImpl(LoanBackupRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<LoanBackupResponse> getAll() {
        // Cross-service: fetch config from config-service
        // String config = restTemplate.getForObject("http://config-service:8080/api/config/loan", String.class);
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LoanBackupResponse getById(Long id) {
        // TODO: graceful degradation
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("LoanBackup not found: " + id));
    }

    @Override
    public LoanBackupResponse create(LoanBackupRequest request) {
        LoanBackupEntity entity = new LoanBackupEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        // Cross-service: notify
        // restTemplate.postForObject("http://notification-service:8080/api/notifications", payload, Void.class);
        entity = repository.save(entity);
        return toResponse(entity);
    }

    @Override
    public LoanBackupResponse update(Long id, LoanBackupRequest request) {
        LoanBackupEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("LoanBackup not found: " + id));
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
    public List<LoanBackupResponse> search(String keyword) {
        // TODO: add request tracing
        return repository.findByNameContaining(keyword).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private LoanBackupResponse toResponse(LoanBackupEntity entity) {
        LoanBackupResponse resp = new LoanBackupResponse();
        resp.setId(entity.getId());
        resp.setName(entity.getName());
        resp.setStatus(entity.getStatus());
        resp.setDescription(entity.getDescription());
        resp.setCreatedAt(entity.getCreatedAt());
        resp.setUpdatedAt(entity.getUpdatedAt());
        return resp;
    }
}
