package de.emaarco.bpmnandai.camunda.domain

import de.emaarco.bpmnandai.shared.exception.ObjectNotFoundException
import org.camunda.bpm.engine.DecisionService
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.TaskService
import org.camunda.bpm.engine.runtime.Execution
import org.camunda.bpm.engine.task.Task
import org.springframework.stereotype.Service

@Service
class ProcessService(
    private val taskService: TaskService,
    private val runtimeService: RuntimeService,
    private val decisionService: DecisionService,
) {

    fun getAllTasks(): List<Task> {
        return taskService.createTaskQuery().list()
    }

    fun getTaskForBusinessKey(businessKey: String, taskId: String): Task {
        return taskService.createTaskQuery()
            .processInstanceBusinessKey(businessKey)
            .taskDefinitionKey(taskId).singleResult()
            ?: throw ObjectNotFoundException("No task found for businessKey '$businessKey' and task '$taskId'")
    }

    fun getProcessVariable(businessKey: String, variableName: String): Any {
        val execution = getExecution(businessKey)
        return runtimeService.getVariable(execution.id, variableName)
            ?: throw ObjectNotFoundException("Variable '$variableName' does not exist")
    }

    fun startInstanceOfProcess(processKey: String?, businessKey: String?, vars: Map<String, Any?>) {
        runtimeService.startProcessInstanceByKey(processKey, businessKey, vars)
    }

    fun finishTask(taskId: String, variables: Map<String, Any>? = null) {
        taskService.complete(taskId, variables)
    }

    fun evaluateDecision(map: Map<String, Any?>) {
        var result = decisionService.evaluateDecisionTableByKey("decision_check_creditworthiness", map)
        println(">>>> " + result.singleResult.keys);
        println(">>> " + result)
    }

    /* ------------------------- private helper methods ------------------------- */

    private fun getExecution(businessKey: String): Execution {
        return runtimeService.createExecutionQuery()
            .processInstanceBusinessKey(businessKey).singleResult()
            ?: throw ObjectNotFoundException("Found no execution with businessKey '$businessKey'")
    }

}