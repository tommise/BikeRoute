
package tietorakenteet;

public class LinkedList<E> {
    
    private int koko;
    private LinkedListSolmu head;
    
    /**
     * LinkedListin konstruktori, koko aletetaan nollaksi
     */
    
    public LinkedList() {
        this.koko = 0;         
        this.head = null;
    }
    
    /**
     * Lisää elementin alkuun
     * @param obj 
     */
    
    public void addFirst(E obj) {
        
        if (isEmpty()) {
            head = new LinkedListSolmu(obj);
        } else {
            LinkedListSolmu apu = head;
            head = new LinkedListSolmu(null, obj, apu);
            head.getSeuraava().setEdellinen(head);
        }
        
        koko++;
    }
    
    /**
     * Lisää elementin tietyn elementin jälkeen
     * @param obj
     * @param lisattavaSolmu 
     */
    
    public void addAfter(E obj, E lisattavaSolmu) {
        if (isEmpty()) {
            return;
        }
        
        LinkedListSolmu nyky = head;
        
        while (nyky != null && !nyky.getSolmu().equals(obj)) {
            nyky = nyky.getSeuraava();
        }
        
        if (nyky == null) {
            return;
        }
        
        LinkedListSolmu uusiSolmu = new LinkedListSolmu(nyky, lisattavaSolmu, nyky.getSeuraava());
        
        if (nyky.getSeuraava() != null) {
            nyky.getSeuraava().setEdellinen(uusiSolmu);
        }
        
        nyky.setSeuraava(uusiSolmu);
        
        koko++;
        
    }
    
    /**
     * Palauttaa halutun elementin listalta
     * @param indeksi
     * @return haluttu elementti indeksistä x tai null jos ei löydy
     */
    
    public E get(int indeksi) {
        if (indeksi < 0 || indeksi > koko - 1) {
            throw new IndexOutOfBoundsException();
        }
        
        LinkedListSolmu nyky = head;

        int laskuri = 0;
        
        while (nyky != null) {
            
            if (laskuri == indeksi) {
                return (E) nyky.getSolmu();
            }

            nyky = nyky.getSeuraava();
            
            laskuri++;
        }
        
        return null;
    }
    /**
     * Tarkistaa löytyykö haluttu objekti listalta
     * @param obj
     * @return 
     */
    
    public boolean contains(E obj) {
        
        LinkedListSolmu nyky = head;
        
        while (nyky != null) {
            
            if (nyky.getSolmu() == obj) {
                return true;
            }

            nyky = nyky.getSeuraava();
        }
        return false;
    }
    
    /**
     * Poistaa halutun elementin listalta
     * @param obj 
     */
    
    public void remove(E obj) {
        if (isEmpty()) {
            return;
        }
        
        if (head.getSolmu().equals(obj)) {
            head = head.getSeuraava();
            return;
        }
        
        LinkedListSolmu nyky = head;
        
        while (nyky != null && !nyky.getSolmu().equals(obj)) {
            nyky = nyky.getSeuraava();
        }
        
        if (nyky == null) {
            return;
        }
        
        if (nyky.getSeuraava() != null) {
            nyky.getSeuraava().setEdellinen(nyky.getEdellinen());
        }

        nyky.getEdellinen().setSeuraava(nyky.getSeuraava());
        
        koko--;
    }
    
    /**
     * Palauttaa listan koon
     * @return koko
     */
    
    public int size() {
        return koko;
    }
    
    /**
     * tarkistaa onko lista tyhjä
     * @return true jos tyhjä, false jos ei
     */
    
    public boolean isEmpty() {
        return koko == 0;
    }    
    
    /**
     * LinkedListin toString metodi
     * @return palauttaa listan elementit String muodossa
     */
    
    @Override
    public String toString() {
        LinkedListSolmu apu = head;
        
        StringBuilder rakentaja = new StringBuilder("[");
        
        while (apu != null) {
            rakentaja.append(apu.getSolmu());
            rakentaja.append(", ");
            
            apu = apu.getSeuraava();
        }
        
        // Poistetaan viimeiset ", " ja lisätään tilalle "]"
        
        rakentaja.deleteCharAt(rakentaja.length() - 1);
        rakentaja.deleteCharAt(rakentaja.length() - 1);
        rakentaja.append("]");
        
        return rakentaja.toString();
    }
    
    /**
     * 
     * @param <E> 
     */
    
    class LinkedListSolmu<E> {
        
        private E solmu;
        private LinkedListSolmu edellinen;
        private LinkedListSolmu seuraava;
        
        /**
         * ListaSolmun konstruktori, parametrina data
         * @param solmu 
         */
        
        public LinkedListSolmu(E solmu) {
            this.solmu = solmu;
            this.edellinen = null;
            this.seuraava = null;
        }
        
        /**
         * Konstruktori jossa edellinen, data ja seuraava
         * @param edellinen
         * @param solmu
         * @param seuraava 
         */
        
        public LinkedListSolmu(LinkedListSolmu edellinen, E solmu, LinkedListSolmu seuraava) {
            this.edellinen = edellinen;
            this.solmu = solmu;
            this.seuraava = seuraava;
        }
        
        /**
         * Palauttaa edellisen solmun
         * @return edellinen solmu
         */
        
        public LinkedListSolmu getEdellinen() {
            return this.edellinen;
        }
        
        /**
         * Asettaa edellisen solmun
         * @param solmu 
         */
        
        public void setEdellinen(LinkedListSolmu solmu) {
            this.edellinen = solmu;
        }
        
        /**
         * Palauttaa seuraavan solmun
         * @return seuraava solmu
         */
        
        public LinkedListSolmu getSeuraava() {
            return this.seuraava;
        }
        
        /**
         * Asettaa seuraavan solmun
         * @param solmu 
         */
        
        public void setSeuraava(LinkedListSolmu solmu) {
            this.seuraava = solmu;
        }
        
        /**
         * Palauttaa solmun (eli datan)
         * @return 
         */
        
        public E getSolmu() {
            return this.solmu;
        }
    }
}
