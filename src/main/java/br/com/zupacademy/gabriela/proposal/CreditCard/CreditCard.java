package br.com.zupacademy.gabriela.proposal.CreditCard;

import br.com.zupacademy.gabriela.proposal.proposal.Proposal;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class CreditCard {

    public CreditCard(Proposal proposal, String number, LocalDateTime createdAt) {
        this.id = proposal.getId();
        this.number = number;
        this.createdAt = createdAt;
        this.proposal = proposal;
    }

    @Deprecated
    protected CreditCard() { }

    @Id
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String number;

    @OneToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "proposal_credit_card_fk"))
    private Proposal proposal;

    private LocalDateTime createdAt;

}
