
package komponentit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tietorakenteet.ArrayList;

public class VerkkoTest {
    
    Verkko verkko;
    Solmu solmu1;
    Solmu solmu2;
    Solmu solmu3;
    
    @Before
    public void setUp() {
        this.verkko = new Verkko();
        this.solmu1 = new Solmu(1, 2356235, 2352352);
        this.solmu2 = new Solmu(2, 8794265, 1799835);
        this.solmu3 = new Solmu(3, 2873592, 4218794);
    }
    
    @Test
    public void lisaaVerkolleSolmuja() {
        
        this.verkko.addSolmu(solmu1);
        this.verkko.addSolmu(solmu2);
        
        assertEquals(2, this.verkko.getSolmut().size());
    }
    
    @Test
    public void lisaaSolmutVerkolleArrayListina() {
        ArrayList<Solmu> solmut = new ArrayList<>();
        
        solmut.add(solmu1);
        solmut.add(solmu2);
        solmut.add(solmu3);
        
        this.verkko = new Verkko(solmut);
        
        assertEquals(3, this.verkko.getSolmut().size());
    }
}
