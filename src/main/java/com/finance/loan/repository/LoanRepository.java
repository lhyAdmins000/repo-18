package com.finance.loan.repository;

import com.finance.loan.model.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

    Optional<LoanEntity> findByCollateral(String collateral);
}
