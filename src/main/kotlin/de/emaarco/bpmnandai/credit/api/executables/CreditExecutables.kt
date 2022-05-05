package de.emaarco.bpmnandai.credit.api.executables

import de.emaarco.bpmnandai.credit.domain.facade.CreditFacade
import de.emaarco.bpmnandai.credit.domain.facade.CreditPmmlFacade
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class CreditExecutables(private val creditPmmlFacade: CreditPmmlFacade, private val creditFacade: CreditFacade) {

    private val log = KotlinLogging.logger {}

    /**
     * A DMN-task that is invoked to check whether a customer is creditworthy
     * (...customer has created a credit-request that can be identified by the provided ID)
     */
    fun checkCreditworthiness(requestId: String): String {
        log.info { "Check whether credit-application '${requestId}' can be approved" }
        return creditPmmlFacade.checkCreditworthiness(requestId)
    }

    /**
     * Update the loan-request with information on the creditworthiness of the applicant
     */
    fun saveResultOfCreditworthinessCheck(requestId: String) {
        creditFacade.saveResultOfCreditworthinessCheck(requestId)
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

    /**
     * Send a rejection message to the credit-applicant
     */
    fun sendRejectionMessage(requestId: String) {
        log.info { "Send rejection message for request '$requestId' to applicants e-mail" }
    }

}