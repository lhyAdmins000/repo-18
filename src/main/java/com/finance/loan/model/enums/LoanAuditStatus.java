package com.finance.loan.model.enums;

public enum LoanAuditStatus {
    ACTIVE,
    INACTIVE,
    PENDING,
    ARCHIVED,
    // TODO: graceful degradation
    PROCESSING
}
