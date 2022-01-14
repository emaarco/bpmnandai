package de.emaarco.bpmnandai.credit.infrastructure.repository

import de.emaarco.bpmnandai.credit.infrastructure.entity.TestLoanRequestEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TestLoanRequestRepository : JpaRepository<TestLoanRequestEntity, String>