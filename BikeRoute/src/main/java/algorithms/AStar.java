
package algorithms;

import components.Heuristic;
import components.Kaari;
import components.Solmu;
import data_structures.ArrayList;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * A* algoritmi sekä lyhyimmän reitin tallettaminen listaan
 */

public class AStar {

    public void etsi(Solmu alku, Solmu loppu){

        Heuristic heuristic = new Heuristic();
        HashSet<Solmu> kasitelty = new HashSet<Solmu>();
        PriorityQueue<Solmu> queue = new PriorityQueue<Solmu>(luoPrioriteetti());

        boolean loytyi = false;
        
        queue.add(alku);
        alku.setG(0);

        while ((!loytyi) && (!queue.isEmpty())) {
            Solmu nykyinenSolmu = queue.poll();
            kasitelty.add(nykyinenSolmu);

            if (nykyinenSolmu.getNimi().equals(loppu.getNimi())){
                luoReitti(loppu);                
                loytyi = true;
            }

            for (Kaari kaari : nykyinenSolmu.getKaaret()) {
                Solmu solmu = kaari.getLoppu();
                double paino = kaari.getEtaisyys();
                                
                double g = nykyinenSolmu.getG() + paino;
                double f = g + heuristic.manhattanDistance(solmu, loppu);
                                
                if ((f >= solmu.getF() && (kasitelty.contains(solmu)))) {
                    continue;
                } 
                
                if ((f < solmu.getF() || (!queue.contains(solmu)))) {
                    
                    solmu.setEdellinenSolmu(nykyinenSolmu);
                    solmu.setG(g);
                    solmu.setF(f);

                    if (queue.contains(solmu)){
                        queue.remove(solmu);
                    }

                    queue.add(solmu);
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
        
        for (int i = reitti.size()-1; i >= 0; i--) { 
            kaannettyReitti.add(reitti.get(i)); 
        } 
        
        return kaannettyReitti; 
    }
    
    /**
     * Luodaan Comparator joka asketaan prioriteetti F scoren mukaan
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
