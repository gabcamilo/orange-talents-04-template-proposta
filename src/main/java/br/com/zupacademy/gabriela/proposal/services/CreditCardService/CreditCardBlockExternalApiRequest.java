package br.com.zupacademy.gabriela.proposal.services.CreditCardService;

public class CreditCardBlockExternalApiRequest {
    private final String sistemaResponsavel;

    public CreditCardBlockExternalApiRequest() {
        this.sistemaResponsavel = "proposta";
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
