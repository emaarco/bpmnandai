package de.emaarco.bpmnandai.invoice.domain.config

import com.veryfi.kotlin.Client
import com.veryfi.kotlin.VeryfiClientFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class VeryfiConfig {

    @Value("\${api.veryfi.clientId}")
    private lateinit var clientId: String

    @Value("\${api.veryfi.authorization}")
    private lateinit var apiKey: String

    @Value("\${api.veryfi.userName}")
    private lateinit var userName: String

    @Value("\${api.veryfi.clientSecret}")
    private lateinit var clientSecret: String

    @Bean
    fun buildVeryfiClient(): Client {
        return VeryfiClientFactory.createClient(clientId, clientSecret, userName, apiKey)
    }


}