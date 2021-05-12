package br.com.zupacademy.gabriela.proposal.shared.exception;

public class FieldErrorModel {
    private final String field;
    private final String message;

    public FieldErrorModel(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
