
package algorithms;

import static domain.GraphBuilder.luoPolkupyoraVerkosto;
import static org.junit.Assert.*;

import components.Solmu;
import components.Verkko;
import datastructures.ArrayList;
import domain.GraphBuilder;

import org.junit.Before;
import org.junit.Test;
import util.PathHelper;

public class AStarTest {

    AStar astar;    
    Verkko verkko;
    GraphBuilder graphBuilder;
    PathHelper pathHelper;
    double epsilon = 0.001;
    
    @Before
    public void setUp() {
        this.astar = new AStar();
        this.verkko = luoPolkupyoraVerkosto();
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
