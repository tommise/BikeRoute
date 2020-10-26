# Määrittelydokumentti

### Mitä ongelmaa ratkaiset ja miksi valitsit kyseiset algoritmit/tietorakenteet?

Sovelluksen tarkoitus on auttaa käyttäjää löytämään lyhyin reitti paikasta A paikkaan B ja vertailemaan kuinka kauan käytetyillä algoritmeilla (Dijkstra, A Star tai IDA Star) menee tähän aikaa. Käyttäjä valitsee reittinsä visuaalisen käyttöliitymän kautta joka toteutetaan JXMapViewer2 projektia hyödyntäen. Projekti on toteutettu Javalla.

### Mitä algoritmeja ja tietorakenteita toteutat työssäsi?

Lyhimmän reitin etsintään hyödynnän Dijkstran, A Star sekä IDA Star algoritmejä. Tietorakenteiksi muodostuu itse toteutetut: ArrayList, HashMap, HashSet, PriorityQueue ja Stack.

Dijkstra hyödyntää:
- PriorityQueuta tallettamaan solmut siinä järjestyksessä, että pienin etäisyys _g arvo_ on ensimmäisenä
- ArrayListiä kaarien läpikäyntiin

A* hyödyntää:
- PriorityQueuta tallettamaan solmut siinä järjestyksessä, että pienin arvioitu etäisyys _f arvo_ on ensimmäisenä
- HashSettiä pitämään kirjaa siitä, mitkä solmut on jo käsitelty
- ArrayListiä kaarien läpikäyntiin

IDA* hyödyntää:
- Pinoa (stack) tallettaakseen solmuja
- ArrayListiä kaarien läpikäyntiin

### Mitä syötteitä ohjelma saa ja miten näitä käytetään?

Ohjelma lukee kaksi eri karttaa .csv tiedostojen pohjalta ja luo näistä algoritmeille käyttöön verkon. Käyttäjä valitsee lähtöpisteen ja saapumispisteen ohjelman visuaalisen käyttöliittymän kautta sekä käytettävän algoritmin. Valittujen koordinaattien pohjalta piirtyy käyttöliittymään lyhin reitti sekä lyhimmän reitin kokonaismatka. 

### Tavoitteena olevat aika- ja tilavaativuudet (m.m. O-analyysit)?

**Dijkstra:**
Aikavaativuus: O((|E| + |V|) log |V|) missä |E| on kaarien lukumäärä (edges) ja |V| on solmujen lukumäärä (vertices)
Tilavaativuus: O(|V|)

**A Star:**
Aikavaativuus: O((|E| + |V|) log |V|) missä |E| on kaarien lukumäärä (edges) ja |V| on solmujen lukumäärä (vertices)
Tilavaativuus: O(|V|)

**IDA Star:**
Aikavaativuus: O(b^d)
Tilavaativuus: O(d) 

Missä b on uloimpien solmujen lapsisolmujen lukumäärä _(branching factor)_ ja missä d on ensimmäisen löydetyn reitin syvyys

#### Lähteet:

- [A* algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm), Wikipedia
- [Dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm), Wikipedia
- [Introduction to the A* Algorithm](https://www.redblobgames.com/pathfinding/a-star/introduction.html), Red Blob Games
- [Iterative deepening A*](https://en.wikipedia.org/wiki/Iterative_deepening_A*), Wikipedia
- [Time complexity of iterative-deepening-A∗](https://www.sciencedirect.com/science/article/pii/S0004370201000947), Richard E. Korf,  Michael Reid & Stefan Edelkamp

_Koulutusohjelma: Tietojenkäsittelytieteen kandidaatti (TKT)_
