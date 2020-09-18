package algorithms;

import components.Kaari;
import components.Solmu;
import datastructures.ArrayList;

import java.util.PriorityQueue;

/**
 * Dijkstran algoritmi hyödyntäen PriorityQueta sekä lyhyimmän reitin tallettaminen listaan
 */

public class Dijkstra {
    
    /**
     * Etsii lyhyimmän reitin halutusta alkusolmusta loppuun Dijkstran algoritmin mukaisesti
     * @param alku alkusolmu mistä lähdetään liikkeelle
     * @param loppu tavoitesolmu mihin halutaan päätyä
     */
    
    public void etsi(Solmu alku, Solmu loppu) {
        
        alku.setMinimiEtaisyys(0);
        PriorityQueue<Solmu> pq = new PriorityQueue<Solmu>();
        pq.add(alku);

        while (!pq.isEmpty()) {
            Solmu solmu = pq.poll();
            ArrayList<Kaari> kaaret = solmu.getKaaret();
            
            for (int i = 0; i < kaaret.size(); i++) {
                Kaari kaari = kaaret.get(i);
                Solmu s = kaari.getLoppu();
                double uusiMinimiEtaisyys = solmu.getMinimiEtaisyys() + kaari.getEtaisyys();

                if (uusiMinimiEtaisyys < s.getMinimiEtaisyys()) {
                    pq.remove(solmu);
                    s.setEdellinenSolmu(solmu);
                    s.setMinimiEtaisyys(uusiMinimiEtaisyys);
                    pq.add(s);
                }
            }
        }
        
        luoReitti(loppu);
    }

    /**
     * Luodaan reitti saadun tuloksen perusteella
     * @param tavoiteSolmu solmu johon reitti päättyy
     * @return lyhyin reitti lista muodossa
     */    
    
    public ArrayList<Solmu> luoReitti(Solmu tavoiteSolmu) {
        ArrayList<Solmu> reitti = new ArrayList<Solmu>();

        for (Solmu solmu = tavoiteSolmu; solmu != null; solmu = solmu.getEdellinenSolmu()) {
            reitti.add(solmu);
        }
        
        ArrayList<Solmu> kaannettyReitti = new ArrayList<Solmu>(); 
        
        for (int i = reitti.size() - 1; i >= 0; i--) { 
            kaannettyReitti.add(reitti.get(i)); 
        } 
        
        return kaannettyReitti; 
    }
}