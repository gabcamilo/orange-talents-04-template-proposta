package br.com.zupacademy.gabriela.proposal.shared.exception;

import feign.FeignException;
import javassist.NotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandlerAdvice {
    private MessageSource messageSource;

    public ApiExceptionHandlerAdvice(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @ExceptionHandler(FieldErrorException.class)
    public ResponseEntity<FieldErrorResponse> handle(FieldErrorException exception){

        FieldErrorResponse responseBody = new FieldErrorResponse();
        FieldErrorModel errorModel = new FieldErrorModel(exception.getFieldName(), exception.getMessage());

        responseBody.addError(errorModel);

        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(responseBody);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FieldErrorResponse> handle(MethodArgumentNotValidException exception){
        FieldErrorResponse errorResponse = new FieldErrorResponse();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(error -> {
            String errorMessage = getErrorMessage(error);
            errorResponse.addError(new FieldErrorModel(error.getField(), errorMessage));
        });

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handle() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<HttpErrorResponse> handle(HttpClientErrorException exception) {
        HttpErrorResponse response = new HttpErrorResponse(exception.getStatusCode().value(), exception.getMessage());

        return ResponseEntity.status(exception.getStatusCode()).body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handle(MethodArgumentTypeMismatchException exception) {

        if(exception.getName().equals("walletEnum")){
            return handle(new FieldErrorException("wallet", exception.getValue() + " wallet does not exist", HttpStatus.BAD_REQUEST));
        }

        // Defensive programming, but it must never get to this generic message return bellow
        return handle(new FieldErrorException(exception.getName(), exception.getValue() + " is not a valid argument", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> handle(FeignException exception) {
        return ResponseEntity.unprocessableEntity().build();
    }

    private String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }
}
