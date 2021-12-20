package de.hsa.bpmnandai.project.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduledCreditRequest {

    @Scheduled(fixedRate = 1000)
    public void doSomething() {
        log.info("Starting new process");
    }

}
