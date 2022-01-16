package de.emaarco.bpmnandai.credit.domain.facade

import de.emaarco.bpmnandai.camunda.domain.ProcessService
import de.emaarco.bpmnandai.credit.domain.model.LoanRequest
import de.emaarco.bpmnandai.credit.domain.service.CreditService
import de.emaarco.bpmnandai.credit.domain.service.DroolsService
import de.emaarco.bpmnandai.credit.domain.service.LoanRequestTestDataService
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class CreditPmmlFacade(
    private val processService: ProcessService,
    private val creditService: CreditService,
    private val droolsService: DroolsService,
    private val testDataService: LoanRequestTestDataService,
) {

    private val log = KotlinLogging.logger {}
    private val processKey = "Process_KreditAnfrage_Ext"

    fun processLoanRequests() {
        creditService.deleteAllRequests()
        val allRequests: List<LoanRequest> = testDataService.getAllRequests()
        var iterationCounter = 1
        for (request in allRequests) {
            creditService.saveLoanRequest(request)
            val businessKey: String = request.id
            val vars: MutableMap<String, Any> = HashMap()
            vars["requestId"] = businessKey
            log.info("Starting instance '${iterationCounter++}' of '${allRequests.size}' with key '${businessKey}'")
            processService.startInstanceOfProcess(processKey, businessKey, vars)
        }
    }

    fun checkCreditworthiness(requestId: String): String {
        val bankLoanRequest: LoanRequest = creditService.getSpecificRequest(requestId)
        val result: String = droolsService.evaluateCreditApprovalDecisionModel(bankLoanRequest)
        bankLoanRequest.creditworthy = result
        creditService.saveLoanRequest(bankLoanRequest)
        return result
    }

}