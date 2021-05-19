package br.com.zupacademy.gabriela.proposal.proposal;

import br.com.zupacademy.gabriela.proposal.services.RestrictionAnalysisService.RestrictionAnalysisService;
import br.com.zupacademy.gabriela.proposal.shared.enums.ProposalStatusEnum;
import br.com.zupacademy.gabriela.proposal.shared.exception.FieldErrorException;
import org.bouncycastle.asn1.isismtt.x509.Restriction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequestMapping("/proposals")
@RestController
public class ProposalWriteController {

    private final ProposalRepository proposalRepository;
    private final RestrictionAnalysisService restrictionAnalysisService;

    @Autowired
    public ProposalWriteController(ProposalRepository proposalRepository, RestrictionAnalysisService restrictionAnalysisService) {
        this.proposalRepository = proposalRepository;
        this.restrictionAnalysisService = restrictionAnalysisService;
    }

    @PostMapping
    public ResponseEntity<CreateProposalResponse> create(@RequestBody @Valid CreateProposalRequest request) {
        Proposal proposal = request.convert();

        String document = proposal.getDocument();
        final List<Proposal> proposalsWithThisDocument = proposalRepository.findByDocument(document);
        if(proposalsWithThisDocument.size() != 0){
            throw new FieldErrorException("document", "This document already exists", HttpStatus.UNPROCESSABLE_ENTITY );
        }

        proposalRepository.save(proposal);

        proposal.saveProposalRestrictionStatusFromExternalService(proposalRepository, restrictionAnalysisService);
        // TODO: Rollback proposal save when there is a error on status save

        return ResponseEntity.created(URI.create("/proposals")).body(new CreateProposalResponse(proposal));
    }
}
