package de.emaarco.bpmnandai.credit.domain.mapper

import de.emaarco.bpmnandai.credit.domain.model.LoanRequest
import de.emaarco.bpmnandai.credit.infrastructure.entity.LoanRequestEntity
import de.emaarco.bpmnandai.credit.infrastructure.entity.TestLoanRequestEntity
import org.mapstruct.Mapper

@Mapper
interface LoanMapper {

    fun mapToEntity(model: LoanRequest): LoanRequestEntity

    fun mapToModel(entity: LoanRequestEntity): LoanRequest

    fun mapToModel(entities: List<TestLoanRequestEntity>): List<LoanRequest>

}