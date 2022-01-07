package de.emaarco.bpmnandai.credit.api.executables

import de.emaarco.bpmnandai.credit.domain.facade.ExternalDmnFacade
import org.springframework.stereotype.Component

@Component
class CreditExecutables(private val externalDmnFacade: ExternalDmnFacade) {

    /**
     * A DMN-task that is invoked to check whether a customer is creditworthy
     * (...customer has created a credit-request that can be identified by the provided ID)
     */
    fun checkCreditworthiness(requestId: String): String {
        println("Check whether credit-application '${requestId}' can be approved")
        return externalDmnFacade.checkCreditworthiness(requestId)
    }

}