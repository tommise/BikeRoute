
package algorithms;

import static io.VerkonRakentaja.luoTestiVerkko;
import static org.junit.Assert.*;

import components.Solmu;
import components.Verkko;
import org.junit.Before;
import org.junit.Test;

public class DijkstraTest {
    
    Verkko verkko;
    Dijkstra dijkstra;
    double epsilon = 0.001;
    
    @Before
    public void setUp() {
        this.dijkstra = new Dijkstra();
        this.verkko = luoTestiVerkko();
    } 
    
    @Test
    public void palauttaaOikeinLyhyimmanPolun() {
        Solmu lahto = verkko.getSolmuByNimi("Rantatie");
        Solmu tavoite = verkko.getSolmuByNimi("Keskustie");
        
        dijkstra.etsi(lahto, tavoite);
        java.util.ArrayList<Solmu> reitti = dijkstra.luoReitti(tavoite);
        
        double reitinKokonaisPituus = reitti.get(reitti.size() - 1).getMinimiEtaisyys();
        double reitinTavoitePituus = 325.0;
        
        assertEquals(reitinKokonaisPituus, reitinTavoitePituus, epsilon);
    }
}
