
package util;

import components.Solmu;
import data_structures.ArrayList;

public class PathHelper {
    
    /**
     * Tulostaa reitin käyttöliittymälle
     * 
     * @param reitti tulostettava reitti
     */
    
    public void tulostaDijkstraReitti(ArrayList<Solmu> reitti) {
        
        System.out.println("Reitti Dijkstralla:");
        System.out.println("");
        
        for (int i = 0; i < reitti.size(); i++) {
            Solmu solmu = reitti.get(i);
            System.out.println(solmu.getNimi() + " " + solmu.getMinimiEtaisyys() + "m");
        }        
        
        System.out.println("");
        System.out.println("Kokonaisreitti yhteensä: " + reitti.get(reitti.size() - 1).getMinimiEtaisyys() + "m");
        System.out.println(""); 
    }
    
    public void tulostaAstarReitti(ArrayList<Solmu> reitti) {
        
        System.out.println("Reitti AStarilla:");
        System.out.println("");      
        
        for (int i = 0; i < reitti.size(); i++) {
            Solmu solmu = reitti.get(i);
            System.out.println(solmu.getNimi() + " " + solmu.getG() + "m");
        }
        
        System.out.println("");
        System.out.println("Kokonaisreitti yhteensä: " + reitti.get(reitti.size() - 1).getG() + "m");
        System.out.println("");         
    }
}
