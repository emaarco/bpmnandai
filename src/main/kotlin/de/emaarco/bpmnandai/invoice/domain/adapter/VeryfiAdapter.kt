package de.emaarco.bpmnandai.invoice.domain.adapter

import org.json.JSONObject
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class VeryfiAdapter {

    private val restTemplate = RestTemplate();
    private val url = "https://api.veryfi.com/api/v7/partner/documents/"
    private val clientId = "vrfiSrfloBWuRPW6MOD0m6C9wvhaslKw9pYcujm"
    private val authorization = "apikey marco.schaeck1:7d1da79a48a8ba920dfc02cb1cb30e7b"

    fun processInvoice(file: String, fileName: String): JSONObject {
        val headers = getRequestHeader()
        val requestBody = getRequestBody(file, fileName);
        //val entity: HttpEntity<String> = HttpEntity(requestBody.toString(), headers)
        //val response: ResponseEntity<String> = restTemplate.postForEntity(url, entity, String::class.java)
        //return JSONObject(response.body);
        return JSONObject(
            "{\n" +
                    "    \"abn_number\": \"\",\n" +
                    "    \"account_number\": \"\",\n" +
                    "    \"bill_to_address\": \"Am Eser 19\\n86150 Augsburg\",\n" +
                    "    \"bill_to_name\": \"Marco Schäck\",\n" +
                    "    \"bill_to_vat_number\": \"\",\n" +
                    "    \"card_number\": \"\",\n" +
                    "    \"cashback\": 0.0,\n" +
                    "    \"category\": \"Office Supplies & Software\",\n" +
                    "    \"created\": \"2022-01-07 14:20:06\",\n" +
                    "    \"currency_code\": \"EUR\",\n" +
                    "    \"date\": \"2022-01-07 00:00:00\",\n" +
                    "    \"delivery_date\": \"\",\n" +
                    "    \"discount\": 0.0,\n" +
                    "    \"document_reference_number\": \"\",\n" +
                    "    \"document_title\": \"Rechnung\",\n" +
                    "    \"document_type\": \"receipt\",\n" +
                    "    \"due_date\": \"\",\n" +
                    "    \"duplicate_of\": \"\",\n" +
                    "    \"external_id\": \"\",\n" +
                    "    \"id\": 52908222,\n" +
                    "    \"img_file_name\": \"52908222.pdf\",\n" +
                    "    \"img_thumbnail_url\": \"https://scdn.veryfi.com/receipts/e7c1edff-f44b-4a5b-b37d-442bf9acdf7d/thumbnail.jpg?Expires=1641566107&Signature=WmWBSsOZPUT-jrmJ9Y5-9MZYpZmV4oGen40lPqLzlwqFhhNquKu6DBBTw4Ew8QwCamgFkBRifRigojdOMvfAO33wf5cEU6nkRtuSCtASWhAV2oMXXmdCbqGllk6il4zTgu8mvkiPA3A0Qe5YBhHTrGiaqAey2q-2fyXA-TZc-WQXc6uj5LW13qHhy1T~r26AvUiIqa0pWwmHr4A3rf9AygGv9dQFdvacbF1-AC4jBROXe4TZoMiPkxbVFmTVc~jiLKtoLnAPBnaub84gyqp1I5ie~C6EDqueaolMNlXlrEU8Q-sxjMiYM8u3MAWZ2vN1slIIjYWAvKFgk4HuBItotw__&Key-Pair-Id=APKAJCILBXEJFZF4DCHQ\",\n" +
                    "    \"img_url\": \"https://scdn.veryfi.com/receipts/e7c1edff-f44b-4a5b-b37d-442bf9acdf7d/2260f6ab-9b98-40a3-8fc4-f772565a80e2.pdf?Expires=1641566107&Signature=Lf-kRmjA8qq4gz3-qZ-ZsgZNZOJ9YKyl~fwtRUA0CKaN7xDZAL3Unun1UH6nkdV3QQcsF0ik-agDQwIBC-ATbtz98ee4QOM~a6NkK~4~TeZgOhB7cQw5CpPpi963o-Tqi1focBYDwzx~hCpER0~uhv6N7K19AO~zObzJsuhoEaTgkk~FJnciGEukeKwf8LDUUhbZ2m7VJ9yyTg5yXn9MnbiapZfB8xX6nzuNIQZlC13XbK3ckXoC5J8ZfJVJU~89H-i2pcbzb8Q2Xm24Ro87KRoeS6afYppKJNedzCbmXIB752iP1sCRv5uNe8l~~wXaC3usOsbgkgOLmVp3R2xGxg__&Key-Pair-Id=APKAJCILBXEJFZF4DCHQ\",\n" +
                    "    \"insurance\": 0.0,\n" +
                    "    \"invoice_number\": \"127685282\",\n" +
                    "    \"is_duplicate\": 0,\n" +
                    "    \"line_items\": [\n" +
                    "        {\n" +
                    "            \"date\": \"\",\n" +
                    "            \"description\": \"Logitech FOLIO TOUCH iPad Pro\\nWir wünschen Ihnen ein erfolgreiches und glückliches neues Jahr 2022!\",\n" +
                    "            \"discount\": 0.0,\n" +
                    "            \"id\": 142206470,\n" +
                    "            \"order\": 0,\n" +
                    "            \"price\": 0.0,\n" +
                    "            \"quantity\": 1.0,\n" +
                    "            \"reference\": \"\",\n" +
                    "            \"section\": \"\",\n" +
                    "            \"sku\": \"373 917-79\",\n" +
                    "            \"tax\": 0.0,\n" +
                    "            \"tax_rate\": 0.0,\n" +
                    "            \"total\": 107.09,\n" +
                    "            \"type\": \"product\",\n" +
                    "            \"unit_of_measure\": \"\"\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"notes\": \"\",\n" +
                    "    \"ocr_text\": \"büro shop24\\nRechnung\\n\\tbüroshop24 GmbH\\n\\tIn der Niederwiese 2\\n\\t65473 Bischofsheim\\n\\twww.bueroshop24.de\\n\\trechnungsversand@bueroshop24.de\\n\\tTel.: 0800/888 333 2\\n\\tDeutsche Bank\\n\\tIBAN DE61 5107 0021 0031 1316 00\\nbüroshop24 GmbH • 65473 Bischofsheim\\t\\t\\t\\t\\t\\t\\t\\tBIC DEUTDEFF510\\n.\\n\\tUSt-IdNr. DE814608985\\n\\nHerrn\\nMarco Schäck\\nAm Eser 19\\n86150 Augsburg\\n\\nRechnungs-Nr. Kunden-Nr. Rg.-/Liefer-Datum Auftrags-Nr. Besteller/Bestell-Nr.\\t\\t\\t\\tPaket-Nr.\\n127685282 82944497\\t07.01.2022\\t713957345\\t\\t\\t\\t\\t\\t\\t195629854\\n\\nZahlung per PayPal\\nBestell-Nr.\\tMenge Artikelbezeichnung\\t\\t\\t\\tEinzelpreis\\tGesamtpreis\\tUSt.\\tBemerkung\\n\\tincl. USt.\\tincl. USt.\\tKZ\\n373 917-79\\t1 Logitech FOLIO TOUCH iPad Pro\\t\\t107,09\\t107,09\\t1\\nWir wünschen Ihnen ein erfolgreiches und glückliches neues Jahr 2022!\\n\\nZahlartgebühr\\t\\tWarenwert Netto\\tGesamt-Netto\\tUSt.-Betrag % USt. Rg.-Betrag EUR\\n\\tincl. USt.\\n89,99\\t89,99\\t17,10 19\\t107,09\\t1 07.01.2022\\n\\nGF: Hans R. Schmid, Dr. Peter Kirchberg, Michael Kelsch, Siegfried Sorg, Amtsgericht Darmstadt, HRB 99426, Öko-Kontrollstelle: DE-ÖKO-003\\n\\nWichtiger Hinweis für\\t\\tBeginn der Widerrufsbelehrung für Verbraucher\\ndie Zahlung per\\t\\t\\tSie haben das Recht, binnen 30 Tagen ohne Angabe von Gründen diesen Vertrag zu widerrufen. Die Widerrufsfrist beträgt\\nBanküberweisung:\\t\\t30 Tage ab dem Tag, an dem Sie oder ein von Ihnen benannter Dritter, der nicht der Beförderer\\tdie letzte Ware in Besitz ge\\nnommen hat. Um Ihr Widerrufsrecht auszuüben, müssen Sie uns (büroshop24 GmbH, In der Niederwiese 2, 65473 Bischofsheim,\\nBitte verwenden Sie die\\t\\tTel.: 0800/888 333 2, rechnungsversand@bueroshop24.de) mittels einer eindeutigen Erklärung (z. B. Postbrief oder E-Mail) über\\nnachfolgenden Daten:\\t\\tIhren Entschluss, diesen Vertrag zu widerrufen, informieren. Sie können dafür das im Internet hinterlegte Muster\\nWiderrufsformular verwenden, das jedoch nicht vorgeschrieben\\tZur Wahrung der Widerrufsfrist reicht es aus, dass Sie die Mit\\nKreditinstitut:\\t\\t\\tteilung über die Ausübung des Widerrufsrechts vor Ablauf der Widerrufsfrist absenden.\\nDeutsche Bank\\t\\t\\tFolgen des Widerrufs\\nWenn Sie diesen Vertrag widerrufen, haben wir Ihnen alle Zahlungen, die wir von Ihnen erhalten haben, einschließlich der Liefer\\nIBAN:\\t\\t\\t\\tkosten (mit Ausnahme der zusätzlichen Kosten, die sich daraus ergeben, dass Sie eine andere Art der Lieferung als die von uns\\nDE61 5107 0021 0031 1316 00\\tangebotene, günstige Standardlieferung gewählt haben), unverzüglich und spätestens binnen 14 Tagen ab dem Tag zurückzu\\nzahlen, an dem die Mitteilung über Ihren Widerruf dieses Vertrags bei uns eingegangen\\tFür diese Rückzahlung verwenden wir\\nBIC:\\t\\t\\t\\tdasselbe Zahlungsmittel, das Sie bei der ursprünglichen Transaktion eingesetzt haben, es sei denn, mit Ihnen wurde ausdrücklich\\netwas anderes vereinbart; in keinem Fall werden Ihnen wegen dieser Rückzahlung Entgelte berechnet. Wir holen die Ware ab.\\nDEUTDEFF510\\t\\t\\tWir tragen die Kosten der Rücksendung der Waren. Sie müssen für etwaigen Wertverlust der Waren nur aufkommen, wenn die\\nser auf einen zur Prüfung der Beschaffenheit, Eigenschaften und Funktionsweise der Waren nicht notwendigen Umgang mit ih\\nBitte geben Sie im Feld\\t\\tnen zurückzuführen ist.\\nVerwendungszweck\\tAusschluss des Widerrufrechtes\\nfolgende Daten an:\\t\\tDas Widerrufsrecht besteht nicht bei Verträgen zur Lieferung (a) von Waren, die nicht vorgefertigt sind und für deren Herstellung\\neine individuelle Auswahl oder Bestimmung durch den Verbraucher maßgeblich ist oder die eindeutig auf die persönlichen Bedürf\\nRg. 127685282\\t\\tnisse des Verbrauchers zugeschnitten sind, (b) von Ton- oder Videoaufnahmen oder Computersoftware in einer versiegelten Pa\\nckung, wenn die Versiegelung nach der Lieferung entfernt wurde.\\nKd. 82944497\\nEnde der Widerrufsbelehrung\\n\\tist,\\n\\tist.\\n\\tist.\",\n" +
                    "    \"order_date\": \"2022-01-07\",\n" +
                    "    \"payment_display_name\": \"\",\n" +
                    "    \"payment_terms\": \"30 Tagen\",\n" +
                    "    \"payment_type\": \"american_express\",\n" +
                    "    \"phone_number\": \"0800/888 333\",\n" +
                    "    \"purchase_order_number\": \"82944497\",\n" +
                    "    \"rounding\": 0.0,\n" +
                    "    \"service_end_date\": \"\",\n" +
                    "    \"service_start_date\": \"\",\n" +
                    "    \"ship_date\": \"\",\n" +
                    "    \"ship_to_address\": \"\",\n" +
                    "    \"ship_to_name\": \"\",\n" +
                    "    \"shipping\": 0.0,\n" +
                    "    \"store_number\": \"65473\",\n" +
                    "    \"subtotal\": 89.99,\n" +
                    "    \"tax\": 17.1,\n" +
                    "    \"tax_lines\": [\n" +
                    "        {\n" +
                    "            \"base\": 89.99,\n" +
                    "            \"name\": \"\",\n" +
                    "            \"order\": 0,\n" +
                    "            \"rate\": 0.0,\n" +
                    "            \"total\": 17.1\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"tip\": 0.0,\n" +
                    "    \"total\": 107.09,\n" +
                    "    \"total_weight\": \"\",\n" +
                    "    \"tracking_number\": \"\",\n" +
                    "    \"updated\": \"2022-01-07 14:20:08\",\n" +
                    "    \"vat_number\": \"DE814608985\",\n" +
                    "    \"vendor\": {\n" +
                    "        \"address\": \"In der Niederwiese 2, 65474 Bischofsheim, Germany\",\n" +
                    "        \"category\": \"\",\n" +
                    "        \"email\": \"rechnungsversand@bueroshop24.de\",\n" +
                    "        \"fax_number\": \"\",\n" +
                    "        \"name\": \"büroshop24 GmbH\",\n" +
                    "        \"phone_number\": \"0800/888 333\",\n" +
                    "        \"raw_name\": \"büroshop24 GmbH\",\n" +
                    "        \"vendor_logo\": \"https://cdn.veryfi.com/logos/tmp/380842392.png\",\n" +
                    "        \"vendor_reg_number\": \"\",\n" +
                    "        \"vendor_type\": \"store\",\n" +
                    "        \"web\": \"www.bueroshop24.de\"\n" +
                    "    },\n" +
                    "    \"vendor_account_number\": \"\",\n" +
                    "    \"vendor_bank_name\": \"Deutsche Bank\",\n" +
                    "    \"vendor_bank_number\": \"\",\n" +
                    "    \"vendor_bank_swift\": \"DEUTDEFF510\",\n" +
                    "    \"vendor_iban\": \"DE61 5107 0021 0031 1316 00\"\n" +
                    "}"
        )
    }

    private fun getRequestHeader(): HttpHeaders {
        val headers = HttpHeaders();
        headers.contentType = MediaType.APPLICATION_JSON
        headers.add("CLIENT-ID", clientId)
        headers.add("AUTHORIZATION", authorization)
        return headers;
    }

    private fun getRequestBody(file: String, fileName: String): JSONObject {
        val requestBody = JSONObject()
        requestBody.put("file_data", file)
        requestBody.put("file_name", fileName)
        return requestBody;
    }

}