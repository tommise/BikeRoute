
package tietorakenteet;

public class EntryMap<K, V> {
    
    private K avain;
    private V arvo;
    private EntryMap<K, V> seuraava;
    
    /**
     * HashMap entryn konstruktori
     * @param avain
     * @param arvo
     * @param seuraava 
     */
    
    public EntryMap(K avain, V arvo, EntryMap<K, V> seuraava) {
        this.avain = avain;
        this.arvo = arvo;
        this.seuraava = seuraava;
    }
    
    /**
     * Palauttaa avaimen
     * @return avain
     */
    
    public K getAvain() {
        return this.avain;
    }
    
    /**
     * Palauttaa arvon
     * @return arvo
     */
    
    public V getArvo() {
        return this.arvo;
    }
    
    /**
     * Asettaa avaimen
     * @param avain 
     */
    
    public void setAvain(K avain) {
        this.avain = avain;
    }
    
    /**
     * Asettaa arvon
     * @param arvo 
     */
    
    public void setArvo(V arvo) {
        this.arvo = arvo;
    }
    
    /**
     * Palauttaa seuraavan
     * @return seuraava
     */
    
    public EntryMap<K, V> getSeuraava() {
        return this.seuraava;
    }

    /**
     * Asettaa seuraavan
     * @param obj 
     */
    
    public void setSeuraava(EntryMap<K, V> obj) {
        this.seuraava = obj;
    }
}
