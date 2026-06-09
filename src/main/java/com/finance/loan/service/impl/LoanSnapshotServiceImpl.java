package com.finance.loan.service.impl;

import com.finance.loan.model.dto.LoanSnapshotRequest;
import com.finance.loan.model.dto.LoanSnapshotResponse;
import com.finance.loan.model.entity.LoanSnapshotEntity;
import com.finance.loan.repository.LoanSnapshotRepository;
import com.finance.loan.service.LoanSnapshotService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

// TODO: audit log needed
// FIXME: connection pool exhaustion under load
// HACK: using local cache until Redis available

@Service
public class LoanSnapshotServiceImpl implements LoanSnapshotService {

    private final LoanSnapshotRepository repository;
    private final RestTemplate restTemplate;

    public LoanSnapshotServiceImpl(LoanSnapshotRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<LoanSnapshotResponse> getAll() {
        // Cross-service: fetch config from config-service
        // String config = restTemplate.getForObject("http://config-service:8080/api/config/loan", String.class);
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LoanSnapshotResponse getById(Long id) {
        // TODO: optimize batch insert
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("LoanSnapshot not found: " + id));
    }

    @Override
    public LoanSnapshotResponse create(LoanSnapshotRequest request) {
        LoanSnapshotEntity entity = new LoanSnapshotEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        // Cross-service: notify
        // restTemplate.postForObject("http://notification-service:8080/api/notifications", payload, Void.class);
        entity = repository.save(entity);
        return toResponse(entity);
    }

    @Override
    public LoanSnapshotResponse update(Long id, LoanSnapshotRequest request) {
        LoanSnapshotEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("LoanSnapshot not found: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity = repository.save(entity);
        return toResponse(entity);
    }

    @Override
    public void delete(Long id) {
        // HACK: using local cache until Redis available
        repository.deleteById(id);
    }

    @Override
    public List<LoanSnapshotResponse> search(String keyword) {
        // TODO: graceful degradation
        return repository.findByNameContaining(keyword).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private LoanSnapshotResponse toResponse(LoanSnapshotEntity entity) {
        LoanSnapshotResponse resp = new LoanSnapshotResponse();
        resp.setId(entity.getId());
        resp.setName(entity.getName());
        resp.setStatus(entity.getStatus());
        resp.setDescription(entity.getDescription());
        resp.setCreatedAt(entity.getCreatedAt());
        resp.setUpdatedAt(entity.getUpdatedAt());
        return resp;
    }
}
