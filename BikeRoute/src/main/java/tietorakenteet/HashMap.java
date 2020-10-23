
package tietorakenteet;

public class HashMap<K, V> {
    
    private int koko;
    private int alkioita;
    private java.util.ArrayList<K> avaimet;
    private java.util.ArrayList<V> arvot;
    private EntryMap<K, V>[] taulukko;
    
    /**
     * HashMapin konstruktori
     */
    
    public HashMap() {
        this.koko = 50;
        this.alkioita = 0;
        this.avaimet = new java.util.ArrayList<>();
        this.arvot = new java.util.ArrayList<>();
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
     * Palauttaa hajautustaulun koon
     * @return koko int muodossa
     */
    
    public int size() {
        return this.alkioita;
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
     * Tarkistaa löytyykö haluttu arvo hajautustaulusta
     * @param arvo
     * @return palauttaa trtue jos löytyy, false jos ei
     */    
    
    public boolean containsValue(V arvo) {
        for (int i = 0; i < arvot.size(); i++) {
            V verrattavaArvo = arvot.get(i);
            
            if (arvo == verrattavaArvo) {
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
        
        if (alkioita == koko - 1) {
            kasvataListanKokoa();
        }
        
        EntryMap<K, V> uusi = new EntryMap<>(avain, arvo, null);
        
        this.avaimet.add(avain);
        this.arvot.add(arvo);
        
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
        alkioita++;
    }
    
    /**
     * Kaksinkertaistaa listan koon
     */
    
    public void kasvataListanKokoa() {
        int uusiKoko = koko * 2;
        EntryMap<K, V>[] kasvatettuLista = new EntryMap[uusiKoko];
        
        for (int i = 0; i < taulukko.length; i++) {
            EntryMap<K, V> entryMap = taulukko[i];
            kasvatettuLista[i] = entryMap;
        }
        
        taulukko = kasvatettuLista;
        koko = uusiKoko;
    }
    
    /**
     * Tarkistaa onko lista tyhjä
     * @return palauttaa true jos on, false jos ei
     */
    
    public boolean isEmpty() {
        return alkioita == 0;
    }
    
    /**
     * Palauttaa arvot ArrayListissä
     * @return values in ArrayList
     */
    
    public java.util.ArrayList<V> values() {
        return this.arvot;
    }
    
    /**
     * Poistaa halutun avaimen hajautustaulusta
     * @param avain 
     */
    
    public void remove(K avain) {
        
        if (avain == null || !containsKey(avain)) {
            return;
        }
        
        int hajautusarvo = hajautusarvo(avain);
        
        EntryMap previous = null;
        EntryMap entry = taulukko[hajautusarvo];
        
        while (entry != null) {
            
            if (entry.getAvain().equals(avain)) {
                if (previous == null) {
                    entry = entry.getSeuraava();
                    taulukko[hajautusarvo] = entry;
                    alkioita--;
                    return;
                } else {
                    previous.setSeuraava(entry.getSeuraava());
                }
            }
            previous = entry;
            entry = entry.getSeuraava();
        }
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
