package br.com.zupacademy.gabriela.proposal.creditCard;

import br.com.zupacademy.gabriela.proposal.creditCard.block.CreditCardBlock;
import br.com.zupacademy.gabriela.proposal.creditCard.travelAlert.CreditCardTravelAlert;
import br.com.zupacademy.gabriela.proposal.proposal.Proposal;
import br.com.zupacademy.gabriela.proposal.shared.enums.CreditCardStatusEnum;

import javax.persistence.*;

import java.time.LocalDate;
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

    @Transient
    private CreditCardStatusEnum status;

    @OneToOne(
            mappedBy = "creditCard",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    //TODO: improve this with Optional (?)
    private CreditCardBlock block;

    @OneToOne(
            mappedBy = "creditCard",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private CreditCardTravelAlert travelAlert;

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
    protected CreditCard() {
    }

    public Long getId() {
        return id;
    }


    public LocalDateTime getBlockDate() {
        return block.getBlockedAt();
    }

    public CreditCardStatusEnum getStatus() {
        return status;
    }

    public LocalDate getTravelEndDate() {
        //TODO: improve this with Optional (?)
        if(travelAlert != null){
            return travelAlert.getTravelEndDate();
        }
        return null;
    }

    public String getTravelDestination() {
        //TODO: improve this with Optional (?)
        if(travelAlert != null){
            return travelAlert.getDestination();
        }
        return null;
    }

    public void block(CreditCardBlock block){
        this.block = block;
        loadStatus();
    }

    public void createTravelAlert(CreditCardTravelAlert creditCardTravelAlert) {
        this.travelAlert = creditCardTravelAlert;
    }


    // Do not use this method in responses. Use the following getNumberObfuscated method instead
    public String getNumber() {
        return number;
    }

    public String getNumberObfuscated() {
        String lastFourDigits = number.substring(14);
        return "****-****-****" + lastFourDigits;
    }

    @PostUpdate
    @PostLoad
    public void loadStatus(){
        if(this.block == null){
            this.status = CreditCardStatusEnum.ATIVO;
        }
        else{
            this.status = CreditCardStatusEnum.BLOQUEADO;
        }
    }

}
