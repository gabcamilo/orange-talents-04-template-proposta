package br.com.zupacademy.gabriela.proposal.proposal;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import java.math.BigDecimal;

import static javax.persistence.GenerationType.SEQUENCE;

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

    public Proposal(String document, String email, String name, String address, BigDecimal salary) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    @Deprecated
    public Proposal() {

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
}
