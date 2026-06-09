package com.finance.loan.exception;

public class LoanSnapshotException extends RuntimeException {
    public LoanSnapshotException(String message) {
        super(message);
    }
    public LoanSnapshotException(String message, Throwable cause) {
        super(message, cause);
    }
}
