
package tietorakenteet;

public class EntrySet<E> {
    
    private E avain;
    private EntrySet<E> seuraava;
    
    /**
     * HashSet entryn konstruktori
     * @param avain
     * @param seuraava 
     */
    
    public EntrySet(E avain, EntrySet<E> seuraava) {
        this.avain = avain;
        this.seuraava = seuraava;
    }
    
    /**
     * Palauttaa avaimen
     * @return avain
     */
    
    public E getAvain() {
        return this.avain;
    }
    
    /**
     * Asettaa avaimen
     * @param avain avain
     */
    
    public void setAvain(E avain) {
        this.avain = avain;
    }
    
    /**
     * Palauttaa seuraavan
     * @return seuraava
     */
    
    public EntrySet<E> getSeuraava() {
        return this.seuraava;
    }
    
    /**
     * Asettaa seuraavan
     * @param obj seuraava
     */
    
    public void setSeuraava(EntrySet<E> obj) {
        this.seuraava = obj;
    }
}
