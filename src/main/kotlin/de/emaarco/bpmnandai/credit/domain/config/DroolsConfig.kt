package de.emaarco.bpmnandai.credit.domain.config

import org.kie.api.KieServices
import org.kie.api.runtime.KieRuntimeFactory
import org.kie.dmn.api.core.DMNRuntime
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DroolsConfig {

    @Bean
    fun getDmnRuntime(): DMNRuntime {
        val ks = KieServices.Factory.get()
        val kieContainer = ks.kieClasspathContainer
        return KieRuntimeFactory.of(kieContainer.kieBase).get(DMNRuntime::class.java)
    }

}