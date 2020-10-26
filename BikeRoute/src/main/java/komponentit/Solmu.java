
package komponentit;

import tietorakenteet.ArrayList;

/**
 * Solmua kuvaava luokka
 */

public class Solmu {
    
    private final long id;     
    
    private double gluku;
    private double fluku;
    
    private final double latitude;
    private final double longitude;
    
    private ArrayList<Kaari> kaaret;  
    private Solmu edellinenSolmu;   
    private Heuristiikka heur;
    
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
        this.heur = new Heuristiikka();
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
     * Palauttaa heuristisen etäisyyden euklidisen etäisyyden perusteella
     * @param solmu haluttu solmu
     * @return heuristinen etäisyys double muodossa
     */
    
    public double euklidinenHeuristiikka(Solmu solmu) {
        return heur.euklidinenEtaisyys(this, solmu);
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
     * Resetoi arvot alkuperäsarvoonsa
     */
    
    public void resetSolmu() {
        this.edellinenSolmu = null;
        this.gluku = Double.MAX_VALUE;
    }
}
