
package algoritmit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

import komponentit.Heuristiikka;
import komponentit.Kaari;
import komponentit.Solmu;

/**
 * A* algoritmi sekä lyhyimmän reitin tallettaminen listaan
 */

public class AStar {
    
    Heuristiikka heuristiikka;
    
    /**
     * A* algoritmin konstruktori
     */
    
    public AStar() {
        this.heuristiikka = new Heuristiikka();
    }
    
    /**
     * Etsii lyhyimmän reitin halutusta alkusolmusta loppuun A* algoritmin mukaisesti
     * @param alku alkusolmu mistä lähdetään liikkeelle
     * @param loppu tavoitesolmu mihin halutaan päätyä
     */

    public void etsi(Solmu alku, Solmu loppu) {
    
        HashSet<Solmu> kasitelty = new HashSet<Solmu>();
        PriorityQueue<Solmu> queue = new PriorityQueue<Solmu>(luoPrioriteetti());
        
        queue.add(alku);
        alku.setG(0);

        while (!queue.isEmpty()) {
            Solmu nykyinenSolmu = queue.poll();
            kasitelty.add(nykyinenSolmu);

            if (alku.getID() == loppu.getID()) {
                luoReitti(loppu);
            }
            
            ArrayList<Kaari> kaaret = nykyinenSolmu.getKaaret();

            for (int i = 0; i < kaaret.size(); i++) {
                Kaari kaari = kaaret.get(i);
                Solmu solmu = kaari.getLoppu();
                
                if (kasitelty.contains(solmu)) {
                    continue;
                }
                
                double tentativeG = nykyinenSolmu.getG() + kaari.getEtaisyys();
                
                if (tentativeG < solmu.getG()) {
                    double f = tentativeG + heuristiikka.manhattanEtaisyys(solmu, loppu);
                    solmu.setF(f);
                    solmu.setG(tentativeG);
                    
                    solmu.setEdellinenSolmu(nykyinenSolmu);

                    if (!queue.contains(solmu)) {
                        queue.add(solmu);
                    }
                }
            }
        }
    
        
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
