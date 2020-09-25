
package io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
import org.openstreetmap.osmosis.core.container.v0_6.NodeContainer;
import org.openstreetmap.osmosis.core.container.v0_6.RelationContainer;
import org.openstreetmap.osmosis.core.container.v0_6.WayContainer;
import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;
import org.openstreetmap.osmosis.core.domain.v0_6.Way;
import org.openstreetmap.osmosis.core.task.v0_6.Sink;

public class KartanKasittelija implements Sink {
    
    private ArrayList<KarttaTie> tiet;
    private Map<Long, KarttaObjekti> objektit;
        
    /**
     * Kartan käsittelijän konstruktori
     */
        
    public KartanKasittelija() {
        this.tiet = new ArrayList<KarttaTie>();
        this.objektit = new HashMap<Long, KarttaObjekti>();
    }
        
    /**
     * Käsittelee objektit ja tiet sekä tekee näistä käsiteltäviä objekteja
     * @param entityContainer 
     */
        
    public void process(EntityContainer entityContainer) {
        
        if (entityContainer instanceof NodeContainer) { 
            Node solmu = ((NodeContainer) entityContainer).getEntity();
            
        } else if (entityContainer instanceof WayContainer) {
            Way tie = ((WayContainer) entityContainer).getEntity();
            
        } else if (entityContainer instanceof RelationContainer) {
            Entity entity = ((RelationContainer) entityContainer).getEntity();
        }
    }
    
    /**
     * Palauttaa tiet listalla
     * @return tiet listarakenteella
     */
    
    public ArrayList<KarttaTie> getTiet() {
        return this.tiet;
    }
    
    /**
     * Palauttaa objektit
     * @return objektit map muodossa
     */
    
    public Map<Long, KarttaObjekti> getObjektit() {
        return this.objektit;
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

