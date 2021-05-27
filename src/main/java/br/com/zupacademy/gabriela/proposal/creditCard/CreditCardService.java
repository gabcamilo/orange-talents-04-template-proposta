package br.com.zupacademy.gabriela.proposal.creditCard;

import br.com.zupacademy.gabriela.proposal.proposal.Proposal;
import br.com.zupacademy.gabriela.proposal.services.CreditCardService.CreditCardApiClient;
import feign.FeignException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class CreditCardService {
    protected final CreditCardApiClient creditCardApiClient;
    protected final CreditCardRepository creditCardRepository;

    @Autowired
    public CreditCardService(CreditCardApiClient creditCardApiClient, CreditCardRepository creditCardRepository){
        this.creditCardApiClient = creditCardApiClient;
        this.creditCardRepository = creditCardRepository;
    }

    public CreditCard obtainCreditCardFromExternalService(@Valid Proposal proposal){
        try{
            ObtainCreditCardResponse creditCardResponse = creditCardApiClient.obtainCreditCard(proposal.getId());
            return creditCardResponse.convert(proposal);

        } catch (FeignException.UnprocessableEntity e){
            System.out.println("Credit card external service presented a problem");
            throw e;
        }
    }

    public CreditCard obtainCreditCard(Long creditCardId) throws NotFoundException {
        Optional<CreditCard> creditCardOptional = creditCardRepository.findById(creditCardId);

        if(creditCardOptional.isEmpty()){
            throw new NotFoundException("/api/credit-cards/"+creditCardId+"/block");
        }
        return creditCardOptional.get();
    }

    public String obtainFullNumber(CreditCard creditCard){
        return creditCard.getNumber();
    }
}
