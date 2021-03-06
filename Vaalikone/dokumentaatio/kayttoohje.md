# Käyttöohje

Lataa tiedosto [Vaalikone_viikko5.jar](https://github.com/mlkulmala/ot-harjoitustyo/releases/download/viikko5/Vaalikone_viikko5.jar)

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla
```
java -jar Vaalikone_viikko5.jar
```

## Vaalikoneen aloittaminen

Sovellus avautuu näkymään, jossa Vaalikoneen toiminta esitellään lyhyesti. Käyttäjä saa valittua
vaalikoneeseen oman vaalipiirinsä ehdokkaat valikosta valitsemalla. Painamalla *Aloita* vaalikone 
käynnistyy, jolloin siirrytään seuraavaan näkymään ja ensimmäiseen väitteeseen.

<img src="https://raw.githubusercontent.com/mlkulmala/ot-harjoitustyo/master/Vaalikone/dokumentaatio/kuvat/welcomeView2.png" width="500">

## Vastaaminen vaalikoneen väitteisiin

Kysymysnäkymässä käyttäjä voi valita viidestä vaihtoehdosta, mitä mieltä hän on esitetystä väitteestä.

<img src="https://raw.githubusercontent.com/mlkulmala/ot-harjoitustyo/master/Vaalikone/dokumentaatio/kuvat/questionView.png" width="500">

Joka kerta käyttäjän painaessa vastausvaihtoehtoa näkymän alalaitaan tulostuu kolme ehdokasta, joiden mielipide on
lähimpänä käyttäjän antamaa vastausta sekä prosenttiluku, joka kertoo kuinka hyvin mielipiteet ovat vastanneet toisiaan kaikkien tähän asti vastattujen väitteiden osalta. Väitteiden välillä voi liikkua painikkeilla *edellinen* ja 
*seuraava*. Samaan väitteeseen voi vastata monta kertaa ja aiempien väitteiden vastaukset säilyvät näkymästä toiseen siirryttäessä.

Viimeisen väitteen jälkeen päästään vaalikoneen tuloksiin klikkaamalla *Siirry tuloksiin*, jolloin
näkymä vaihtuu tulosnäkymään.

## Vaalikoneen tulokset

Tulosnäkymässä on listattuna kolme ehdokasta kerrallaan:

<img src="https://raw.githubusercontent.com/mlkulmala/ot-harjoitustyo/master/Vaalikone/dokumentaatio/kuvat/resultsView.PNG" width="500">

Jokaisen ehdokkaan kohdalla on painike *Tutustu ehdokkaaseen*, josta pääsee ehdokkaan esittelevään näkymään.

<img src="https://raw.githubusercontent.com/mlkulmala/ot-harjoitustyo/master/Vaalikone/dokumentaatio/kuvat/candidateView.png" width="500">

*Aloita alusta* -painiketta painamalla vaalikone käynnistyy uudelleen ja edelliset vastaukset nollautuvat.

