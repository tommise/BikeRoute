
package tietorakenteet;

/**
 * ArrayList listaa kuvaava luokka
 * @param <E> geneerinen listarakenne
 */

public class ArrayList<E> {
    
    /**
     * Listalla Object muotoinen lista sekä listan koko
     */
    
    private Object[] arrayList;
    private int size;
    
    /**
     * ArrayListin konstruktori jossa alustetaan listarakenne sekä listan koko
     */
    
    public ArrayList() {
        arrayList = new Object[1000];
        this.size = 0;
    }
    
    /**
     * Lisää listarakenteelle olion
     * 
     * @param obj Lisättävä olio
     */
    
    public void add(E obj) {
        
        if (listaTaysi()) {
            kasvataKokoa();
        }
        
        this.arrayList[this.size] = obj;
        this.size++;
    }
    
    /**
     * Palauttaa halutun olion indeksin perusteella
     * 
     * @param indeksi - haluttavan olion indeksi
     * @return palautettava olio
     */
    
    public E get(int indeksi) {
        E obj;
        
        if (indeksi < 0 || indeksi >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        obj = (E)this.arrayList[indeksi];
        
        return obj;
    }
    
    /**
     * Palauttaa listan koon
     * 
     * @return listan koko
     */
    
    public int size() {
        return this.size;
    }
    
    /**
     * Tarkistaa onko lista täysi
     * 
     * @return true jos listarakenteen pituus vastaa kokoa
     */
    
    public boolean listaTaysi() {
        return this.arrayList.length == this.size;
    }
    
    /**
     * Kaksinkertaistaa listan kokoa
     */
    
    public void kasvataKokoa() {
        Object[] kasvatettuLista = new Object[arrayList.length * 2];
        
        for (int i = 0; i < this.arrayList.length; i++) {
            kasvatettuLista[i] = this.arrayList[i];
        }
        
        this.arrayList = kasvatettuLista;
    }
}
