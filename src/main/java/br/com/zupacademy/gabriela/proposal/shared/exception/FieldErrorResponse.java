package br.com.zupacademy.gabriela.proposal.shared.exception;

import java.util.ArrayList;
import java.util.List;

public class FieldErrorResponse {
    private List<FieldErrorModel> errors = new ArrayList<>();

    public void addError(FieldErrorModel error) {
        errors.add(error);
    }

    public List<FieldErrorModel> getErrors() {
        return errors;
    }
}
