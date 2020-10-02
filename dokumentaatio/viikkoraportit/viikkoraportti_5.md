# Viikkoraportti 5

## Mitä olen tehnyt tällä viikolla?

_io_ pakkaus on edennyt eli XML muotoisen datan lukemista ja siitä verkon tekemistä. Tein myös alustavan tekstikäyttöliittymän, joka mahdollisesti muutetaan vielä visuaaliseksi käyttöliittymäksi. Lisäsin suorituskykyvertailun, josta kuitenkin kävi ilmi, että jotain fiksattavaa A* algoritmissä saattaisi olla paikallaan.

## Miten ohjelma on edistynyt?

Karttadatan lukeminen on edistynyt harppauksen. JPS:n toteutus, parin oman tietorakenteen sekä mahdollisen visuaalisen käyttöliittymän toteutus on siis vielä jäljellä.

## Mitä opin tällä viikolla / tänään?

Suurin osa viikosta meni XML kikkailuihin, joten näistä käytännön kautta mukavasti opin ja etenkin Osmosis kirjastosta.

## Mikä jäi epäselväksi tai tuottanut vaikeuksia? / Kysymyksiä kurssin ohjaajalle?

Karttadatan lukeminen osoittautui hankalaksi, monia duplikaattisolmuja löytyy edelleen ja en löytänyt vielä syytä. Hieman haastavampi viikko oli selkeästi. AStarkaan ei tällä hetkellä toimi itse karttadatan kanssa, esimerkkiverkon kanssa taas toimii kuin ennen. A* on myös hitaampi kuin Dijkstra nopeustesteissä, joten jotain on pielessä. Tämän koitan korjailla pikimmiten. JPS:n toteutuksessa tökkii se, etten ole vielä saanut karttadataa käsiteltävään muotoon ja se, miten lähden JPS:ää toteuttamaan ilman ruudukkototeutusta. Alkuviikon pajaohjaus ei varmaankaan olisi pahitteeksi.

## Mitä teen seuraavaksi?

Pyrin viimeistelemään kartan lukemisen ja tähän liittyvät haasteet mahdollisimman pian.

### Tuntikirjanpito

ma 28.09.
- Suorituskykytestauksen tekoa (1h)

ti 29.09.
- _io_ pakkausta, karttadatan luokkien tekoa (3h)

ke 30.09.
- _io_ pakkausta, karttadatan luokkien tekoa (2h)

to 1.10.
- Vertaisarviointi (1h)
- _io_ pakkausta, karttadatan luokkien tekoa ja muun rungon muuttamista tämän mukaiseksi (1h)

pe 2.10.
- _io_ pakkausta, karttadatan jatkoa (3h)
- Viikkoraportti 5 (0,5h)

**Yhteensä:** 11,5h
