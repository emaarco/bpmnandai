package de.emaarco.bpmnandai.project.domain.service

import de.emaarco.bpmnandai.project.infrastructure.entity.BankLoanRequestEntity
import org.kie.dmn.api.core.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class DroolsService(private val dmnRuntime: DMNRuntime) {

    @Value("\${dmn.drools.model.name}")
    private lateinit var dmnModelName: String

    @Value("\${dmn.drools.model.namespace}")
    private lateinit var dmnModelNameSpace: String

    @Value("\${dmn.drools.model.output}")
    private val dmnModelOutput: String? = null

    fun evaluateCreditApprovalDecisionModel(request: BankLoanRequestEntity): String {
        val dmnModel: DMNModel = initializeModel()
        val isApproved: DMNDecisionResult = executeDecisionModel(dmnModel, request)
        return mapResult(isApproved)
    }

    private fun initializeModel(): DMNModel {
        return dmnRuntime.getModel(dmnModelNameSpace, dmnModelName)
    }

    private fun executeDecisionModel(dmnModel: DMNModel, request: BankLoanRequestEntity): DMNDecisionResult {
        val context: DMNContext = initializeContext(request)
        val dmnResult: DMNResult = dmnRuntime.evaluateAll(dmnModel, context)
        return dmnResult.getDecisionResultByName(dmnModelOutput)
    }

    private fun mapResult(result: DMNDecisionResult): String {
        val rawResult: Any = result.result
        return if (Objects.isNull(rawResult)) {
            "UNKNOWN"
        } else {
            rawResult as String
        }
    }

    private fun initializeContext(entity: BankLoanRequestEntity): DMNContext {
        val dmnContext: DMNContext = dmnRuntime.newContext()
        dmnContext.set("Kreditanfrage", entity)
        return dmnContext
    }

}