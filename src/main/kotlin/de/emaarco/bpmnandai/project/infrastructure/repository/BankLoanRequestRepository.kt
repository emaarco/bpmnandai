package de.emaarco.bpmnandai.project.infrastructure.repository

import org.springframework.data.jpa.repository.JpaRepository
import de.emaarco.bpmnandai.project.infrastructure.entity.BankLoanRequestEntity

interface BankLoanRequestRepository : JpaRepository<BankLoanRequestEntity, String> {

    fun findByRequestId(id: String): BankLoanRequestEntity?

}