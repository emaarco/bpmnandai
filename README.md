# BPMNANDAI

<p>Im Rahmen dieses Projektes sollen <b>BPMN basierte Prozesse</b>, 
durch den Einsatz von <b>Künstlicher Intelligenz</b> automatisiert werden</p>

### KI in Form einer DMN-Tabelle abbilden

<p>...abgebildet als Business Rule Task - 
und angesprochen durch die Camunda DMN-Engine, 
welche direkt in die Camunda BPMN Engine integriert ist</p>

![KI - integriert in Prozess durch Decision Table](docs/credit-request-de.png)

---

### KI im PMML-Format in DMN-Model einbinden

<p>...abgebildet als Business Rule Task - 
und angesprochen durch eine dedizierte DMN-Engine 
(<a href="https://drools.org">Drools DMN-/Rule-Engine</a>)</p>


---

### KI über Service-Task integrieren

<p>Durch diesen Prozess wird die Zahlung von Eingangsrechnungen automatisiert.</br>
Dabei werden durch die eine Künstliche Intelligenz (OCR = Machine Learning) Daten aus den Rechnungen extrahiert.</br>
Diese Daten sind z.B. die Rechnungspositionen, der Rechnungsbetrag und weitere Zahlungsinformationen.
</p>

![KI - integriert in Prozess durch Service-Task](docs/invoice-de.png)

---