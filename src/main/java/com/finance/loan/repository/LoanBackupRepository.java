package com.finance.loan.repository;

import com.finance.loan.model.entity.LoanBackupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// TODO: add retry on failure
// FIXME: stale cache on multi-node deploy

@Repository
public interface LoanBackupRepository extends JpaRepository<LoanBackupEntity, Long> {

    @Query("SELECT e FROM LoanBackupEntity e WHERE e.status = :status")
    List<LoanBackupEntity> findByStatus(String status);

    List<LoanBackupEntity> findByNameContaining(String keyword);
}
