# Toteutusdokumentti

## Ohjelman yleisrakenne

Projektissa toteutettiin kolme erilaista reitinhakualgoritmia. Nämä algoritmit ovat Dijkstra, A* sekä Fringe Search. Käytännössä A* on paranneltu versio Dijkstrasta ja Fringe Search paranneltu versio A*:sta.

Projektin rakenne on seuraava:
- Paketissa _algoritmit_ on A*, Dijkstra ja Fringe Search
- Paketissa _komponentit_ on Heuristiikka, Kaari, Solmu ja Verkko
- Paketissa _tietorakenteet_ on ArrayList, HashSet, PriorityQueue
- Paketissa _io_ on Kartankäsittelijä, KarttaObjekti, KarttaTie, VerkonKasittelija ja VerkonRakentaja
- Paketissa _ui_ on Käyttöliittymä
- Paketissa _util_ on ReitinTulostaja ja SuorituskykyTestaus

## Aika- ja tilavaativuudet

### Algoritmit

Dijkstran ja A* aikavaativuudet ovat O((|E|+|V|) log |V|) missä |E| on kaarien lukumäärä (edges) ja |V| on solmujen lukumäärä (vertices) ja tilavaativuus O(|V|). Vaikka Dijkstralla ja A* on sama aikavaativuus niin käytännössä algoritmeillä on verrattain suurikin nopeusero. Fringe Searchin..

### Tietorakenteet

**ArrayListin** lisäyksen _(add)_ aikavaativuus on O(1) ellei kokoa joudu kasvattamaan jolloin O(n). Noudon _(get)_ aikavaativuus on O(1).

**PriorityQueuen** lisäyksen _(add)_, poiston _(remove)_ ja pollin _(poll)_ aikavaativuudet ovat O(log(n)).

**HashSet** 

## Puutteet ja parannusehdotukset

_kesken_

#### Lähteet

- [A* algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm), Wikipedia
- [Introduction to the A* Algorithm](https://www.redblobgames.com/pathfinding/a-star/introduction.html), Red Blob Games
- [Dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm), Wikipedia
- [Jump point search](https://en.wikipedia.org/wiki/Jump_point_search), Wikipedia
- Cormen, T., Leiserson, C., Rivest, R. & and Stein, C. Introduction to Algorithms. Third Edition.
