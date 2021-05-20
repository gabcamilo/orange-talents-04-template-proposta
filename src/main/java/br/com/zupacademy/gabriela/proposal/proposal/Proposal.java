package br.com.zupacademy.gabriela.proposal.proposal;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;
import br.com.zupacademy.gabriela.proposal.services.RestrictionAnalysisService.RestrictionAnalysisService;
import br.com.zupacademy.gabriela.proposal.shared.enums.ProposalStatusEnum;

import javax.persistence.*;

import java.math.BigDecimal;

import static javax.persistence.GenerationType.SEQUENCE;

@Table(
        uniqueConstraints = @UniqueConstraint(name = "proposal_document_unique", columnNames = "document")
)
@Entity
public class Proposal {
    @Id
    @SequenceGenerator(
            name = "proposal_id_sequence",
            sequenceName = "proposal_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE, generator = "proposal_id_sequence")
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String document;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private BigDecimal salary;

    @Column(nullable = false)
    private ProposalStatusEnum status;

    @OneToOne(
            mappedBy = "proposal",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )

    private CreditCard creditCard;

    public Proposal(String document, String email, String name, String address, BigDecimal salary) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.status = ProposalStatusEnum.NAO_ANALISADO;
    }

    @Deprecated
    public Proposal() { }

    public Long getId() {
        return id;
    }

    public String getDocument() {
        return document;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public ProposalStatusEnum getStatus() {
        return status;
    }

    public String getCreditCardNumberObfuscated() {
        if(creditCard == null){
            return "";
        }
        String lastFourDigits = creditCard.getNumber().substring(14);
        return "****-****-****" + lastFourDigits;
    }

    public Long getCreditCardId() {

        if(creditCard == null){
            return null;
        }
        return creditCard.getId();
    }

    public void saveProposalRestrictionStatusFromExternalService(
            ProposalRepository proposalRepository,
            RestrictionAnalysisService restrictionAnalysisService
    ) {
        status = restrictionAnalysisService.getRestrictionAnalysis(this);
        proposalRepository.save(this);
    }

}
