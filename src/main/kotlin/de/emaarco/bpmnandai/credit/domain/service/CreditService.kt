package de.emaarco.bpmnandai.credit.domain.service

import de.emaarco.bpmnandai.credit.infrastructure.entity.BankLoanRequestEntity
import de.emaarco.bpmnandai.credit.infrastructure.repository.BankLoanRequestRepository
import de.emaarco.bpmnandai.shared.exception.ObjectNotFoundException
import org.springframework.stereotype.Service

@Service
class CreditService(private val bankRepository: BankLoanRequestRepository) {
    
    fun getAllRequests(): List<BankLoanRequestEntity> {
        return bankRepository.findAll()
    }

    fun getSpecificRequest(requestId: String): BankLoanRequestEntity {
        return findCreditRequest(requestId)
    }

    fun saveCreditRequest(request: BankLoanRequestEntity) {
        bankRepository.save(request)
    }

    /* ------------------------- private helper methods ------------------------- */

    private fun findCreditRequest(requestId: String): BankLoanRequestEntity {
        return bankRepository.findByRequestId(requestId)
            ?: throw ObjectNotFoundException("There is no request with the id '$requestId'")
    }

}