
package components;

/**
* Kaari luokka, jota hyödynnetään verkkojen komponentteina
*/

public class Kaari {
    
    private final Solmu alku;
    private final Solmu loppu;
    private double etaisyys;
    private String nimi = "";
    private String tienTyyppi = "";
    private final Heuristiikka heuristiikka;
    
    /**
     * Kaari luokan pääkonstruktori jota hyödynnetään karttadatan kanssa
     * @param alku Solmu josta kaari lähtee
     * @param loppu Solmu johon kaari päättyy
     * @param nimi Tien nimi
     * @param tienTyyppi Tien tyyppi
     */
    
    public Kaari(Solmu alku, Solmu loppu, String nimi, String tienTyyppi) {
        this.alku = alku;
        this.loppu = loppu;
        this.nimi = nimi;
        this.tienTyyppi = tienTyyppi;
        this.heuristiikka = new Heuristiikka();
    }
    
    /**
     * Kaari luokan konstruktori testikäyttöön
     * @param alku Solmu josta kaari lähtee
     * @param loppu Solmu johon kaari päättyy
     * @param etaisyys Kaaren etäisyys (paino)
     */

    public Kaari(Solmu alku, Solmu loppu, double etaisyys) {
        this.alku = alku;
        this.loppu = loppu;
        this.etaisyys = etaisyys; 
        this.heuristiikka = new Heuristiikka();
    }
    
    /**
     * Lasketaan kaarelle etäisyys Haversine metodia hyödyntäen
     */
    
    public void laskeEtaisyys() {
        double alkulat = alku.getLatitude();
        double alkulon = alku.getLongitude();
        
        double loppulat = loppu.getLatitude();
        double loppulon = loppu.getLongitude();
        
        this.etaisyys = heuristiikka.haversineMethod(alkulat, alkulon, loppulat, loppulon);
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
    
    /**
     * Palauttaa tien tyypin
     * @return tien tyyppi String muodossa
     */
    
    public String getTienTyyppi() {
        return this.tienTyyppi;
    }
    
    /**
     * Palauttaa kaaren nimen
     * @return nimi String muodossa
     */
    
    public String getNimi() {
        return this.nimi;
    }
    
    /**
     * Asettaa kaarelle nimen
     * @param nimi joka asetetaan
     */
    
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }   

    @Override
    public String toString() {
        return this.nimi;
    }
}
