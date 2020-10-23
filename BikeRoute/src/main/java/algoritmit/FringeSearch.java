
package algoritmit;

import java.util.HashMap;
import java.util.LinkedList;

import komponentit.Heuristiikka;
import komponentit.Kaari;
import komponentit.Solmu;

import tietorakenteet.ArrayList;

/**
 * Etsii lyhimmän reitin alkusolmusta loppusolmuun ja rakentaa reitin tämän jälkeen
 * Pseudokoodia https://en.wikipedia.org/wiki/Fringe_search mukaillen
*/

public class FringeSearch {
    ArrayList<Solmu> reitti;
    Heuristiikka heuristiikka;
    HashMap<Integer, Double> cacheG;
    HashMap<Integer, Solmu> link_to_parent;
    
    /**
     * Konstruktori jossa asetetaan heuristiikka
     */
    public FringeSearch() {
        this.heuristiikka = new Heuristiikka();
    }
    
    public void etsi(Solmu start, Solmu goal) {
        
        // fringe F = s
        LinkedList<Solmu> F = new LinkedList<>();
        F.add(start);

        // cache C[start] = (0, null)
        cacheG = new HashMap<>(); // <Solmun ID, g> 
        link_to_parent = new HashMap<>(); // <Solmun ID, parent>
        
        cacheG.put((int) start.getID(), 0.0);
        link_to_parent.put((int) start.getID(), null);
        
        // flimit = h(start)
        double flimit = heuristiikka.manhattanEtaisyys(start, goal);     
        
        boolean found = false;
        
        while (!found && !F.isEmpty()) {
            double fmin = Double.MAX_VALUE;      
            
            // for node in F, !! from left to right !!
            for (int i = 0; i < F.size(); i++) {
                Solmu node = F.get(i);
                
                double g = cacheG.get((int) node.getID());    
                double hnode = heuristiikka.manhattanEtaisyys(node, goal);
                    
                double f = g + hnode;
                
                if (f > flimit) {
                    fmin = Math.min(f, fmin);
                    continue;
                }
                    
                if (node == goal) {
                    found = true;
                    break;
                }
                
                ArrayList<Kaari> kaaret = node.getKaaret();
                    
                // for child in children(node), !! from right to left !!
                for (int j = kaaret.size() - 1; j >= 0; j++) {
                    Kaari kaari = kaaret.get(i);
                    Solmu lapsi = kaari.getLoppu();
                    
                    double g_child = g + kaari.getEtaisyys();
                    
                    if (link_to_parent.get((int) lapsi.getID()) != null) {
                        double g_cached = cacheG.get((int) lapsi.getID());
                        
                        if (g_child >= g_cached) {
                            continue;
                        }                        
                    }
                    
                    if (F.contains(lapsi)) {
                        F.remove(lapsi);
                    }
                    
                    F.add(i + 1, lapsi);
                    cacheG.put((int) lapsi.getID(), g_child);
                    link_to_parent.put((int) lapsi.getID(), node);                    
                }
                
                F.remove(node);
            }
            
            flimit = fmin;
        }
        
        // Lista reitin rakentamiselle
        this.reitti = new ArrayList<>();
        
        if (found) {
            System.out.println("Reitti löytyi!");
            rakennaReitti(goal);
        }
    }
    
    public void rakennaReitti(Solmu solmu) {     
        
        this.reitti.add(solmu);
        
        Solmu parent = link_to_parent.get((int) solmu.getID());
        
        if (parent != null) {
            double g = cacheG.get((int) solmu.getID());
            solmu.setG(g);
            
            rakennaReitti(parent);
        }
    }  
    
    public ArrayList<Solmu> luoReitti() {
        ArrayList<Solmu> kaannettyReitti = new ArrayList<>(); 
        
        for (int i = reitti.size() - 1; i >= 0; i--) {
            Solmu solmu = reitti.get(i);
            kaannettyReitti.add(solmu);
        }
        
        return kaannettyReitti; 
    }
}
