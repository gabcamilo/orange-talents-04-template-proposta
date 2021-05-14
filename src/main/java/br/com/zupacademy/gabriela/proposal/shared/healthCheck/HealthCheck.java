package br.com.zupacademy.gabriela.proposal.shared.healthCheck;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HealthCheck implements HealthIndicator {

    @Override
    public Health health() {
        Map<String, Object> details = new HashMap<>();
        details.put("version", "1.0.0");
        details.put("description", "Proposal app custom health check");
        details.put("address", "127.0.0.1");
        return Health.up().withDetails(details).build();
    }
}
