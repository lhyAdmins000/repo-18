package com.finance.loan.exception;

public class LoanLogException extends RuntimeException {
    public LoanLogException(String message) {
        super(message);
    }
    public LoanLogException(String message, Throwable cause) {
        super(message, cause);
    }
}
