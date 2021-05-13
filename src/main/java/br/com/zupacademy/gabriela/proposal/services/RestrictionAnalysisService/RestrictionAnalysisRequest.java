package br.com.zupacademy.gabriela.proposal.services.RestrictionAnalysisService;

import br.com.zupacademy.gabriela.proposal.proposal.CreateProposalRequest;
import br.com.zupacademy.gabriela.proposal.proposal.Proposal;

import javax.validation.Valid;

public class RestrictionAnalysisRequest {
    private String documento;
    private String nome;
    private String idProposta;

    public RestrictionAnalysisRequest(@Valid Proposal proposal) {
        this.documento = proposal.getDocument();
        this.nome = proposal.getName();
        this.idProposta = proposal.getId().toString();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
