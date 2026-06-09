package com.finance.loan.exception;

public class LoanArchiveException extends RuntimeException {
    public LoanArchiveException(String message) {
        super(message);
    }
    public LoanArchiveException(String message, Throwable cause) {
        super(message, cause);
    }
}
