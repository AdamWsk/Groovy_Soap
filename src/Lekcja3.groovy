import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def bundleJson = '''
{
  "resourceType": "Bundle",
  "type": "searchset",
  "total": 3,
  "entry": [
    { "resource": { "resourceType": "Observation", "id": "obs-1", "status": "final",
        "code": { "text": "Temperatura" },
        "subject": { "reference": "Patient/123" },
        "valueQuantity": { "value": 36.6, "unit": "Cel" } } },
    { "resource": { "resourceType": "Observation", "id": "obs-2", "status": "final",
        "code": { "text": "Tetno" },
        "subject": { "reference": "Patient/123" },
        "valueQuantity": { "value": 72, "unit": "/min" } } },
    { "resource": { "resourceType": "Observation", "id": "obs-3", "status": "preliminary",
        "code": { "text": "Temperatura" },
        "subject": { "reference": "Patient/123" },
        "valueQuantity": { "value": 38.2, "unit": "Cel" } } }
  ]
}
'''

def bundle = new JsonSlurper().parseText(bundleJson)

def idki = bundle.entry.collect {e -> e.resource.id}
println idki

println "Zadanie 2"
def observ = bundle.entry.resource.find { e -> e.id == "obs-2" }
println "$observ.code.text: $observ.valueQuantity.value  $observ.valueQuantity.unit"

//zadanie 3
// Sprawdź, czy wszystkie mają status final (every). Jeśli nie, wypisz id tej, która go nie ma (find).
def isFinal = bundle.entry.resource.every { s -> s.status == "final" }
println isFinal

if (!isFinal) {
    def inna = bundle.entry.resource.find { s -> s.status != "final" }
    println inna.id
}
