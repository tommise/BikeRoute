# Määrittelydokumentti

### Mitä ongelmaa ratkaiset ja miksi valitsit kyseiset algoritmit/tietorakenteet?

Sovelluksen tarkoitus on auttaa käyttäjää löytämään lyhyin pyöräilyreitti paikasta A paikkaan B ja vertailemaan kuinka kauan käytetyillä algoritmeilla (Dijkstra, A* tai JPS) menee tähän aikaa. Sovellus hyödyntää Openstreetmap karttadataa ja se on toteutettu Javalla. Käyttäjä valitsee reittinsä visuaalisen käyttöliitymän kautta joka toteutetaan JMapViewer tai JXMapViewer2 Java komponenttia hyödyntäen.

### Mitä algoritmeja ja tietorakenteita toteutat työssäsi?

Lyhimmän pyöräilyreitin etsintään hyödynnän Dijkstran, A* sekä JPS algoritmejä. Tietorakenteiksi muodostuu itse toteutetut: List, PriorityQueue...

### Mitä syötteitä ohjelma saa ja miten näitä käytetään?

Ohjelma lukee Openstreetmapin XML muotoista karttadataa. Käyttäjä valitsee lähtöpisteen ja saapumispisteen ohjelman visuaalisen käyttöliittymän kautta sekä käytettävän algoritmin. Valittujen koordinaattien pohjalta luodaan verkko jossa pyörätiet kuvaavat kaaria ja solmut risteyksiä sekä kaarien painot kuvaavat välimatkaa. 

### Tavoitteena olevat aika- ja tilavaativuudet (m.m. O-analyysit)?

Jokaisen algoritmin (Dijkstran, A* sekä JPS) aikavaativuus on O((|E| + |V|) log |V|) missä |E| on kaarien lukumäärä (edges) ja |V| on solmujen lukumäärä (vertices). Tilavaativuudet vastaavasti kaikilla O(|V|).

#### Lähteet:

- [A* algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm), Wikipedia
- [Dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm), Wikipedia
- [Jump point search, JPS](https://en.wikipedia.org/wiki/Jump_point_search), Wikipedia
- [Routing on Openstreetmap](https://wiki.openstreetmap.org/wiki/Routing), Openstreetmap
- Cormen, T., Leiserson, C., Rivest, R. & and Stein, C. Introduction to Algorithms. Third Edition. 

*Tietojenkäsittelytieteen kandidaatti (TKT)*
