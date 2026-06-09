package com.finance.loan.model.enums;

public enum LoanBackupStatus {
    ACTIVE,
    INACTIVE,
    PENDING,
    ARCHIVED,
    // TODO: graceful degradation
    PROCESSING
}
