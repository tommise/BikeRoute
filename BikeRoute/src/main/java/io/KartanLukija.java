
package io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
import org.openstreetmap.osmosis.core.container.v0_6.NodeContainer;
import org.openstreetmap.osmosis.core.container.v0_6.WayContainer;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;
import org.openstreetmap.osmosis.core.domain.v0_6.Tag;
import org.openstreetmap.osmosis.core.domain.v0_6.Way;
import org.openstreetmap.osmosis.core.domain.v0_6.WayNode;
import org.openstreetmap.osmosis.core.task.v0_6.Sink;

public class KartanLukija implements Sink {
    
    private final ArrayList<KarttaKaari> kaaret;
    private final HashMap<Long, KarttaSolmu> solmut;
    private final List<String> kielletytTunnisteet;
        
    /**
     * Kartanlukijan konstruktori, jossa alustetaan kaaret ja solmut sekä määritetään pyöräilylle kielletyt 
     */
        
    public KartanLukija() {
        this.kaaret = new ArrayList<KarttaKaari>();
        this.solmut = new HashMap<Long, KarttaSolmu>();
        this.kielletytTunnisteet = Arrays.asList("null", "unclassified", "trunk");
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
            solmut.put(id, karttaSolmu);
        
        } else if (entityContainer instanceof WayContainer) {
            
            Way tie = ((WayContainer) entityContainer).getEntity();
            Collection<Tag> tunnisteet = tie.getTags();
            
            if (voikoPyorailla(tunnisteet)) {
                
                KarttaKaari kaari = new KarttaKaari();
                kaari.addTunnisteet(tunnisteet);
                
                for (WayNode solmuKaarenVarrella: tie.getWayNodes()) {
                    
                    KarttaSolmu solmu = solmut.get(solmuKaarenVarrella.getNodeId());
                    
                    kaari.lisaaSolmu(solmu);
                    solmu.addKaari(kaari);
                }
                
                this.kaaret.add(kaari);
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
        return this.kaaret;
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
}



