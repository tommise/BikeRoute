
package tietorakenteet;

import static org.junit.Assert.*;

import komponentit.Solmu;

import java.util.Comparator;
import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTest {
    
    PriorityQueue<Solmu> queue;
    Comparator comp;    
    Solmu solmu1;
    Solmu solmu2;
    Solmu solmu3;
    
    @Before
    public void setUp() {
        comp = new Comparator<Solmu>() {
            @Override
            public int compare(Solmu s1, Solmu s2) {
                
                return Double.compare(s1.getMinimiEtaisyys(), s2.getMinimiEtaisyys());
            }
        };
        
        queue = new PriorityQueue<Solmu>(comp);
        this.solmu1 = new Solmu(1, 10, 10);
        this.solmu2 = new Solmu(2, 20, 20);
        this.solmu3 = new Solmu(3, 30, 30);
    } 
    
    @Test
    public void lisaaElementinJonoon() {
        
        queue.add(solmu3);
        
        assertEquals(1, queue.size());
    }
    
    @Test
    public void poistaaElementinListalta() {
        queue.add(solmu1);
        queue.add(solmu2);
        
        queue.remove(solmu2);
        
        assertEquals(1, queue.size());
    }  
    
    @Test
    public void elementtiLoytyyListalta() {
        queue.add(solmu1);
        queue.add(solmu2);
        
        assertTrue(queue.contains(solmu1));        
    }
    
    @Test
    public void elementtiEiLoydyListalta() {
        queue.add(solmu1);
        queue.add(solmu2);
        
        assertFalse(queue.contains(solmu3));
    }

    @Test
    public void pollPalauttaaEnsimmaisenElementin() {
        queue.add(solmu1);
        queue.add(solmu2);
        queue.add(solmu3);
        
        Solmu polled = queue.poll();
        
        assertEquals(solmu1, polled);
    }
    
    @Test
    public void pollPoistaaEnsimmaisenElementin() {
        queue.add(solmu1);
        queue.add(solmu2);
        queue.add(solmu3);
        
        queue.poll();
        
        assertEquals(false, queue.contains(solmu1));
    }       
    
    @Test
    public void jonoKasvaaKunKapasiteettiTaysi() {
        
        for (int i = 0; i < 15; i++) {
            queue.add(solmu3);
        }
        
        assertTrue(10 < queue.size());
    } 
    
    @Test
    public void palauttaaJononKoonOikein() {
        queue.add(solmu3);
        queue.add(solmu2);
        
        assertEquals(2, queue.size());
    }      
    
    @Test
    public void palauttaaTyhjaKunJonoTyhja() {
        assertTrue(queue.isEmpty());
    }  
    
    @Test
    public void palauttaaFalseKunJonossaElementteja() {
        queue.add(solmu3);
        assertFalse(queue.isEmpty());
    }  

    @Test
    public void palauttaaNullKunPollataanTyhjastaJonosta() {
        Solmu solmu = queue.poll();
        assertEquals(null, solmu);
    }
}