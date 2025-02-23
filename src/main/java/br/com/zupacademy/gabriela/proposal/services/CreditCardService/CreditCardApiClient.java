package br.com.zupacademy.gabriela.proposal.services.CreditCardService;

import br.com.zupacademy.gabriela.proposal.creditCard.ObtainCreditCardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "creditCard", url = "${services.creditCardService.url}")
public interface CreditCardApiClient {
    @GetMapping
    ObtainCreditCardResponse obtainCreditCard(@RequestParam("idProposta") Long idProposal);

    @PostMapping("/{id}/bloqueios")
    void blockCreditCard(@PathVariable("id") String id, CreditCardBlockExternalApiRequest request);

    @PostMapping("/{id}/avisos")
    void createCreditCardTravelAlert(@PathVariable("id") String id, CreditCardTravelAlertExternalApiRequest request);

    @PostMapping("/{id}/carteiras")
    void associateWallet(@PathVariable("id") String id, AssociateWalletExternalApiRequest request);
}
