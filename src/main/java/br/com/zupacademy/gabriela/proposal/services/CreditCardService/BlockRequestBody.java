package br.com.zupacademy.gabriela.proposal.services.CreditCardService;

public class BlockRequestBody {
    private final String sistemaResponsavel;

    public BlockRequestBody() {
        this.sistemaResponsavel = "proposta";
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
