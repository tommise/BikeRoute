
package komponentit;

import algoritmit.AStar;
import algoritmit.Dijkstra;
import io.VerkonRakentaja;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tietorakenteet.ArrayList;

public class SolmuTest {
    
    Verkko verkko;
    Dijkstra dijkstra;
    AStar aStar;
    double epsilon = 0.001;
    
    @Before
    public void setUp() {
        VerkonRakentaja rakentaja = new VerkonRakentaja();
        this.verkko = rakentaja.luoTestiVerkko();
        this.dijkstra = new Dijkstra();
        this.aStar = new AStar();
    }
    
    @Test
    public void resetoiSolmunOikeinDijkstra() {
        List<Solmu> solmut = verkko.getSolmut();
        
        Solmu alku = solmut.get(10);
        Solmu loppu = solmut.get(18);
        
        dijkstra.etsi(alku, loppu);
        ArrayList<Solmu> reitti = dijkstra.luoReitti(loppu);
        
        Solmu ensimmainen = reitti.get(0);
        ensimmainen.resetSolmu();
        
        assertEquals(Double.MAX_VALUE, ensimmainen.getMinimiEtaisyys(), epsilon);
        assertNull(ensimmainen.getEdellinenSolmu());        
    }
    
    @Test
    public void resetoiSolmunOikeinAstar() {
        List<Solmu> solmut = verkko.getSolmut();
        
        Solmu alku = solmut.get(10);
        Solmu loppu = solmut.get(18);
        
        aStar.etsi(alku, loppu);
        ArrayList<Solmu> reitti = aStar.luoReitti(loppu);
        
        Solmu ensimmainen = reitti.get(0);
        ensimmainen.resetSolmu();
        
        assertEquals(Double.MAX_VALUE, ensimmainen.getG(), epsilon);
        assertNull(ensimmainen.getEdellinenSolmu());
    }    
}
