
package tietorakenteet;

public class HashMap<K, V> {
    
    private final int koko;
    private final ArrayList<K> avaimet;
    private final ArrayList<V> arvot;
    private final EntryMap<K, V>[] taulukko;
    
    /**
     * HashMapin konstruktori
     */
    
    public HashMap() {
        this.koko = 50;
        this.avaimet = new ArrayList<>();
        this.arvot = new ArrayList<>();
        this.taulukko = new EntryMap[this.koko];
    }
    
    /**
     * Laskee hajautusarvon annetun avaimen perusteella
     * @param avain
     * @return palauttaa hajautusarvon
     */
    
    private int hajautusarvo(K avain) {
        return Math.abs(avain.hashCode()) % koko;
    }
    
    /**
     * Palauttaa arvon avaimen perusteella
     * @param avain
     * @return 
     */
    public V get(K avain) {
        int hajautusarvo = hajautusarvo(avain);
        
        if (taulukko[hajautusarvo] == null) {
            return null;
        }
        
        EntryMap<K, V> pari = taulukko[hajautusarvo];
        
        while (pari != null) {
            
            if (pari.getAvain().equals(avain)) {
                return pari.getArvo();
            }
            
            pari = pari.getSeuraava();
        }
        
        return null;
    }
    
    /**
     * Tarkistaa löytyykö haluttu avain hajautustaulusta
     * @param avain
     * @return palauttaa trtue jos löytyy, false jos ei
     */
    
    public boolean containsKey(K avain) {
        for (int i = 0; i < avaimet.size(); i++) {
            K verrattavaAvain = avaimet.get(i);
            
            if (avain == verrattavaAvain) {
                return true;
            }
            
        }
        return false;
    }    
    
    /**
     * Asettaa hajautustaulukkoo uuden arvon jos tyhjä, muuten lisää avaimelle
     * @param avain
     * @param arvo 
     */
    
    public void put(K avain, V arvo) {
        
        if (avain == null) {
            return;
        }
        
        EntryMap<K, V> uusi = new EntryMap<K,V>(avain, arvo, null);
        
        this.avaimet.add(avain);
        
        int hajautusarvo = hajautusarvo(avain);
        
        if (taulukko[hajautusarvo] == null) {
            taulukko[hajautusarvo] = uusi;
            return;
        }
        
        EntryMap<K, V> nyky = taulukko[hajautusarvo];
        EntryMap<K, V> edellinen = null;
        
        while (nyky != null) {
            
            if (nyky.getAvain().equals(uusi)) {
                
                uusi.setSeuraava(nyky.getSeuraava());
                
                if (edellinen != null) {
                    edellinen.setSeuraava(uusi);
                    return;
                }
                
                if (edellinen == null) {
                    taulukko[hajautusarvo] = uusi;
                    return;
                }
            }
            
            edellinen = nyky;
            nyky = nyky.getSeuraava();
        }
        
        edellinen.setSeuraava(uusi);
    }
    
    /**
     * HashMapin avain-arvo parit sekä seuraava pari
     * @param <K> avain
     * @param <V> arvo
     */
    
    private class EntryMap<K, V> {

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
}
