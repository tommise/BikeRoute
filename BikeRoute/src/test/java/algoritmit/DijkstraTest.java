
package algoritmit;

import io.VerkonRakentaja;
import java.util.List;
import static org.junit.Assert.*;

import komponentit.Solmu;
import komponentit.Verkko;

import org.junit.Before;
import org.junit.Test;

import tietorakenteet.ArrayList;

public class DijkstraTest {
    
    Verkko verkko;
    Dijkstra dijkstra;
    double epsilon = 0.001;
    VerkonRakentaja rakentaja;
    
    @Before
    public void setUp() {
        this.dijkstra = new Dijkstra();
        
        this.rakentaja = new VerkonRakentaja();
        this.rakentaja.luoTestiVerkko();
        this.verkko = rakentaja.luoTestiVerkko();
    } 
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti1() {
        
        List<Solmu> solmut = verkko.getSolmut();
        
        Solmu alku = solmut.get(0);
        Solmu loppu = solmut.get(solmut.size() - 1);
        
        dijkstra.etsi(alku, loppu);
        ArrayList<Solmu> reitti = dijkstra.luoReitti(loppu);
        
        double lyhyinPolkuDijkstra = reitti.get(reitti.size() - 1).getMinimiEtaisyys();
        double reitinOikeaPituus = 786.9615717880192;
        
        assertEquals(lyhyinPolkuDijkstra, reitinOikeaPituus, epsilon);
    }    
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti2() {
        
        List<Solmu> solmut = verkko.getSolmut();
        
        Solmu alku = solmut.get(3);
        Solmu loppu = solmut.get(12);
        
        dijkstra.etsi(alku, loppu);
        ArrayList<Solmu> reitti = dijkstra.luoReitti(loppu);
        
        double lyhyinPolkuDijkstra = reitti.get(reitti.size() - 1).getMinimiEtaisyys();
        double reitinOikeaPituus = 375.823394569141;
        
        assertEquals(lyhyinPolkuDijkstra, reitinOikeaPituus, epsilon);
    } 

    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti3() {
        
        List<Solmu> solmut = verkko.getSolmut();
        
        Solmu alku = solmut.get(10);
        Solmu loppu = solmut.get(18);
        
        dijkstra.etsi(alku, loppu);
        ArrayList<Solmu> reitti = dijkstra.luoReitti(loppu);
        
        double lyhyinPolkuDijkstra = reitti.get(reitti.size() - 1).getMinimiEtaisyys();
        double reitinOikeaPituus = 293.860036810901;
        
        assertEquals(lyhyinPolkuDijkstra, reitinOikeaPituus, epsilon);
    }
}
