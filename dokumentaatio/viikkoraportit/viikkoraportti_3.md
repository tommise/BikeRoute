# Viikkoraportti 3

## Mitä olen tehnyt tällä viikolla?

A* algoritmia sekä omaa ArrayList luokkaa. Etsin myös tietoa XML karttadatan muuttamisesta käsiteltävään muotoon ja nyt alan käsittää miten etenen tämän osalta eteenpäin.

## Miten ohjelma on edistynyt?

Mielestäni suhteellisen hyvin. Vielä käyttöliittymä uupuu, mutta kaksi algoritmia käytännössä valmiita ja omia tietorakenteita lähdetty tekemään.

## Mitä opin tällä viikolla / tänään?

Opin rajaamaan openstreetmap karttadataa sekä sen, miten tätä voisi mahdollisesti Javalla käsitellä.

## Mikä jäi epäselväksi tai tuottanut vaikeuksia? / Kysymyksiä kurssin ohjaajalle?

En saa checkstyleä konffattua projektiini vaikka minkälaista kiekuraa olen kokeillut ja asiaan ratkaisua etsinyt. (_Execution failed for task ':checkstyleMain'.)_ Tiedostossa gradle.build on nyt kommentoitu checkstyle osat pois, koska projekti ei muuten buildaa. Voiko johtua siitä, että käytössä JDK 11? Muuten projektin eteneminen alkaa hyvin hahmottua.

## Mitä teen seuraavaksi?

Siirryn toteuttamaan omaa PriorityQueue rakennetta sekä karttadatan käsittelyä.

### Tuntikirjanpito

su 13.09.
- AStar luokan aloitusta (2h)

ti 15.09.
- AStar luokan jatkoa (1h)
- Heuristiikkaluokka (0,5h)
- Oman ArrayListin tekemistä (1h)

ke 16.09.
- Etsitty tietyn kaupungin eristämistä openstreetmapin karttadatasta, lisätty Helsinki.osm.abf karttadatoihin (1h)
- Etsitty tietoa XML karttadatan muuttamisesta käsiteltävään muotoo, lisätty Osmosis projektiin (2h)

to 17.09.
- AStar algoritmi loppuun + testit (2h)
- Oman ArrayListin viimeisteleminen + testit (3h)
- Checkstylen käyttöönoton ongelman ratkaisua (1h)

pe 18.09.
- Viikkoraportin 3 kirjoittelua (0,5h)

**Yhteensä:** 14h
