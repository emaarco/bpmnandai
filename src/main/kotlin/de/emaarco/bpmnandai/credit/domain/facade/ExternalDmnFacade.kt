package de.emaarco.bpmnandai.credit.domain.facade

import de.emaarco.bpmnandai.camunda.domain.ProcessService
import de.emaarco.bpmnandai.credit.domain.service.CreditService
import de.emaarco.bpmnandai.credit.domain.service.DroolsService
import de.emaarco.bpmnandai.credit.infrastructure.entity.BankLoanRequestEntity
import org.springframework.stereotype.Component

@Component
class ExternalDmnFacade(
    private val processService: ProcessService,
    private val creditService: CreditService,
    private val droolsService: DroolsService,
) {

    private val processKey = "Process_KreditAnfrage_Ext"

    fun processLoanRequests() {
        val allRequests: List<BankLoanRequestEntity> = creditService.getAllRequests()
        var iterationCounter = 1
        for (request in allRequests) {
            val businessKey: String = request.requestId
            val vars: MutableMap<String, Any> = HashMap()
            vars["requestId"] = businessKey
            println("Starting instance '${iterationCounter++}' of '${allRequests.size}' with key '${businessKey}'")
            processService.startInstanceOfProcess(processKey, businessKey, vars)
        }
    }

    fun checkCreditworthiness(requestId: String): String {
        val bankLoanRequest: BankLoanRequestEntity = creditService.getSpecificRequest(requestId)
        val result: String = droolsService.evaluateCreditApprovalDecisionModel(bankLoanRequest)
        bankLoanRequest.predictionIsApproved2 = result
        creditService.saveCreditRequest(bankLoanRequest)
        return result
    }
}