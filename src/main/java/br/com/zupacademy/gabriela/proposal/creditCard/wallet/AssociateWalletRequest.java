package br.com.zupacademy.gabriela.proposal.creditCard.wallet;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AssociateWalletRequest {
    private final String email;

    @NotBlank
    @Email
    public AssociateWalletRequest(@JsonProperty("email") String email) {
        this.email = email;
    }

    public CreditCardWallet convert(CreditCard creditCard, Wallets wallets){
        return new CreditCardWallet(creditCard, wallets, email);
    }
}
