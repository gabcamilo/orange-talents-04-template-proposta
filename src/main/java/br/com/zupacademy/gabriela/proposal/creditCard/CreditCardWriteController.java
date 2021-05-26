package br.com.zupacademy.gabriela.proposal.creditCard;

import br.com.zupacademy.gabriela.proposal.creditCard.responses.CreditCardBlockResponse;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/credit-cards/{creditCardId}/block")
public class CreditCardWriteController {

    private final CreditCardService creditCardService;

    @Autowired
    public CreditCardWriteController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping
    ResponseEntity<CreditCardBlockResponse> blockCreditCard (@PathVariable Long creditCardId, HttpServletRequest request) throws Exception {
        CreditCard creditCard = creditCardService.blockCreditCard(creditCardId, request);
        return ResponseEntity.ok(new CreditCardBlockResponse(creditCard));
    }
}
