package de.emaarco.bpmnandai.camunda.domain

import de.emaarco.bpmnandai.shared.exception.ObjectNotFoundException
import org.camunda.bpm.engine.HistoryService
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.TaskService
import org.camunda.bpm.engine.history.HistoricProcessInstance
import org.camunda.bpm.engine.runtime.Execution
import org.camunda.bpm.engine.task.Task
import org.springframework.stereotype.Service

@Service
class ProcessService(
    private val taskService: TaskService,
    private val camundaRuntimeService: RuntimeService,
    private val historyService: HistoryService,
) {

    fun getAllHistoricProcessInstances(): List<HistoricProcessInstance> {
        return historyService.createHistoricProcessInstanceQuery().list();
    }

    fun startInstanceOfProcess(processKey: String?, businessKey: String?, vars: Map<String, Any?>) {
        camundaRuntimeService.startProcessInstanceByKey(processKey, businessKey, vars)
    }

    fun getAllTasks(): List<Task> {
        return taskService.createTaskQuery().list()
    }

    fun getTaskForBusinessKey(businessKey: String): Task {
        return taskService.createTaskQuery().processInstanceBusinessKey(businessKey).singleResult()
            ?: throw ObjectNotFoundException("No task found for businessKey '${businessKey}'")
    }

    fun finishTask(taskId: String, variables: Map<String, Any>? = null) {
        taskService.complete(taskId, variables)
    }

    fun getProcessVariable(businessKey: String, variableName: String): Any {
        val execution = getExecution(businessKey)
        return camundaRuntimeService.getVariable(execution.id, variableName)
            ?: throw ObjectNotFoundException("Variable '$variableName' does not exist");
    }

    private fun getExecution(businessKey: String): Execution {
        return camundaRuntimeService.createExecutionQuery()
            .processInstanceBusinessKey(businessKey).singleResult()
            ?: throw ObjectNotFoundException("Found no execution with businessKey '$businessKey'")
    }

}