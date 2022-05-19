package de.emaarco.bpmnandai.credit.domain.facade

import de.emaarco.bpmnandai.camunda.domain.ProcessService
import de.emaarco.bpmnandai.credit.domain.model.LoanRequest
import de.emaarco.bpmnandai.credit.domain.service.CreditService
import de.emaarco.bpmnandai.credit.domain.service.LoanRequestTestDataService
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class CreditFacade(
    private val processService: ProcessService,
    private val creditService: CreditService,
    private val testDataService: LoanRequestTestDataService,
) {

    private val log = KotlinLogging.logger {}
    private val processKey = "Prozess_KreditAnfrage"

    fun getSpecificLoanRequest(requestId: String): LoanRequest {
        return creditService.getSpecificRequest(requestId)
    }

    fun processLoanRequests() {
        creditService.deleteAllRequests()
        val requests: List<LoanRequest> = testDataService.getAllRequests()
        var currentIteration = 0
        for (request in requests) {
            creditService.saveLoanRequest(request)
            val businessKey: String = request.id
            val processVariables = getProcessVariables(request)
            processService.startInstanceOfProcess(processKey, businessKey, processVariables)
            currentIteration++
            log.info("Provided loan-request '$currentIteration' of '${requests.size}' to engine")
        }
    }

    fun saveResultOfCreditworthinessCheck(requestId: String) {
        val loanRequest = creditService.getSpecificRequest(requestId)
        var map = getProcessVariables(loanRequest)
        processService.evaluateDecision(map);
        val isCreditworthy = processService.getProcessVariable(requestId, "creditworthy") as String
        loanRequest.creditworthy = isCreditworthy
        creditService.saveLoanRequest(loanRequest)
        log.debug { "Updated credit-request '$requestId' with result of creditworthiness check" }
    }

    fun recheckLoanRequest(requestId: String, result: Boolean): LoanRequest {
        val taskId = processService.getTaskForBusinessKey(requestId, "Task_AnfrageNachpruefen").id
        val updatedRequest = creditService.recheckLoanRequest(requestId, result)
        processService.finishTask(taskId, mapOf("recheck_creditworthy" to updatedRequest.creditworthy))
        return updatedRequest
    }

    /* ------------------------- private helper methods ------------------------- */

    private fun getProcessVariables(entity: LoanRequest): Map<String, Any?> {
        val varMap: MutableMap<String, Any?> = HashMap()
        varMap["requestId"] = entity.id
        varMap["gender"] = entity.applicant.gender
        varMap["flag_own_realty"] = entity.applicant.ownsRealty
        varMap["flag_own_car"] = entity.applicant.ownsCar
        varMap["count_children"] = entity.applicant.nrOfChildren
        varMap["annual_income"] = entity.applicant.annualIncome
        varMap["education_type"] = entity.applicant.educationType
        varMap["family_status"] = entity.applicant.familyStatus
        varMap["housing_type"] = entity.applicant.housingType
        varMap["days_employed"] = entity.applicant.daysEmployed
        varMap["job"] = entity.applicant.job
        varMap["age"] = entity.applicant.age
        varMap["credit_amount"] = entity.creditAmount
        return varMap
    }

}