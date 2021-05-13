package br.com.zupacademy.gabriela.proposal.services.RestrictionAnalysisService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "restrictionAnalysis", url = "${restrictionAnalysisApiUrl}")
public interface RestrictionAnalysisClient {
    @PostMapping
    RestrictionAnalysisResponse restrictionAnalysis(RestrictionAnalysisRequest restrictionAnalysisRequest);
}
