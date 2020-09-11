
package components;

import java.util.ArrayList;
import java.util.List;

/**
 * Solmu luokka - solmulla on mm. nimi, kaaret, vierailtu, edellinenkaari ja minimipituus
 */

public class Solmu implements Comparable<Solmu> {
    
    private String nimi;
    private double minimiEtaisyys = Double.MAX_VALUE;    
    private Solmu edellinenSolmu;
    private List<Kaari> kaaret;    

    public Solmu(String nimi) {
        this.nimi = nimi;
        this.kaaret = new ArrayList<>();
    }

    public void addNaapuri(Kaari kaari) {
        this.kaaret.add(kaari);
    }
    
    public String getNimi() {
        return nimi;
    }

    public List<Kaari> getKaaret() {
        return kaaret;
    }

    public double getMinimiEtaisyys() {
        return minimiEtaisyys;
    }

    public void setMinimiEtaisyys(double minimiEtaisyys) {
        this.minimiEtaisyys = minimiEtaisyys;
    }
    
    public Solmu getEdellinenSolmu() {
        return edellinenSolmu;
    }

    public void setEdellinenSolmu(Solmu edellinenSolmu) {
        this.edellinenSolmu = edellinenSolmu;
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
