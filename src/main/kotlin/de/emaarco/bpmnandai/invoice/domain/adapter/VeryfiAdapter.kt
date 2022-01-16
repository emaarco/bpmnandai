package de.emaarco.bpmnandai.invoice.domain.adapter

import mu.KotlinLogging
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.io.File

/**
 * Adapter to interact with the 'veryfi' api
 * (...which is used to extract data from invoices...)
 */
@Component
class VeryfiAdapter {

    private val log = KotlinLogging.logger { }

    private val restTemplate = RestTemplate();
    private val url = "https://api.veryfi.com/api/v7/partner/documents/"

    @Value("\${api.veryfi.clientId}")
    private lateinit var clientId: String

    @Value("\${api.veryfi.authorization}")
    private lateinit var authorization: String

    fun processInvoice(file: String, fileName: String): JSONObject {
        val headers = getRequestHeader()
        val requestBody = getRequestBody(file, fileName);
        return processInvoiceSafe(requestBody, headers);
    }

    /* ------------------------- private helper methods ------------------------- */

    private fun processInvoiceSafe(requestBody: JSONObject, headers: HttpHeaders): JSONObject {
        return try {
            val entity: HttpEntity<String> = HttpEntity(requestBody.toString(), headers)
            val response: ResponseEntity<String> = restTemplate.postForEntity(url, entity, String::class.java)
            JSONObject(response.body)
        } catch (ex: Exception) {
            log.info { "The provided invoice could not be processed by the api: ${ex.message}" }
            getFallbackResponse()
        }
    }

    private fun getRequestHeader(): HttpHeaders {
        val headers = HttpHeaders();
        headers.contentType = MediaType.APPLICATION_JSON
        headers.add("CLIENT-ID", clientId)
        headers.add("AUTHORIZATION", authorization)
        return headers
    }

    private fun getRequestBody(file: String, fileName: String): JSONObject {
        val requestBody = JSONObject()
        requestBody.put("file_data", file)
        requestBody.put("file_name", fileName)
        return requestBody
    }

    private fun getFallbackResponse(): JSONObject {
        val fileContent = File("src/main/resources/veryfi/example-result.json").readBytes()
        return JSONObject(fileContent)
    }

}