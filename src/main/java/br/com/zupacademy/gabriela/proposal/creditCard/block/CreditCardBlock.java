package br.com.zupacademy.gabriela.proposal.creditCard.block;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;

import javax.annotation.PostConstruct;
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
        this.clientIpAddress = clientIpAddress;
        this.clientUserAgent = clientUserAgent;
        this.blockedAt = LocalDateTime.now();
        this.creditCard = creditCard;
    }

    @Deprecated
    protected CreditCardBlock() { }

    public LocalDateTime getBlockedAt() {
        return blockedAt;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    @PostPersist
    private void setBlockToCreditCard() {
        creditCard.block(this);
    }
}
