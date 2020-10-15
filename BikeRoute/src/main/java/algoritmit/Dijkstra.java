package algoritmit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import komponentit.Kaari;
import komponentit.Solmu;

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
        PriorityQueue<Solmu> prioriteettijono = new PriorityQueue<Solmu>(luoPrioriteetti());
        prioriteettijono.add(alku);

        while (!prioriteettijono.isEmpty()) {
            Solmu nyky = prioriteettijono.poll();
            ArrayList<Kaari> kaaret = nyky.getKaaret();
            
            for (int i = 0; i < kaaret.size(); i++) {
                Kaari kaari = kaaret.get(i);
                Solmu solmu = kaari.getLoppu();
                double uusiMinimiEtaisyys = nyky.getMinimiEtaisyys() + kaari.getEtaisyys();

                if (uusiMinimiEtaisyys < solmu.getMinimiEtaisyys()) {
                    prioriteettijono.remove(nyky);
                    solmu.setEdellinenSolmu(nyky);
                    solmu.setMinimiEtaisyys(uusiMinimiEtaisyys);
                    
                    prioriteettijono.add(solmu);
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
            Solmu solmu = reitti.get(i);
            kaannettyReitti.add(solmu); 
        }
        
        return kaannettyReitti; 
    }
    
    /**
     * Luodaan Comparator joka lasketaan minimi etäisyyden mukaan
     * @return comparator olio
     */
    
    public Comparator luoPrioriteetti() {
        
        Comparator comp = new Comparator<Solmu>() {
            
            @Override
            public int compare(Solmu s1, Solmu s2) {
                
                return Double.compare(s1.getMinimiEtaisyys(), s2.getMinimiEtaisyys());
            }
        };
        
        return comp;
    }
}