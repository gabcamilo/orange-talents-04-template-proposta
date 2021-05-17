package br.com.zupacademy.gabriela.proposal.CreditCard;

import br.com.zupacademy.gabriela.proposal.proposal.Proposal;
import br.com.zupacademy.gabriela.proposal.proposal.ProposalRepository;
import br.com.zupacademy.gabriela.proposal.services.CreditCardService.CreditCardService;
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

    @Autowired
    public AssociateCreditCardTask(
            ProposalRepository proposalRepository,
            CreditCardRepository creditCardRepository,
            CreditCardService creditCardService) {
        this.proposalRepository = proposalRepository;
        this.creditCardRepository = creditCardRepository;
        this.creditCardService = creditCardService;
    }

    @Scheduled(fixedDelayString = "${creditCard.associateCardTask.frequency}")
    private void run() {
        List<Proposal> eligibleProposals = proposalRepository.findByStatusAndCreditCardNull(ProposalStatusEnum.ELEGIVEL);
        eligibleProposals.forEach(
                proposal -> {
                    CreditCard creditCard = creditCardService.obtainCreditCard(proposal);
                    creditCardRepository.save(creditCard);
                }
        );
    }
}
