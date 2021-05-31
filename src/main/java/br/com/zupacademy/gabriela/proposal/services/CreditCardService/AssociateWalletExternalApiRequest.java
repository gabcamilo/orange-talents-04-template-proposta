package br.com.zupacademy.gabriela.proposal.services.CreditCardService;

import br.com.zupacademy.gabriela.proposal.creditCard.wallet.Wallets;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AssociateWalletExternalApiRequest {
    private final String email;
    private final String carteira;

    public AssociateWalletExternalApiRequest(@JsonProperty("email") String email, Wallets carteira) {
        this.email = email;
        this.carteira = carteira.toString();
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
