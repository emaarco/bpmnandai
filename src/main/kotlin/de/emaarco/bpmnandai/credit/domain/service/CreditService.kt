package de.emaarco.bpmnandai.credit.domain.service

import de.emaarco.bpmnandai.credit.domain.mapper.LoanMapper
import de.emaarco.bpmnandai.credit.domain.model.LoanRequest
import de.emaarco.bpmnandai.credit.infrastructure.repository.LoanRequestRepository
import de.emaarco.bpmnandai.shared.exception.ObjectNotFoundException
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service

@Service
class CreditService(private val loanRequestRepository: LoanRequestRepository) {

    private val loanMapper = Mappers.getMapper(LoanMapper::class.java)

    fun getSpecificRequest(requestId: String): LoanRequest {
        return findLoanRequest(requestId)
    }

    fun recheckLoanRequest(requestId: String, result: Boolean): LoanRequest {
        val matchingRequest = findLoanRequest(requestId)
        matchingRequest.updateResultOfCreditworthinessCheck(result)
        saveLoanRequest(matchingRequest)
        return matchingRequest
    }

    fun saveLoanRequest(request: LoanRequest) {
        loanRequestRepository.save(this.loanMapper.mapToEntity(request))
    }

    fun deleteAllRequests() {
        loanRequestRepository.deleteAll()
        loanRequestRepository.flush()
    }

    /* ------------------------- private helper methods ------------------------- */

    private fun findLoanRequest(requestId: String): LoanRequest {
        return loanRequestRepository.findFirstById(requestId)
            ?.let { loanMapper.mapToModel(it) }
            ?: throw ObjectNotFoundException("There is no request with the id '$requestId'")
    }

}