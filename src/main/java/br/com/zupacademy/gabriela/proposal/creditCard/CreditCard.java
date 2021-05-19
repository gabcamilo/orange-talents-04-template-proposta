package br.com.zupacademy.gabriela.proposal.creditCard;

import br.com.zupacademy.gabriela.proposal.proposal.Proposal;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
public class CreditCard {

    @Id
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String number;

    @OneToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "proposal_credit_card_fk"))
    private Proposal proposal;

    private LocalDateTime createdAt;

    public CreditCard(Proposal proposal, String number, LocalDateTime createdAt) {
        this.id = proposal.getId();
        this.number = number;
        this.createdAt = createdAt;
        this.proposal = proposal;
    }

    public CreditCard(Long id) {
        this.id = id;
    }

    @Deprecated
    protected CreditCard() { }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }
}
