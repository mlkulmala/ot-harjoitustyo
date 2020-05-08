# Arkkitehtuurikuvaus

## Rakenne

VotingAid-sovelluksen pakkausrakenne on seuraava:

<img src="https://raw.githubusercontent.com/mlkulmala/ot-harjoitustyo/master/Vaalikone/dokumentaatio/kuvat/luokkakaavio_Vaalikone.png" width="400">

- `votingaid.main` sovelluksen käynnistys
- `votingaid.ui`: JavaFX:llä toteutettu käyttöliittymä 
- `votingaid.domain`: sovelluslogiikka
- `votingaid.dao`: tiedon pysyväistallennus 

## Käyttöliittymä

Käyttöliittymässä on neljä erillistä näkymää, jotka on toteutettu omina luokkinaan.

- aloitusnäkymä `(WelcomeView)`
- väitteet ja vastausvaihtoehdot `(QuestionView)`
- vaalikoneen tulokset `(ResultsView)`
- ehdokkaan esittely `(CandidateView)`



## Sovelluslogiikka

`Candidate`-luokan oliot pitävät sisällään ehdokkaiden tiedot. `AnswerList` pitää 
kirjaa kunkin ehdokkaan vastauksista, väitekohtaisista osumaprosenteista, jotka 
ilmaisevat missä määrin ehdokkaan ja käyttäjän antamat näkemykset käyvät yksiin 
kyseisen väitteen kohdalla sekä vaalikoneen etenemisen myötä päivitettävästä 
osumaprosentista, joka ilmaisee mielipiteiden yhtenevyyttä kaikkien sillä hetkellä
vastattujen väitteiden osalta. 

<img src="https://raw.githubusercontent.com/mlkulmala/ot-harjoitustyo/master/Vaalikone/dokumentaatio/kuvat/luokkakaavio_Answers.png" width="500">

`CandidateLogic` sisältää listan kaikista `AnswerList`-olioista ja sen myötä 
ehdokkaista. `CandidateLogic`-luokan metodi `compareToCandidateAnswers` 
suorittaa vertailun käyttäjän ja ehdokkaan välillä ja järjestää ehdokkaat 
järjestykseen yllä mainitun osumaprosentin mukaan.

Luokka `Question` sisältää väitteen, tiedon siitä, onko väitteeseen jo vastattu, sekä käyttäjän
antaman vastauksen, joka säilyy siihen asti, kun vaalikone aloitetaan alusta. `QuestionList` pitää
kirjaa kaikista väitteistä sekä tietoa siitä, mikä väite on vastausvuorossa eli aktiivinen.

<img src="https://raw.githubusercontent.com/mlkulmala/ot-harjoitustyo/master/Vaalikone/dokumentaatio/kuvat/luokkakaavio_Questions.png" width="300">



## Tietojen haku 

Pakkauksen `votingaid.dao` luokka `CandidateMemoryDao` hakee ehdokkaiden tiedot 
ja heidän vastauksensa ja luokka `QuestionMemoryDao` hakee 
kysymykset tietokannasta. Sovelluslogiikka ei käytä luokkia suoraan, vaan ne on
eristetty rajapintojen `CandidateDao` ja `QuestionDao` taakse. Näin tiedon 
tallennustapaa voidaan helposti vaihtaa.

### Tietokanta

Sovellus olettaa, että suoritushakemistossa on `votingAid.mv.db`-niminen tietokanta.
Tietokannan rakenne on seuraava:

<img src="https://raw.githubusercontent.com/mlkulmala/ot-harjoitustyo/master/Vaalikone/dokumentaatio/kuvat/tietokantakaavio.png" width="750">



## Päätoiminnallisuudet

Sovelluksen toimintalogiikka sekvenssikaaviona.

Sovellus avautuu näkymään, jossa esitellään Vaalikoneen toiminta. Vaalikone
käynnistyy klikkaamalla *startButton*-painiketta.

<img src="https://raw.githubusercontent.com/mlkulmala/ot-harjoitustyo/master/Vaalikone/dokumentaatio/kuvat/sekvenssikaavio_ui.png" width="800">

Kun käyttäjä vastaa väitteeseen klikkaamalla jotain vastauspainiketta, sovelluksen kontrolli etenee seuraavasti:

<img src="https://raw.githubusercontent.com/mlkulmala/ot-harjoitustyo/master/Vaalikone/dokumentaatio/kuvat/sekvenssikaavio_compare.png" width="700">

Tapahtumankäsittelijä kutsuu luokan `CandidateLogic` metodia `compareToCandidateAnswers`, joka saa
parametriksi vastattavan väitteen id-numeron ja käyttäjän antaman vastauksen. `CandidateLogic` käy 
läpi listallaan pitämät `AnswerList`-oliot, vertaa käyttäjän vastausta kunkin ehdokkaan vastaukseen 
ja laskee ja tallentaa vastaavuusprosentin `AnswerList`-olioon. Kun kaikki oliot on käyty läpi, metodi 
palauttaa `AnswerList`-olioiden järjestetyn listan. jonka mukaan käyttäjää lähimmäs vastanneet ehdokkaat 
listataan kärkeen.

Tulosnäkymässä järjestetyn `AnswerList`-olioiden listalta poimitaan oikeat tiedot kultakin ehdokkaalta.



## Ohjelman rakenteeseen jääneet heikkoudet

Käyttöliittymä on erotettu neljään eri luokkaan, joiden koodi on jaettu useisiin metodeihin. 
Metodit eivät kuitenkaan noudata kovin yhtenäistä logiikkaa. Käyttöliittymän luokat käyttävät 
myös melko samankaltaisia ja osittain myös samoja metodeja.

Aloitusnäkymässä vaalipiirin valinta on nyt koodiin kirjoitettu (ainoa vaihtoehto on "Helsinki"),
koska sovelluksen mukana tuleva tietokanta sisältää vain yhden vaalipiirin ehdokkaita. Parempi 
toteutus olisi esimerkiksi automaattisella tekstintäydennyksellä toimiva tekstikenttä, joka 
tarjoaisi tietokannasta löytyvät vaihtoehdot.

Sovellus sisältää vain vaalikoneen käyttäjän osuuden eli sovelluksessa ei ole mahdollista lisätä 
tai muuttaa vaalikoneen käyttämää tietokantaa.


