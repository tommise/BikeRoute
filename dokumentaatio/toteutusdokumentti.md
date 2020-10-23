# Toteutusdokumentti

## Ohjelman yleisrakenne

Projektissa toteutettiin kolme erilaista reitinhakualgoritmia. Nämä algoritmit ovat Dijkstra, A Star, IDA Star sekä Fringe Search. Käytännössä A Star on paranneltu versio Dijkstrasta, IDA Star paranneltu versio A Starista ja Fringe Search paranneltu versio IDA Starista.

Projektin rakenne on seuraava:
- Paketissa _algoritmit_ on A Star, IDA Star, Dijkstra sekä Fringe Search
- Paketissa _komponentit_ on Heuristiikka, Kaari, Solmu ja Verkko
- Paketissa _tietorakenteet_ on ArrayList, HashMap, HashSet, LinkedList ja PriorityQueue
- Paketissa _io_ on Kartanlukija ja Verkonrakentaja
- Paketissa _suorituskykytestaus_ on suorituskykytestaukselle luokka SuorituskykyTestaus
- Paketissa _tyokalut_ on luokka Matikka joka pitää sisällään omia toteutuksia javan Math.x metodeista _(itseisarvo, kertoma, potenssi, neliöjuuri, radiaani, sini, kosini, arkustangentti ja arkustangentti2)_
- Paketissa _ui_ on Käyttöliittymä sekä käyttöliittymän apuluokat Etappi, EtappienKasittelija ja ReitinPiirtaja

## Aika- ja tilavaativuudet

### Algoritmit

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

### Tietorakenteet

**ArrayListin** lisäyksen _(add)_ aikavaativuus on O(1) ellei kokoa joudu kasvattamaan jolloin O(n). Noudon _(get)_ aikavaativuus on O(1).

**HashSet** 

**HashMap**

**LinkedList**

**PriorityQueuen** lisäyksen _(add)_, poiston _(remove)_ ja pollin _(poll)_ aikavaativuudet ovat O(log(n)).

## Puutteet ja parannusehdotukset

- IDA Star sekä Fringe Search toimivat hitaanlaisesti
- Esimerkkiverkko kattaa vain pienen alueen - OSM tiedoston lukemisen yhteydessä solmut luetaan normaalisti, mutta kaaret eivät yhdisty osm.pbf tiedoston kautta oikein - siksi tehty esimerkkiverkko Talin siirtolapuutarhasta
- Osa matikkaluokan omista Math.x metodeista palvelee vain projektin käyttötarkoitusta eikä rajatapauksia, ei sinänsä projektin toimivuuden kannalta olennainen

#### Lähteet

- [A* algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm), Wikipedia
- [Introduction to the A* Algorithm](https://www.redblobgames.com/pathfinding/a-star/introduction.html), Red Blob Games
- [Dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm), Wikipedia
- [Fringe Search](https://en.wikipedia.org/wiki/Fringe_search), Wikipedia
- [Fringe Search: Beating A* at Pathfinding on Game Maps](http://webdocs.cs.ualberta.ca/~games/pathfind/publications/cig2005.pdf), Bjornsson, Y., Enzenberger, M., Holte R. & Schaeffer J.
- [Introduction to the A* Algorithm](https://www.redblobgames.com/pathfinding/a-star/introduction.html), Red Blob Games
- [Iterative deepening A*](https://en.wikipedia.org/wiki/Iterative_deepening_A*), Wikipedia
- [Time complexity of iterative-deepening-A∗](https://www.sciencedirect.com/science/article/pii/S0004370201000947), Richard E. Korf,  Michael Reid & Stefan Edelkamp
