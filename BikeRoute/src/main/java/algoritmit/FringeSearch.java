
package algoritmit;

import java.util.HashMap;
import java.util.LinkedList;

import komponentit.Heuristiikka;
import komponentit.Kaari;
import komponentit.Solmu;

import tietorakenteet.ArrayList;

public class FringeSearch {
    ArrayList<Solmu> reitti;
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
        
        System.out.println("alku: " + alku.getID());
        System.out.println("loppu: " + loppu.getID());
        
        // fringe F = s
        LinkedList<Solmu> F = new LinkedList<>();
        F.add(alku);

        // cache C[start] = (0, null)
        cacheG = new HashMap<>(); // <Solmun ID, g> 
        link_to_parent = new HashMap<>(); // <Solmun ID, parent>
        
        cacheG.put((int) alku.getID(), 0.0);
        link_to_parent.put((int) alku.getID(), null);
        
        // flimit = h(start)
        double flimit = heuristiikka.manhattanEtaisyys(alku, loppu);
        //double flimit = heuristiikka.haversineMetodi(alku.getLatitude(), alku.getLongitude(), loppu.getLatitude(), loppu.getLongitude());        
        
        // found = false
        boolean found = false;
        
        // while (found == false) AND (F not empty)
        while (!found && !F.isEmpty()) {
            // fmin = ∞
            double fmin = Double.MAX_VALUE;      
            
            // for node in F, !! from left to right !!
            for (int i = 0; i < F.size(); i++) {
                Solmu solmu = F.get(i);
                
                System.out.println("Solmu: " + solmu.getID());
                
                ArrayList<Solmu> solmunLapset = new ArrayList<>();
                
                ArrayList<Kaari> kaaret = solmu.getKaaret();                
                
                for (int j = 0; j < kaaret.size(); j++) {
                    
                    Kaari kaari = kaaret.get(i);
                    
                    // (g, parent) = C[node]
                    double g = cacheG.get((int) solmu.getID());
                    System.out.println("g_main: " + g);
                    
                    // f = g + h(node)
                    
                    double hnode = heuristiikka.manhattanEtaisyys(solmu, loppu);
                    //double hnode = heuristiikka.haversineMetodi(solmu.getLatitude(), solmu.getLongitude(), loppu.getLatitude(), loppu.getLongitude());
                    
                    double f = g + hnode;
                    
                    // if f > flimit 
                    //    fmin = min(f, fmin)
                    //    continue;
                    
                    if (f > flimit) {
                        fmin = Math.min(f, fmin);
                        continue;
                    }
                    
                    Solmu lapsi = kaari.getLoppu();
                    solmunLapset.add(lapsi);
                }
                
                // if node == goal 
                //      found = true
                //      break;
                    
                if (solmu == loppu) {
                    found = true;
                    break;
                }
                    
                // for child in children(node), !! from right to left !!
                
                for (int j = solmunLapset.size() - 1; j >= 0; j--) {
                    Solmu lapsi = solmunLapset.get(i);
                    
                    ArrayList<Kaari> lapsenKaaret = lapsi.getKaaret();
                    
                    for (int k = lapsenKaaret.size() - 1; k >= 0; k--) {
                        Kaari lapsenKaari = lapsenKaaret.get(k);
                        
                        // g_child = g + cost(node, child)
                        double g = cacheG.get((int) solmu.getID());
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
                    
                        // if child in F
                            // remove child from F
                        if (F.contains(lapsi)) {
                            F.remove(lapsi);
                        }
                        
                        // insert child in F past node
                        F.add(i + 1, lapsi);
                        
                        // C[child] = (g_child, node)
                        cacheG.put((int) lapsi.getID(), g_child);
                        link_to_parent.put((int) lapsi.getID(), solmu);
                    }
                }
                // remove node from F
                F.remove(solmu);
            }
            // flimit = fmin
            flimit = fmin;
        }
        
        // Lista reitin rakentamiselle
        this.reitti = new ArrayList<>();
        
        // if reachedgoal == true 
            //reverse_path(goal)
        if (found) {
            System.out.println("Reitti löytyi!");
            rakennaReitti(loppu);
        }
    }
    
    public void rakennaReitti(Solmu solmu) {     
        
        /*
        reverse_path(node)
            (g, parent) = C[node]
            if parent != null
                reverse_path(parent)
            print node
        */
        
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
