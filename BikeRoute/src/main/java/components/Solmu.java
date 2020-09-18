
package components;

import java.util.ArrayList;
import java.util.List;

/**
 * Solmu luokka - solmulla on mm. nimi, kaaret, vierailtu, edellinenkaari ja minimipituus
 */

public class Solmu implements Comparable<Solmu> {
    
    private String nimi;
    private double minimiEtaisyys = Double.MAX_VALUE;    
    private List<Kaari> kaaret;   
    private Solmu edellinenSolmu;    
    
    private double g;
    private double f = 0;   
    
    public int latitude;
    public int longitude;
    
    public Solmu(String nimi, int latitude, int longitude) {
        this.nimi = nimi;
        this.latitude = latitude;
        this.longitude = longitude;
        this.kaaret = new ArrayList<Kaari>();
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
    
    public double getG() {
        return this.g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getF() {
        return this.f;
    }

    public void setF(double f) {
        this.f = f;
    }    
    
    public int getLatitude() {
        return this.latitude;
    }
    
    public int getLongitude() {
        return this.longitude;
    }
    
    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }
    
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
