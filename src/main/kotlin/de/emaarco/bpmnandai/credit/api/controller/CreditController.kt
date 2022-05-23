package de.emaarco.bpmnandai.credit.api.controller

import de.emaarco.bpmnandai.credit.api.mapper.LoanApiMapper
import de.emaarco.bpmnandai.credit.api.transport.LoanRequestTO
import de.emaarco.bpmnandai.credit.api.transport.NewLoanRequestTO
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
    @Operation(description = "Get a specific loan-request")
    fun getLoanRequest(@PathVariable requestId: String): ResponseEntity<LoanRequestTO> {
        log.debug { "Received request to get loan-request with id '$requestId'" }
        val matchingLoanRequest = creditFacade.getSpecificLoanRequest(requestId)
        return ResponseEntity.ok().body(loanMapper.mapToTO(matchingLoanRequest))
    }

    @PostMapping("/pmml")
    @Operation(summary = "Create a new loan request (variant: pmml)")
    fun createLoanRequestForPmml(@RequestBody loanRequest: NewLoanRequestTO): ResponseEntity<LoanRequestTO> {
        log.debug { "Received request to create a new loan request: $loanRequest" }
        val createdRequest = creditFacade.createLoanRequest(loanMapper.mapToModel(loanRequest))
        return ResponseEntity.ok().body(loanMapper.mapToTO(createdRequest))
    }

    @PostMapping("/decisionTable")
    @Operation(summary = "Create a new loan request (variant: decisionTable)")
    fun createLoanRequestForDecisionTable(@RequestBody loanRequest: NewLoanRequestTO): ResponseEntity<LoanRequestTO> {
        log.debug { "Received request to create a new loan request: $loanRequest" }
        val updatedRequest = creditPmmlFacade.createLoanRequest(loanMapper.mapToModel(loanRequest))
        return ResponseEntity.ok().body(loanMapper.mapToTO(updatedRequest))
    }

    @PostMapping("/{requestId}/recheck/{creditworthy}")
    @Operation(summary = "Check whether the provided request should really be rejected")
    fun recheckLoanRequest(
        @PathVariable(name = "requestId") requestId: String,
        @PathVariable(name = "creditworthy") creditworthy: Boolean
    ): ResponseEntity<LoanRequestTO> {
        log.debug { "Rechecked the loan request '$requestId' - approved: $creditworthy" }
        val updatedRequest = creditFacade.recheckLoanRequest(requestId, creditworthy)
        return ResponseEntity.ok().body(loanMapper.mapToTO(updatedRequest))
    }

}