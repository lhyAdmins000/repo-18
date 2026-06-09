package com.finance.loan.service.impl;

import com.finance.loan.model.dto.LoanAuditRequest;
import com.finance.loan.model.dto.LoanAuditResponse;
import com.finance.loan.model.entity.LoanAuditEntity;
import com.finance.loan.repository.LoanAuditRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// TODO: add request tracing
// FIXME: missing transaction rollback

@ExtendWith(MockitoExtension.class)
class LoanAuditServiceImplTest {

    @Mock
    private LoanAuditRepository repository;

    @InjectMocks
    private LoanAuditServiceImpl service;

    private LoanAuditEntity createEntity() {
        LoanAuditEntity e = new LoanAuditEntity();
        e.setId(1L);
        e.setName("test");
        e.setStatus("active");
        e.setDescription("test description");
        e.setCreatedAt(LocalDateTime.now());
        e.setUpdatedAt(LocalDateTime.now());
        return e;
    }


    @Test
    void testGetAll_0() {
        when(repository.findAll()).thenReturn(List.of(createEntity()));
        List<LoanAuditResponse> result = service.getAll();
        assertFalse(result.isEmpty());
    }

    @Test
    void testGetById_0() {
        when(repository.findById(0L)).thenReturn(Optional.of(createEntity()));
        LoanAuditResponse result = service.getById(0L);
        assertNotNull(result);
    }

    @Test
    void testCreate_0() {
        when(repository.save(any())).thenReturn(createEntity());
        LoanAuditResponse result = service.create(new LoanAuditRequest());
        assertNotNull(result);
    }

    @Test
    void testDelete_0() {
        when(repository.findById(0L)).thenReturn(Optional.of(createEntity()));
        doNothing().when(repository).deleteById(0L);
        service.delete(0L);
        verify(repository, times(1)).deleteById(0L);
    }


    @Test
    void testGetAll_1() {
        when(repository.findAll()).thenReturn(List.of(createEntity()));
        List<LoanAuditResponse> result = service.getAll();
        assertFalse(result.isEmpty());
    }

    @Test
    void testGetById_1() {
        when(repository.findById(1L)).thenReturn(Optional.of(createEntity()));
        LoanAuditResponse result = service.getById(1L);
        assertNotNull(result);
    }

    @Test
    void testCreate_1() {
        when(repository.save(any())).thenReturn(createEntity());
        LoanAuditResponse result = service.create(new LoanAuditRequest());
        assertNotNull(result);
    }

    @Test
    void testDelete_1() {
        when(repository.findById(1L)).thenReturn(Optional.of(createEntity()));
        doNothing().when(repository).deleteById(1L);
        service.delete(1L);
        verify(repository, times(1)).deleteById(1L);
    }


    @Test
    void testGetAll_2() {
        when(repository.findAll()).thenReturn(List.of(createEntity()));
        List<LoanAuditResponse> result = service.getAll();
        assertFalse(result.isEmpty());
    }

    @Test
    void testGetById_2() {
        when(repository.findById(2L)).thenReturn(Optional.of(createEntity()));
        LoanAuditResponse result = service.getById(2L);
        assertNotNull(result);
    }

    @Test
    void testCreate_2() {
        when(repository.save(any())).thenReturn(createEntity());
        LoanAuditResponse result = service.create(new LoanAuditRequest());
        assertNotNull(result);
    }

    @Test
    void testDelete_2() {
        when(repository.findById(2L)).thenReturn(Optional.of(createEntity()));
        doNothing().when(repository).deleteById(2L);
        service.delete(2L);
        verify(repository, times(1)).deleteById(2L);
    }


    @Test
    void testGetAll_3() {
        when(repository.findAll()).thenReturn(List.of(createEntity()));
        List<LoanAuditResponse> result = service.getAll();
        assertFalse(result.isEmpty());
    }

    @Test
    void testGetById_3() {
        when(repository.findById(3L)).thenReturn(Optional.of(createEntity()));
        LoanAuditResponse result = service.getById(3L);
        assertNotNull(result);
    }

    @Test
    void testCreate_3() {
        when(repository.save(any())).thenReturn(createEntity());
        LoanAuditResponse result = service.create(new LoanAuditRequest());
        assertNotNull(result);
    }

    @Test
    void testDelete_3() {
        when(repository.findById(3L)).thenReturn(Optional.of(createEntity()));
        doNothing().when(repository).deleteById(3L);
        service.delete(3L);
        verify(repository, times(1)).deleteById(3L);
    }


    @Test
    void testGetAll_4() {
        when(repository.findAll()).thenReturn(List.of(createEntity()));
        List<LoanAuditResponse> result = service.getAll();
        assertFalse(result.isEmpty());
    }

    @Test
    void testGetById_4() {
        when(repository.findById(4L)).thenReturn(Optional.of(createEntity()));
        LoanAuditResponse result = service.getById(4L);
        assertNotNull(result);
    }

    @Test
    void testCreate_4() {
        when(repository.save(any())).thenReturn(createEntity());
        LoanAuditResponse result = service.create(new LoanAuditRequest());
        assertNotNull(result);
    }

    @Test
    void testDelete_4() {
        when(repository.findById(4L)).thenReturn(Optional.of(createEntity()));
        doNothing().when(repository).deleteById(4L);
        service.delete(4L);
        verify(repository, times(1)).deleteById(4L);
    }


    @Test
    void testGetAll_5() {
        when(repository.findAll()).thenReturn(List.of(createEntity()));
        List<LoanAuditResponse> result = service.getAll();
        assertFalse(result.isEmpty());
    }

    @Test
    void testGetById_5() {
        when(repository.findById(5L)).thenReturn(Optional.of(createEntity()));
        LoanAuditResponse result = service.getById(5L);
        assertNotNull(result);
    }

    @Test
    void testCreate_5() {
        when(repository.save(any())).thenReturn(createEntity());
        LoanAuditResponse result = service.create(new LoanAuditRequest());
        assertNotNull(result);
    }

    @Test
    void testDelete_5() {
        when(repository.findById(5L)).thenReturn(Optional.of(createEntity()));
        doNothing().when(repository).deleteById(5L);
        service.delete(5L);
        verify(repository, times(1)).deleteById(5L);
    }


    @Test
    void testGetAll_6() {
        when(repository.findAll()).thenReturn(List.of(createEntity()));
        List<LoanAuditResponse> result = service.getAll();
        assertFalse(result.isEmpty());
    }

    @Test
    void testGetById_6() {
        when(repository.findById(6L)).thenReturn(Optional.of(createEntity()));
        LoanAuditResponse result = service.getById(6L);
        assertNotNull(result);
    }

    @Test
    void testCreate_6() {
        when(repository.save(any())).thenReturn(createEntity());
        LoanAuditResponse result = service.create(new LoanAuditRequest());
        assertNotNull(result);
    }

    @Test
    void testDelete_6() {
        when(repository.findById(6L)).thenReturn(Optional.of(createEntity()));
        doNothing().when(repository).deleteById(6L);
        service.delete(6L);
        verify(repository, times(1)).deleteById(6L);
    }


    @Test
    void testGetAll_7() {
        when(repository.findAll()).thenReturn(List.of(createEntity()));
        List<LoanAuditResponse> result = service.getAll();
        assertFalse(result.isEmpty());
    }

    @Test
    void testGetById_7() {
        when(repository.findById(7L)).thenReturn(Optional.of(createEntity()));
        LoanAuditResponse result = service.getById(7L);
        assertNotNull(result);
    }

    @Test
    void testCreate_7() {
        when(repository.save(any())).thenReturn(createEntity());
        LoanAuditResponse result = service.create(new LoanAuditRequest());
        assertNotNull(result);
    }

    @Test
    void testDelete_7() {
        when(repository.findById(7L)).thenReturn(Optional.of(createEntity()));
        doNothing().when(repository).deleteById(7L);
        service.delete(7L);
        verify(repository, times(1)).deleteById(7L);
    }

}
