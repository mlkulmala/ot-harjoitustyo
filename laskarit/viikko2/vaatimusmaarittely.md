### Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjä voi verrata omia mielipiteitään vaaliehdokkaiden 
mielipiteisiin ja saada selville, keiden kanssa näkemykset kohtaavat parhaiten.
Sovellus esittää väitteitä, jolloin käyttäjä vastaa missä määrin hän on samaa
mieltä väitteen kanssa. Mielipiteiden yhteneväisyys eri ehdokkaiden kanssa 
ilmoitetaan prosentteina ja ehdokkaat järjestetään tuon prosentin mukaan. 
Ehdokkaita ja heidän mielipiteitään on mahdollista myös selata vastaamatta 
itse väitteisiin.

## Käyttäjät

Sovelluksella on yksi käyttäjärooli eli normaali käyttäjä.

## Käyttöliittymäluonnos

Sovellus aukeaa näkymään, jossa esitellään vaalikoneen periaate. Seuraavissa
näkymissä on väitteet valintapainikkeineen, viimeisessä yhteenveto vaalikoneen
tuloksista.

Aloitusnäkymästä on mahdollista valita myös ehdokkaiden selailu, jolloin
sovellus siirtyy suoraan ehdokaslistaan.

## Toiminnallisuus (perusversio)

Sovelluksen käyttö ei edellytä kirjautumista. Käyttäjä voi aloitunäkymässä 
valita, aloittaako hän vaalikoneen käytön eli vertailun omien ja ehdokkaiden
vastausten välillä vai selaako hän vain ehdokkaita.

Väitteet tulevat näkyviin yksi kerrallaan, omina näkyminään. Väitteiden 
välillä voi navigoida painikkeilla *edellinen* ja *seuraava*. Väitteeseen
voi myös jättää vastaamatta, jolloin se jätetään vertailussa huomioimatta. 
Väitteeseen voi myös palata myöhemmin ennen tulosten yhteenvetoa.

Viimeiden väitteen näkymässä on painike *siirry tuloksiin*, jolloin 
siirrytään yhteenvetonäkymään. Ehdokkaat on järjestetty prosenttien mukaan
siten, että ylimpänä ovat ne ehdokkaat, joiden kanssa mielipiteet käyvät 
parhaiten yksiin.

## Jatkokehitysideoita

- Perusversiossa kaikki ehdokkaat edustavat samaa aluetta, mutta vaalikonetta
on mahdollista laajentaa niin, että alussa valitaan vaalipiiri, jolloin 
ehdokaslistaa rajataan vaalipiirin perusteella.

- Vaalikoneesta on mahdollista tehdä myös painotettu, niin että käyttäjän
tärkeäksi kokeamia aihealueita painotetaan vertailussa muita enemmän.

 
