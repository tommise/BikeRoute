# Toteutusdokumentti

## Ohjelman yleisrakenne

Projektissa toteutettiin kolme erilaista reitinhakualgoritmia. Nämä algoritmit ovat Dijkstra, A* sekä JPS (Jump Point Search). Käytännössä A* on paranneltu versio Dijkstrasta ja Jump Point Search paranneltu versio A*:sta.

Projektin rakenne on seuraava:
- Paketissa _algorithms_ on A*, Dijkstra ja JPS
- Paketissa _components_ on Heuristiikka, Kaari, Solmu, SolmuJPS ja Verkko
- Paketissa _datastructures_ on ArrayList, HashSet, PriorityQueue
- Paketissa _io_ on Kartankäsittelijä, KarttaObjekti, KarttaTie, VerkonKasittelija ja VerkonRakentaja
- Paketissa _ui_ on Käyttöliittymä
- Paketissa _util_ on ReitinTulostaja ja SuorituskykyTestaus

## Aika- ja tilavaativuudet

### Algoritmit

Dijkstran, A* ja JPS aikavaativuudet ovat O((|E|+|V|) log |V|) missä |E| on kaarien lukumäärä (edges) ja |V| on solmujen lukumäärä (vertices) ja tilavaativuus O(|V|). Vaikka kaikilla reitinhakualgoritmeillä on sama aikavaativuus niin käytännössä algoritmeillä on verrattain suurikin nopeusero.

### Tietorakenteet

**ArrayListin** lisäyksen _(add)_ aikavaativuus on O(1) ellei kokoa joudu kasvattamaan jolloin O(n). Noudon _(get)_ aikavaativuus on O(1).

**PriorityQueuen** lisäyksen _(add)_, poiston _(remove)_ ja pollin _(poll)_ aikavaativuudet ovat O(log(n)).

# Puutteet ja parannusehdotukset

_kesken_

# Lähteet

[A* algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm), Wikipedia
[Introduction to the A* Algorithm](https://www.redblobgames.com/pathfinding/a-star/introduction.html), Red Blob Games
[Dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm), Wikipedia
[Jump point search](https://en.wikipedia.org/wiki/Jump_point_search), Wikipedia
Cormen, T., Leiserson, C., Rivest, R. & and Stein, C. Introduction to Algorithms. Third Edition.
