package de.emaarco.bpmnandai.project.domain.facade

import de.emaarco.bpmnandai.project.domain.service.CreditService
import de.emaarco.bpmnandai.project.domain.service.ProcessService
import de.emaarco.bpmnandai.project.infrastructure.entity.BankLoanRequestEntity
import org.camunda.bpm.engine.history.HistoricProcessInstance
import org.springframework.stereotype.Component

@Component
class SimpleDmnFacade(
    private val processService: ProcessService,
    private var creditService: CreditService,
) {

    private val PROCESS_KEY = "Process_KreditAnfrage"

    fun processLoanRequests() {
        val entities: List<BankLoanRequestEntity> = creditService.getAllRequests()
        val totalEntries = entities.size
        var currentIteration = 0
        for (request in entities) {
            val processVariables = getProcessVariables(request)
            val businessKey: String = request.requestId
            processService.startInstanceOfProcess(PROCESS_KEY, businessKey, processVariables)
            currentIteration++
            println("Provided loan-request '${currentIteration}' of '${totalEntries}' to engine")
        }
    }

    fun addHistory() {
        val entities: List<BankLoanRequestEntity> = creditService.getAllRequests()
        var iteration = 0;
        val historic: List<HistoricProcessInstance> = processService.getAllHistoricProcessInstances()
        historic.forEach { instance ->
            val entity: BankLoanRequestEntity? = entities.find { e -> e.requestId == instance.businessKey };
            entity?.predictionIsApproved = instance.endActivityId
            println("Requested result nr. '${++iteration}'")
        };
        creditService.saveCreditRequests(entities)
    }

    private fun getProcessVariables(entity: BankLoanRequestEntity): Map<String, Any?> {
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
        return varMap
    }

}