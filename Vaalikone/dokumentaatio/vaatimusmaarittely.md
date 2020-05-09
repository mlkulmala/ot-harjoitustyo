# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjä voi verrata omia mielipiteitään vaaliehdokkaiden 
mielipiteisiin ja saada selville, keiden kanssa näkemykset kohtaavat parhaiten.
Sovellus esittää väitteitä, jolloin käyttäjä vastaa missä määrin hän on samaa
mieltä väitteen kanssa. Mielipiteiden yhteneväisyys eri ehdokkaiden kanssa 
ilmoitetaan prosentteina ja ehdokkaat järjestetään tuon prosentin mukaan. 

## Käyttäjät

Sovelluksella on yksi käyttäjärooli eli normaali käyttäjä.

## Käyttöliittymäluonnos

Sovellus aukeaa näkymään, jossa esitellään vaalikoneen periaate ja valitaan vaalipiiri. Seuraavissa
näkymissä esitetään väitteet vastausvaihtoehtoineen. Viimeisen väitteen näkymästä päästään tulosnäkymään, josta ehdokkaat on listattuna. Kunkin ehdokkaan
kohdalta on mahdollista siirtyä erilliseen näkymään tarkastelemaan kyseisen ehdokkaan tietoja.  

## Sovelluksen tarjoama toiminnallisuus

Sovelluksen käyttö ei edellytä kirjautumista eikä käyttäjästä kysytä mitään tietoja.

Väitteet tulevat näkyviin yksi kerrallaan, omina näkyminään. Väitteiden 
välillä voi navigoida painikkeilla *edellinen* ja *seuraava*. 
Vastausvaihtoehtoja on viisi, kukin omana painikkeenaan. Väitteeseen voi 
myös jättää vastaamatta, jolloin se jätetään vertailussa huomioimatta, 
tai siihen voi palata myöhemmin ennen tulosten yhteenvetoa. Aiemmin 
annetut vastaukset säilyvät kyseisen väitteen näkymässä.

Kun käyttäjä painaa vastauspainiketta, näkymään tulostuu lista kolmesta
ehdokkaasta, joiden kanssa mielipiteet käyvät parhaiten yksiin. Lista 
päivittyy, jos vastausta muutetaan, tai kun vastataan seuraaviin väitteisiin.

Viimeisen väitteen näkymässä on painike *siirry tuloksiin*, jolloin 
siirrytään tulosnäkymään. Tulosnäkymässä näkyy kolme ehdokasta kerrallaan.
Kunkin ehdokkaan kohdalla on painike, josta pääsee tarkastelemaan kyseisen
ehdokkaan tietoja tarkemmin. 

Käyttäjän antamia vastauksia ei tallenneta minnekään. Käyttäjän antamat
vastaukset nollautuvat, jos vaalikone aloitetaan alusta.


## Jatkokehitysideoita

- Perusversiossa kaikki ehdokkaat edustavat samaa aluetta, mutta vaalikonetta
on mahdollista laajentaa niin, että alussa valitaan vaalipiiri, jolloin 
ehdokaslistaa rajataan vaalipiirin perusteella.

- Ennen vaalikoneen aloittamista voisi olla myös vaihtoehto pelkästään 
ehdokaslistojen selailuun. 

- Vaalikoneesta on mahdollista tehdä myös painotettu siten, 
että käyttäjän tärkeäksi kokemia aihealueita painotetaan vertailussa 
muita enemmän.

- Käyttäjän antamat vastaukset säilyvät sovelluksen muistissa siihen asti,
kun vaalikone aloitetaan alusta. Sovellukseen olisi mahdollista lisätä (anonyyin)
käyttäjän vastausten tallennus, jolloin käyttäjämäärän kasvaessa käyttäjät 
voisivat verrata, miten muut ovat vastanneet heihin verrattuna.
 
