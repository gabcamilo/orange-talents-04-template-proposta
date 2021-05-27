package br.com.zupacademy.gabriela.proposal.creditCard.block;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/credit-cards/{creditCardId}/block")
public class CreditCardBlockController {

    private final CreditCardBlockService creditCardBlockService;

    @Autowired
    public CreditCardBlockController(CreditCardBlockService creditCardBlockService) {
        this.creditCardBlockService = creditCardBlockService;
    }

    @PostMapping
    ResponseEntity<CreditCardBlockResponse> blockCreditCard (
            @PathVariable Long creditCardId,
            HttpServletRequest httpServletRequest
    ) throws Exception {
        CreditCard creditCard = creditCardBlockService.blockCreditCard(creditCardId, httpServletRequest);
        return ResponseEntity.ok(new CreditCardBlockResponse(creditCard));
    }
}
