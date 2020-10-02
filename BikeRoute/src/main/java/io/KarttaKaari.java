
package io;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import org.openstreetmap.osmosis.core.domain.v0_6.Tag;

public class KarttaKaari {
    
    KarttaSolmu alku;
    KarttaSolmu loppu;    
    private HashMap<Long, KarttaSolmu> solmut;
    private HashMap<String, String> tunnisteet;
    
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
    
    public void setAlku(KarttaSolmu alku) {
        this.alku = alku;
    }
    
    public void setLoppu(KarttaSolmu loppu) {
        this.loppu = loppu;
    }
}