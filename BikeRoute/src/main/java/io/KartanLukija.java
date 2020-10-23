package io;

import crosby.binary.osmosis.OsmosisReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import komponentit.Kaari;
import komponentit.Solmu;
import komponentit.Verkko;

import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
import org.openstreetmap.osmosis.core.container.v0_6.NodeContainer;
import org.openstreetmap.osmosis.core.container.v0_6.WayContainer;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;
import org.openstreetmap.osmosis.core.domain.v0_6.Tag;
import org.openstreetmap.osmosis.core.domain.v0_6.Way;
import org.openstreetmap.osmosis.core.domain.v0_6.WayNode;
import org.openstreetmap.osmosis.core.task.v0_6.Sink;

import tietorakenteet.ArrayList;
import tietorakenteet.HashSet;

public class KartanLukija implements Sink {
    
    private Verkko verkko;
    
    private final String polku;
    private final HashMap<Long, Kaari> kaaret;
    private final HashMap<Long, Solmu> solmut;    

    private final HashSet<String> kielletytTunnisteet;      
    private final ArrayList<KarttaKaari> karttaKaaret;
    private final HashMap<Long, KarttaSolmu> karttaSolmut;
        
    /**
     * Kartanlukijan konstruktori
     * Alustetaan kaaret, solmut sekä määritetään pyöräilylle kielletyt tiet
     */
        
    public KartanLukija() {
        this.karttaKaaret = new ArrayList<>();
        this.karttaSolmut = new HashMap<>();
        this.kielletytTunnisteet = new HashSet<>();
        alustaKielletytTunnisteet();
        
        this.kaaret = new HashMap<>();
        this.solmut = new HashMap<>();
        
        this.polku = "./maps/davis.osm.pbf"; 
        //this.polku = "./maps/talinsiirtolapuutarha.osm.pbf";
    }
    
    /**
     * Lukee tiedoston polun perusteella ja alustaa Osmosiksen
     */
    
    public void lueTiedosto() {
        InputStream inputStream = null;
        
        try {
            inputStream = new FileInputStream(this.polku);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        
        OsmosisReader osmosisLukija = new OsmosisReader(inputStream);
        osmosisLukija.setSink(this);
        osmosisLukija.run();
    }    
    
    /**
     * Kasittelee verkon karttadatan perusteella
     */

    public void kasitteleVerkko() {
        kasitteleKaariLista(karttaKaaret);
        ArrayList<Solmu> solmutVerkolle = new ArrayList<>();        
        
        for (Solmu value : solmut.values()) {
            solmutVerkolle.add(value);
        }
        
        this.verkko = new Verkko(solmutVerkolle);
    }  
    
    /**
     * Palauttaa käsitellyn verkon
     * @return verkko olio
     */
    
    public Verkko getVerkko() {
        return this.verkko;
    }
    
    /**
     * Käsittelee luodun kartan kaarilistan perusteella
     * @param karttaKaaret annettu kaarilista
     */
    
    public void kasitteleKaariLista(ArrayList<KarttaKaari> karttaKaaret) {
        
        for (int i = 0; i < karttaKaaret.size(); i++) {
            KarttaKaari karttaKaari = karttaKaaret.get(i);
            
            HashMap<Long, KarttaSolmu> solmutTienVarrella = karttaKaari.getKarttaSolmut();
            
            if (!solmutTienVarrella.isEmpty() && karttaKaari.getSolmuLista().size() > 1) {
                
                Solmu alku = valitseSolmu(karttaKaari.noudaEnsimmainen());
                Solmu loppu = valitseSolmu(karttaKaari.noudaViimeinen());
                String nimi = karttaKaari.getTunniste("name");
                String tienTyyppi = karttaKaari.getTunniste("highway");
                
                Kaari kaari = new Kaari(alku, loppu, nimi, tienTyyppi);
                
                this.kaaret.put(laskeKaarenId(kaari), kaari);
                
                ArrayList<KarttaSolmu> karttaSolmuLista = karttaKaari.getSolmuLista();
                
                for (int j = 0; j < karttaSolmuLista.size(); j++) {
                    KarttaSolmu solmu = karttaSolmuLista.get(j);
                    if (solmu.kaariLaskuri > 1) {
                        kaari = puolitaKaari(solmu, kaari, nimi, tienTyyppi);
                    }
                }
            }
        }
            
        for (Kaari kaari : this.kaaret.values()) {
            Solmu alku = kaari.getAlku();
            Solmu loppu = kaari.getLoppu();
            
            alku.addKaari(kaari);
            loppu.addKaari(kaari);
        }
    }
    
    /**
     * Alustaa kielletyt tunnisteet listalle
     */
    
    private void alustaKielletytTunnisteet() {
        String[] tunnisteet = {"null", "unclassified", "trunk", "construction", "motorway"};
        
        for (int i = 0; i < tunnisteet.length; i++) {
            String tunniste = tunnisteet[i];
            kielletytTunnisteet.add(tunniste);
        }
    }
    
    /**
     * Valitsee tietyn solmun ID:n perusteella solmuista
     * @param karttaSolmu haettava solmu
     * @return palautetaan löydetty solmu
     */
    
    private Solmu valitseSolmu(KarttaSolmu karttaSolmu) {
        
        Solmu solmu = this.solmut.get(karttaSolmu.getID());
        
        if (solmu == null) {
            
            long id = karttaSolmu.getID();
            double latitude = karttaSolmu.getLatitude();
            double longitude = karttaSolmu.getLongitude();
            
            solmu = new Solmu(id, latitude, longitude);
            this.solmut.put(karttaSolmu.getID(), solmu);
        }
        
        return solmu;
    }
    
    /**
     * Käsittelee kartan Osmosis kirjastoa hyödyntäen
     * @param entityContainer karttaan liittyvät oliot konteissaan
     */
    
    @Override
    public void process(EntityContainer entityContainer) {
        
        if (entityContainer instanceof NodeContainer) { 
            
            Node solmu = ((NodeContainer) entityContainer).getEntity();
            
            long id = solmu.getId();
            double latitude = solmu.getLatitude();
            double longitude = solmu.getLongitude();
            
            KarttaSolmu karttaSolmu = karttaSolmut.get(id);
            
            if (karttaSolmu != null) {
                karttaSolmu.setLatitude(latitude);
                karttaSolmu.setLongitude(longitude);
            } else {
                karttaSolmu = new KarttaSolmu(id, latitude, longitude);
                karttaSolmut.put(id, karttaSolmu);
            }
        
        } else if (entityContainer instanceof WayContainer) {
            
            Way tie = ((WayContainer) entityContainer).getEntity();
            
            ArrayList<Tag> tunnisteet = new ArrayList<>();
            
            for (Tag tunniste : tie.getTags()) {
                tunnisteet.add(tunniste);
            }
            
            if (voikoPyorailla(tunnisteet)) {
                
                KarttaKaari kaari = new KarttaKaari(tie.getId());
                kaari.addTunnisteet(tunnisteet);
                
                for (WayNode solmuKaarenVarrella: tie.getWayNodes()) {
                    
                    long id = solmuKaarenVarrella.getNodeId();
                    KarttaSolmu karttaSolmu = this.karttaSolmut.get(id);
                    
                    if (karttaSolmu == null) {
                        karttaSolmu  = new KarttaSolmu(id);
                        karttaSolmut.put(karttaSolmu.getID(), karttaSolmu);
                    }
                    
                    kaari.lisaaSolmu(karttaSolmu);
                    karttaSolmu.addKaari(kaari);
                    karttaSolmu.kaariLaskuri++;
                }
                
                this.karttaKaaret.add(kaari);
            }
        }
    }
    
    /**
     * Tarkistaa onko tie soveltuva pyöräilylle
     * @param tunnisteet tiehen liittyvät tunnisteet
     * @return palauttaa true, jos tiellä voi pyöräillä, muuten false
     */
    
    private boolean voikoPyorailla(ArrayList<Tag> tunnisteet) {
        boolean nimettyTie = false;
        boolean pyoraSallittu = false;
        
        for (int i = 0; i < tunnisteet.size(); i++) {
            
            Tag tagi = tunnisteet.get(i);
            
            String tunniste = tagi.getKey();
            
            if (tunniste.equals("name")) {
                nimettyTie = true;
            }
            
            if (tunniste.equals("highway")) {
                pyoraSallittu = !this.kielletytTunnisteet.contains(tagi.getValue());
            }
            
            if (nimettyTie && pyoraSallittu) {
                break;
            }
            
        }
        
        return nimettyTie && pyoraSallittu;
    }
    
    /**
     * Puolittaa halutun kaaren sillä solmu on löytynyt matkalla
     * @param kartSolmu
     * @param kaari
     * @param nimi
     * @param tienTyyppi
     * @return oikeanpuoleinen kaari
     */
    
    private Kaari puolitaKaari(KarttaSolmu kartSolmu, Kaari kaari, String nimi, String tienTyyppi) {
        this.kaaret.remove(laskeKaarenId(kaari));
        
        Solmu solmu = valitseSolmu(kartSolmu);
        
        Solmu alku = kaari.getAlku();
        Solmu loppu = kaari.getLoppu();
                
        Kaari vasen = new Kaari(alku, solmu, nimi, tienTyyppi);
        Kaari oikea = new Kaari(solmu, loppu, nimi, tienTyyppi);
        
        this.kaaret.put(laskeKaarenId(vasen), vasen);
        this.kaaret.put(laskeKaarenId(oikea), oikea);
        
        return oikea;
    } 
    /**
     * Palauttaa kaaret listalla
     * @return kaaret listarakenteella
     */
    
    public ArrayList<KarttaKaari> getKaaret() {
        return this.karttaKaaret;
    }
    
    /**
     * Laskee kaarelle id:n
     * @param kaari annettu kaari
     * @return id long muodossa
     */
    
    public long laskeKaarenId(Kaari kaari) {
        long solmujenIdSumma = kaari.getAlku().getID() + kaari.getLoppu().getID();
        
        String id = String.valueOf(solmujenIdSumma);
        
        long uusiId = Math.abs(id.hashCode());
        
        return uusiId;
    }    
        
    /**
     * Välttämätön komponentti Osmosiksen rajapinnan toteuttamiseksi
     * Tarvitsee java.util.Map olion
     * @param data 
     */
    
    @Override
    public void initialize(java.util.Map<String, Object> data) {
    }
        
    /**
     * Välttämätön komponentti Osmosiksen rajapinnan toteuttamiseksi
     */

    @Override
    public void complete() {
    }
        
    /**
     * Välttämätön komponentti Osmosiksen rajapinnan toteuttamiseksi
     */
    
    @Override
    public void release() {
    }  
    
    /**
     * Apuluokka kartan kaarien käsittelemiseen
     */
    
    class KarttaKaari {
      
        private long id;
        private final HashMap<Long, KarttaSolmu> solmut;
        private final HashMap<String, String> tunnisteet;

        /**
         * Konstruktori kartan teille
         */

        public KarttaKaari(long id) {
            this.id = id;
            this.solmut = new HashMap<>();
            this.tunnisteet = new HashMap<>();
        }
        
        public long getId() {
            return this.id;
        }

        /**
         * Lisää karttakaarelle tunnisteet
         * @param tunnisteet lisättävät tunnisteet listana
         */

        public void addTunnisteet(ArrayList<Tag> tunnisteet) {
            if (!tunnisteet.isEmpty()) {
                
                for (int i = 0; i < tunnisteet.size(); i++) {
                    Tag tunniste = tunnisteet.get(i);
                    String avain = tunniste.getKey();
                    String arvo = tunniste.getValue();

                    this.tunnisteet.put(avain, arvo);
                }
            }
        }

        /**
         * Palauttaa parametrina annetun tunnisteen
         * @param tunniste String muodossa
         * @return palautettava tunniste
         */

        public String getTunniste(String tunniste) {
            return this.tunnisteet.get(tunniste);
        }      

        /**
         * Lisää kaareen liittyvän solmun HashMapille
         * @param karttaSolmu lisättävä solmu
         */

        public void lisaaSolmu(KarttaSolmu karttaSolmu) {
            this.solmut.put(karttaSolmu.getID(), karttaSolmu);
        }
        
        /**
         * Palauttaa karttaSolmut
         * @return palautettavat solmut
         */
        
        public HashMap<Long, KarttaSolmu> getKarttaSolmut() {
            return this.solmut;
        }
        
        /**
         * Palauttaa solmut listassa
         * @return palautettavat solmut
         */

        public ArrayList<KarttaSolmu> getSolmuLista() {
            
            ArrayList<KarttaSolmu> karttaSolmut = new ArrayList<>();
            
            for (KarttaSolmu karttaSolmu : this.solmut.values()) {
                karttaSolmut.add(karttaSolmu);
            }

            return karttaSolmut;
        }

        /**
         * Palauttaa ensimmäisen solmun listalla, jos solmuja on useita
         * @return ensimmäinen solmu
         */

        public KarttaSolmu noudaEnsimmainen() {
            long karttaSolmuId = this.getSolmuLista().get(0).getID();
            KarttaSolmu karttaSolmu = this.solmut.get(karttaSolmuId);
            this.solmut.remove(karttaSolmuId);

            return karttaSolmu;
        }

        /**
         * Palauttaa viimeisen solmun listalla, jos solmuja on useita
         * @return viimeinen solmu
         */

        public KarttaSolmu noudaViimeinen() {
            int viimeisenIndeksi = this.getSolmuLista().size() - 1;
            
            long karttaSolmuId = this.getSolmuLista().get(viimeisenIndeksi).getID();
            KarttaSolmu karttaSolmu = this.solmut.get(karttaSolmuId);
            this.solmut.remove(karttaSolmuId);

            return karttaSolmu;
        }
    }
    
    /**
     * Apuluokka kartan solmujen käsittelemiseen
     */
    
    class KarttaSolmu {

        private long id;
        private double latitude;
        private double longitude;
        public int kaariLaskuri;
        private HashMap<Long, KarttaKaari> kaaret;        

        /**
         * Karttasolmun konstruktori
         * @param id karttasolmun id
         * @param lat karttasolmun latitude
         * @param lon karttasolmun longitude
         */

        public KarttaSolmu(long id, double lat, double lon) {
            this.id = id;
            this.latitude = lat;
            this.longitude = lon;
            this.kaariLaskuri = 0;
            this.kaaret = new HashMap<>();
        }
        
        public KarttaSolmu(long id) {
            this.id = id;
            this.kaariLaskuri = 0;
            this.kaaret = new HashMap<>();
        }

        /**
         * Lisää kaaren listalle
         * @param kaari lisättävä kaari olio
         */

        public void addKaari(KarttaKaari kaari) {
            this.kaaret.put(kaari.getId(), kaari);
        }   

        /**
         * Palauttaa latituden
         * @return latitude
         */

        public double getLatitude() {
            return this.latitude;
        }

        /**
         * Palauttaa longituden
         * @return longitude
         */

        public double getLongitude() {
            return this.longitude;
        }

        /**
         * Palauttaa id:n
         * @return id
         */

        public long getID() {
            return this.id;
        }

        /**
         * Asettaa latituden
         * @param lat asetettava latitude
         */

        public void setLatitude(double lat) {
            this.latitude = lat;
        }

        /**
         * Asettaa longituden
         * @param lon asetettava longitude
         */

        public void setLongitude(double lon) {
            this.longitude = lon;
        }
    }
}