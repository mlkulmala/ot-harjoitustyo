# Arkkitehtuurikuvaus

## Rakenne

VotingAid-sovelluksen pakkausrakenne on seuraava:

<img src="https://raw.githubusercontent.com/mlkulmala/ot-harjoitustyo/master/Vaalikone/dokumentaatio/kuvat/pakkauskaavio1.png" width="300">

- `votingaid.main` TAITAA OLLA IHAN TURHA PAKKAUS, MUUTA TÄMÄ
- `votingaid.ui`: JavaFX:llä toteutetun käyttöliittymä 
- `votingaid.domain`: sovelluslogiikka
- `votingaid.dao`: tiedon pysyväitallennus 

## Käyttöliittymä

Käyttöliittymässä on kolme erillistä näkymää, jotka on toteutettu omina luokkinaan.

- aloitusnäkymä `(WelcomeView)`
- kysymykset ja vastausvaihtoehdot `(QuestionView)`
- vaalikoneen tulokset `(ResultsView)`


## Sovelluslogiikka

<img src="https://raw.githubusercontent.com/mlkulmala/ot-harjoitustyo/master/Vaalikone/dokumentaatio/kuvat/luokkakaavio_Answers.png" width="500">

`Candidate`-luokan oliot pitävät sisällään ehdokkaan tiedot. `AnswerList` pitää 
kirjaa kunkin ehdokkaan vastauksista, kysymyskohtaisista prosenteista, jotka 
ilmaisevat missä määrin ehdokkaan ja käyttäjän antamat näkemykset käyvät yksiin 
sekä vaalikoneen etenemisen myötä päivittävästä prosenttiluvusta, joka ilmaisee 
mielipiteiden yhtenevyyttä kullakin hetkellä vastattujen kysymysten osalta. 

`CandidateLogic` sisältää listan kaikista `AnswerList`-olioista ja sen myötä 
ehdokkaista. `CandidateLogic`-luokan metodi `compareToCandidateAnswers` 
suorittaa vertailun käyttäjän ja ehdokkaan välillä ja järjestää ehdokkaat 
järjestykseen sen mukaan, miten näkymykset vastaavat toisiaan.

## Tietojen haku 

Pakkauksen `votingaid.dao` luokka `CandidateMemoryDao` hakee ehdokkaiden tiedot 
ja heidän vastauksensa tietokannasta ja luokka `QuestionMemoryDao` hakee 
kysymykset tiedostosta. Sovelluslogiikka ei käytä luokkia suoraan, vaan ne on
eristetty rajapintojen `CandidateDao` ja `QuestionDao` taakse. Näin tiedon 
tallennustapaa voidaan helposti vaihtaa.

### Tiedostot

XXXXX Tämä osuus saattaa mennä uusiksi XXXXXX

### Tietokanta

XXXX Työn alla XXXX 

## Päätoiminnallisuudet

Sovelluksen toimintalogiikka sekvenssikaaviona.

Sovellus avautuu näkymään, jossa esitellään Vaalikoneen toiminta. Vaalikone
käynnistyy klikkaamalla *startButton*-painiketta.

<img src="https://raw.githubusercontent.com/mlkulmala/ot-harjoitustyo/master/Vaalikone/dokumentaatio/kuvat/sekvenssikaavio_ui.png" width="500">




