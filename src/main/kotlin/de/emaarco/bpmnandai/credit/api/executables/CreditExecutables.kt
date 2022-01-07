package de.emaarco.bpmnandai.credit.api.executables

import de.emaarco.bpmnandai.credit.domain.facade.ExternalDmnFacade
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class CreditExecutables(private val externalDmnFacade: ExternalDmnFacade) {

    private val log = KotlinLogging.logger {}

    /**
     * A DMN-task that is invoked to check whether a customer is creditworthy
     * (...customer has created a credit-request that can be identified by the provided ID)
     */
    fun checkCreditworthiness(requestId: String): String {
        log.info("Check whether credit-application '${requestId}' can be approved")
        return externalDmnFacade.checkCreditworthiness(requestId)
    }

}