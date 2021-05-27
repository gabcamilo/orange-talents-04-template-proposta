package br.com.zupacademy.gabriela.proposal.creditCard.travelAlert;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;
import br.com.zupacademy.gabriela.proposal.creditCard.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/credit-cards/{creditCardId}/travel-alert")
public class CreditCardTravelAlertController {

    private final CreditCardTravelAlertService creditTravelAlertCardService;

    @Autowired
    public CreditCardTravelAlertController(CreditCardTravelAlertService creditTravelAlertCardService) {
        this.creditTravelAlertCardService = creditTravelAlertCardService;
    }

    @PostMapping
    ResponseEntity<CreditCardTravelAlertResponse> createCreditCardTravelAlert (
            @PathVariable Long creditCardId,
            @RequestBody CreditCardTravelAlertRequest request,
            HttpServletRequest httpServletRequest
    ) throws Exception {

        CreditCard creditCard = creditTravelAlertCardService.createCreditCardTravelAlert(creditCardId, request, httpServletRequest);
        return ResponseEntity.ok(new CreditCardTravelAlertResponse(creditCard));
    }
}
