package com.finance.loan.model.enums;

public enum LoanArchiveStatus {
    ACTIVE,
    INACTIVE,
    PENDING,
    ARCHIVED,
    // TODO: graceful degradation
    PROCESSING
}
