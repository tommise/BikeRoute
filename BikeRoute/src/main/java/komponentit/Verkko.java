
package komponentit;

import tietorakenteet.ArrayList;

/**
 * Verkko luokka - luokalla verkkoon kuuluvat kaaret
 */

public class Verkko {
    
    ArrayList<Solmu> solmut;
    
    /**
     * Verkon konstruktori, jossa alustetaan uusi ArrayList lista solmuille
     */
    
    public Verkko() {
        this.solmut = new ArrayList<>();
    }
    
    /**
     * Verkon parametrillinen konstruktori
     * @param solmut asetettavat solmut listalla
     */
    
    public Verkko(ArrayList<Solmu> solmut) {
        this.solmut = solmut;
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
}
