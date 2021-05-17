package br.com.zupacademy.gabriela.proposal.proposal;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequestMapping("/proposals")
@RestController
public class ProposalReadController {

    private ProposalRepository proposalRepository;

    @Autowired
    public ProposalReadController(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    @GetMapping("/{id}")
    ResponseEntity<ObtainProposalResponse> obtain(@PathVariable Long id) throws NotFoundException {

        Optional<Proposal> proposal = proposalRepository.findById(id);
        if(proposal.isPresent()){
            return ResponseEntity.ok(new ObtainProposalResponse(proposal.get()));
        }
        throw new NotFoundException("/proposals/"+id);
    }
}
