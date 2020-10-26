
package algoritmit;

import static org.junit.Assert.*;

import io.VerkonLukija;
import komponentit.Solmu;
import org.junit.Before;
import org.junit.Test;
import tietorakenteet.ArrayList;

/**
 * Yksikkötestit IDA Star algoritmille
 * sisältää reittejä kartasta tali ja muutamia
 * lyhyempiä reittejä kartasta davis
 */

public class IDAStarTest {
    
    IDAStar idaStar;
    double epsilon = 0.01;
    VerkonLukija reader;
    
    @Before
    public void setUp() {
        this.idaStar = new IDAStar();
        
        this.reader = new VerkonLukija();
    } 
    
    /**
     * Reitit joita käytetään suorituskyvyn vertailussa
     */
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti4() {
        ArrayList<Solmu> solmut = reader.luoVerkkoDavisista().getSolmut();
        
        Solmu alku = solmut.get(16);
        Solmu loppu = solmut.get(36);
        
        idaStar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = idaStar.luoReitti(loppu);
        
        double tulos = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 881.4246491349884; 
        
        assertEquals(tulos, reitinOikeaPituus, epsilon);        
    }    
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti5() {
        
        ArrayList<Solmu> solmut = reader.luoVerkkoTalista().getSolmut();
        
        Solmu alku = solmut.get(0);
        Solmu loppu = solmut.get(solmut.size() - 1);
        
        idaStar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = idaStar.luoReitti(loppu);
        
        double tulos = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 802.8398626554501;
        
        assertEquals(tulos, reitinOikeaPituus, epsilon);
    }   
    
    /**
     * Muita reittejä Tali kartan pohjalta
     */
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti2Tali() {
        
        ArrayList<Solmu> solmut = reader.luoVerkkoTalista().getSolmut();
        
        Solmu alku = solmut.get(3);
        Solmu loppu = solmut.get(12);
        
        idaStar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = idaStar.luoReitti(loppu);
        
        double tulos = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 375.823394569141;
        
        assertEquals(tulos, reitinOikeaPituus, epsilon);
    }

    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti3Tali() {
        
        ArrayList<Solmu> solmut = reader.luoVerkkoTalista().getSolmut();
        
        Solmu alku = solmut.get(10);
        Solmu loppu = solmut.get(18);
        
        idaStar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = idaStar.luoReitti(loppu);
        
        double tulos = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 293.860036810901;
        
        assertEquals(tulos, reitinOikeaPituus, epsilon);
    }    
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti2TaliVaarinpain() {
        
        ArrayList<Solmu> solmut = reader.luoVerkkoTalista().getSolmut();
        
        Solmu alku = solmut.get(12);
        Solmu loppu = solmut.get(3);
        
        idaStar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = idaStar.luoReitti(loppu);
        
        double tulos = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 375.823394569141;
        
        assertEquals(tulos, reitinOikeaPituus, epsilon);
    }
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti3TaliVaarinpain() {
        
        ArrayList<Solmu> solmut = reader.luoVerkkoTalista().getSolmut();
        
        Solmu alku = solmut.get(18);
        Solmu loppu = solmut.get(10);
        
        idaStar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = idaStar.luoReitti(loppu);
        
        double tulos = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 293.860036810901;
        
        assertEquals(tulos, reitinOikeaPituus, epsilon);
    } 
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti5Vaarinpain() {
        
        ArrayList<Solmu> solmut = reader.luoVerkkoTalista().getSolmut();
        
        Solmu alku = solmut.get(solmut.size() - 1);
        Solmu loppu = solmut.get(0);
        
        idaStar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = idaStar.luoReitti(loppu);
        
        double tulos = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 802.8398626554501;
        
        assertEquals(tulos, reitinOikeaPituus, epsilon);
    }    
    
    /**
     * Muita reittejä Davis kartan pohjalta
     */    
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti2Davis() {
        ArrayList<Solmu> solmut = reader.luoVerkkoDavisista().getSolmut();
        
        Solmu alku = solmut.get(150);
        Solmu loppu = solmut.get(120);
        
        idaStar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = idaStar.luoReitti(loppu);
        
        double tulos = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 901.4661092508106; 
        
        assertEquals(tulos, reitinOikeaPituus, epsilon); 
    }
    
    @Test
    public void palauttaaOikeinLyhyimmanPolunReitti3Davis() {
        ArrayList<Solmu> solmut = reader.luoVerkkoDavisista().getSolmut();
        
        Solmu alku = solmut.get(101);
        Solmu loppu = solmut.get(66);
        
        idaStar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = idaStar.luoReitti(loppu);
        
        double tulos = reitti.get(reitti.size() - 1).getG();
        double reitinOikeaPituus = 765.6776429291275; 
        
        assertEquals(tulos, reitinOikeaPituus, epsilon); 
    }    
}
