
package components;

import java.util.ArrayList;
import java.util.List;


/**
 * Verkko luokka - luokalla verkkoon kuuluvat kaaret
 */

public class Verkko {
    
    List<Solmu> solmut;
    
    /**
     * Verkon konstruktori, jossa alustetaan uusi ArrayList lista solmuille
     */
    
    public Verkko() {
        this.solmut = new ArrayList<Solmu>();
    }
    
    /**
     * Verkon parametrillinen konstruktori
     * @param solmut asetettavat solmut listalla
     */
    
    public Verkko(List<Solmu> solmut) {
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
    
    public List<Solmu> getSolmut() {
        return solmut;
    }
    
    /**
     * Palauttaa solmun nimen perusteella
     * @param nimi - solmun (tien) nimi
     * @return löydettäessä palauttaa solmun, muuten null
     */
    
    public Solmu getSolmuByNimi(String nimi) {
        
        for (int i = 0; i < solmut.size(); i++) {
            Solmu solmu = solmut.get(i);
            
            if (solmu.getNimi().equals(nimi)) {
                return solmu;
            }
        }

        return null;
    }
    
    /**
     * Palauttaa solmun ID:n perusteella
     * @param stringID solmun ID
     * @return palautettava solmu olio, null jos tyhjä
     */
    
    public Solmu getSolmuByID(String stringID) {
        
        long id = Long.parseLong(stringID);
        
        for (int i = 0; i < solmut.size(); i++) {
            Solmu solmu = solmut.get(i);
            
            if (solmu.getID() == id) {
                return solmu;
            }
        }

        return null;
    }
}
