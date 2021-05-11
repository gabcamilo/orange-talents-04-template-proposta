package br.com.zupacademy.gabriela.proposal.proposal;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateProposalRequest {

    @NotBlank
    private String document;

    @CPF
    @Nullable
    private String cpf;

    @CNPJ
    @Nullable
    private String cnpj;

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
            String cpf,
            String cnpj,
            String email, String name,
            String address,
            BigDecimal salary) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.document = pickDocument(cpf, cnpj);
    }

    private String pickDocument(String cpf, String cnpj) {
        if (cnpj == null) {
            return cpf;
        } else {
            return cnpj;
        }
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

