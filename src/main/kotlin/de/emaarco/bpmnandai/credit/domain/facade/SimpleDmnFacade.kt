package de.emaarco.bpmnandai.credit.domain.facade

import de.emaarco.bpmnandai.camunda.domain.ProcessService
import de.emaarco.bpmnandai.credit.domain.service.CreditService
import de.emaarco.bpmnandai.credit.infrastructure.entity.CreditRequestEntity
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class SimpleDmnFacade(
    private val processService: ProcessService,
    private var creditService: CreditService,
) {

    private val log = KotlinLogging.logger {}
    private val processKey = "Prozess_KreditAnfrage"

    fun processLoanRequests() {
        val entities: List<CreditRequestEntity> = creditService.getAllRequests()
        val totalEntries = entities.size
        var currentIteration = 0
        for (request in entities) {
            val businessKey: String = request.requestId
            val processVariables = getProcessVariables(request)
            processService.startInstanceOfProcess(processKey, businessKey, processVariables)
            currentIteration++
            log.info("Provided loan-request '$currentIteration' of '$totalEntries' to engine")
        }
    }

    fun saveResultOfCreditworthinessCheck(requestId: String) {
        val approved = processService.getProcessVariable(requestId, "approved") as String
        val creditRequest = creditService.getSpecificRequest(requestId)
        creditRequest.predictionIsApproved = approved
        creditService.saveCreditRequest(creditRequest)
        log.debug { "Updated credit-request '$requestId' with result of creditworthiness check" }
    }

    private fun getProcessVariables(entity: CreditRequestEntity): Map<String, Any?> {
        val varMap: MutableMap<String, Any?> = HashMap()
        varMap["emi"] = entity.emi
        varMap["city_category"] = entity.cityCategory
        varMap["loan_period"] = entity.loanPeriod
        varMap["monthly_income"] = entity.monthlyIncome
        varMap["interest_rate"] = entity.interestRate
        varMap["employer_category1"] = entity.employerCategory
        varMap["source_category"] = entity.sourceCategory
        varMap["gender"] = entity.gender
        varMap["existing_emi"] = entity.existingEmi
        varMap["loan_amount"] = entity.loanAmount
        varMap["contacted"] = entity.contacted
        varMap["requestId"] = entity.requestId
        return varMap
    }

}