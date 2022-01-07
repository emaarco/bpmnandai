package de.emaarco.bpmnandai.credit.api.controller

import de.emaarco.bpmnandai.credit.domain.facade.ExternalDmnFacade
import de.emaarco.bpmnandai.credit.domain.facade.SimpleDmnFacade
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/api/credit")
class CreditController(
    private val simpleDmnFacade: SimpleDmnFacade,
    private val externalDmnFacade: ExternalDmnFacade,
) {

    @PostMapping("/simple-dmn")
    fun processRequestsWithDecisionTable(): ResponseEntity<Void> {
        println("Request request to process credit-requests (with camunda dmn-engine)")
        simpleDmnFacade.processLoanRequests()
        simpleDmnFacade.addHistory()
        return ResponseEntity.ok().build()
    }

    @PostMapping("/external-dmn")
    fun processRequestsWithPmmlDMN(): ResponseEntity<Void> {
        println("Request request to process credit-requests (with dedicated drools decision-engine)")
        externalDmnFacade.processLoanRequests()
        return ResponseEntity.ok().build()
    }

}