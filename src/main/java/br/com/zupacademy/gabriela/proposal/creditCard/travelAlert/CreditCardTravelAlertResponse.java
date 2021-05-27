package br.com.zupacademy.gabriela.proposal.creditCard.travelAlert;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;

import java.time.LocalDate;

public class CreditCardTravelAlertResponse {

    private final Long id;
    private final String number;
    private final String travelDestination;
    private final LocalDate travelEndDate;

    public CreditCardTravelAlertResponse(CreditCard creditCard) {
        id = creditCard.getId();
        number = creditCard.getNumberObfuscated();
        travelEndDate = creditCard.getTravelEndDate();
        travelDestination = creditCard.getTravelDestination();
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getTravelDestination() {
        return travelDestination;
    }

    public LocalDate getTravelEndDate() {
        return travelEndDate;
    }
}
