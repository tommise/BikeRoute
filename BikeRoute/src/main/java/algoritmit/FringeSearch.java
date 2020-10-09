
package algoritmit;

import java.util.ArrayList;
import java.util.HashMap;

import komponentit.Heuristiikka;
import komponentit.Kaari;
import komponentit.Solmu;

public class FringeSearch {
    
    Heuristiikka heuristiikka;
    HashMap<Integer, Double> cacheG;
    HashMap<Integer, Solmu> link_to_parent;
    
    public FringeSearch() {
        this.heuristiikka = new Heuristiikka();
    }
    
    /**
     * Etsii lyhimmän reitin alkusolmusta loppusolmuun ja rakentaa reitin tämän jälkeen
     * Pseudokoodista: https://en.wikipedia.org/wiki/Fringe_search
     * @param alku alku solmu
     * @param loppu loppu solmu
    */
    
    // init(start, goal)
    public void etsi(Solmu alku, Solmu loppu) {
        
        // fringe F = s
        ArrayList<Solmu> F = new ArrayList<>();
        F.add(alku);

        // cache C[start] = (0, null)
        cacheG = new HashMap<>(); // <Solmun ID, g> 
        link_to_parent = new HashMap<>(); // <Solmun ID, parent>
        
        cacheG.put((int) alku.getID(), 0.0);
        link_to_parent.put((int) alku.getID(), null);
        
        // flimit = h(start)
        double flimit = heuristiikka.manhattanEtaisyys(alku, loppu);
        
        // found = false
        boolean found = false;
        
        // while (found == false) AND (F not empty)
        while (!found && !F.isEmpty()) {
            // fmin = ∞
            double fmin = Double.MAX_VALUE;      
            
            // for node in F, from left to right
            for (int i = 0; i < F.size(); i++) {
                Solmu solmu = F.get(i);
                
                tietorakenteet.ArrayList<Kaari> kaaret = solmu.getKaaret();
                
                for (int j = 0; j < kaaret.size(); j++) {
                    
                    Kaari kaari = kaaret.get(i);
                    
                    // (g, parent) = C[node]
                    double g = cacheG.get((int) solmu.getID());
                    
                    // f = g + h(node)
                    double f = g + heuristiikka.manhattanEtaisyys(solmu, loppu);
                    
                    // if f > flimit 
                    //    fmin = min(f, fmin)
                    //    continue;
                    
                    if (f > flimit) {
                        fmin = Math.min(f, fmin);
                        continue;
                    }
                    
                    // if node == goal 
                    //  found = true
                    //  break;
                    
                    if (solmu == loppu) {
                        found = true;
                        break;
                    }
                    
                    // for child in children(node), from right to left
                    Solmu lapsi = kaari.getLoppu();
                    tietorakenteet.ArrayList<Kaari> lapsenKaaret = lapsi.getKaaret();
                    
                    for (int k = 0; k < lapsenKaaret.size(); k++) {
                        Kaari lapsenKaari = lapsenKaaret.get(k);
                        
                        // g_child = g + cost(node, child)
                        double g_child = g + lapsenKaari.getEtaisyys();

                        // if C[child] != null
                            // (g_cached, parent) = C[child]
                            // if g_child >= g_cached
                                // continue
                        
                        if (link_to_parent.get((int) lapsi.getID()) != null) {
                            double g_cached = cacheG.get((int) lapsi.getID());
                            
                            if (g_child >= g_cached) {
                                continue;
                            }
                        }
                        
                        // if child in F -> remove child from F
                        if (F.contains(lapsi)) {
                            F.remove(lapsi);
                        }
                        
                        // insert child in F past node
                        F.add(lapsi);
                        
                        // C[child] = (g_child, node)
                        cacheG.put((int) lapsi.getID(), g_child);
                        link_to_parent.put((int) lapsi.getID(), solmu);
                    }
                    // remove node from F
                    F.remove(solmu);
                }
                // Jotta päästään pois uloimmasta loopista
                if (solmu == loppu) {
                    break;
                }
            }
            // flimit = fmin
            flimit = fmin;
        }
        
        // if reachedgoal == true 
            //reverse_path(goal)
        if (found) {
            luoReitti(loppu);
        }
    }
    
    public ArrayList<Solmu> luoReitti(Solmu loppu) {
        ArrayList<Solmu> kaannettyReitti = new ArrayList<Solmu>();

        /*
        reverse_path(node)
            (g, parent) = C[node]
            if parent != null
                reverse_path(parent)
            print node
        */
        
        return kaannettyReitti;
    }    
}
