
package components;

/**
* Kaari luokka, jota hyödynnetään verkkojen komponentteina
*/

public class Kaari {
    
    private final Solmu alku;
    private final Solmu loppu;
    private final double etaisyys;
    
    /**
     * Kaari luokan konstruktori
     * @param alku Solmu josta kaari lähtee
     * @param loppu Solmu johon kaari päättyy
     * @param etaisyys Kaaren etäisyys (paino)
     */

    public Kaari(Solmu alku, Solmu loppu, double etaisyys) {
        this.alku = alku;
        this.loppu = loppu;
        this.etaisyys = etaisyys;        
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
        return etaisyys;
    }    
}
