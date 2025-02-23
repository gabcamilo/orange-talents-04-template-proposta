package br.com.zupacademy.gabriela.proposal.creditCard;

import io.opentracing.Span;
import io.opentracing.Tracer;
import br.com.zupacademy.gabriela.proposal.proposal.Proposal;
import br.com.zupacademy.gabriela.proposal.proposal.ProposalRepository;
import br.com.zupacademy.gabriela.proposal.shared.enums.ProposalStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssociateCreditCardTask {

    private final ProposalRepository proposalRepository;
    private final CreditCardRepository creditCardRepository;
    private final CreditCardService creditCardService;
    private final Tracer tracer;

    @Autowired
    public AssociateCreditCardTask(
            ProposalRepository proposalRepository,
            CreditCardRepository creditCardRepository,
            CreditCardService creditCardService, Tracer tracer) {
        this.proposalRepository = proposalRepository;
        this.creditCardRepository = creditCardRepository;
        this.creditCardService = creditCardService;
        this.tracer = tracer;
    }

    @Scheduled(fixedDelayString = "${creditCard.associateCardTask.frequency}")
    private void run() {
        List<Proposal> eligibleProposals = proposalRepository.findByStatusAndCreditCardNull(ProposalStatusEnum.ELEGIVEL);
        eligibleProposals.forEach(
                proposal -> {
                    Span activeSpan = tracer.activeSpan();
                    activeSpan.setBaggageItem("user.email", "orange@talents.com");
                    CreditCard creditCard = creditCardService.obtainCreditCardFromExternalService(proposal);
                    creditCardRepository.save(creditCard);
                }
        );
    }
}
