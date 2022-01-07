package de.emaarco.bpmnandai.project.domain.service

import de.emaarco.bpmnandai.project.infrastructure.entity.BankLoanRequestEntity
import de.emaarco.bpmnandai.project.infrastructure.repository.BankLoanRequestRepository
import de.emaarco.bpmnandai.shared.ObjectNotFoundException
import org.springframework.stereotype.Component

@Component
class CreditService(private val bankRepository: BankLoanRequestRepository) {

    fun getAllRequests(): List<BankLoanRequestEntity> {
        return bankRepository.findAll()
    }

    fun getSpecificRequest(requestId: String): BankLoanRequestEntity {
        return bankRepository.findByRequestId(requestId)
            ?: throw ObjectNotFoundException("There is no request with the id '${requestId}'")
    }

    fun saveCreditRequests(requests: List<BankLoanRequestEntity>) {
        bankRepository.saveAll(requests)
    }

    fun saveCreditRequest(request: BankLoanRequestEntity) {
        bankRepository.save(request)
    }

}