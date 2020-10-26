
package komponentit;

/**
* Kaari luokka, jota hyödynnetään verkkojen komponentteina
*/

public class Kaari {
    
    private final Solmu alku;
    private final Solmu loppu;
    private final double etaisyys;
    private final Heuristiikka heuristiikka;

    /**
     * Kaaren konstruktori jossa asetetaan alku ja loppusolmut
     * @param alku
     * @param loppu 
     */
    
    public Kaari(Solmu alku, Solmu loppu) {
        this.alku = alku;
        this.loppu = loppu;
        this.heuristiikka = new Heuristiikka();
        
        double alkuLat = alku.getLatitude();
        double alkuLon = alku.getLongitude();
        double loppuLat = loppu.getLatitude();
        double loppuLon = loppu.getLongitude();
        
        /**
         * Asetetaan kaarelle pituus (paino) haversine tekniikan mukaisesti
         */
        
        this.etaisyys = heuristiikka.haversineFormula(alkuLat, alkuLon, loppuLat, loppuLon);        
    }

    /**
     * Palauttaa solmun, mistä kaari alkaa
     * @return solmu olio
     */

    public Solmu getAlku() {
        return alku;
    }
    
    /**
     * Palauttaa solmun, mihin kaari loppuu
     * @return solmu olio
     */

    public Solmu getLoppu() {
        return loppu;
    }
    
    /**
     * Palauttaa etaisyyden (paino)
     * @return etaisyys doublena
     */
    
    public double getEtaisyys() {
        return this.etaisyys;
    }
}
