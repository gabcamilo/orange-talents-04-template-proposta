package br.com.zupacademy.gabriela.proposal.proposal;

import br.com.zupacademy.gabriela.proposal.shared.exception.FieldErrorException;
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

@RequestMapping("/proposals")
@RestController
public class ProposalWriteController {

    private ProposalRepository proposalRepository;

    @Autowired
    public ProposalWriteController(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    @PostMapping
    public ResponseEntity<CreateProposalResponse> create(@RequestBody @Valid CreateProposalRequest request) {
        Proposal proposal = request.convert();

        String document = proposal.getDocument();
        final List<Proposal> proposalsWithThisDocument = proposalRepository.findByDocument(document);
        if(proposalsWithThisDocument.size() != 0){
            throw new FieldErrorException("This document already exists", HttpStatus.UNPROCESSABLE_ENTITY, "document");
        }

        proposalRepository.save(proposal);
        return ResponseEntity.created(URI.create("/proposals")).body(new CreateProposalResponse(proposal));
    }
}
