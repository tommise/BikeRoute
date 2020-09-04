# Määrittelydokumentti

### Mitä ongelmaa ratkaiset ja miksi valitsit kyseiset algoritmit/tietorakenteet?

Sovelluksen tarkoitus on auttaa käyttäjää löytämään lyhyin pyöräilyreitti paikasta A paikkaan B. Lyhyimmän polun löytämiseen paras algoritmi lienee Dijkstran algoritmi tai A*. Sovellus hyödyntää Openstreetmap karttadataa ja se on tehty Javalla. Käyttäjä valitsee reittinsä GUI:n pohjalta joka toteutetaan JMapViewer tai JXMapViewer2 Java komponenttia hyödyntäen.

### Mitä algoritmeja ja tietorakenteita toteutat työssäsi?

Lyhimmän pyöräilyreitin etsintään hyödynnän Dijkstran algoritmia sekä mahdollisesti A* toteutusta. Dijkstran algoritmi toteutetaan keolla.

### Mitä syötteitä ohjelma saa ja miten näitä käytetään?

Ohjelma lukee Openstreetmapin XML muotoista karttadataa. Käyttäjä valitsee lähtöpisteen ja saapumispisteen ohjelman visuaalisen käyttöliittymän kautta. Valittujen koordinaattien pohjalta luodaan verkko jossa pyörätiet kuvaavat kaaria ja solmut risteyksiä. Kaarien painot kuvaavat välimatkaa metreissä risteyksestä risteykseen.

### Tavoitteena olevat aika- ja tilavaativuudet (m.m. O-analyysit)?

Dijkstran algoritmi toteutetaan kekoa käyttäen, jolloin aikavaativuudeksi saadaan O((E + V) log V) sekä tilavaativuudeksi O(V). Vastaavat aikavaativuudet ovat myös A* algoritmilla.

#### Lähteet:

Openstreetmap, Routing - https://wiki.openstreetmap.org/wiki/Routing (katsottu 3.9.2020)

*Kurssin hallintaan liittyvistä syistä määrittelydokumentissä tulee mainita opinto-ohjelma johon kuulut:*
Tietojenkäsittelytieteen kandidaatti (TKT)
