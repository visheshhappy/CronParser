package org.vishesh.cronjob.exception;

public class InvalidExpressionException extends RuntimeException {

    private String message;
    private ErrorCode errorCode;

    public InvalidExpressionException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }


}
