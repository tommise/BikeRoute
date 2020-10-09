
package tietorakenteet;

public class HashSet<E> {
    private final int koko;
    private final EntrySet<E>[] alkiot;
    
    /**
     * HashSetin konstruktori
     */
    
    public HashSet() {
        this.koko = 50;
        this.alkiot = new EntrySet[koko];
    }
    
    /**
     * Laskee hajautusarvon annetun avaimen perusteella
     * @param avain
     * @return palauttaa hajautusarvon
     */
    
    private int hajautusarvo(E avain) {
        return Math.abs(avain.hashCode()) % koko;
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
        
        EntrySet<E> newEntry = new EntrySet<>(obj, null);
        
        if (alkiot[hajautusarvo] != null) {
            
            EntrySet<E> nyky = alkiot[hajautusarvo];
            EntrySet<E> edellinen = null;

            while (nyky != null) {

                if (edellinen != null && nyky.getAvain().equals(obj)) {
                    newEntry.setSeuraava(nyky.getSeuraava());
                    alkiot[hajautusarvo] = nyky.getSeuraava();

                    return;

                } else if (edellinen == null && nyky.getAvain().equals(obj)) {
                    newEntry.setSeuraava(nyky.getSeuraava());
                    edellinen.setSeuraava(newEntry);

                    return;
                }

                edellinen = nyky;
                nyky = nyky.getSeuraava();
            }

            edellinen.setSeuraava(newEntry); 
        }
        
        if (alkiot[hajautusarvo] == null) {
            alkiot[hajautusarvo] = newEntry;
        }
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
            if (apu.getAvain().equals(obj)) {
                return true;
            }
            
            apu = apu.getSeuraava();
        }
        
        return false;
    }
}
