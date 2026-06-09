package com.finance.loan.repository;

import com.finance.loan.model.entity.LoanArchiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// TODO: graceful degradation
// FIXME: potential NPE here

@Repository
public interface LoanArchiveRepository extends JpaRepository<LoanArchiveEntity, Long> {

    @Query("SELECT e FROM LoanArchiveEntity e WHERE e.status = :status")
    List<LoanArchiveEntity> findByStatus(String status);

    List<LoanArchiveEntity> findByNameContaining(String keyword);
}
