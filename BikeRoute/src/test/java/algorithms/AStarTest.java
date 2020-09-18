
package algorithms;

import components.Solmu;
import components.Verkko;
import data_structures.ArrayList;
import domain.GraphBuilder;
import static domain.GraphBuilder.luoPolkupyoraVerkosto;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import util.PathHelper;

public class AStarTest {

    AStar aStar;    
    Verkko verkko;
    GraphBuilder graphBuilder;
    PathHelper pathHelper;
    double epsilon = 0.001;
    
    @Before
    public void setUp() {
        this.aStar = new AStar();
        this.verkko = luoPolkupyoraVerkosto();
    } 
    
    @Test
    public void palauttaaOikeinLyhyimmänPolun() {
        Solmu lahto = verkko.getByName("Rantatie");
        Solmu tavoite = verkko.getByName("Keskustie");
        
        aStar.etsi(lahto, tavoite);
        ArrayList<Solmu> reitti = aStar.luoReitti(tavoite);
        
        double reitinKokonaisPituus = reitti.get(reitti.size()-1).getG();
        double reitinTavoitePituus = 325.0;
        
        assertEquals(reitinKokonaisPituus, reitinTavoitePituus, epsilon);
    }
}
