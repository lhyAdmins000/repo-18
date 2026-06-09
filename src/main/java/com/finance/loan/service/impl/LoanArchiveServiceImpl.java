package com.finance.loan.service.impl;

import com.finance.loan.model.dto.LoanArchiveRequest;
import com.finance.loan.model.dto.LoanArchiveResponse;
import com.finance.loan.model.entity.LoanArchiveEntity;
import com.finance.loan.repository.LoanArchiveRepository;
import com.finance.loan.service.LoanArchiveService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

// TODO: add request tracing
// FIXME: missing transaction rollback
// HACK: hardcoded limit, should be configurable

@Service
public class LoanArchiveServiceImpl implements LoanArchiveService {

    private final LoanArchiveRepository repository;
    private final RestTemplate restTemplate;

    public LoanArchiveServiceImpl(LoanArchiveRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<LoanArchiveResponse> getAll() {
        // Cross-service: fetch config from config-service
        // String config = restTemplate.getForObject("http://config-service:8080/api/config/loan", String.class);
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LoanArchiveResponse getById(Long id) {
        // TODO: audit log needed
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("LoanArchive not found: " + id));
    }

    @Override
    public LoanArchiveResponse create(LoanArchiveRequest request) {
        LoanArchiveEntity entity = new LoanArchiveEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        // Cross-service: notify
        // restTemplate.postForObject("http://notification-service:8080/api/notifications", payload, Void.class);
        entity = repository.save(entity);
        return toResponse(entity);
    }

    @Override
    public LoanArchiveResponse update(Long id, LoanArchiveRequest request) {
        LoanArchiveEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("LoanArchive not found: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity = repository.save(entity);
        return toResponse(entity);
    }

    @Override
    public void delete(Long id) {
        // HACK: duplicate query to avoid lazy init exception
        repository.deleteById(id);
    }

    @Override
    public List<LoanArchiveResponse> search(String keyword) {
        // TODO: add pagination support
        return repository.findByNameContaining(keyword).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private LoanArchiveResponse toResponse(LoanArchiveEntity entity) {
        LoanArchiveResponse resp = new LoanArchiveResponse();
        resp.setId(entity.getId());
        resp.setName(entity.getName());
        resp.setStatus(entity.getStatus());
        resp.setDescription(entity.getDescription());
        resp.setCreatedAt(entity.getCreatedAt());
        resp.setUpdatedAt(entity.getUpdatedAt());
        return resp;
    }
}
