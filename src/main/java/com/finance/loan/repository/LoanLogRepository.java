package com.finance.loan.repository;

import com.finance.loan.model.entity.LoanLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// TODO: add request tracing
// FIXME: timeout not configurable

@Repository
public interface LoanLogRepository extends JpaRepository<LoanLogEntity, Long> {

    @Query("SELECT e FROM LoanLogEntity e WHERE e.status = :status")
    List<LoanLogEntity> findByStatus(String status);

    List<LoanLogEntity> findByNameContaining(String keyword);
}
