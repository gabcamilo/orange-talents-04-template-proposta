package br.com.zupacademy.gabriela.proposal.proposal;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;
import br.com.zupacademy.gabriela.proposal.shared.enums.ProposalStatusEnum;

import java.math.BigDecimal;

public class ObtainProposalResponse {

    private final Long id;
    private final String document;
    private final String email;
    private final String name;
    private final String address;
    private final BigDecimal salary;
    private final ProposalStatusEnum status;
    private final Long creditCardId;
    private final String creditCardNumber;

    public ObtainProposalResponse(Proposal proposal) {
        this.id = proposal.getId();
        this.document = proposal.getDocument();
        this.email = proposal.getEmail();
        this.name = proposal.getName();
        this.address = proposal.getAddress();
        this.salary = proposal.getSalary();
        this.status = proposal.getStatus();
        this.creditCardId = proposal.getCreditCardId();
        this.creditCardNumber = proposal.getCreditCardNumber();
    }

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

    public Long getCreditCardId() { return creditCardId; }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

}
