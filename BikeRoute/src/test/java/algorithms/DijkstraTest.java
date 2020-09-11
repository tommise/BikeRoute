
package algorithms;

import components.Solmu;
import components.Verkko;
import domain.GraphBuilder;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static domain.GraphBuilder.luoPolkupyoraVerkosto;

public class DijkstraTest {
    
    Verkko verkko;
    Dijkstra dijkstra;
    GraphBuilder graphBuilder;
    double epsilon = 0.001;
    
    @Before
    public void setUp() {
        this.dijkstra = new Dijkstra();
        this.verkko = luoPolkupyoraVerkosto();
    } 
    
    @Test
    public void palauttaaOikeinLyhyimmänPolun() {
        Solmu lahto = verkko.getByName("Rantatie");
        Solmu tavoite = verkko.getByName("Keskustie");
        
        List<Solmu> reitti = dijkstra.etsiLyhyinReitti(lahto, tavoite);
        
        double reitinKokonaisPituus = reitti.get(reitti.size()-1).getMinimiEtaisyys();
        double reitinTavoitePituus = 325.0;
        
        assertEquals(reitinKokonaisPituus, reitinTavoitePituus, epsilon);
    }
}
