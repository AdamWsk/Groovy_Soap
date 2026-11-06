import groovy.xml.XmlSlurper

def soap = '''
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:rec="http://example.com/recepty">
  <soapenv:Header/>
  <soapenv:Body>
    <rec:PobierzReceptyResponse>
      <rec:recepta id="R1"><rec:lek>Apap</rec:lek><rec:status>ZREALIZOWANA</rec:status></rec:recepta>
      <rec:recepta id="R2"><rec:lek>Amotaks</rec:lek><rec:status>WYSTAWIONA</rec:status></rec:recepta>
      <rec:recepta id="R3"><rec:lek>Ibuprom</rec:lek><rec:status>ZREALIZOWANA</rec:status></rec:recepta>
    </rec:PobierzReceptyResponse>
  </soapenv:Body>
</soapenv:Envelope>
'''

def cdaXml = '''
<ClinicalDocument xmlns="urn:hl7-org:v3">
  <templateId root="1.2.3.4.5"/>
  <code code="57833-6" codeSystem="2.16.840.1.113883.6.1" displayName="Prescription for medication"/>
  <recordTarget>
    <patientRole>
      <id root="2.16.840.1.113883.3.4424.1.1.616" extension="12345678901"/>
      <patient>
        <name><given>Jan</given><family>Kowalski</family></name>
        <administrativeGenderCode code="M"/>
        <birthTime value="19850312"/>
      </patient>
    </patientRole>
  </recordTarget>
</ClinicalDocument>
'''

// Zadanie 1
// Sparsuj odpowiedź SOAP. Wypisz liczbę recept i listę ich id (collect + @id).
def soapSlurped = new XmlSlurper().parseText(soap)
//Ścieżka do listy recept:
def recepty = soapSlurped.Body.PobierzReceptyResponse.recepta
println "Ilość recept:"+recepty.size()
//Pobieranie id z jednej recepty
println recepty[0].@id        // R1
//Zamieniamy recepty[0] na r i wstaw do collect:
def idRecept = recepty.collect { r -> r.@id.text() }
println idRecept              // [R1, R2, R3]

//Zadanie 2
//Znajdź receptę R2 i wypisz R2: Amotaks WYSTAWIONA (find).
def receptR2 = recepty.find { r -> r.@id == "R2" }
println "${receptR2.@id}: ${receptR2.lek} ${receptR2.status}"


//Zadanie 3
// Sprawdź, czy wszystkie recepty mają status ZREALIZOWANA (every), a potem wypisz listę id tych, które go nie mają (findAll + collect).

def czyZrealizowane = recepty.every { r -> r.status == "ZREALIZOWANA"}
println czyZrealizowane

def niezrealizowane = recepty.findAll { r -> r.status != "ZREALIZOWANA" }.collect {r -> r.@id.text() }
println niezrealizowane

//Zadanie 4
//Sparsuj dokument CDA i wypisz Kowalski Jan, PESEL 12345678901.

def cdaXmlSlurped = new XmlSlurper().parseText(cdaXml)

def patient = cdaXmlSlurped.recordTarget.patientRole.patient

println "${cdaXmlSlurped.recordTarget.patientRole.patient.name.family} ${cdaXmlSlurped.recordTarget.patientRole.patient.name.given}, PESEL " +
        "${cdaXmlSlurped.recordTarget.patientRole.id.@extension}"





















