# Määrittelydokumentti

### Mitä ongelmaa ratkaiset ja miksi valitsit kyseiset algoritmit/tietorakenteet?

Sovelluksen tarkoitus on auttaa käyttäjää löytämään lyhyin pyöräilyreitti paikasta A paikkaan B ja vertailemaan kuinka kauan käytetyillä algoritmeilla (Dijkstra, A* tai Fringe Search) menee tähän aikaa. Sovellus hyödyntää Openstreetmap karttadataa ja se on toteutettu Javalla. Käyttäjä valitsee reittinsä visuaalisen käyttöliitymän kautta joka toteutetaan JXMapViewer2 projektia hyödyntäen.

### Mitä algoritmeja ja tietorakenteita toteutat työssäsi?

Lyhimmän pyöräilyreitin etsintään hyödynnän Dijkstran, A* sekä Fringe Search algoritmejä. Tietorakenteiksi muodostuu itse toteutetut: ArrayList, PriorityQueue, HashMap, HashSet, LinkedList ja PriorityQueue.

Dijkstra hyödyntää:
- PriorityQueuta tallettamaan solmut siinä järjestyksessä, että pienin etäisyys _g arvo_ on ensimmäisenä
- ArrayListiä kaarien läpikäyntiin

A* hyödyntää:
- PriorityQueuta tallettamaan solmut siinä järjestyksessä, että pienin arvioitu etäisyys _f arvo_ on ensimmäisenä
- HashSettiä pitämään kirjaa siitä, mitkä solmut on jo käsitelty
- ArrayListiä kaarien läpikäyntiin

IDA* hyödyntää:
- ArrayListiä tallettaakseen solmuja kekomaiseen tapaan

Fringe Search hyödyntää:
- Kaksisuuntaista linkitettyä listää _(doubly linked list)_ solmuille
- Hajautustaulua etäisyyksille _g arvot_ sekä vanhemmalle solmulle
- ArrayListiä kaarien läpikäyntiin

### Mitä syötteitä ohjelma saa ja miten näitä käytetään?

Ohjelma rakentaa itse verkon tai lukee vaihtoehtoisesti Openstreetmapin XML muotoista karttadataa. Käyttäjä valitsee lähtöpisteen ja saapumispisteen ohjelman visuaalisen käyttöliittymän kautta sekä käytettävän algoritmin. Valittujen koordinaattien pohjalta piirtyy käyttöliittymään lyhin reitti sekä lyhimmän reitin kokonaismatka. 

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

**Fringe Search:**
Aikavaativuus:
Tilavaativuus:

#### Lähteet:

- [A* algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm), Wikipedia
- [Dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm), Wikipedia
- [Fringe Search: Beating A* at Pathfinding on Game Maps](http://webdocs.cs.ualberta.ca/~games/pathfind/publications/cig2005.pdf), Bjornsson, Y., Enzenberger, M., Holte R. & Schaeffer J.
- [Fringe Search](https://en.wikipedia.org/wiki/Fringe_search), Wikipedia
- [Iterative deepening A*](https://en.wikipedia.org/wiki/Iterative_deepening_A*), Wikipedia
- [Time complexity of iterative-deepening-A∗](https://www.sciencedirect.com/science/article/pii/S0004370201000947), Richard E. Korf,  Michael Reid & Stefan Edelkamp

_Koulutusohjelma: Tietojenkäsittelytieteen kandidaatti (TKT)_
