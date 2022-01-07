package de.emaarco.bpmnandai.project.api.executables

import de.emaarco.bpmnandai.project.domain.facade.ExternalDmnFacade
import org.springframework.stereotype.Component

@Component
class DmnExecutables(private val externalDmnFacade: ExternalDmnFacade) {

    /**
     * A DMN-task that is invoked to check whether a customer is creditworthy
     * (...customer has created a credit-request that can be identified by the provided ID)
     */
    fun checkCreditworthiness(requestId: String): String {
        println("Check whether credit-application '${requestId}' can be approved")
        return externalDmnFacade.checkCreditworthiness(requestId)
    }

}