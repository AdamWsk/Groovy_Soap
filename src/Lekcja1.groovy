def imie = "Adam"          // def = "zmienna", typ Groovy sam rozpozna
def wiek = 35
println "Cześć, $imie, masz $wiek lat"   // $ wstawia wartość do tekstu
println "Za rok: ${wiek + 1}"            // ${} gdy jest wyrażenie

def leki = ["Apap", "Ibuprom", "Polopiryna"]
println leki[0]            // pierwszy element
println leki.size()        // ile elementów
leki << "Rutinoscorbin"    // dodanie na koniec

// Mapa: jeden obiekt z polami
def lek = [nazwa: "Apap", dawkaMg: 500, naRecepte: false]

println lek.nazwa        // Apap
println lek["dawkaMg"]   // 500, to samo co lek.dawkaMg
lek.cena = 12.50         // dodanie nowego pola
println lek.producent    // null, takiego pola nie ma (bez błędu)

// Lista map: wiele obiektów
def lekiJakoMapa = [
        [nazwa: "Apap",    dawkaMg: 500,  naRecepte: false],
        [nazwa: "Amotaks", dawkaMg: 1000, naRecepte: true],
        [nazwa: "Ibuprom", dawkaMg: 200,  naRecepte: false]
]

println lekiJakoMapa[1].nazwa    // Amotaks

lekiJakoMapa.each { lekMapa ->
    println "${lekMapa.nazwa}, ${lekMapa.dawkaMg} mg"
}

//Zadanie 1: Zadania z lekcji 1
//Utwórz listę pacjenci z 3 pacjentami. Każdy pacjent to mapa z polami imie, nazwisko, wiek.
//Wypisz każdego pacjenta w formacie Kowalski Jan, 40 lat.
//Wypisz tylko pacjentów powyżej 30 lat. Format zostaje ten sam.
//(Bonus) Policz, ilu pacjentów ma powyżej 30 lat, i wypisz wynik.

def pacjenci = [
        [imie:"Adam", nazwisko:"Wosiek", wiek:35],
        [imie:"Tadeusz", nazwisko:"Michalski", wiek:55],
        [imie:"Andrzej", nazwisko:"Nowak", wiek:22]
]

pacjenci.each { pacjent ->
        println "$pacjent.nazwisko $pacjent.imie, $pacjent.wiek lat."
}
println "Lista pacjentow starszych niz 30 lat:"
pacjenci.each {pacjent ->
    if (pacjent.wiek > 30) {
        println "$pacjent.nazwisko $pacjent.imie, $pacjent.wiek lat."
    }
}
//zadanie 4
println "Liczba pacjentow starszych niz 30 lat:"
println pacjenci.count { pacjent -> pacjent.wiek > 30 }


//zadanie 5
def ile = 0

pacjenci.each { pacjent ->
    if (pacjent.wiek > 30) {
        ile++
    }
}

println "Liczba pacjentow starszych niz 30 lat: $ile"