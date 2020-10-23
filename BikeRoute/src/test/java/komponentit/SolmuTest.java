
package komponentit;

import static org.junit.Assert.*;

import algoritmit.AStar;
import algoritmit.Dijkstra;
import io.VerkonRakentaja;
import org.junit.Before;
import org.junit.Test;
import tietorakenteet.ArrayList;


public class SolmuTest {
    
    Verkko verkko;
    Dijkstra dijkstra;
    AStar astar;
    double epsilon = 0.001;
    
    @Before
    public void setUp() {
        VerkonRakentaja rakentaja = new VerkonRakentaja();
        this.verkko = rakentaja.luoTestiVerkko();
        this.dijkstra = new Dijkstra();
        this.astar = new AStar();
    }
    
    @Test
    public void resetoiSolmunGArvonOikein() {
        ArrayList<Solmu> solmut = verkko.getSolmut();
        
        Solmu alku = solmut.get(10);
        Solmu loppu = solmut.get(18);
        
        dijkstra.etsi(alku, loppu);
        ArrayList<Solmu> reitti = dijkstra.luoReitti(loppu);
        
        Solmu ensimmainen = reitti.get(0);
        ensimmainen.resetSolmu();
        
        assertEquals(Double.MAX_VALUE, ensimmainen.getG(), epsilon);  
    }
    
    @Test
    public void resetoiSolmunEdellisenSolmunOikein() {
        ArrayList<Solmu> solmut = verkko.getSolmut();
        
        Solmu alku = solmut.get(10);
        Solmu loppu = solmut.get(18);
        
        astar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = astar.luoReitti(loppu);
        
        Solmu ensimmainen = reitti.get(0);
        ensimmainen.resetSolmu();
        
        assertNull(ensimmainen.getEdellinenSolmu());
    }    
}
