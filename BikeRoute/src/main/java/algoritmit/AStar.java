
package algoritmit;

import java.util.Comparator;

import komponentit.Kaari;
import komponentit.Solmu;

import tietorakenteet.ArrayList;
import tietorakenteet.HashSet;
import tietorakenteet.PriorityQueue;

/**
 * A* algoritmi sekä lyhyimmän reitin tallettaminen listaan
 */

public class AStar {
    
    /**
     * Etsii lyhyimmän reitin halutusta alkusolmusta loppuun A* algoritmin mukaisesti
     * @param alku alkusolmu mistä lähdetään liikkeelle
     * @param loppu tavoitesolmu mihin halutaan päätyä
     */

    public void etsi(Solmu alku, Solmu loppu) {
    
        HashSet<Solmu> kasitelty = new HashSet<>();
        
        PriorityQueue<Solmu> prioriteettijono = new PriorityQueue<>(luoPrioriteetti());
        prioriteettijono.add(alku);
        alku.setG(0);        

        while (!prioriteettijono.isEmpty()) {
            Solmu nyky = prioriteettijono.poll();
            ArrayList<Kaari> kaaret = nyky.getKaaret();
            
            kasitelty.add(nyky);            

            for (int i = 0; i < kaaret.size(); i++) {
                Kaari kaari = kaaret.get(i);
                Solmu solmu = kaari.getLoppu();
                
                if (kasitelty.contains(solmu)) {
                    continue;
                }
                
                double uusiG = nyky.getG() + kaari.getEtaisyys();
                
                if (uusiG < solmu.getG()) {
                    solmu.setG(uusiG);
                    solmu.setEdellinenSolmu(nyky);
                    
                    double f = uusiG + solmu.euklidinenHeuristiikka(loppu);
                    solmu.setF(f);
                    
                    if (!prioriteettijono.contains(solmu)) {
                        prioriteettijono.add(solmu);
                    }
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
        ArrayList<Solmu> reitti = new ArrayList<>();

        for (Solmu solmu = tavoiteSolmu; solmu != null; solmu = solmu.getEdellinenSolmu()) {
            reitti.add(solmu);
        }
        
        ArrayList<Solmu> kaannettyReitti = new ArrayList<>(); 
        
        for (int i = reitti.size() - 1; i >= 0; i--) {
            Solmu solmu = reitti.get(i);
            kaannettyReitti.add(solmu);
        }
        
        return kaannettyReitti;
    }
    
    /**
     * Luodaan Comparator joka lasketaan prioriteetti F scoren mukaan
     * @return comparator olio
     */
    
    public Comparator luoPrioriteetti() {
        
        Comparator comp = new Comparator<Solmu>() {
            
            @Override
            public int compare(Solmu s1, Solmu s2) {
                
                if (s1.getF() > s2.getF()) {
                    return 1;
                }
                
                if (s1.getF() < s2.getF()) {
                    return -1;
                } 
                
                return 0;
            }
        };
        
        return comp;
    }
}
