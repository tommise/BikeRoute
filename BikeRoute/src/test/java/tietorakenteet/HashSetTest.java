
package tietorakenteet;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HashSetTest {
    
    HashSet<String> hashSet;
    
    @Before
    public void setUp() {
        this.hashSet = new HashSet<>();
    } 
    
    @Test
    public void containsPalauttaaOikeinKunLoytyy() {
        hashSet.add("101");
        hashSet.add("202");
        
        boolean sisaltaako = hashSet.contains("202");
        
        assertEquals(true, sisaltaako);
    }
    
    @Test
    public void containsPalauttaaOikeinKunEiLoydy() {
        hashSet.add("101");
        hashSet.add("202");
        
        boolean sisaltaako = hashSet.contains("102");
        
        assertEquals(false, sisaltaako);
    }
    
    @Test
    public void lisaaOlionListalle() {
        hashSet.add("1000");
        hashSet.add("2000");
        
        assertEquals(true, hashSet.contains("1000"));
    }
    
    @Test
    public void kokoPaivittyyOikein() {
        hashSet.add("1000");
        hashSet.add("2000");
        hashSet.add("3000");
        
        assertEquals(3, hashSet.size());
    }
    
    @Test
    public void listaOnAlussaTyhja() {
        assertTrue(hashSet.isEmpty());
    }
    
    @Test
    public void tyhjanOlionLisaysEiOnnistu() {
        hashSet.add(null);
        
        assertTrue(hashSet.isEmpty());
    }
}