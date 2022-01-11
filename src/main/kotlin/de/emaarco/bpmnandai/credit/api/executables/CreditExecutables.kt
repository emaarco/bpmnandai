package de.emaarco.bpmnandai.credit.api.executables

import de.emaarco.bpmnandai.credit.domain.facade.ExternalDmnFacade
import de.emaarco.bpmnandai.credit.domain.facade.SimpleDmnFacade
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class CreditExecutables(
    private val externalDmnFacade: ExternalDmnFacade,
    private val simpleDmnFacade: SimpleDmnFacade,
) {

    private val log = KotlinLogging.logger {}

    /**
     * A DMN-task that is invoked to check whether a customer is creditworthy
     * (...customer has created a credit-request that can be identified by the provided ID)
     */
    fun checkCreditworthiness(requestId: String): String {
        log.info { "Check whether credit-application '${requestId}' can be approved" }
        return externalDmnFacade.checkCreditworthiness(requestId)
    }

    fun saveResultOfCreditworthinessCheck(requestId: String) {
        simpleDmnFacade.saveResultOfCreditworthinessCheck(requestId);
    }

    /**
     * Will be triggered if the person applying is creditworthy.
     * If triggered, it creates a credit-offer for the provided request
     */
    fun createCreditOffer(requestId: String) {
        log.info { "Credit for request '$requestId' can be granted - creating offer!" }
    }

    /**
     * Sends the credit-offer to the credit-applicant
     */
    fun sendCreditOffer(requestId: String) {
        log.info { "Send credit offer for request '$requestId' to applicants e-mail" }
    }

}