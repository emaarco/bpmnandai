package de.emaarco.bpmnandai.credit.domain.service

import de.emaarco.bpmnandai.credit.domain.mapper.LoanMapper
import de.emaarco.bpmnandai.credit.domain.model.LoanRequest
import de.emaarco.bpmnandai.credit.infrastructure.repository.TestLoanRequestRepository
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service

@Service
class LoanRequestTestDataService(private val loanRequestRepository: TestLoanRequestRepository) {

    private val loanMapper = Mappers.getMapper(LoanMapper::class.java)

    fun getAllRequests(): List<LoanRequest> {
        return loanMapper.mapToModel(loanRequestRepository.findAll())
    }

}