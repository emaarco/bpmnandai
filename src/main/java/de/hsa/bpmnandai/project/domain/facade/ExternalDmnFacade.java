package de.hsa.bpmnandai.project.domain.facade;

import de.hsa.bpmnandai.project.domain.service.CreditService;
import de.hsa.bpmnandai.project.domain.service.DroolsService;
import de.hsa.bpmnandai.project.domain.service.ProcessService;
import de.hsa.bpmnandai.project.infrastructure.entity.BankLoanRequestEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExternalDmnFacade {

    private final ProcessService processService;
    private final CreditService creditService;
    private final DroolsService droolsService;

    private final String PROCESS_KEY = "Process_KreditAnfrage_Ext";

    public void processLoanRequests() {
        final List<BankLoanRequestEntity> allRequests = creditService.getAllRequests();
        int iterationCounter = 1;
        for (final BankLoanRequestEntity request : allRequests) {
            final String businessKey = request.getId();
            final Map<String, Object> vars = Map.of("requestId", businessKey);
            log.info("Starting instance '{}' of '{}' with key '{}'", iterationCounter++, allRequests.size(), businessKey);
            processService.startInstanceOfProcess(PROCESS_KEY, businessKey, vars);
        }
    }

    public String checkCreditworthiness(final String requestId) {
        final BankLoanRequestEntity bankLoanRequest = this.creditService.getSpecificRequest(requestId);
        final String result = droolsService.evaluateCreditApprovalDecisionModel(bankLoanRequest);
        bankLoanRequest.setPredictionIsApproved2(result);
        creditService.saveCreditRequest(bankLoanRequest);
        return result;
    }

}
