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

    public String checkCreditworthiness(final String requestId) {
        log.info("Executing check");
        return externalDmnFacade.checkCreditworthiness(requestId);
    }

}
