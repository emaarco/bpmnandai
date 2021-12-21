package de.hsa.bpmnandai.project.domain.scheduler;

import de.hsa.bpmnandai.project.infrastructure.BankLoanRequestEntity;
import de.hsa.bpmnandai.project.infrastructure.repository.BankLoanRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledCreditRequest {

    private final RuntimeService camundaRuntimeService;
    private final BankLoanRequestRepository bankRepository;

    @Scheduled(fixedRate = 10000)
    public void doSomething() {
        log.info("Starting new process");
        processLoanRequests();
    }

    private void processLoanRequests() {
        final List<BankLoanRequestEntity> entities = this.bankRepository.findAll();
        log.info("Number of loan-requests: {}", entities.size());
    }

}
