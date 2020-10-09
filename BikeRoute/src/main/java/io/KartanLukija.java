package io;

import crosby.binary.osmosis.OsmosisReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

public class KartanLukija implements Sink {
    
    private Verkko verkko;
    
    private final String polku;
    private final ArrayList<Kaari> kaaret;
    private final HashMap<Long, Solmu> solmut;    

    private final List<String> kielletytTunnisteet;      
    private final ArrayList<KarttaKaari> karttaKaaret;
    private final HashMap<Long, KarttaSolmu> karttaSolmut;
        
    /**
     * Kartanlukijan konstruktori, jossa alustetaan kaaret ja solmut sekä määritetään pyöräilylle kielletyt 
     */
        
    public KartanLukija() {
        this.karttaKaaret = new ArrayList<KarttaKaari>();
        this.karttaSolmut = new HashMap<Long, KarttaSolmu>();
        this.kielletytTunnisteet = Arrays.asList("null", "unclassified", "trunk", "construction", "motorway");
        
        this.kaaret = new ArrayList<Kaari>();
        this.solmut = new HashMap<Long, Solmu>();
        
        this.polku = "./maps/helsinki.osm.pbf";          
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
        
        List<Solmu> solmutVerkolle = new LinkedList<Solmu>(this.solmut.values());
        
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
        
        for (KarttaKaari karttaKaari: karttaKaaret) {
            
            if (karttaKaari.getSolmut().size() > 1) {
                Solmu alku = valitseSolmu(karttaKaari.noudaEnsimmainen());
                Solmu loppu = valitseSolmu(karttaKaari.noudaViimeinen());
                String nimi = karttaKaari.getTunniste("name");
                String tienTyyppi = karttaKaari.getTunniste("highway");
                
                Kaari kaari = new Kaari(alku, loppu, nimi, tienTyyppi);
                this.kaaret.add(kaari);
            }
        }
        
        for (Kaari kaari: kaaret) {
            Solmu alku = kaari.getAlku();
            Solmu loppu = kaari.getLoppu();
            
            alku.addKaari(kaari);
            loppu.addKaari(kaari);
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
    
    public void process(EntityContainer entityContainer) {
        
        if (entityContainer instanceof NodeContainer) { 
            
            Node solmu = ((NodeContainer) entityContainer).getEntity();
            
            long id = solmu.getId();
            double latitude = solmu.getLatitude();
            double longitude = solmu.getLongitude();
            
            KarttaSolmu karttaSolmu = new KarttaSolmu(id, latitude, longitude);
            karttaSolmut.put(id, karttaSolmu);
        
        } else if (entityContainer instanceof WayContainer) {
            
            Way tie = ((WayContainer) entityContainer).getEntity();
            Collection<Tag> tunnisteet = tie.getTags();
            
            if (voikoPyorailla(tunnisteet)) {
                
                KarttaKaari kaari = new KarttaKaari();
                kaari.addTunnisteet(tunnisteet);
                
                for (WayNode solmuKaarenVarrella: tie.getWayNodes()) {
                    
                    KarttaSolmu solmu = karttaSolmut.get(solmuKaarenVarrella.getNodeId());
                    
                    kaari.lisaaSolmu(solmu);
                    solmu.addKaari(kaari);
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
    
    private boolean voikoPyorailla(Collection<Tag> tunnisteet) {
        boolean nimettyTie = false;
        boolean pyoraSallittu = false;
        
        for (Tag tag : tunnisteet) {
            
            String tunniste = tag.getKey();
            
            if (tunniste.equals("name")) {
                nimettyTie = true;
            }
            
            if (tunniste.equals("highway")) {
                pyoraSallittu = !this.kielletytTunnisteet.contains(tag.getValue());
            }
            
            if (nimettyTie && pyoraSallittu) {
                break;
            }
            
        }
        
        return nimettyTie && pyoraSallittu;
    }
        
    /**
     * Palauttaa kaaret listalla
     * @return kaaret listarakenteella
     */
    
    public ArrayList<KarttaKaari> getKaaret() {
        return this.karttaKaaret;
    }
        
    /**
     * Välttämätön komponentti Osmosiksen rajapinnan toteuttamiseksi
     * @param data 
     */
    
    public void initialize(Map<String, Object> data) {
    }
        
    /**
     * Välttämätön komponentti Osmosiksen rajapinnan toteuttamiseksi
     */

    public void complete() {
    }
        
    /**
     * Välttämätön komponentti Osmosiksen rajapinnan toteuttamiseksi
     */
    
    public void release() {
    }  
    
    /**
     * Apuluokka kartan kaarien käsittelemiseen
     */
    
    class KarttaKaari {
      
        private final HashMap<Long, KarttaSolmu> solmut;
        private final HashMap<String, String> tunnisteet;

        /**
         * Konstruktori kartan teille
         */

        public KarttaKaari() {
            this.solmut = new HashMap<Long, KarttaSolmu>();
            this.tunnisteet = new HashMap<String, String>();
        }

        /**
         * Lisää karttakaarelle tunnisteet
         * @param tunnisteet lisättävät tunnisteet listana
         */

        public void addTunnisteet(Collection<Tag> tunnisteet) {
            if (!tunnisteet.isEmpty()) {
                for (Tag tunniste : tunnisteet) {
                    this.tunnisteet.put(tunniste.getKey(), tunniste.getValue());
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
         * Palauttaa solmut listassa
         * @return palautettavat solmut
         */

        public LinkedList<KarttaSolmu> getSolmut() {
            LinkedList<KarttaSolmu> lista = new LinkedList<KarttaSolmu>(this.solmut.values());

            return lista;
        }

        /**
         * Palauttaa ensimmäisen solmun listalla, jos solmuja on useita
         * @return ensimmäinen solmu
         */

        public KarttaSolmu noudaEnsimmainen() {
            long id = this.getSolmut().getFirst().getID();
            KarttaSolmu karttaSolmu = this.solmut.remove(id);

            return karttaSolmu;
        }

        /**
         * Palauttaa viimeisen solmun listalla, jos solmuja on useita
         * @return viimeinen solmu
         */

        public KarttaSolmu noudaViimeinen() {
            long id = this.getSolmut().getLast().getID();
            KarttaSolmu karttaSolmu = this.solmut.remove(id);

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
        private ArrayList<KarttaKaari> kaaret;

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
            this.kaaret = new ArrayList<KarttaKaari>();   
        }

        /**
         * Lisää kaaren listalle
         * @param kaari lisättävä kaari olio
         */

        public void addKaari(KarttaKaari kaari) {
            this.kaaret.add(kaari);
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