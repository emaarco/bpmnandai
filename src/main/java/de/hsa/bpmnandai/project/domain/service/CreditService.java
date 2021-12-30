package de.hsa.bpmnandai.project.domain.service;

import de.hsa.bpmnandai.project.infrastructure.entity.BankLoanRequestEntity;
import de.hsa.bpmnandai.project.infrastructure.repository.BankLoanRequestRepository;
import de.hsa.bpmnandai.shared.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.String.format;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreditService {

    private final BankLoanRequestRepository bankRepository;
    
    public List<BankLoanRequestEntity> getAllRequests() {
        return bankRepository.findAll();
    }

    public BankLoanRequestEntity getSpecificRequest(final String requestId) {
        return bankRepository.findById(requestId).orElseThrow(
                () -> new ObjectNotFoundException(format("There is no request with the id '%s'", requestId)));
    }

    public void saveCreditRequests(final List<BankLoanRequestEntity> requests) {
        this.bankRepository.saveAll(requests);
    }

    public void saveCreditRequest(final BankLoanRequestEntity request) {
        this.bankRepository.save(request);
    }

}
