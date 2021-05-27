package br.com.zupacademy.gabriela.proposal.creditCard.block;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;
import br.com.zupacademy.gabriela.proposal.creditCard.CreditCardService;
import br.com.zupacademy.gabriela.proposal.services.CreditCardService.CreditCardBlockExternalApiRequest;
import br.com.zupacademy.gabriela.proposal.services.CreditCardService.CreditCardApiClient;
import br.com.zupacademy.gabriela.proposal.shared.enums.CreditCardStatusEnum;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;

@Service
public class CreditCardBlockService {

    private final CreditCardBlockRepository creditCardBlockRepository;
    private final CreditCardService creditCardService;
    protected final CreditCardApiClient creditCardApiClient;

    @Autowired
    public CreditCardBlockService(CreditCardBlockRepository creditCardBlockRepository, CreditCardService creditCardService, CreditCardApiClient creditCardApiClient) {
        this.creditCardBlockRepository = creditCardBlockRepository;
        this.creditCardService = creditCardService;
        this.creditCardApiClient = creditCardApiClient;
    }

    public CreditCard blockCreditCard(
            Long creditCardId,
            HttpServletRequest httpServletRequest
    ) throws Exception {

        CreditCard creditCard = creditCardService.obtainCreditCard(creditCardId);
        testCreditCardIsAlreadyBlocked(creditCard);
        blockCreditCardInTheExternalApi(creditCard);
        CreditCardBlock creditCardBlock = makeCreditCardBlock(creditCard, httpServletRequest);
        creditCardBlockRepository.save(creditCardBlock);

        return creditCard;
    }

    private void testCreditCardIsAlreadyBlocked(CreditCard creditCard) throws HttpClientErrorException {
        if(creditCard.getStatus() == CreditCardStatusEnum.BLOQUEADO){
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Credit card is already blocked");
            // TODO: handle when credit card is blocked in the system but not in the credit card api
        }
    }

    private void blockCreditCardInTheExternalApi(CreditCard creditCard) throws FeignException {
        //TODO: make "proposta" a constant
        creditCardApiClient.blockCreditCard(
                creditCard.getNumber(),
                new CreditCardBlockExternalApiRequest()
        );
    }

    private CreditCardBlock makeCreditCardBlock(CreditCard creditCard, HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        return new CreditCardBlock(creditCard, ipAddress, userAgent);
    }
}
