package br.com.zupacademy.gabriela.proposal.services.CreditCardService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "creditCard", url = "${services.creditCardService.url}")
public interface CreditCardClient {
    @GetMapping
    ObtainCreditCardResponse obtainCreditCard(@RequestParam("idProposta") Long idProposal);
}
