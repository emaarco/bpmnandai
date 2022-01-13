package de.emaarco.bpmnandai.credit.domain.service

import de.emaarco.bpmnandai.credit.infrastructure.entity.CreditRequestEntity
import de.emaarco.bpmnandai.credit.infrastructure.repository.CreditRequestRepository
import de.emaarco.bpmnandai.shared.exception.ObjectNotFoundException
import org.springframework.stereotype.Service

@Service
class CreditService(private val bankRepository: CreditRequestRepository) {
    
    fun getAllRequests(): List<CreditRequestEntity> {
        return bankRepository.findAll()
    }

    fun getSpecificRequest(requestId: String): CreditRequestEntity {
        return findCreditRequest(requestId)
    }

    fun saveCreditRequest(request: CreditRequestEntity) {
        bankRepository.save(request)
    }

    /* ------------------------- private helper methods ------------------------- */

    private fun findCreditRequest(requestId: String): CreditRequestEntity {
        return bankRepository.findByRequestId(requestId)
            ?: throw ObjectNotFoundException("There is no request with the id '$requestId'")
    }

}