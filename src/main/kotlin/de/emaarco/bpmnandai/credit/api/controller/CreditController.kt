package de.emaarco.bpmnandai.credit.api.controller

import de.emaarco.bpmnandai.credit.domain.facade.ExternalDmnFacade
import de.emaarco.bpmnandai.credit.domain.facade.SimpleDmnFacade
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/api/credit")
@Tag(name = "Credit Approval Controller")
class CreditController(
    private val simpleDmnFacade: SimpleDmnFacade,
    private val externalDmnFacade: ExternalDmnFacade,
) {

    private val log = KotlinLogging.logger {}

    @PostMapping("/simple-dmn")
    fun processRequestsWithDecisionTable(): ResponseEntity<Void> {
        log.debug("Request request to process credit-requests (with camunda dmn-engine)")
        simpleDmnFacade.processLoanRequests()
        simpleDmnFacade.addHistory()
        return ResponseEntity.ok().build()
    }

    @PostMapping("/external-dmn")
    fun processRequestsWithPmmlDMN(): ResponseEntity<Void> {
        log.debug("Request request to process credit-requests (with dedicated drools decision-engine)")
        externalDmnFacade.processLoanRequests()
        return ResponseEntity.ok().build()
    }

}