
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

public class DijkstraTest {
    
    Verkko verkko;
    Dijkstra dijkstra;
    VerkonRakentaja graphBuilder;
    ReitinTulostaja pathHelper;
    double epsilon = 0.001;
    
    @Before
    public void setUp() {
        this.dijkstra = new Dijkstra();
        this.verkko = luoTestiVerkko();
    } 
    
    @Test
    public void palauttaaOikeinLyhyimmanPolun() {
        Solmu lahto = verkko.getByName("Rantatie");
        Solmu tavoite = verkko.getByName("Keskustie");
        
        dijkstra.etsi(lahto, tavoite);
        ArrayList<Solmu> reitti = dijkstra.luoReitti(tavoite);
        
        double reitinKokonaisPituus = reitti.get(reitti.size() - 1).getMinimiEtaisyys();
        double reitinTavoitePituus = 325.0;
        
        assertEquals(reitinKokonaisPituus, reitinTavoitePituus, epsilon);
    }
}
