def leki = [
        [nazwa: "Apap",    dawkaMg: 500,  naRecepte: false],
        [nazwa: "Amotaks", dawkaMg: 1000, naRecepte: true],
        [nazwa: "Ibuprom", dawkaMg: 0,  naRecepte: false]
]

// find zwraca pierwszy pasujący element. Jeśli nic nie pasuje, zwraca null:
def amotaks = leki.find { lek -> lek.nazwa == "Amotaks" }
println amotaks.dawkaMg      // 1000

//findAll zwraca listę wszystkich pasujących:
def wysokie = leki.findAll { lek -> lek.dawkaMg > 400 }
println wysokie.size()       // 2

// Te dwie metody zwracają true albo false, więc idealnie nadają się do asercji:
println leki.any { lek -> lek.naRecepte }        // true: choć jeden
println leki.every { lek -> lek.dawkaMg > 0 }    // true: wszystkie

//eachWithIndex to each z numerem elementu. Numeracja jest od 0:
leki.eachWithIndex { lek, i ->
    println "${i + 1}. ${lek.nazwa}"     // 1. Apap, 2. Amotaks, 3. Ibuprom
}

//sort układa według wskazanego pola:
def posortowane = leki.sort(false) { lek -> lek.dawkaMg }
println posortowane
//false oznacza, że oryginalna lista zostaje bez zmian. Samo leki.sort { ... } przestawiłoby elementy w leki.

//Metody można wywoływać jedna po drugiej. To częsty zapis w skryptach:
println leki.findAll { lek -> lek.dawkaMg > 400 }.collect { lek -> lek.nazwa }
println(leki)
// [Apap, Amotaks]

// to samo co wyzej w 2 linijkach:
def wysokie2 = leki.findAll { lek -> lek.dawkaMg > 400 }
def nazwy = wysokie2.collect { lek -> lek.nazwa }
println nazwy

// Zadania: Znajdź pacjenta o PESEL 23456789012 i wypisz jego nazwisko (find).
//Zrób listę nazwisk pacjentów powyżej 30 lat i ją wypisz (findAll + collect).
//Sprawdź, czy wszyscy są zaszczepieni, i wypisz wynik (every).
//Wypisz pacjentów ponumerowanych od 1, od najmłodszego, w formacie 1. Nowak Anna, 28 lat (sort + eachWithIndex).


def pacjenci = [
        [imie: "Jan",   nazwisko: "Kowalski",  wiek: 40, pesel: "12345678901", zaszczepiony: true],
        [imie: "Anna",  nazwisko: "Nowak",     wiek: 28, pesel: "23456789012", zaszczepiony: true],
        [imie: "Piotr", nazwisko: "Zielinski", wiek: 67, pesel: "34567890123", zaszczepiony: false]
]
println "Zadanie1"
def annaPesel = pacjenci.find( peselAnny -> peselAnny.pesel == "23456789012" )
println annaPesel

println "Zadanie2"
def pacjenciPo30 = pacjenci.findAll( pacjent -> pacjent.wiek > 30).collect (pacjent -> pacjent.nazwisko)
println pacjenciPo30

println "Zadanie3"
def czyZaszczepieni = pacjenci.every( pacjent -> pacjent.zaszczepiony)
println czyZaszczepieni

println "Zadanie4"
pacjenci.sort {pacjent -> pacjent.wiek}.eachWithIndex { pacjent, int i ->
    println "${i+1}. ${pacjent.nazwisko}, ${pacjent.imie} ${pacjent.wiek} lat."
}




















