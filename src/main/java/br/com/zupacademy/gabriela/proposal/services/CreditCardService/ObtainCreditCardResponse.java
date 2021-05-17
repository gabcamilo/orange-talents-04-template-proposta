package br.com.zupacademy.gabriela.proposal.services.CreditCardService;

import br.com.zupacademy.gabriela.proposal.CreditCard.CreditCard;
import br.com.zupacademy.gabriela.proposal.proposal.Proposal;

import java.time.LocalDateTime;

public class ObtainCreditCardResponse {

    private String id;
    private LocalDateTime emitidoEm;

    public ObtainCreditCardResponse(String id, LocalDateTime emitidoEm) {
        this.id = id;
        this.emitidoEm = emitidoEm;
    }

    public CreditCard convert (Proposal proposal) {
        return new CreditCard(proposal, id, emitidoEm);
    }
}
