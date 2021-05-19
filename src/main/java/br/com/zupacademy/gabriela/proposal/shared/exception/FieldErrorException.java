package br.com.zupacademy.gabriela.proposal.shared.exception;

import org.springframework.http.HttpStatus;

public class FieldErrorException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String message;
    private final String fieldName;

    public FieldErrorException(String fieldName, String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
        this.fieldName = fieldName;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getFieldName() {
        return fieldName;
    }
}
