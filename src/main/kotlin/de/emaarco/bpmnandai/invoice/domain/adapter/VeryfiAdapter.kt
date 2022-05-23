package de.emaarco.bpmnandai.invoice.domain.adapter

import com.veryfi.kotlin.Client
import mu.KotlinLogging
import org.json.JSONObject
import org.springframework.stereotype.Component
import java.io.File

/**
 * Adapter to interact with the 'veryfi' api
 * (...which is used to extract data from invoices...)
 */
@Component
class VeryfiAdapter(val veryfiClient: Client) {

    private val log = KotlinLogging.logger { }

    fun processInvoice(file: String, fileName: String): JSONObject {
        return try {
            val parameters = JSONObject().put("file_data", file)
            val response = veryfiClient.processDocument(fileName, listOf("Invoice"), true, parameters)
            JSONObject(response)
        } catch (ex: Exception) {
            log.info { "The provided invoice could not be processed by the api: ${ex.message}" }
            getFallbackResponse()
        }
    }

    /* ------------------------- private helper methods ------------------------- */

    private fun getFallbackResponse(): JSONObject {
        val fileContent = File("src/main/resources/veryfi/example-result.json").readBytes()
        return JSONObject(fileContent)
    }

}