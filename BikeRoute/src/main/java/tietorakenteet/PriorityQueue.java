
package tietorakenteet;

import java.util.Comparator;

/**
 * PriorityQueueta kuvaava luokka
 * @param <E> geneerinen rakenne
 */

public class PriorityQueue<E> {

    private final Comparator<E> comparator;
    private Object[] prjono;
    private int koko;
    
    /**
     * PriorityQueuen konstruktori joka saa parametrinaan comparator olion
     * @param comparator 
     */

    public PriorityQueue(Comparator comparator) {
        this.comparator = comparator;
        this.koko = 0;
        this.prjono = new Object[10];
    }
    
    /**
     * Lisää elementin jonoon
     * @param obj lisättävä elementti
     */
    
    public void add(Object obj) {
        
        if (isFull()) {
            kasvataListanKokoa();
        }
        
        prjono[koko] = obj;
        
        if (!isEmpty()) {
            int tail = koko - 1;
            
            if (comparator.compare((E) obj, (E) prjono[tail]) < 0) {
                
                Object apu1 = prjono[tail];
                Object apu2;
                
                prjono[tail] = obj;
                
                while (tail <= koko) {
                    tail++;
                    
                    apu2 = prjono[tail];
                    prjono[tail] = apu1;
                    apu1 = apu2;
                }
            }
        }

        kasvataKokoLaskuria(); 
    } 
    
    /**
     * Tarkistaa löytyykö haluttu elementti jonosta
     * @param obj kysytty elementti
     * @return palauttaa true jos löytyy, false jos ei löydy
     */
    
    public boolean contains(E obj) {
        
        for (int i = 0; i < this.koko; i++) {
            if (prjono[i] == obj) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Palauttaa ensimmäisen elementin ja korjaa listan
     * @return ensimmäisen elementti jonossa, jos prjono on tyhjä palauttaa null
     */    
    
    public E poll() {
        
        Object[] uusiLista = new Object[prjono.length];
        final Object palautettavaObj = prjono[0];
        
        int j = 0;
        
        for (int i = 1; i < prjono.length; i++) {
            uusiLista[j] = prjono[i];
            j++;
        }
        
        prjono = uusiLista;
        
        vahennaKokoLaskuria();        
        
        return (E) palautettavaObj;
    }
    
    /**
     * Palauttaa jonon koon
     * @return jonon koko
     */
    
    public int size() {
        return koko;
    }
    
    /**
     * Tarkistaa onko prjono tyhjä
     * @return palauttaa true jos tyhjä, false jos ei
     */
    
    public boolean isEmpty() {
        return koko == 0;
    }    
    
    /**
     * Tarkistaa onko prjono täysi
     * @return palauttaa true jos täysi, false jos ei
     */
    
    public boolean isFull() {
        return koko == prjono.length - 1;
    }
    
    /**
     * Vähentää listan koko laskuria
     */
    public void vahennaKokoLaskuria() {
        if (!isEmpty()) {
            koko--;
        }
    }
    
    /**
     * Kasvattaa listan koko laskuria
     */
    
    public void kasvataKokoLaskuria() {
        koko++;
    }
    
    /**
     * Kasvattaa listan kokoa
     */
    
    private void kasvataListanKokoa() {
        Object[] apu = new Object[prjono.length * 2];
        
        for (int i = 0; i < this.prjono.length; i++) {
            apu[i] = this.prjono[i];
        }
        
        this.prjono = apu;
    }
}