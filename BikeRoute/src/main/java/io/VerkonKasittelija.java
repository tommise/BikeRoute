
package io;

import components.Kaari;
import components.Solmu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class VerkonKasittelija {

    private Map<Long, Kaari> kaaret;
    private Map<Long, Solmu> solmut;
    
    /**
     * Konstruktori verkon käsittelijälle joka alustaa HashMapit
     */
    
    public VerkonKasittelija() {
        this.kaaret = new HashMap<Long, Kaari>();
        this.solmut = new HashMap<Long, Solmu>();
    }
        
    /**
     * Käsittelee tiet ja objektit
     * @param tiet
     * @param objektit 
     */

    public void kasitteleTiet(ArrayList<KarttaTie> tiet, Map<Long, KarttaObjekti> objektit) {
        
    }
        
    /**
     * Palauttaa kaaret
     * @return kaaret listassa
     */
        
    public List<Kaari> getKaaret() {
        List<Kaari> kaaret = new LinkedList<Kaari>();
            
        kaaret = (List<Kaari>) this.kaaret.values();
            
        return kaaret;
    }
        
    /**
     * Palauttaa solmut
     * @return solmut listassa
     */
        
    public List<Solmu> getSolmut() {
        List<Solmu> solmut = new LinkedList<Solmu>();
            
        solmut = (List<Solmu>) this.solmut.values();
            
        return solmut;
    }
}
