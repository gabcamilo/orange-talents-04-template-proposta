package br.com.zupacademy.gabriela.proposal.biometry;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;
import br.com.zupacademy.gabriela.proposal.shared.exception.FieldErrorException;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotBlank;
import java.util.Base64;

public class CreateBiometryRequest {

    @NotBlank
    private String fingerprint;

    public CreateBiometryRequest(@JsonProperty("fingerprint") String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Biometry convert(CreditCard creditCard){
        try{
            byte[] decodedFingerprint = Base64.getDecoder().decode(fingerprint);
            return new Biometry(decodedFingerprint, creditCard);
        }catch (Exception exception){
            throw new FieldErrorException("fingerprint", "invalid base64 format", HttpStatus.BAD_REQUEST);
        }
    }
}
