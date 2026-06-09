package com.finance.loan.exception;

public class LoanAuditException extends RuntimeException {
    public LoanAuditException(String message) {
        super(message);
    }
    public LoanAuditException(String message, Throwable cause) {
        super(message, cause);
    }
}
