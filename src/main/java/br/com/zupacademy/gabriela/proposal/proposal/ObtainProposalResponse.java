package br.com.zupacademy.gabriela.proposal.proposal;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;
import br.com.zupacademy.gabriela.proposal.shared.enums.ProposalStatusEnum;

import java.math.BigDecimal;

public class ObtainProposalResponse {

    private Long id;
    private String document;
    private String email;
    private String name;
    private String address;
    private BigDecimal salary;
    private ProposalStatusEnum status;
    private String creditCardNumber;

    public ObtainProposalResponse(Proposal proposal) {
        this.id = proposal.getId();
        this.document = proposal.getDocument();
        this.email = proposal.getEmail();
        this.name = proposal.getName();
        this.address = proposal.getAddress();
        this.salary = proposal.getSalary();
        this.status = proposal.getStatus();
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

    public String getCreditCardNumber() {
        return creditCardNumber;
    }
}
