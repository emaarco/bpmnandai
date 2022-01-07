package de.emaarco.bpmnandai.project.domain.service

import org.camunda.bpm.engine.HistoryService
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.history.HistoricProcessInstance
import org.springframework.stereotype.Service

@Service
class ProcessService(
    private val camundaRuntimeService: RuntimeService,
    private val historyService: HistoryService,
) {

    fun getAllHistoricProcessInstances(): List<HistoricProcessInstance> {
        return historyService.createHistoricProcessInstanceQuery().list();
    }

    fun startInstanceOfProcess(processKey: String?, businessKey: String?, vars: Map<String, Any?>) {
        camundaRuntimeService.startProcessInstanceByKey(processKey, businessKey, vars)
    }

}