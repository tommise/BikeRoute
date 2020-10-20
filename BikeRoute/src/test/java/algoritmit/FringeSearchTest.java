
package algoritmit;

import static org.junit.Assert.*;

import io.VerkonRakentaja;
import komponentit.Solmu;
import komponentit.Verkko;
import org.junit.Before;
import org.junit.Test;

import tietorakenteet.ArrayList;

public class FringeSearchTest {
       
    Verkko verkko;
    FringeSearch fringe;     
    double epsilon = 0.001;
    VerkonRakentaja rakentaja; 
    
    @Before
    public void setUp() {
        this.fringe = new FringeSearch();

        this.rakentaja = new VerkonRakentaja();
        this.rakentaja.luoTestiVerkko();
        this.verkko = rakentaja.luoTestiVerkko();
    } 
 
    /*
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti1() {
        
        ArrayList<Solmu> solmut = verkko.getSolmut();
        
        Solmu alku = solmut.get(0);
        Solmu loppu = solmut.get(solmut.size() - 1);
        
        fringe.etsi(alku, loppu);
        ArrayList<Solmu> reitti = fringe.luoReitti();
        
        double fringeReitti = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 802.8398626554501;
        
        assertEquals(fringeReitti, reitinOikeaPituus, epsilon);
    }    
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti2() {
        
        ArrayList<Solmu> solmut = verkko.getSolmut();
        
        Solmu alku = solmut.get(3);
        Solmu loppu = solmut.get(12);
        
        fringe.etsi(alku, loppu);
        ArrayList<Solmu> reitti = fringe.luoReitti();
        
        double fringeReitti = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 375.823394569141;
        
        assertEquals(fringeReitti, reitinOikeaPituus, epsilon);
    } 

    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti3() {
        
        ArrayList<Solmu> solmut = verkko.getSolmut();
        
        Solmu alku = solmut.get(10);
        Solmu loppu = solmut.get(18);
        
        fringe.etsi(alku, loppu);
        ArrayList<Solmu> reitti = fringe.luoReitti();
        
        double fringeReitti = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 293.860036810901;
        
        assertEquals(fringeReitti, reitinOikeaPituus, epsilon);
    }
    */
}
