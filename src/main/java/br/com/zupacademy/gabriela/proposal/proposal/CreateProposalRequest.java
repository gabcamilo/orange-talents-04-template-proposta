package br.com.zupacademy.gabriela.proposal.proposal;

import br.com.zupacademy.gabriela.proposal.shared.validation.CpfOrCnpj;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateProposalRequest {

    @CpfOrCnpj
    @NotBlank
    private String document;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @DecimalMin("0")
    @NotNull
    private BigDecimal salary;

    public CreateProposalRequest(
            String document,
            String email,
            String name,
            String address,
            BigDecimal salary) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.document = document;
    }

    public Proposal convert() {
        return new Proposal(
                this.document,
                this.email,
                this.name,
                this.address,
                this.salary);
    }
}

