
package tietorakenteet;

/**
 * Geneerinen hajautustaulu solmujen ja kaarten läpikäyntiin
 * .txt muotoisesta tiedostosta luokassa io
 * @param <K> avain
 * @param <V> arvo
 */

public class HashMap<K, V> {
    
    private int koko;
    private int alkioita;
    private ArrayList<K> avaimet;
    private ArrayList<V> arvot;
    private EntryMap<K, V>[] taulukko;
    
    /**
     * HashMapin konstruktori
     */
    
    public HashMap() {
        this.koko = 1000;
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
        
        V arvo = null;
        
        while (pari != null) {
            
            if (pari.getAvain().equals(avain)) {
                arvo = pari.getArvo();
                break;
            }
            
            pari = pari.getSeuraava();
        }
        
        return arvo;
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
        
        if (koko == alkioita) {
            kasvataListanKokoa();
        }

        EntryMap<K, V> uusi = new EntryMap<>(avain, arvo, null);
        
        this.avaimet.add(avain);
        this.arvot.add(arvo);
        alkioita++;
        
        int hajautusarvo = hajautusarvo(avain);
        
        if (taulukko[hajautusarvo] == null) {
            taulukko[hajautusarvo] = uusi;
        }
    }
    
    /**
     * Palauttaa hajautustaulun koon
     * @return koko int muodossa
     */
    
    public int size() {
        return this.alkioita;
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
     * Palauttaa arvot ArrayListissä
     * @return values in ArrayList
     */
    
    public ArrayList<V> values() {
        return this.arvot;
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
         * Palauttaa seuraavan
         * @return seuraava
         */

        public EntryMap<K, V> getSeuraava() {
            return this.seuraava;
        }
    }    
}
