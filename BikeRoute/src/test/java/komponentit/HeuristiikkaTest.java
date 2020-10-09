
package components;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HeuristiikkaTest {
    
    Heuristiikka heuristiikka;
    
    double epsilon = 0.3;
    
    @Before
    public void setUp() {
        this.heuristiikka = new Heuristiikka();
    }
    
    /**
     * Kahden pisteen oikea etaisyys laskettu linnuntielaskurin avulla, esim mapdevelopers
     */
    
    @Test
    public void haversinePalauttaaOikein() {
        Solmu gurula = new Solmu("Gurula", 60.2042304, 24.9614875);
        Solmu klusteri = new Solmu("Klusteri", 60.1696052, 24.921479993131108);
        
        double klulat = klusteri.getLatitude();
        double klulon = klusteri.getLongitude();
        
        double gurlat = gurula.getLatitude();
        double gurlon = gurula.getLongitude();
        
        double etaisyys = heuristiikka.haversineMethod(klulat, klulon, gurlat, gurlon);
        
        double oikeaEtaisyys = 4440;
        
        assertEquals(oikeaEtaisyys, etaisyys, epsilon);
    }
}