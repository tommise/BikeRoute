
package komponentit;

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
     * Palauttaa solmun latitudin ja longitudin perusteella
     * @param latitude
     * @param longitude
     * @return löydettäessä palauttaa solmun, muuten null
     */
    
    public Solmu getSolmuByLatAndLon(double latitude, double longitude) {
        
        for (int i = 0; i < solmut.size(); i++) {
            Solmu solmu = solmut.get(i);
            
            if (solmu.getLatitude() == latitude && solmu.getLongitude() == longitude) {
                return solmu;
            }
        }

        return null;
    }
}
