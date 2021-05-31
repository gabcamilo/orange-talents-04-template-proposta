package br.com.zupacademy.gabriela.proposal.creditCard.wallet;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;
import br.com.zupacademy.gabriela.proposal.creditCard.CreditCardService;
import br.com.zupacademy.gabriela.proposal.services.CreditCardService.AssociateWalletExternalApiRequest;
import br.com.zupacademy.gabriela.proposal.services.CreditCardService.CreditCardApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/credit-cards/{creditCardId}/wallet/{wallet}")
public class CreditCardWalletController {

    private final CreditCardService creditCardService;
    private final CreditCardApiClient creditCardApiClient;
    private final CreditCardWalletRepository cardWalletRepository;

    @Autowired
    public CreditCardWalletController(CreditCardService creditCardService, CreditCardApiClient creditCardApiClient, CreditCardWalletRepository cardWalletRepository) {
        this.creditCardService = creditCardService;
        this.creditCardApiClient = creditCardApiClient;
        this.cardWalletRepository = cardWalletRepository;
    }

    @PostMapping
    ResponseEntity<AssociateWalletResponse> associateWallet(
            @PathVariable Long creditCardId,
            @PathVariable Wallets wallet,
            @Valid @RequestBody AssociateWalletRequest request) throws Exception {

        CreditCard creditCard = creditCardService.obtainCreditCard(creditCardId);

        CreditCardWallet creditCardWallet = request.convert(creditCard, wallet);
        AssociateWalletExternalApiRequest associateWalletExternalApiRequest = new AssociateWalletExternalApiRequest(creditCardWallet.getEmail(), wallet);

        String number = creditCard.getNumber();

        creditCardApiClient.associateWallet(number, associateWalletExternalApiRequest);

        cardWalletRepository.save(creditCardWallet);

        return ResponseEntity.ok(new AssociateWalletResponse(creditCardWallet));
    }
}
