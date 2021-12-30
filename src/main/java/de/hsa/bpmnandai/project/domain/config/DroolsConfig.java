package de.hsa.bpmnandai.project.domain.config;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieRuntimeFactory;
import org.kie.dmn.api.core.DMNRuntime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {

    @Bean
    public DMNRuntime getRuntime() {
        final KieServices ks = KieServices.Factory.get();
        final KieContainer kieContainer = ks.getKieClasspathContainer();
        return KieRuntimeFactory.of(kieContainer.getKieBase()).get(DMNRuntime.class);
    }

}
