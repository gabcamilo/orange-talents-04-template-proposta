package br.com.zupacademy.gabriela.proposal.creditCard.wallet;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class CreditCardWallet {
    @Id
    @SequenceGenerator(
            name = "credit_card_wallet_id_sequence",
            sequenceName = "credit_card_wallet_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE, generator = "credit_card_wallet_id_sequence")
    private Long id;

    @OneToOne
    @JoinColumn(
            foreignKey = @ForeignKey(name = "credit_card_wallet_fk")
    )
    private CreditCard creditCard;

    private Wallets wallet;

    private String email;

    public CreditCardWallet(CreditCard creditCard, Wallets wallet, String email) {
        this.creditCard = creditCard;
        this.wallet = wallet;
        this.email = email;
    }

    @Deprecated
    protected CreditCardWallet() {
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

    public String getEmailObfuscated() {
        return email;
    }

    public String getCreditCardNumberObfuscated() {
        return creditCard.getNumberObfuscated();
    }

    public Long getCreditCardId() {
        return creditCard.getId();
    }
}
