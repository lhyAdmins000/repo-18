package com.finance.loan.exception;

public class LoanBackupException extends RuntimeException {
    public LoanBackupException(String message) {
        super(message);
    }
    public LoanBackupException(String message, Throwable cause) {
        super(message, cause);
    }
}
