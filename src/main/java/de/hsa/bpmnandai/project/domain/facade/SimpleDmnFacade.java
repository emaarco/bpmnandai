package de.hsa.bpmnandai.project.domain.facade;

import de.hsa.bpmnandai.project.domain.service.CreditService;
import de.hsa.bpmnandai.project.domain.service.ProcessService;
import de.hsa.bpmnandai.project.infrastructure.entity.BankLoanRequestEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@RequiredArgsConstructor
public class SimpleDmnFacade {

    private final ProcessService processService;
    private final CreditService creditService;

    private final String PROCESS_KEY = "Process_KreditAnfrage";

    public void processLoanRequests() {
        final List<BankLoanRequestEntity> entities = this.creditService.getAllRequests();
        final int totalEntries = entities.size();
        int currentIteration = 0;
        log.info("Number of loan-requests: {}", entities.size());
        for (final BankLoanRequestEntity request : entities) {
            final Map<String, Object> processVariables = getProcessVariables(request);
            final String businessKey = request.getId();
            processService.startInstanceOfProcess(PROCESS_KEY, businessKey, processVariables);
            currentIteration++;
            log.info("Provided loan-request '{}' of '{}' to engine", currentIteration, totalEntries);
        }
    }

    public void addHistory() {
        final List<BankLoanRequestEntity> entities = this.creditService.getAllRequests();
        final AtomicInteger iteration = new AtomicInteger();
        final List<HistoricProcessInstance> historic = processService.getAllHistoricProcessInstances();
        historic.forEach(historicInstance -> {
            final String businessKey = historicInstance.getBusinessKey();
            final Optional<BankLoanRequestEntity> entity = entities.stream().filter(e -> e.getId().equals(businessKey)).findFirst();
            entity.ifPresent(e -> e.setPredictionIsApproved(historicInstance.getEndActivityId()));
            log.info("Requested result nr. '{}'", iteration.incrementAndGet());
        });
        this.creditService.saveCreditRequests(entities);
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
