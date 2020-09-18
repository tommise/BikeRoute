
package components;

import datastructures.ArrayList;



/**
 * Solmu luokka - solmulla on mm. nimi, kaaret, vierailtu, edellinenkaari ja minimipituus
 */

public class Solmu implements Comparable<Solmu> {
    
    private final String nimi;
    private double minimiEtaisyys = Double.MAX_VALUE;    
    private final ArrayList<Kaari> kaaret;   
    private Solmu edellinenSolmu;    
    
    private double g;
    private double f = 0;   
    
    public int latitude;
    public int longitude;
    
    /**
     * Solmu luokan konstruktori
     * @param nimi solmun nimi
     * @param latitude solmun leveys koordinaatti
     * @param longitude solmun korkeus koordinaatti
     */
    
    public Solmu(String nimi, int latitude, int longitude) {
        this.nimi = nimi;
        this.latitude = latitude;
        this.longitude = longitude;
        this.kaaret = new ArrayList<Kaari>();
    }
    
    /**
     * Lisää solmun kaarilistaan haluttavan kaaren eli naapurin
     * @param kaari lisättävä kaari
     */

    public void addNaapuri(Kaari kaari) {
        this.kaaret.add(kaari);
    }
    
    /**
     * Palauttaa solmun nimen
     * @return palautettavan solmun nimi
     */
    
    public String getNimi() {
        return nimi;
    }

    /**
     * Palauttaa solmun kaaret ArrayList listana
     * @return palautettava kaarilista ArrayList muodossa
     */
    
    public ArrayList<Kaari> getKaaret() {
        return kaaret;
    }
    
    /**
     * Palauttaa minimi etäisyyden
     * @return palautettava etäisyys double muodossa
     */

    public double getMinimiEtaisyys() {
        return minimiEtaisyys;
    }
    
    /**
     * Asettaa minimi etäisyyden 
     * @param minimiEtaisyys asetettava uusi etäisyys
     */

    public void setMinimiEtaisyys(double minimiEtaisyys) {
        this.minimiEtaisyys = minimiEtaisyys;
    }
    
    /**
     * Palauttaa edellisen solmun
     * @return edellinen solmu olio
     */
    
    public Solmu getEdellinenSolmu() {
        return edellinenSolmu;
    }

    /**
     * Asettaa edellisen solmun
     * @param edellinenSolmu asetettava edellinen solmu
     */
    
    public void setEdellinenSolmu(Solmu edellinenSolmu) {
        this.edellinenSolmu = edellinenSolmu;
    } 
    
    /**
     * Palauttaa A* algoritmissä käytetyn g scoren
     * @return palautettava g score double muodossa
     */
    
    public double getG() {
        return this.g;
    }
    
    /**
     * Asettaa A* algoritmissä käytetyn g scoren uuden arvon
     * @param g asetettava g score
     */  

    public void setG(double g) {
        this.g = g;
    }
    
    /**
     * Palauttaa A* algoritmissä käytetyn f scoren
     * @return palautettava f score double muodossa
     */    

    public double getF() {
        return this.f;
    }
    
    /**
     * Asettaa A* algoritmissä käytetyn f scoren uuden arvon
     * @param f asetettava uusi f score
     */
    
    public void setF(double f) {
        this.f = f;
    }    
    
    /**
     * Palauttaa leveys koordinaatin
     * @return leveys koordinaatti int muodossa
     */
    
    public int getLatitude() {
        return this.latitude;
    }
    
    /**
     * Palauttaa korkeus koordinaatin
     * @return korkeus koordinaatti int muodossa
     */
    
    public int getLongitude() {
        return this.longitude;
    }
    
    /**
     * Asettaa uuden leveys koordinaatin
     * @param latitude asetettava leveys koordinaatti int muodossa
     */
    
    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    /**
     * Asettaa uuden korkeus koordinaatin
     * @param longitude asetettava korkeus koordinaatti int muodossa
     */    
    
    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
    
    @Override
    public String toString() {
        return this.nimi;
    }

    @Override
    public int compareTo(Solmu verrattava) {
        return Double.compare(this.minimiEtaisyys, verrattava.minimiEtaisyys);
    }
}
