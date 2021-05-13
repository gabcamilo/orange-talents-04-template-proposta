package br.com.zupacademy.gabriela.proposal.services.RestrictionAnalysisService;

import br.com.zupacademy.gabriela.proposal.shared.enums.RestrictionAnalysisStatusEnum;

public class RestrictionAnalysisResponse {
    private String documento;
    private String nome;
    private RestrictionAnalysisStatusEnum resultadoSolicitacao;
    private String idProposta;

    public RestrictionAnalysisResponse(String documento, String nome, RestrictionAnalysisStatusEnum resultadoSolicitacao, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

}
