
package komponentit;

import tietorakenteet.ArrayList;

/**
 * Solmua kuvaava luokka
 */

public class Solmu {
    
    private final long id;     
    
    private double gluku;
    private double fluku;
    private double hluku;
    
    private double latitude;
    private double longitude;
    
    private ArrayList<Kaari> kaaret;    
    private Solmu edellinenSolmu;    
    
    /**
     * Solmu luokan konstruktori
     * @param id solmun id
     * @param latitude solmun leveys koordinaatti
     * @param longitude solmun korkeus koordinaatti
     */
    
    public Solmu(long id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.gluku = Double.MAX_VALUE;
        this.kaaret = new ArrayList<>();
    }
    
    /**
     * Lisää solmun kaarilistaan haluttavan kaare
     * @param kaari lisättävä kaari
     */

    public void addKaari(Kaari kaari) {
        this.kaaret.add(kaari);
    }

    /**
     * Palauttaa solmun kaaret ArrayList listana
     * @return palautettava kaarilista ArrayList muodossa
     */
    
    public ArrayList<Kaari> getKaaret() {
        return kaaret;
    }
    
    /**
     * Palauttaa solmun ID:n
     * @return palautettava id long muodossa
     */
    
    public long getID() {
        return this.id;
    }
    
    /**
     * Asettaa edellisen solmun, käytetään algoritmin reitin lukemisen helpottamiseksi
     * @param edellinenSolmu asetettava edellinen solmu
     */
    
    public void setEdellinenSolmu(Solmu edellinenSolmu) {
        this.edellinenSolmu = edellinenSolmu;
    } 
    
    /**
     * Palauttaa edellisen solmun
     * @return edellinen solmu olio
     */
    
    public Solmu getEdellinenSolmu() {
        return edellinenSolmu;
    }
    
    /**
     * Palauttaa g luvun
     * @return palautettava gluku score double muodossa
     */
    
    public double getG() {
        return this.gluku;
    }
    
    /**
     * Asettaa g luvulle uuden arvon
     * @param g asetettava gluku score
     */  

    public void setG(double g) {
        this.gluku = g;
    }
    
    /**
     * Palauttaa f arvon
     * @return palautettava fluku score double muodossa
     */    

    public double getF() {
        return this.fluku;
    }
    
    /**
     * Asettaa f luvulle uuden arvon
     * @param f asetettava uusi fluku score
     */
    
    public void setF(double f) {
        this.fluku = f;
    }  
    
    /**
     * Palauttaa h luvun
     * @return h luku double muodossa
     */
    
    public double getH() {
        return this.hluku;
    }
    
    /**
     * Asettaa h luvun
     * @param luku 
     */
    
    public void setH(double luku) {
        this.hluku = luku;
    }    
    
    /**
     * Palauttaa leveys koordinaatin
     * @return leveys koordinaatti int muodossa
     */
    
    public double getLatitude() {
        return this.latitude;
    }
    
    /**
     * Palauttaa korkeus koordinaatin
     * @return korkeus koordinaatti int muodossa
     */
    
    public double getLongitude() {
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
    
    /**
     * Resetoi arvot alkuperäsarvoonsa
     */
    
    public void resetSolmu() {
        this.edellinenSolmu = null;
        this.gluku = Double.MAX_VALUE;
    }
}
