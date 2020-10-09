
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
        
        assertEquals(true, this.hashMap.containsKey(5));
    }
    
    @Test
    public void noutaaArvonHajautustaulusta() {
        this.hashMap.put(2, 600.00);
        
        double arvo = this.hashMap.get(2);
        
        assertEquals(600.00, arvo, epsilon);
    }
    
    @Test
    public void containsPalauttaaFalseKunEiLoydy() {
        this.hashMap.put(10, 100.00);
        
        assertEquals(false, this.hashMap.containsKey(8));
    }
    
    @Test
    public void containsPalauttaaTrueKunLoytyy() {
        this.hashMap.put(11, 100.00);
        
        assertEquals(true, this.hashMap.containsKey(11));
    }
}
