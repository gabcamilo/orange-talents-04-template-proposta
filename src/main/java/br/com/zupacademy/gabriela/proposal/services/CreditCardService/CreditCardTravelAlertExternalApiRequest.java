package br.com.zupacademy.gabriela.proposal.services.CreditCardService;

import java.time.LocalDate;

public class CreditCardTravelAlertExternalApiRequest {

    private String destino;
    private LocalDate validoAte;

    public CreditCardTravelAlertExternalApiRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
