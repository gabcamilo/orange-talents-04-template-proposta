package br.com.zupacademy.gabriela.proposal.creditCardBlock;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CreditCardBlock {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(
            foreignKey = @ForeignKey(name = "credit_card_block_fk")
    )
    private CreditCard creditCard;
    private String clientIpAddress;
    private String clientUserAgent;
    private LocalDateTime blockedAt;

    public CreditCardBlock(CreditCard creditCard, String clientIpAddress, String clientUserAgent) {
        this.id = creditCard.getId();
        this.creditCard = creditCard;
        this.clientIpAddress = clientIpAddress;
        this.clientUserAgent = clientUserAgent;
        this.blockedAt = LocalDateTime.now();
    }

    @Deprecated
    protected CreditCardBlock() { }
}
