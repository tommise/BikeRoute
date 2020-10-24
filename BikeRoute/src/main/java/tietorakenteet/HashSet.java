
package tietorakenteet;

public class HashSet<E> {
    private int koko;
    private int alkioita;
    private EntrySet<E>[] alkiot;
    
    /**
     * HashSetin konstruktori
     */
    
    public HashSet() {
        this.koko = 1000;
        this.alkioita = 0;
        this.alkiot = new EntrySet[koko];
    }
    
    /**
     * Laskee hajautusarvon annetun avaimen perusteella
     * @param data
     * @return palauttaa hajautusarvon
     */
    
    private int hajautusarvo(E data) {
        return Math.abs(data.hashCode()) % koko;
    }
    
    /**
     * Palauttaa listan koon
     * @return koko int muodossa
     */
    
    public int size() {
        return this.alkioita;
    }
    
    /**
     * Tarkastaa onko lista tyhja
     * @return true jos on, false jos ei
     */
    
    public boolean isEmpty() {
        return this.alkioita == 0;
    }    
    
    /**
     * Lisätään uusi olio listalle
     * @param obj lisättävä olio
     */
    
    public void add(E obj) {
        
        if (obj == null) {
            return;
        }
        
        int hajautusarvo = hajautusarvo(obj);
        
        EntrySet<E> uusiEntry = new EntrySet<>(obj, null);
        
        if (alkiot[hajautusarvo] != null) {
            
            EntrySet<E> nyky = alkiot[hajautusarvo];
            EntrySet<E> edellinen = null;

            while (nyky != null) {

                edellinen = nyky;
                nyky = nyky.getSeuraava();
            }

            edellinen.setSeuraava(uusiEntry); 
        }
        
        if (alkiot[hajautusarvo] == null) {
            alkiot[hajautusarvo] = uusiEntry;
        }
        
        alkioita++;
    }
    
    /**
     * Tarkastaa löytyykö kysytty olio 
     * @param obj haluttu olio
     * @return palauttaa true jos löytyi, false jos ei
     */
    
    public boolean contains(E obj) {
        int hajautusarvo = hajautusarvo(obj);
        
        if (alkiot[hajautusarvo] == null) {
            return false;
        }
        
        EntrySet<E> apu = alkiot[hajautusarvo];
        
        while (apu != null) {
            if (apu.getData().equals(obj)) {
                return true;
            }
            
            apu = apu.getSeuraava();
        }
        
        return false;
    }
    
    /**
     * Hashsetin entryt, jossa avain sekä seuraava
     * @param <E> 
     */
    
    private class EntrySet<E> {

        private E data;
        private EntrySet<E> seuraava;

        /**
         * HashSet entryn konstruktori
         * @param data
         * @param seuraava 
         */

        public EntrySet(E data, EntrySet<E> seuraava) {
            this.data = data;
            this.seuraava = seuraava;
        }

        /**
         * Palauttaa avaimen
         * @return avain
         */

        public E getData() {
            return this.data;
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
}
