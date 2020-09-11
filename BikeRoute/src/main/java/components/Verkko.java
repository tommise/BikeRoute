
package components;

import java.util.*;

/**
 * Verkko luokka - luokalla verkkoon kuuluvat kaaret
 */

public class Verkko {
    
    ArrayList<Solmu> kaaret;
    
    public Verkko() {
        this.kaaret = new ArrayList<Solmu>();
    }
    
    /**
     * Lisää verkolle solmun
     * 
     * @param node - lisättävä solmu 
     */
    
    public void addSolmu(Solmu solmu) {
        this.kaaret.add(solmu);
    }    
    
    public ArrayList<Solmu> getKaaret() {
        return kaaret;
    }
    
    /**
     * Palauttaa solmun nimen perusteella
     * 
     * @param name - solmun (tien) nimi
     * @return löydettäessä palauttaa solmun, muuten null
     */
    
    public Solmu getByName(String name) {
        for (Solmu kaari : kaaret) {
            if (kaari.getNimi().equals(name)) {
                return kaari;
            }
        }
        return null;
    }
}
