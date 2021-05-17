package br.com.zupacademy.gabriela.proposal.services.CreditCardService;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;
import br.com.zupacademy.gabriela.proposal.proposal.Proposal;
import feign.FeignException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class CreditCardService {
    private final CreditCardClient creditCardClient;

    public CreditCardService(CreditCardClient creditCardClient){
        this.creditCardClient = creditCardClient;
    }

    public CreditCard obtainCreditCard(@Valid Proposal proposal){
        try{
            ObtainCreditCardResponse creditCardResponse = creditCardClient.obtainCreditCard(proposal.getId());
            return creditCardResponse.convert(proposal);

        } catch (FeignException.UnprocessableEntity e){
            System.out.println("Credit card external service presented a problem");
            throw e;
        }
    }
}
