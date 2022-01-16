package de.emaarco.bpmnandai.credit.api.controller

import de.emaarco.bpmnandai.credit.api.mapper.LoanApiMapper
import de.emaarco.bpmnandai.credit.api.transport.LoanRequestTO
import de.emaarco.bpmnandai.credit.domain.facade.CreditFacade
import de.emaarco.bpmnandai.credit.domain.facade.CreditPmmlFacade
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KotlinLogging
import org.mapstruct.factory.Mappers
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/api/credit")
@Tag(name = "Credit Approval Controller")
class CreditController(
    private val creditFacade: CreditFacade,
    private val creditPmmlFacade: CreditPmmlFacade,
) {

    private val log = KotlinLogging.logger {}
    private val loanMapper = Mappers.getMapper(LoanApiMapper::class.java)

    @GetMapping("/{requestId}")
    @Operation(description = "Get a specific credit-request")
    fun getCreditRequest(@PathVariable requestId: String): ResponseEntity<LoanRequestTO> {
        log.debug { "Received request to get credit-request with id '$requestId'" }
        val matchingLoanRequest = creditFacade.getSpecificLoanRequest(requestId)
        return ResponseEntity.ok().body(loanMapper.mapToTO(matchingLoanRequest))
    }

    @PostMapping("/{requestId}/recheck/{result}")
    @Operation(summary = "Check whether the provided request should really be rejected")
    fun recheckLoanRequest(
        @PathVariable(name = "requestId") requestId: String,
        @PathVariable(name = "result") result: Boolean
    ): ResponseEntity<LoanRequestTO> {
        log.debug { "Rechecked the loan request '$requestId' - approved: $result" }
        val updatedRequest = creditFacade.recheckLoanRequest(requestId, result)
        return ResponseEntity.ok().body(loanMapper.mapToTO(updatedRequest))
    }

    @PostMapping("/simple-dmn")
    @Operation(description = "Process all credit-requests using the internal dmn-engine")
    fun processRequestsWithDecisionTable(): ResponseEntity<Void> {
        log.debug { "Request request to process credit-requests (with camunda dmn-engine)" }
        creditFacade.processLoanRequests()
        return ResponseEntity.ok().build()
    }

    @PostMapping("/external-dmn")
    @Operation(description = "Process all credit-requests using the drools decision-engine")
    fun processRequestsWithPmmlDMN(): ResponseEntity<Void> {
        log.debug { "Request request to process credit-requests (with dedicated drools decision-engine)" }
        creditPmmlFacade.processLoanRequests()
        return ResponseEntity.ok().build()
    }

}