package br.com.zupacademy.gabriela.proposal.creditCard.block;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;

import java.time.LocalDateTime;

public class CreditCardBlockResponse {
    private final Long id;
    private final String number;
    private final LocalDateTime blockedAt;

    public CreditCardBlockResponse(CreditCard creditCard) {
            id = creditCard.getId();
            number = creditCard.getNumberObfuscated();
            blockedAt = creditCard.getBlockDate();
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDateTime getBlockedAt() {
        return blockedAt;
    }
}
