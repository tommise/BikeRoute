
package components;

/**
* Kaari luokka - kaarella alku, loppu ja etaisyys (paino)
*/

public class Kaari {
    
    private Solmu alku;
    private Solmu loppu;
    private double etaisyys;

    public Kaari(Solmu alku, Solmu loppu,  double etaisyys) {
        this.alku = alku;
        this.loppu = loppu;
        this.etaisyys = etaisyys;        
    }

    public Solmu getAlku() {
        return alku;
    }

    public Solmu getLoppu() {
        return loppu;
    }
    
    public double getEtaisyys() {
        return etaisyys;
    }    
}
