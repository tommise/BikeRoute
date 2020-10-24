
package tietorakenteet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class StackTest {
    Stack<Integer> pino;
    
    @Before
    public void setUp() {
        pino = new Stack<>();
    }
    
    @Test
    public void pushLisaaElementitKekoon() {
        pino.push(1);
        pino.push(2);
        
        int kakkonen = pino.pop();
        
        assertEquals(2, kakkonen);
        assertEquals(1, pino.size());
    }
    
    @Test
    public void popPalauttaaOikeatElementit() {
        pino.push(1);
        pino.push(2);
        pino.push(3);
        
        int kolmonen = pino.pop();
        int kakkonen = pino.pop();
        int ykkonen = pino.pop();
        
        assertEquals(3, kolmonen);
        assertEquals(2, kakkonen);
        assertEquals(1, ykkonen);
    }
    
    @Test
    public void containsPalauttaaTrueKunElementtiLoytyy() {
        pino.push(1);
        pino.push(2);
        pino.push(3);
        
        assertTrue(pino.contains(2));
    }
    
    @Test
    public void containsPalauttaaFalseKunElementtiaEiLoydy() {
        pino.push(1);
        pino.push(2);
        pino.push(3);
        
        assertFalse(pino.contains(4));
    }
    
    @Test
    public void popPalauttaaNullKunListaTyhja() {
        assertNull(pino.pop());
    }
    
    @Test
    public void peekPalauttaaNullKunListaTyhja() {
        assertNull(pino.peek());
    }    
    
    @Test
    public void peekPalauttaaPinonViimeisenElementin() {
        pino.push(1);
        pino.push(6);
        pino.push(4);
        
        int paallimmainen = pino.peek();

        assertEquals(4, paallimmainen);
    }
    
    @Test
    public void palauttaaListanKoonOikein() {
        pino.push(1);
        pino.push(2);
        pino.push(3);
        pino.pop();
        pino.pop();
        
        assertEquals(1, pino.size());
    }
    
    @Test
    public void pinoOnAlussaTyhja() {
        assertTrue(pino.isEmpty());
    }
    
    @Test
    public void isEmptyToimiiPoistonJalkeen() {
        pino.push(1);
        pino.pop();
        
        assertTrue(pino.isEmpty());
    }
}
