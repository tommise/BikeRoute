# Määrittelydokumentti

### Mitä ongelmaa ratkaiset ja miksi valitsit kyseiset algoritmit/tietorakenteet?

Sovelluksen tarkoitus on auttaa käyttäjää löytämään lyhyin pyöräilyreitti paikasta A paikkaan B ja vertailemaan kuinka kauan käytetyillä algoritmeilla (Dijkstra, A* tai Fringe Search) menee tähän aikaa. Sovellus hyödyntää Openstreetmap karttadataa ja se on toteutettu Javalla. Käyttäjä valitsee reittinsä visuaalisen käyttöliitymän kautta joka toteutetaan JXMapViewer2 Java komponenttia hyödyntäen.

### Mitä algoritmeja ja tietorakenteita toteutat työssäsi?

Lyhimmän pyöräilyreitin etsintään hyödynnän Dijkstran, A* sekä Fringe Search algoritmejä. Tietorakenteiksi muodostuu itse toteutetut: ArrayList, PriorityQueue sekä HashSet.

### Mitä syötteitä ohjelma saa ja miten näitä käytetään?

Ohjelma lukee Openstreetmapin XML muotoista karttadataa. Käyttäjä valitsee lähtöpisteen ja saapumispisteen ohjelman visuaalisen käyttöliittymän kautta sekä käytettävän algoritmin. Valittujen koordinaattien pohjalta luodaan verkko jossa pyörätiet kuvaavat kaaria ja solmut risteyksiä sekä kaarien painot kuvaavat välimatkaa. 

### Tavoitteena olevat aika- ja tilavaativuudet (m.m. O-analyysit)?

Dijkstran ja A* aikavaativuudet ovat O((|E| + |V|) log |V|) missä |E| on kaarien lukumäärä (edges) ja |V| on solmujen lukumäärä (vertices). Tilavaativuudet vastaavasti O(|V|). Fringe Searchin..

#### Lähteet:

- [A* algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm), Wikipedia
- [Dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm), Wikipedia
- [Fringe Search](https://en.wikipedia.org/wiki/Fringe_search), Wikipedia
- [Routing on Openstreetmap](https://wiki.openstreetmap.org/wiki/Routing), Openstreetmap
- Cormen, T., Leiserson, C., Rivest, R. & and Stein, C. Introduction to Algorithms. Third Edition. 

*Tietojenkäsittelytieteen kandidaatti (TKT)*
