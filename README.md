# VotingAidApp

Sovelluksen avulla käyttäjä voi verrata omia mielipiteitään vaaliehdokkaiden
mielipiteisiin ja saada selville, keiden kanssa näkemykset kohtaavat parhaiten.
Ehdokkaita ja heidän mielipiteitään on mahdollista myös selata vastaamatta 
itse väitteisiin.

## Dokumentaatio

[Tyoaikakirjanpito](https://github.com/mlkulmala/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[Vaatimusmaarittely](https://github.com/mlkulmala/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/mlkulmala/ot-harjoitustyo/blob/master/Vaalikone/dokumentaatio/arkkitehtuuri.md)


## Releaset

[Viikko 5](https://github.com/mlkulmala/ot-harjoitustyo/releases/download/viikko5/Vaalikone_viikko5.jar)

[Viikko 6](https://github.com/mlkulmala/ot-harjoitustyo/releases/download/viikko6/Vaalikone_viikko6.jar)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla
```
mvn test
```
Testikattavuusraportti luodaan komennolla
```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto *target/site/jacoc/index.html*

### Suoritettavan jarin generointi

Komento
```
mvn package
```
generoi hakemistoon *target* suoritettavan jar-tiedoston Vaalikone-1.0-SNAPSHOT.jar


### JavaDoc

JavaDoc generoidaan komennolla
```
mvn javadoc:javadoc
```
JavaDocia voi tarkastella avaamalla selaimella tiedosto *target/site/apidocs/index.html*

### Checkstyle

Tiedoston checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```
Mahdolliset virheilmitukset selviävät avaamalla selaimella tiedosto *target/site/checkstyle.html*

