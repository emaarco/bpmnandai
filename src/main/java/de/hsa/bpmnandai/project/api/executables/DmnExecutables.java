package de.hsa.bpmnandai.project.api.executables;

import de.hsa.bpmnandai.project.domain.facade.ExternalDmnFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DmnExecutables {

    private final ExternalDmnFacade externalDmnFacade;

    /**
     * A DMN-task that is invoked to check whether a customer is creditworthy
     * (...customer has created a credit-request that can be identified by the provided ID)
     */
    public String checkCreditworthiness(final String requestId) {
        log.info("Check whether credit-application '{}' can be approved", requestId);
        return externalDmnFacade.checkCreditworthiness(requestId);
    }

}
