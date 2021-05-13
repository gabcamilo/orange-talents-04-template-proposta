package br.com.zupacademy.gabriela.proposal.services.RestrictionAnalysisService;

import br.com.zupacademy.gabriela.proposal.proposal.CreateProposalRequest;
import br.com.zupacademy.gabriela.proposal.proposal.Proposal;
import br.com.zupacademy.gabriela.proposal.shared.enums.ProposalStatusEnum;
import feign.FeignException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class RestrictionAnalysisService {
    private final RestrictionAnalysisClient restrictionAnalysisClient;

    public RestrictionAnalysisService(RestrictionAnalysisClient restrictionAnalysisClient){
        this.restrictionAnalysisClient = restrictionAnalysisClient;
    }

    public ProposalStatusEnum getRestrictionAnalysis(@Valid Proposal proposal){
        try{
           restrictionAnalysisClient.restrictionAnalysis(new RestrictionAnalysisRequest(proposal));
           return ProposalStatusEnum.ELEGIVEL;
        } catch (FeignException.UnprocessableEntity e){
            return ProposalStatusEnum.NAO_ELEGIVEL;
        }
    }
}
