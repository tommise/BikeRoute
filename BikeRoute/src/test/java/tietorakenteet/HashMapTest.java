
package tietorakenteet;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HashMapTest {
    
    HashMap<Integer, Double> hashMap;
    double epsilon = 0.001;
    
    @Before
    public void setUp() {
        this.hashMap = new HashMap<>();
    } 
    
    @Test
    public void lisaaElementinHajautustauluun() {
        this.hashMap.put(5, 5.5);
        
        assertEquals(5.5, this.hashMap.get(5), epsilon);
        assertNotNull(this.hashMap.get(5));
    }
    
    @Test
    public void lisaaUseammanElementinTauluun() {
        this.hashMap.put(1, 200.00);
        this.hashMap.put(2, 600.00);
        this.hashMap.put(3, 100.00);
        
        assertEquals(3, this.hashMap.size());
    }
    
    @Test
    public void kokoKasvaaKunHajautustauluTaysi() {
        for (int i = 0; i < 2100; i++) {
            this.hashMap.put(i, 200.00);
        }
        
        assertEquals(2100, this.hashMap.size());
    }
    
    @Test
    public void noutaaArvonHajautustaulusta() {
        this.hashMap.put(1, 200.00);
        this.hashMap.put(2, 600.00);
        
        double arvo = this.hashMap.get(2);
        
        assertEquals(600.00, arvo, epsilon);
    }
    
    @Test
    public void tyhjaAvainEiMeneHajautustauluun() {
        this.hashMap.put(null, 100.00);
        
        assertEquals(0, this.hashMap.size());
    }
    
    @Test
    public void getPalauttaaNullKunEiLoydy() {
        assertNull(this.hashMap.get(1));
    }
}
