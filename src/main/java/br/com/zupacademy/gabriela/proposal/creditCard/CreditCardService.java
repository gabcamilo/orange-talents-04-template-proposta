package br.com.zupacademy.gabriela.proposal.creditCard;

import br.com.zupacademy.gabriela.proposal.creditCard.responses.ObtainCreditCardResponse;
import br.com.zupacademy.gabriela.proposal.proposal.Proposal;
import br.com.zupacademy.gabriela.proposal.services.CreditCardService.BlockRequestBody;
import br.com.zupacademy.gabriela.proposal.services.CreditCardService.CreditCardApiClient;
import br.com.zupacademy.gabriela.proposal.shared.enums.CreditCardStatusEnum;
import feign.FeignException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Service
public class CreditCardService {
    private final CreditCardApiClient creditCardApiClient;
    private final CreditCardRepository creditCardRepository;
    private final CreditCardBlockRepository creditCardBlockRepository;

    @Autowired
    public CreditCardService(CreditCardApiClient creditCardApiClient, CreditCardRepository creditCardRepository, CreditCardBlockRepository creditCardBlockRepository){
        this.creditCardApiClient = creditCardApiClient;
        this.creditCardRepository = creditCardRepository;
        this.creditCardBlockRepository = creditCardBlockRepository;
    }

    public CreditCard obtainCreditCard(@Valid Proposal proposal){
        try{
            ObtainCreditCardResponse creditCardResponse = creditCardApiClient.obtainCreditCard(proposal.getId());
            return creditCardResponse.convert(proposal);

        } catch (FeignException.UnprocessableEntity e){
            System.out.println("Credit card external service presented a problem");
            throw e;
        }
    }

    public CreditCard blockCreditCard(Long creditCardId, HttpServletRequest request) throws Exception {

        CreditCard creditCard = testCreditCardExists(creditCardId);
        testCreditCardIsAlreadyBlocked(creditCard);
        blockCreditCardInTheExternalApi(creditCard);
        CreditCardBlock creditCardBlock = makeCreditCardBlock(creditCard, request);
        creditCardBlockRepository.save(creditCardBlock);

        return creditCard;
    }

    private CreditCardBlock makeCreditCardBlock(CreditCard creditCard, HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        return new CreditCardBlock(creditCard, ipAddress, userAgent);
    }

    private CreditCard testCreditCardExists(Long creditCardId) throws NotFoundException {
        Optional<CreditCard> creditCardOptional = creditCardRepository.findById(creditCardId);

        if(creditCardOptional.isEmpty()){
            throw new NotFoundException("/api/credit-cards/"+creditCardId+"/block");
        }
        return creditCardOptional.get();
    }

    private void testCreditCardIsAlreadyBlocked(CreditCard creditCard) throws HttpClientErrorException {
        if(creditCard.getStatus() == CreditCardStatusEnum.BLOQUEADO){
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Credit card already exists");
            // TODO: handle when credit card is blocked in the system but not in the credit card api
        }
    }

    private void blockCreditCardInTheExternalApi(CreditCard creditCard) throws FeignException{
        try{
            //TODO: make "proposta" a constant
            creditCardApiClient.blockCreditCard(
                    creditCard.getNumber(),
                    new BlockRequestBody());
        } catch (FeignException e){
            // TODO: Create a list of pending processes and a task to try running them later
            throw e;
        }
    }
}
