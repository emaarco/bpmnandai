package de.emaarco.bpmnandai.credit.api.mapper

import de.emaarco.bpmnandai.credit.api.transport.LoanRequestTO
import de.emaarco.bpmnandai.credit.domain.model.LoanRequest
import org.mapstruct.Mapper

@Mapper
interface LoanApiMapper {

    fun mapToTO(model: LoanRequest): LoanRequestTO

}