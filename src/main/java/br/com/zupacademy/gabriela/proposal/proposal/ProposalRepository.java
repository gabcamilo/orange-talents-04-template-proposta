package br.com.zupacademy.gabriela.proposal.proposal;

import br.com.zupacademy.gabriela.proposal.shared.enums.ProposalStatusEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProposalRepository extends CrudRepository<Proposal, Long> {
    List<Proposal> findByDocument(String document);

    List<Proposal> findByStatusAndCreditCardNull(ProposalStatusEnum status);
}
