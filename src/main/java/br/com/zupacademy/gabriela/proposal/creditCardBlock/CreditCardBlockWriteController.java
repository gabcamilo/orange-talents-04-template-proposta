package br.com.zupacademy.gabriela.proposal.creditCardBlock;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;
import br.com.zupacademy.gabriela.proposal.creditCard.CreditCardRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api/credit-cards/{creditCardId}/block")
public class CreditCardBlockWriteController {

    private CreditCardBlockRepository creditCardBlockRepository;
    private CreditCardRepository creditCardRepository;

    @Autowired
    public CreditCardBlockWriteController(CreditCardBlockRepository creditCardBlockRepository, CreditCardRepository creditCardRepository) {
        this.creditCardBlockRepository = creditCardBlockRepository;
        this.creditCardRepository = creditCardRepository;
    }

    @PostMapping
    ResponseEntity<?> blockCreditCard (@PathVariable Long creditCardId, HttpServletRequest request) throws NotFoundException {

        Optional<CreditCard> creditCard = creditCardRepository.findById(creditCardId);

        if(creditCard.isEmpty()){
            throw new NotFoundException("/api/credit-cards/"+creditCardId+"/block");
        }

        String ipAddress = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        CreditCardBlock creditCardBlock = new CreditCardBlock(creditCard.get(), ipAddress, userAgent);
        creditCardBlockRepository.save(creditCardBlock);

        return ResponseEntity.ok().build();
    }
}
