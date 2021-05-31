package br.com.zupacademy.gabriela.proposal.creditCard.wallet;

public class AssociateWalletResponse {

    private final Long id;
    private final Wallets wallet;
    private final String email;
    private final String creditCardNumber;
    private final Long creditCardId;


    public AssociateWalletResponse(CreditCardWallet creditCardWallet) {
        this.id = creditCardWallet.getId();
        this.wallet = creditCardWallet.getWallet();
        this.email = creditCardWallet.getEmailObfuscated();
        this.creditCardNumber = creditCardWallet.getCreditCardNumberObfuscated();
        this.creditCardId = creditCardWallet.getCreditCardId();
    }

    public Long getId() {
        return id;
    }

    public Wallets getWallet() {
        return wallet;
    }

    public String getEmail() {
        return email;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public Long getCreditCardId() {
        return creditCardId;
    }
}
