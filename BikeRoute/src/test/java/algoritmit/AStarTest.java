
package algoritmit;

import static org.junit.Assert.*;

import io.VerkonLukija;
import komponentit.Solmu;
import komponentit.Verkko;
import org.junit.Before;
import org.junit.Test;
import tietorakenteet.ArrayList;

public class AStarTest {

    AStar astar;    
    Verkko verkko;
    double epsilon = 0.01;
    VerkonLukija reader;
    
    @Before
    public void setUp() {
        this.astar = new AStar();
        
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
        
        astar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = astar.luoReitti(loppu);
        
        double lyhyinPolkuAStar = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 2444.6838193006743;     

        assertEquals(lyhyinPolkuAStar, reitinOikeaPituus, epsilon);        
    }
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti2() {
        ArrayList<Solmu> solmut = reader.luoVerkkoDavisista().getSolmut();
        
        Solmu alku = solmut.get(150);
        Solmu loppu = solmut.get(41);
        
        astar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = astar.luoReitti(loppu);
        
        double lyhyinPolkuAStar = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 2997.0403280132705; 

        assertEquals(lyhyinPolkuAStar, reitinOikeaPituus, epsilon);
    }
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti3() {
        ArrayList<Solmu> solmut = reader.luoVerkkoDavisista().getSolmut();
        
        Solmu alku = solmut.get(87);
        Solmu loppu = solmut.get(183);
        
        astar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = astar.luoReitti(loppu);
        
        double lyhyinPolkuAStar = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 3415.7757993535465; 

        assertEquals(lyhyinPolkuAStar, reitinOikeaPituus, epsilon);
    }
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti4() {
        ArrayList<Solmu> solmut = reader.luoVerkkoDavisista().getSolmut();
        
        Solmu alku = solmut.get(16);
        Solmu loppu = solmut.get(36);
        
        astar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = astar.luoReitti(loppu);
        
        double lyhyinPolkuAStar = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 881.4246491349884; 

        assertEquals(lyhyinPolkuAStar, reitinOikeaPituus, epsilon);
    }    
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti5() {
        
        ArrayList<Solmu> solmut = reader.luoVerkkoTalista().getSolmut();
        
        Solmu alku = solmut.get(0);
        Solmu loppu = solmut.get(solmut.size() - 1);
        
        astar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = astar.luoReitti(loppu);
        
        double lyhyinPolkuAStar = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 802.8398626554501;
        
        assertEquals(lyhyinPolkuAStar, reitinOikeaPituus, epsilon);
    }
    
    /**
     * Muita reittejä Tali kartan pohjalta
     */
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti2Tali() {
        
        ArrayList<Solmu> solmut = reader.luoVerkkoTalista().getSolmut();
        
        Solmu alku = solmut.get(3);
        Solmu loppu = solmut.get(12);
        
        astar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = astar.luoReitti(loppu);
        
        double lyhyinPolkuAStar = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 375.823394569141;
        
        assertEquals(lyhyinPolkuAStar, reitinOikeaPituus, epsilon);
    }

    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti3Tali() {
        
        ArrayList<Solmu> solmut = reader.luoVerkkoTalista().getSolmut();
        
        Solmu alku = solmut.get(10);
        Solmu loppu = solmut.get(18);
        
        astar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = astar.luoReitti(loppu);
        
        double lyhyinPolkuAStar = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 293.860036810901;
        
        assertEquals(lyhyinPolkuAStar, reitinOikeaPituus, epsilon);
    }    
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti2TaliVaarinpain() {
        
        ArrayList<Solmu> solmut = reader.luoVerkkoTalista().getSolmut();
        
        Solmu alku = solmut.get(12);
        Solmu loppu = solmut.get(3);
        
        astar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = astar.luoReitti(loppu);
        
        double lyhyinPolkuAStar = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 375.823394569141;
        
        assertEquals(lyhyinPolkuAStar, reitinOikeaPituus, epsilon);
    }
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti3TaliVaarinpain() {
        
        ArrayList<Solmu> solmut = reader.luoVerkkoTalista().getSolmut();
        
        Solmu alku = solmut.get(18);
        Solmu loppu = solmut.get(10);
        
        astar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = astar.luoReitti(loppu);
        
        double lyhyinPolkuAStar = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 293.860036810901;
        
        assertEquals(lyhyinPolkuAStar, reitinOikeaPituus, epsilon);
    }
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti5Vaarinpain() {
        
        ArrayList<Solmu> solmut = reader.luoVerkkoTalista().getSolmut();
        
        Solmu alku = solmut.get(solmut.size() - 1);
        Solmu loppu = solmut.get(0);
        
        astar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = astar.luoReitti(loppu);
        
        double lyhyinPolkuAStar = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 802.8398626554501;
        
        assertEquals(lyhyinPolkuAStar, reitinOikeaPituus, epsilon);
    }    
    
    /**
     * Muita reittejä Davis kartan pohjalta
     */
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti5Davis() {
        ArrayList<Solmu> solmut = reader.luoVerkkoDavisista().getSolmut();
        
        Solmu alku = solmut.get(150);
        Solmu loppu = solmut.get(120);
        
        astar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = astar.luoReitti(loppu);
        
        double tulos = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 901.4661092508106; 
        
        assertEquals(tulos, reitinOikeaPituus, epsilon); 
    }
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti6Davis() {
        ArrayList<Solmu> solmut = reader.luoVerkkoDavisista().getSolmut();
        
        Solmu alku = solmut.get(101);
        Solmu loppu = solmut.get(66);
        
        astar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = astar.luoReitti(loppu);
        
        double tulos = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 765.6776429291275; 
        
        assertEquals(tulos, reitinOikeaPituus, epsilon); 
    }        
}
