
package components;

import java.util.*;

/**
 * Verkko luokka - luokalla verkkoon kuuluvat kaaret
 */

public class Verkko {
    
    ArrayList<Solmu> solmut;
    
    public Verkko() {
        this.solmut = new ArrayList<Solmu>();
    }
    
    /**
     * Lisää verkolle solmun
     * 
     * @param solmu - lisättävä solmu 
     */
    
    public void addSolmu(Solmu solmu) {
        this.solmut.add(solmu);
    }    
    
    public ArrayList<Solmu> getSolmut() {
        return solmut;
    }
    
    /**
     * Palauttaa solmun nimen perusteella
     * 
     * @param name - solmun (tien) nimi
     * @return löydettäessä palauttaa solmun, muuten null
     */
    
    public Solmu getByName(String name) {
        for (Solmu solmu : solmut) {
            if (solmu.getNimi().equals(name)) {
                return solmu;
            }
        }
        return null;
    }
}
