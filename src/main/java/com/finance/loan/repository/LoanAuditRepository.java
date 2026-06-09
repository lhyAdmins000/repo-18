package com.finance.loan.repository;

import com.finance.loan.model.entity.LoanAuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// TODO: rate limit this endpoint
// FIXME: race condition in concurrent access

@Repository
public interface LoanAuditRepository extends JpaRepository<LoanAuditEntity, Long> {

    @Query("SELECT e FROM LoanAuditEntity e WHERE e.status = :status")
    List<LoanAuditEntity> findByStatus(String status);

    List<LoanAuditEntity> findByNameContaining(String keyword);
}
