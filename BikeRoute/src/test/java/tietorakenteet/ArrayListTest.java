
package tietorakenteet;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArrayListTest {
    
    ArrayList<Integer> lista;
    
    @Before
    public void setUp() {
        lista = new ArrayList<>();
    } 

    @Test
    public void lisaaElementinListalle() {
        lista.add(100);
        
        assertEquals(1, lista.size());
    }
    
    @Test
    public void palauttaaElementinListalta() {
        lista.add(100);
        lista.add(110);
        lista.add(120);
        
        int indeksi2 = lista.get(2);
        
        assertEquals(120, indeksi2);
    }
    
    @Test
    public void listaKasvaaKunKapasiteettiTaysi() {
        for (int i = 0; i < 40; i++) {
            lista.add(i);
        }
        
        assertEquals(40, lista.size());
    }
    
    @Test
    public void palauttaaListanKoon() {
        lista.add(1);
        lista.add(2);
        lista.add(3);
        
        assertEquals(3, lista.size());
    }
    
    @Test
    public void listaKasvaaKokoaKunListaTaysi() {
        for (int i = 0; i < 1200; i++) {
            lista.add(i);
        }
        
        assertEquals(1200, lista.size());
    }
    
    @Test(expected = IndexOutOfBoundsException.class) 
    public void negatiivinenIndeksiAntaaVirheen() {
        lista.get(-1);
    }
    
    @Test(expected = IndexOutOfBoundsException.class) 
    public void liianSuuriIndeksiAntaaVirheen() {
        lista.get(500);
    }    
}