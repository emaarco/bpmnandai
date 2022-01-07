package de.emaarco.bpmnandai.project.api.controller

import de.emaarco.bpmnandai.project.domain.facade.ExternalDmnFacade
import de.emaarco.bpmnandai.project.domain.facade.SimpleDmnFacade
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/api")
class TestController(
    private val simpleDmnFacade: SimpleDmnFacade,
    private val externalDmnFacade: ExternalDmnFacade,
) {

    @PostMapping("/simple-dmn")
    fun processRequestsWithDecisionTable(): ResponseEntity<Void> {
        println("Request request to perform something")
        simpleDmnFacade.processLoanRequests()
        simpleDmnFacade.addHistory()
        return ResponseEntity.ok().build()
    }

    @PostMapping("/external-dmn")
    fun processRequestsWithPmmlDMN(): ResponseEntity<Void> {
        println("Request request to perform something")
        externalDmnFacade.processLoanRequests()
        return ResponseEntity.ok().build()
    }

}