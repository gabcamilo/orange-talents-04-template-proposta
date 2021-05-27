package br.com.zupacademy.gabriela.proposal.creditCard.travelAlert;

import br.com.zupacademy.gabriela.proposal.creditCard.CreditCard;
import br.com.zupacademy.gabriela.proposal.creditCard.CreditCardService;
import br.com.zupacademy.gabriela.proposal.services.CreditCardService.CreditCardApiClient;
import br.com.zupacademy.gabriela.proposal.services.CreditCardService.CreditCardTravelAlertExternalApiRequest;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class CreditCardTravelAlertService {

    private final CreditCardTravelAlertRepository creditCardTravelAlertRepository;
    private final CreditCardService creditCardService;
    protected final CreditCardApiClient creditCardApiClient;

    @Autowired
    public CreditCardTravelAlertService(CreditCardTravelAlertRepository creditCardTravelAlertRepository, CreditCardService creditCardService, CreditCardApiClient creditCardApiClient) {
        this.creditCardTravelAlertRepository = creditCardTravelAlertRepository;
        this.creditCardService = creditCardService;
        this.creditCardApiClient = creditCardApiClient;
    }

    public CreditCard createCreditCardTravelAlert(
            Long creditCardId,
            CreditCardTravelAlertRequest request,
            HttpServletRequest httpServletRequest
    ) throws NotFoundException {

        CreditCard creditCard = creditCardService.obtainCreditCard(creditCardId);
        CreditCardTravelAlert creditCardTravelAlert =
                makeCreditCardTravelAlertFromRequest(request, httpServletRequest, creditCard);
        createCreditCardTravelAlertInTheExternalApi(creditCardTravelAlert, creditCard);
        creditCardTravelAlertRepository.save(creditCardTravelAlert);

        return creditCard;
    }

    private void createCreditCardTravelAlertInTheExternalApi(CreditCardTravelAlert creditCardTravelAlert, CreditCard creditCard) {

        String creditCardNumber = creditCard.getNumber();
        CreditCardTravelAlertExternalApiRequest creditCardTravelAlertExternalApiRequest =
                new CreditCardTravelAlertExternalApiRequest(
                    creditCardTravelAlert.getDestination(),
                    creditCardTravelAlert.getTravelEndDate()
                );

        creditCardApiClient.createCreditCardTravelAlert(creditCardNumber, creditCardTravelAlertExternalApiRequest);
    }

    private CreditCardTravelAlert makeCreditCardTravelAlertFromRequest(
            CreditCardTravelAlertRequest request,
            HttpServletRequest httpServletRequest,
            CreditCard creditCard) {

        String ipAddress = httpServletRequest.getRemoteAddr();
        String userAgent = httpServletRequest.getHeader("User-Agent");
        return request.convert(creditCard, ipAddress, userAgent);
    }
}
