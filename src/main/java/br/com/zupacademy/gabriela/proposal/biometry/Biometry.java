package br.com.zupacademy.gabriela.proposal.biometry;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class Biometry {

    @Id
    @SequenceGenerator(
            name = "biometry_id_sequence",
            sequenceName = "biometry_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE, generator = "biometry_id_sequence")
    @Column(updatable = false)
    private Long id;

    private byte[] fingerprint;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "credit_card_biometry_fk"))
    private CreditCard creditCard;

    @Deprecated
    protected Biometry() { }

    public Biometry(byte[] fingerprint, CreditCard creditCard) {
        this.fingerprint = fingerprint;
        this.creditCard = creditCard;
    }

    public Long getId() {
        return id;
    }

    public byte[] getFingerprint() {
        return fingerprint;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }
}
