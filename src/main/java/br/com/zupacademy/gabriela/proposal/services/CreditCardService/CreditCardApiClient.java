package br.com.zupacademy.gabriela.proposal.services.CreditCardService;

import br.com.zupacademy.gabriela.proposal.creditCard.ObtainCreditCardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "creditCard", url = "${services.creditCardService.url}")
public interface CreditCardApiClient {
    @GetMapping
    ObtainCreditCardResponse obtainCreditCard(@RequestParam("idProposta") Long idProposal);

    @PostMapping("/{id}/bloqueios")
    void blockCreditCard(@PathVariable("id") String id, BlockRequestBody request);

}
