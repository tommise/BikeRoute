
package komponentit;

import io.VerkonRakentaja;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

public class VerkkoTest {
    
    Verkko verkko;
    
    @Before
    public void setUp() {
        VerkonRakentaja rakentaja = new VerkonRakentaja();
        this.verkko = rakentaja.luoTestiVerkko();
    }
    
    @Test
    public void palauttaaOlionLatJaLonPerusteella() {
        assertNotNull(this.verkko.getSolmuByLatAndLon(60.219675, 24.861553));
    }
    
    @Test
    public void palauttaaNullKunEiLoydyLatJaLonPerusteella() {
        assertNull(this.verkko.getSolmuByLatAndLon(60.114252, 20.1652414));
    }
}
