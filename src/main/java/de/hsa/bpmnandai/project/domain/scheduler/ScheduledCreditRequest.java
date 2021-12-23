package de.hsa.bpmnandai.project.domain.scheduler;

import de.hsa.bpmnandai.project.infrastructure.entity.BankLoanRequestEntity;
import de.hsa.bpmnandai.project.infrastructure.repository.BankLoanRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledCreditRequest {

    private final RuntimeService camundaRuntimeService;
    private final HistoryService historyService;
    private final BankLoanRequestRepository bankRepository;

    private final String PROCESS_KEY = "Process_KreditAnfrage";

    @Scheduled(fixedRate = 1000000000)
    public void doSomething() {
        log.info("Starting Process");
        processLoanRequests();
        addHistory();
    }

    private void addHistory() {
        final List<BankLoanRequestEntity> entities = this.bankRepository.findAll();
        final AtomicInteger iteration = new AtomicInteger();
        final List<HistoricProcessInstance> historic = historyService.createHistoricProcessInstanceQuery().list();
        historic.forEach(historicInstance -> {
            final String businessKey = historicInstance.getBusinessKey();
            final Optional<BankLoanRequestEntity> entity = entities.stream().filter(e -> e.getId().equals(businessKey)).findFirst();
            entity.ifPresent(e -> e.setPredictionIsApproved(historicInstance.getEndActivityId()));
            log.info("Requested result nr. '{}'", iteration.incrementAndGet());
        });
        this.bankRepository.saveAll(entities);
    }

    // TODO: Are processed --> Test, whether the prediction is correct!
    private void processLoanRequests() {
        final List<BankLoanRequestEntity> entities = this.bankRepository.findAll();
        final int totalEntries = entities.size();
        int currentIteration = 0;
        log.info("Number of loan-requests: {}", entities.size());
        for (final BankLoanRequestEntity request : entities) {
            final Map<String, Object> processVariables = getProcessVariables(request);
            final String businessKey = request.getId();
            camundaRuntimeService.startProcessInstanceByKey(PROCESS_KEY, businessKey, processVariables);
            currentIteration++;
            log.info("Provided loan-request '{}' of '{}' to engine", currentIteration, totalEntries);
        }
    }

    private Map<String, Object> getProcessVariables(final BankLoanRequestEntity entity) {
        final Map<String, Object> varMap = new HashMap<>();
        varMap.put("emi", entity.getEmi());
        varMap.put("city_category", entity.getCityCategory());
        varMap.put("loan_period", entity.getLoanPeriod());
        varMap.put("monthly_income", entity.getMonthlyIncome());
        varMap.put("interest_rate", entity.getInterestRate());
        varMap.put("employer_category1", entity.getEmployerCategory());
        varMap.put("source_category", entity.getSourceCategory());
        varMap.put("gender", entity.getGender());
        varMap.put("existing_emi", entity.getExistingEmi());
        varMap.put("loan_amount", entity.getLoanAmount());
        varMap.put("contacted", entity.getContacted());
        return varMap;
    }

}
