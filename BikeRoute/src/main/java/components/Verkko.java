
package components;

import datastructures.ArrayList;

/**
 * Verkko luokka - luokalla verkkoon kuuluvat kaaret
 */

public class Verkko {
    
    ArrayList<Solmu> solmut;
    
    /**
     * Verkon konstruktori, jossa alustetaan uusi ArrayList lista solmuille
     */
    
    public Verkko() {
        this.solmut = new ArrayList<Solmu>();
    }
    
    /**
     * Lisää verkolle solmun
     * @param solmu - lisättävä solmu 
     */
    
    public void addSolmu(Solmu solmu) {
        this.solmut.add(solmu);
    }    
    
    /**
     * Palauttaa kaikki verkolle lisätyt solmut listana
     * @return palautettava ArrayList muotoinen lista
     */
    
    public ArrayList<Solmu> getSolmut() {
        return solmut;
    }
    
    /**
     * Palauttaa solmun nimen perusteella
     * @param nimi - solmun (tien) nimi
     * @return löydettäessä palauttaa solmun, muuten null
     */
    
    public Solmu getByName(String nimi) {
        
        for (int i = 0; i < solmut.size(); i++) {
            Solmu solmu = solmut.get(i);
            
            if (solmu.getNimi().equals(nimi)) {
                return solmu;
            }
        }

        return null;
    }
}
