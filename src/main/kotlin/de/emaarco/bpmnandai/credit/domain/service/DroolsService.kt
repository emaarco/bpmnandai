package de.emaarco.bpmnandai.credit.domain.service

import de.emaarco.bpmnandai.credit.infrastructure.entity.CreditRequestEntity
import org.kie.dmn.api.core.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class DroolsService(private val dmnRuntime: DMNRuntime) {

    @Value("\${dmn.drools.model.name}")
    private lateinit var dmnModelName: String

    @Value("\${dmn.drools.model.namespace}")
    private lateinit var dmnModelNameSpace: String

    @Value("\${dmn.drools.model.output}")
    private val dmnModelOutput: String? = null

    fun evaluateCreditApprovalDecisionModel(request: CreditRequestEntity): String {
        val dmnModel: DMNModel = initializeModel()
        val isApproved = executeDecisionModel(dmnModel, request)
        return mapResult(isApproved)
    }

    /* ------------------------- private helper methods ------------------------- */

    private fun initializeModel(): DMNModel {
        return dmnRuntime.getModel(dmnModelNameSpace, dmnModelName)
    }

    private fun executeDecisionModel(dmnModel: DMNModel, request: CreditRequestEntity): DMNDecisionResult? {
        val context: DMNContext = initializeContext(request)
        val dmnResult: DMNResult = dmnRuntime.evaluateAll(dmnModel, context)
        return dmnResult.getDecisionResultByName(dmnModelOutput)
    }

    private fun mapResult(result: DMNDecisionResult?): String {
        return if ((result == null) || (result.result == null)) {
            "UNKNOWN"
        } else {
            result.result as String
        }
    }

    private fun initializeContext(entity: CreditRequestEntity): DMNContext {
        val dmnContext: DMNContext = dmnRuntime.newContext()
        dmnContext.set("Kreditanfrage", entity)
        return dmnContext
    }

}