
package algoritmit;

import static org.junit.Assert.*;

import io.VerkonLukija;
import komponentit.Solmu;
import komponentit.Verkko;
import org.junit.Before;
import org.junit.Test;
import tietorakenteet.ArrayList;

public class DijkstraTest {

    Dijkstra dijkstra;
    double epsilon = 0.01;
    VerkonLukija reader;
    
    @Before
    public void setUp() {
        this.dijkstra = new Dijkstra();
        this.reader = new VerkonLukija();
    } 
    
    /**
     * Reitit joita käytetään suorituskyvyn vertailussa
     */   
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti1() {
        ArrayList<Solmu> solmut = reader.luoVerkkoDavisista().getSolmut();
        
        Solmu alku = solmut.get(1);
        Solmu loppu = solmut.get(88);
        
        dijkstra.etsi(alku, loppu);
        ArrayList<Solmu> reitti = dijkstra.luoReitti(loppu);
        
        double lyhyinPolkuDijkstra = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 2444.6838193006743;        

        assertEquals(lyhyinPolkuDijkstra, reitinOikeaPituus, epsilon);
    }
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti2() {
        ArrayList<Solmu> solmut = reader.luoVerkkoDavisista().getSolmut();
        
        Solmu alku = solmut.get(150);
        Solmu loppu = solmut.get(41);
        
        dijkstra.etsi(alku, loppu);
        ArrayList<Solmu> reitti = dijkstra.luoReitti(loppu);
        
        double lyhyinPolkuDijkstra = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 2997.0403280132705; 

        assertEquals(lyhyinPolkuDijkstra, reitinOikeaPituus, epsilon);
    }
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti3() {
        ArrayList<Solmu> solmut = reader.luoVerkkoDavisista().getSolmut();
        
        Solmu alku = solmut.get(87);
        Solmu loppu = solmut.get(183);
        
        dijkstra.etsi(alku, loppu);
        ArrayList<Solmu> reitti = dijkstra.luoReitti(loppu);
        
        double lyhyinPolkuDijkstra = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 3415.7757993535465; 

        assertEquals(lyhyinPolkuDijkstra, reitinOikeaPituus, epsilon);
    }
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti4() {
        ArrayList<Solmu> solmut = reader.luoVerkkoDavisista().getSolmut();
        
        Solmu alku = solmut.get(16);
        Solmu loppu = solmut.get(36);
        
        dijkstra.etsi(alku, loppu);
        ArrayList<Solmu> reitti = dijkstra.luoReitti(loppu);
        
        double lyhyinPolkuDijkstra = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 881.4246491349884; 

        assertEquals(lyhyinPolkuDijkstra, reitinOikeaPituus, epsilon);
    }    

    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti5() {
        
        ArrayList<Solmu> solmut = reader.luoVerkkoTalista().getSolmut();
        
        Solmu alku = solmut.get(0);
        Solmu loppu = solmut.get(solmut.size() - 1);
        
        dijkstra.etsi(alku, loppu);
        ArrayList<Solmu> reitti = dijkstra.luoReitti(loppu);
        
        double lyhyinPolkuDijkstra = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 802.8398626554501;
        
        assertEquals(lyhyinPolkuDijkstra, reitinOikeaPituus, epsilon);
    }  
    
    /**
     * Muita reittejä Tali kartan pohjalta
     */
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti2Tali() {
        
        ArrayList<Solmu> solmut = reader.luoVerkkoTalista().getSolmut();
        
        Solmu alku = solmut.get(3);
        Solmu loppu = solmut.get(12);
        
        dijkstra.etsi(alku, loppu);
        ArrayList<Solmu> reitti = dijkstra.luoReitti(loppu);
        
        double lyhyinPolkuDijkstra = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 375.823394569141;
        
        assertEquals(lyhyinPolkuDijkstra, reitinOikeaPituus, epsilon);
    } 

    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti3Tali() {
        
        ArrayList<Solmu> solmut = reader.luoVerkkoTalista().getSolmut();
        
        Solmu alku = solmut.get(10);
        Solmu loppu = solmut.get(18);
        
        dijkstra.etsi(alku, loppu);
        ArrayList<Solmu> reitti = dijkstra.luoReitti(loppu);
        
        double lyhyinPolkuDijkstra = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 293.860036810901;
        
        assertEquals(lyhyinPolkuDijkstra, reitinOikeaPituus, epsilon);
    } 
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti2TaliVaarinpain() {
        
        ArrayList<Solmu> solmut = reader.luoVerkkoTalista().getSolmut();
        
        Solmu alku = solmut.get(12);
        Solmu loppu = solmut.get(3);
        
        dijkstra.etsi(alku, loppu);
        ArrayList<Solmu> reitti = dijkstra.luoReitti(loppu);
        
        double lyhyinPolkuDijkstra = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 375.823394569141;
        
        assertEquals(lyhyinPolkuDijkstra, reitinOikeaPituus, epsilon);
    } 
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti3TaliVaarinpain() {
        
        ArrayList<Solmu> solmut = reader.luoVerkkoTalista().getSolmut();
        
        Solmu alku = solmut.get(18);
        Solmu loppu = solmut.get(10);
        
        dijkstra.etsi(alku, loppu);
        ArrayList<Solmu> reitti = dijkstra.luoReitti(loppu);
        
        double lyhyinPolkuDijkstra = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 293.860036810901;
        
        assertEquals(lyhyinPolkuDijkstra, reitinOikeaPituus, epsilon);
    }  
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti5Vaarinpain() {
        
        ArrayList<Solmu> solmut = reader.luoVerkkoTalista().getSolmut();
        
        Solmu alku = solmut.get(solmut.size() - 1);
        Solmu loppu = solmut.get(0);
        
        dijkstra.etsi(alku, loppu);
        ArrayList<Solmu> reitti = dijkstra.luoReitti(loppu);
        
        double lyhyinPolkuDijkstra = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 802.8398626554501;
        
        assertEquals(lyhyinPolkuDijkstra, reitinOikeaPituus, epsilon);
    }    
     
    /**
     * Muita reittejä Davis kartan pohjalta
     */
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti5Davis() {
        ArrayList<Solmu> solmut = reader.luoVerkkoDavisista().getSolmut();
        
        Solmu alku = solmut.get(150);
        Solmu loppu = solmut.get(120);
        
        dijkstra.etsi(alku, loppu);
        ArrayList<Solmu> reitti = dijkstra.luoReitti(loppu);
        
        double tulos = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 901.4661092508106; 
        
        assertEquals(tulos, reitinOikeaPituus, epsilon); 
    }
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti6Davis() {
        ArrayList<Solmu> solmut = reader.luoVerkkoDavisista().getSolmut();
        
        Solmu alku = solmut.get(101);
        Solmu loppu = solmut.get(66);
        
        dijkstra.etsi(alku, loppu);
        ArrayList<Solmu> reitti = dijkstra.luoReitti(loppu);
        
        double tulos = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 765.6776429291275; 
        
        assertEquals(tulos, reitinOikeaPituus, epsilon); 
    }      
}
