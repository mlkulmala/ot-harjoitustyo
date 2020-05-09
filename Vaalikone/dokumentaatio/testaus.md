# Testausdokumentti

Ohjelmaa on testattu automatisoiduin yksikkö- ja integraatiotestein JUnitilla.

## Yksikkö- ja integraatiotestaus

Kaikki testit ovat keskittyneet pakkauksen `votingaid.domain` luokkien 
testaamiseen. Testit `CandidateLogicTest` ja `QuestionListTest` testaavat 
DAO-luokkien toimintaa. Sovellusta on testattu ainoastaan tilanteessa, jossa
käyttöohjeen kuvauksen mukainen tietokanta on ollut sovelluksen suoritushakemistossa. 


### Testauskattavuus

Käyttöliittymäluokkia lukuunottamatta sovelluksen testauksen rivikattavuus on 91 % ja 
haarautumakattavuus 76 %.

<img src="https://raw.githubusercontent.com/mlkulmala/ot-harjoitustyo/master/Vaalikone/dokumentaatio/kuvat/testikattavuus.png" width="750">


## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Toiminnallisuudet

Määrittelydokumenttiin ja käyttöohjeeseen listatut toiminnallisuudet on käyty läpi.


