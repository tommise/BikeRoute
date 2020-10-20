
package tietorakenteet;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
    
    LinkedList<Integer> solmut;
    
    @Before
    public void setUp() {
        this.solmut = new LinkedList<>();   
    }

    @Test
    public void listaOnAlussaTyhja() {
        assertTrue(solmut.isEmpty());
    }
    
    @Test
    public void listaSizePalauttaaOikeinKunNolla() {
        assertEquals(0, solmut.size());
    }
    
    @Test
    public void listaSizePalauttaaOikeinKunKaksi() {
        solmut.addFront(1);
        solmut.addFront(2);
        
        assertEquals(2, solmut.size());
    }
    
    @Test
    public void poistoToimiiOikein() {
        solmut.addFront(4);        
        solmut.addFront(3);
        solmut.addFront(2);
        solmut.addFront(1);
        
        solmut.remove(2);
        
        assertEquals("[1, 3, 4]", solmut.toString());
    }
    
    @Test
    public void alkuunLisaysToimiiOikein() {
        solmut.addFront(3);
        solmut.addFront(2);
        solmut.addFront(1);
        solmut.addFront(4);
        
        assertEquals("[4, 1, 2, 3]", solmut.toString());
    }
    
    @Test
    public void halutunElementinJalkeenLisaysToimiiOikein() {
        solmut.addFront(3);
        solmut.addFront(2);
        solmut.addFront(1);
        
        solmut.addAfter(1, 4);
        
        assertEquals("[1, 4, 2, 3]", solmut.toString());
    }
    
    @Test
    public void noutoPalauttaaOikein() {
        
        solmut.addFront(3);
        solmut.addFront(2);
        solmut.addFront(1);
        
        int luku = solmut.get(1);
        
        assertEquals(2, luku);
    }
    
    @Test
    public void noutoPalauttaaOikeinEkaIndeksi() {
        
        solmut.addFront(3);
        solmut.addFront(2);
        solmut.addFront(1);
        
        int luku = solmut.get(0);
        
        assertEquals(1, luku);
    }         
    
    @Test
    public void noutoPalauttaaOikeinVikaIndeksi() {

        solmut.addFront(1);
        solmut.addAfter(1, 2);
        solmut.addAfter(2, 3);
        solmut.addAfter(3, 4);
        
        int luku = solmut.get(solmut.size() - 1);
        
        assertEquals(4, luku);
    }    
}
