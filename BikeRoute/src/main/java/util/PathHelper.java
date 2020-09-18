
package util;

import components.Solmu;
import datastructures.ArrayList;

public class PathHelper {
    
    /**
     * Tulostaa Dijkstran reitin käyttöliittymälle
     * @param reitti tulostettava reitti ArrayList muodossa
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
    
    /**
     * Tulostaa A* algoritmin reitin käyttöliittymälle
     * @param reitti tulostettava reitti ArrayList muodossa
     */    
    
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
