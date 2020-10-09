
package tietorakenteet;

import java.util.Comparator;

/**
 * PriorityQueueta kuvaava luokka
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
    
    public void add(E obj) {
        koko++;

        if (prjono.length <= koko) {
            kasvataKokoa();
        }
        
        int indeksi = koko - 1;

        while (indeksi > 0) {
            int verrattava = comparator.compare(obj, (E) prjono[getVanhempi(indeksi)]);
            
            if (verrattava > 0) {
                prjono[indeksi] = prjono[getVanhempi(indeksi)];
                indeksi = getVanhempi(indeksi);
            } else {
                break;
            }
        }

        prjono[indeksi] = obj;
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
     * Poistaa halutun elementin jonosta
     * @param obj haluttu poistettava elementti
     */
    
    public void remove(E obj) {
        
        if (!contains(obj)) {
            return;
        }
        
        int indeksi = getIndeksi(obj);
        
        prjono[indeksi] = prjono[koko - 1];
        koko--;
        maxHeapify(indeksi);
    }
    
    /**
     * Palauttaa ensimmäisen elementin
     * @return ensimmäisen elementti jonossa, jos prjono on tyhjä palauttaa null
     */    
    
    public E poll() {
        
        if (isEmpty()) {
            return null;
        }
        
        E polled = (E) prjono[0];

        prjono[0] = prjono[koko - 1];
        koko--;
        maxHeapify(0);

        return polled;
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
     * @return palauttaa jonon koon
     */
    
    public boolean isEmpty() {
        return koko == 0;
    }    
    
    /**
     * Kasvattaa jonon kokoa
     */
    
    private void kasvataKokoa() {
        Object[] apu = new Object[prjono.length * 2];
        
        for (int i = 0; i < this.prjono.length; i++) {
            apu[i] = this.prjono[i];
        }
        
        this.prjono = apu;
    }  
    
    /**
     * Palauttaa elementin indeksin
     * @param obj etsitty elementti
     * @return palautettava indeksi int muodossa
     */
    
    private int getIndeksi(E obj) {
        
        int index = 0;
        
        for (int i = 0; i < this.koko; i++) {
            if (prjono[i] == obj) {
                index = i;
                break;
            }
        }
        
        return index;
    }    
    
    /**
     * Vaihtaa vanhemman oikean lapsen kanssa.
     * @param indeksi haluttu indeksi
     */

    private void maxHeapify(int indeksi) {
        
        if (onkoLehti(indeksi)) {
            return; 
        }
        
        int vasen = getVasen(indeksi);
        int oikea = getOikea(indeksi);        
  
        if (vasen < indeksi && comparator.compare((E) prjono[vasen], (E) prjono[indeksi]) > 0) {
            vaihdaKeskenaan(indeksi, vasen);
            maxHeapify(vasen);
        }
        
        if (oikea < indeksi && comparator.compare((E) prjono[oikea], (E) prjono[indeksi]) > 0) {
            vaihdaKeskenaan(indeksi, oikea);
            maxHeapify(oikea);
        }
    }
    
    /**
     * Tarkastaa onko solmu ns. lehti eli solmu jolla ei ole lapsia
     * @param indeksi
     * @return palauttaa true jos annetulla indeksillä oleva solmu on lehti
     */
    
    private boolean onkoLehti(int indeksi) { 
        return indeksi >= (koko / 2) && indeksi <= koko;
    }     
    
    /**
     * Palauttaa halutun indeksin elementin vanhemman
     * @param indeksi nykyisen elementin indeksi
     * @return halutun elementin indeksi
     */

    private int getVanhempi(int indeksi) {
        return (indeksi - 1) / 2;
    }
    
    /**
     * Palauttaa vasemman lapsen
     * @param indeksi nykyisen elementin indeksi
     * @return halutun elementin indeksi
     */

    private int getVasen(int indeksi) {
        return indeksi * 2 + 1;
    }
    
    /**
     * Palauttaa oikean lapsen
     * @param indeksi nykyisen elementin indeksi
     * @return halutun elementin indeksi
     */

    private int getOikea(int indeksi) {
        return indeksi * 2 + 2;
    }
    
    /**
     * Vaihtaa kahden elemenin paikkaa keskenään
     * @param indeksi1 ensimmäinen elementti
     * @param indeksi2 toinen elementti
     */

    private void vaihdaKeskenaan(int indeksi1, int indeksi2) {
        E apu = (E) prjono[indeksi1];
        prjono[indeksi1] = prjono[indeksi2];
        prjono[indeksi2] = apu;
    }      
}