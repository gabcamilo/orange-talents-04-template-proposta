package br.com.zupacademy.gabriela.proposal.scheduledTasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AssociateCardTask {

    @Scheduled(fixedDelayString = "${scheduledTasks.associateCardTask.frequency}")
    private void run (){
        System.out.println("Running AssociateCardTask");
    }
}
