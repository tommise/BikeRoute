
package algoritmit;

import io.VerkonRakentaja;
import komponentit.Solmu;
import komponentit.Verkko;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tietorakenteet.ArrayList;

public class IDAStarTest {
    
    Verkko verkko;
    IDAStar idaStar;
    double epsilon = 0.01;
    VerkonRakentaja rakentaja;
    
    @Before
    public void setUp() {
        this.idaStar = new IDAStar();
        
        this.rakentaja = new VerkonRakentaja();
        this.verkko = rakentaja.luoTestiVerkko();
    } 
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti1() {
        
        ArrayList<Solmu> solmut = verkko.getSolmut();
        
        Solmu alku = solmut.get(0);
        Solmu loppu = solmut.get(solmut.size() - 1);
        
        idaStar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = idaStar.luoReitti(loppu);
        
        double tulos = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 802.8398626554501;
        
        assertEquals(tulos, reitinOikeaPituus, epsilon);
    }
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti2() {
        
        ArrayList<Solmu> solmut = verkko.getSolmut();
        
        Solmu alku = solmut.get(3);
        Solmu loppu = solmut.get(12);
        
        idaStar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = idaStar.luoReitti(loppu);
        
        double tulos = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 375.823394569141;
        
        assertEquals(tulos, reitinOikeaPituus, epsilon);
    }

    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti3() {
        
        ArrayList<Solmu> solmut = verkko.getSolmut();
        
        Solmu alku = solmut.get(10);
        Solmu loppu = solmut.get(18);
        
        idaStar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = idaStar.luoReitti(loppu);
        
        double tulos = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 293.860036810901;
        
        assertEquals(tulos, reitinOikeaPituus, epsilon);
    }    
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti1Vaarinpain() {
        
        ArrayList<Solmu> solmut = verkko.getSolmut();
        
        Solmu alku = solmut.get(solmut.size() - 1);
        Solmu loppu = solmut.get(0);
        
        idaStar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = idaStar.luoReitti(loppu);
        
        double tulos = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 802.8398626554501;
        
        assertEquals(tulos, reitinOikeaPituus, epsilon);
    }
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti2Vaarinpain() {
        
        ArrayList<Solmu> solmut = verkko.getSolmut();
        
        Solmu alku = solmut.get(12);
        Solmu loppu = solmut.get(3);
        
        idaStar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = idaStar.luoReitti(loppu);
        
        double tulos = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 375.823394569141;
        
        assertEquals(tulos, reitinOikeaPituus, epsilon);
    }
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti3Vaarinpain() {
        
        ArrayList<Solmu> solmut = verkko.getSolmut();
        
        Solmu alku = solmut.get(18);
        Solmu loppu = solmut.get(10);
        
        idaStar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = idaStar.luoReitti(loppu);
        
        double tulos = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 293.860036810901;
        
        assertEquals(tulos, reitinOikeaPituus, epsilon);
    } 
}
