package de.hsa.bpmnandai.project.domain.service;

import de.hsa.bpmnandai.project.infrastructure.entity.BankLoanRequestEntity;
import de.hsa.bpmnandai.project.infrastructure.repository.BankLoanRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kie.dmn.api.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class DroolsService {

    private final DMNRuntime dmnRuntime;
    private final BankLoanRequestRepository bankRepository;

    @Value("${dmn.drools.model.name}")
    private String dmnModelName;

    @Value("${dmn.drools.model.namespace}")
    private String dmnModelNameSpace;

    @Value("${dmn.drools.model.output}")
    private String dmnModelOutput;

    public String evaluateCreditApprovalDecisionModel(final BankLoanRequestEntity request) {
        final DMNModel dmnModel = initializeModel();
        final DMNDecisionResult isApproved = executeDecisionModel(dmnModel, request);
        return mapResult(isApproved);
    }

    private DMNModel initializeModel() {
        return dmnRuntime.getModel(dmnModelNameSpace, dmnModelName);
    }

    private DMNDecisionResult executeDecisionModel(final DMNModel dmnModel, final BankLoanRequestEntity request) {
        final DMNContext context = initializeContext(request);
        final DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, context);
        return dmnResult.getDecisionResultByName(dmnModelOutput);
    }

    private String mapResult(final DMNDecisionResult result) {
        final Object rawResult = result.getResult();
        if (Objects.isNull(rawResult)) {
            return "UNKNOWN";
        } else {
            return (String) rawResult;
        }
    }

    private DMNContext initializeContext(final BankLoanRequestEntity entity) {
        final DMNContext dmnContext = dmnRuntime.newContext();
        dmnContext.set("Kreditanfrage", entity);
        return dmnContext;
    }

}
