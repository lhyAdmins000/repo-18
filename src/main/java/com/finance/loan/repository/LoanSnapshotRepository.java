package com.finance.loan.repository;

import com.finance.loan.model.entity.LoanSnapshotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// TODO: add request tracing
// FIXME: missing transaction rollback

@Repository
public interface LoanSnapshotRepository extends JpaRepository<LoanSnapshotEntity, Long> {

    @Query("SELECT e FROM LoanSnapshotEntity e WHERE e.status = :status")
    List<LoanSnapshotEntity> findByStatus(String status);

    List<LoanSnapshotEntity> findByNameContaining(String keyword);
}
