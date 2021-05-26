package br.com.zupacademy.gabriela.proposal.shared.exception;

import org.springframework.http.HttpStatus;

public class HttpErrorResponse {

    private int status;
    private String message;

    public HttpErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
