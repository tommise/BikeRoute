package algoritmit;

import java.util.Comparator;

import komponentit.Kaari;
import komponentit.Solmu;

import tietorakenteet.ArrayList;
import tietorakenteet.PriorityQueue;

/**
 * Luokka Dijkstran algoritmille
 */

public class Dijkstra {
    
    /**
     * Etsii lyhyimmän reitin halutusta alkusolmusta loppuun Dijkstran algoritmin mukaisesti
     * @param alku alkusolmu mistä lähdetään liikkeelle
     * @param loppu tavoitesolmu mihin halutaan päätyä
     */
    
    public void etsi(Solmu alku, Solmu loppu) {

        PriorityQueue<Solmu> prioriteettijono = new PriorityQueue<>(luoPrioriteetti());
        prioriteettijono.add(alku);
        alku.setG(0);       

        while (!prioriteettijono.isEmpty()) {
            Solmu nyky = prioriteettijono.poll();
            ArrayList<Kaari> kaaret = nyky.getKaaret();
            
            for (int i = 0; i < kaaret.size(); i++) {
                Kaari kaari = kaaret.get(i);
                Solmu solmu = kaari.getLoppu();
                
                double uusiG = nyky.getG() + kaari.getEtaisyys();

                if (uusiG < solmu.getG()) {
                    solmu.setG(uusiG);                    
                    solmu.setEdellinenSolmu(nyky);
                    
                    prioriteettijono.add(solmu);
                }
            }
        }
        
        luoReitti(loppu);
    }

    /**
     * Luodaan reitti saadun tuloksen perusteella
     * @param loppu solmu johon reitti päättyy
     * @return luotu lyhyin reitti lista muodossa
     */    
    
    public ArrayList<Solmu> luoReitti(Solmu loppu) {
        ArrayList<Solmu> reitti = new ArrayList<>();

        for (Solmu solmu = loppu; solmu != null; solmu = solmu.getEdellinenSolmu()) {
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
     * Luodaan Comparator joka lasketaan minimi etäisyyden mukaan
     * @return comparator olio
     */
    
    public Comparator luoPrioriteetti() {
        
        Comparator comp = new Comparator<Solmu>() {
            
            @Override
            public int compare(Solmu s1, Solmu s2) {
                return Double.compare(s1.getG(), s2.getG());
            }
        };
        
        return comp;
    }
}