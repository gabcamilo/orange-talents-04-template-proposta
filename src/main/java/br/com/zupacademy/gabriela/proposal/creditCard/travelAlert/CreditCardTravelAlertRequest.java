package br.com.zupacademy.gabriela.proposal.creditCard.travelAlert;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;
import br.com.zupacademy.gabriela.proposal.creditCard.travelAlert.CreditCardTravelAlert;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CreditCardTravelAlertRequest {

    @NotBlank
    private String destination;

    @Future
    @NotNull
    private LocalDate travelEndDate;

    public CreditCardTravelAlertRequest(@NotBlank String destination, @Future @NotNull LocalDate travelEndDate) {
        this.destination = destination;
        this.travelEndDate = travelEndDate;
    }

    public CreditCardTravelAlert convert(CreditCard creditCard, String ipAddress, String userAgent){
        return new CreditCardTravelAlert(creditCard, destination, travelEndDate, ipAddress, userAgent);
    }
}
