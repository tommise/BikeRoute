
package util;

import datastructures.ArrayList;

public class SuorituskykyTestaus {
    
    ArrayList<Long> astarAjat;    
    ArrayList<Long> dijkstranAjat;
    ArrayList<Long> jpsAjat;
    
    long alkuAika = 0;
    long loppuAika = 0;
    
    /**
     * Suorituskykytestauksen konstruktori, konstruktori alustaa kaikkien algoritmien listat
     */
    
    public SuorituskykyTestaus() {
        this.astarAjat = new ArrayList<Long>();
        this.dijkstranAjat = new ArrayList<Long>();
        this.jpsAjat = new ArrayList<Long>();
    }
    
    /**
     * Aloittaa ajanlaskun
     */
    
    public void aloita() {
        this.alkuAika = System.nanoTime();
    }
    
    /**
     * Lopettaa ajanlaskun ja tallettaa ajan listarakenteeseen
     * @param algoritmi algoritmi joka käytössä
     */
    
    public void lopeta(String algoritmi) {
        this.loppuAika = System.nanoTime();
        
        long kokonaisaika = loppuAika - alkuAika;
        
        if (algoritmi.equals("aStar")) {
            astarAjat.add(kokonaisaika);
        } else if (algoritmi.equals("Dijkstra")) {
            dijkstranAjat.add(kokonaisaika);
        } else if (algoritmi.equals("JPS")) {
            jpsAjat.add(kokonaisaika);
        }
        
        reset();
    }
    
    /**
     * Muuttaa ajat takaisin nolliksi
     */
    
    public void reset() {
        this.alkuAika = 0;
        this.loppuAika = 0;
    }
}
