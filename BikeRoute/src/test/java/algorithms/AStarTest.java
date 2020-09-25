
package algorithms;

import static io.VerkonRakentaja.luoTestiVerkko;
import static org.junit.Assert.*;

import components.Solmu;
import components.Verkko;
import datastructures.ArrayList;
import io.VerkonRakentaja;

import org.junit.Before;
import org.junit.Test;
import util.ReitinTulostaja;

public class AStarTest {

    AStar astar;    
    Verkko verkko;
    VerkonRakentaja graphBuilder;
    ReitinTulostaja pathHelper;
    double epsilon = 0.001;
    
    @Before
    public void setUp() {
        this.astar = new AStar();
        this.verkko = luoTestiVerkko();
    } 
    
    @Test
    public void palauttaaOikeinLyhyimmanPolun() {
        Solmu lahto = verkko.getByName("Rantatie");
        Solmu tavoite = verkko.getByName("Keskustie");
        
        astar.etsi(lahto, tavoite);
        ArrayList<Solmu> reitti = astar.luoReitti(tavoite);
        
        double reitinKokonaisPituus = reitti.get(reitti.size() - 1).getG();
        double reitinTavoitePituus = 325.0;
        
        assertEquals(reitinKokonaisPituus, reitinTavoitePituus, epsilon);
    }
}
