package de.hsa.bpmnandai.project.domain.service;

import de.hsa.bpmnandai.shared.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.runtime.Execution;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessService {

    private final RuntimeService camundaRuntimeService;
    private final HistoryService historyService;

    public List<HistoricProcessInstance> getAllHistoricProcessInstances() {
        return historyService.createHistoricProcessInstanceQuery().list();
    }

    public void startInstanceOfProcess(final String processKey, final String businessKey, final Map<String, Object> vars) {
        this.camundaRuntimeService.startProcessInstanceByKey(processKey, businessKey, vars);
    }

    public void setProcessVariable(final String businessKey, final String variableName, final String variableValue) {
        final Execution execution = getExecutionByBusinessKey(businessKey);
        camundaRuntimeService.setVariable(execution.getId(), businessKey, variableValue);
        log.info("added variable '{}' with value '{}' to execution '{}'", variableName, variableValue, execution.getId());
    }

    private Execution getExecutionByBusinessKey(final String key) {
        return Optional.ofNullable(this.camundaRuntimeService.createExecutionQuery().processInstanceBusinessKey(key).singleResult())
                .orElseThrow(() -> new ObjectNotFoundException(format("No execution found for businessKey '%S'", key)));
    }

}


