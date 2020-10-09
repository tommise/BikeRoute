
package algorithms;

import static io.VerkonRakentaja.luoTestiVerkko;
import static org.junit.Assert.*;

import components.Solmu;
import components.Verkko;

import org.junit.Before;
import org.junit.Test;

public class AStarTest {

    AStar astar;    
    Verkko verkko;
    double epsilon = 0.001;
    
    @Before
    public void setUp() {
        this.astar = new AStar();
        this.verkko = luoTestiVerkko();
    } 
    
    @Test
    public void palauttaaOikeinLyhyimmanPolun() {
        Solmu alku = verkko.getSolmuByNimi("Rantatie");
        Solmu loppu = verkko.getSolmuByNimi("Keskustie");
        
        astar.etsi(alku, loppu);
        java.util.ArrayList<Solmu> reitti = astar.luoReitti(loppu);
        
        double reitinKokonaisPituus = reitti.get(reitti.size() - 1).getG();
        double reitinTavoitePituus = 325.0;
        
        assertEquals(reitinKokonaisPituus, reitinTavoitePituus, epsilon);
    }
}
