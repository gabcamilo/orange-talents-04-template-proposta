package br.com.zupacademy.gabriela.proposal.creditCard.travelAlert;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class CreditCardTravelAlert {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(
            foreignKey = @ForeignKey(name = "credit_card_travel_alert_fk")
    )
    private CreditCard creditCard;
    private String destination;
    private LocalDate travelEndDate;
    private String clientIpAddress;
    private String clientUserAgent;
    private LocalDateTime createdAt;


    public CreditCardTravelAlert(CreditCard creditCard, String destination, LocalDate travelEndDate, String clientIpAddress, String clientUserAgent) {
        this.id = creditCard.getId();
        this.creditCard = creditCard;
        this.destination = destination;
        this.travelEndDate = travelEndDate;
        this.clientIpAddress = clientIpAddress;
        this.clientUserAgent = clientUserAgent;
        this.createdAt = LocalDateTime.now();
    }

    @Deprecated
    protected CreditCardTravelAlert() { }

    public LocalDate getTravelEndDate() {
        return travelEndDate;
    }

    public String getDestination() {
        return destination;
    }

    @PostPersist
    private void setTravelAlertToCreditCard() {
        creditCard.createTravelAlert(this);
    }
}
