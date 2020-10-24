
package tietorakenteet;

/**
 * Geneerinne pinorakenne käyttäen LIFO(Last In First Out) menetelmää
 * @param <E> 
 */

public class Stack<E> {
    
    private int size;
    private Object[] pino;
    
    /**
     * Pinon konstruktori jossa asetetaan koko ja pinorakenne
     */
    public Stack() {
        this.pino = new Object[1000];
        this.size = 0;
    }
    
    /**
     * Lisää halutun olion pinoon
     * @param obj 
     */
    
    public void push(E obj) {
        pino[size] = obj;
        this.size++;
    }
    
    /**
     * Palauttaa viimeisen olion pinosta
     * @return olio viimeisellä indeksillä
     */
    
    public E pop() {
        if (!isEmpty()) {
            E obj  = (E) pino[size - 1];
            this.size--;
            return obj;
        }
        return null;
    }
    
    /**
     * Katsoo pinon viimeisen olion
     * @return viimeisen indeksin olio
     */
    
    public E peek() {
        if (!isEmpty()) {
            return (E) pino[size - 1];
        }
        return null;
    }
    
    /**
     * Tarkistaa löytyykö tietty olio pinosta
     * @param obj
     * @return palauttaa true jos löytyy, false jos ei
     */
    
    public boolean contains(E obj) {
        
        if (obj == null) {
            return false;
        }
        
        for (int i = 0; i < size; i++) {
            E verrattava = (E) pino[i];
            if (verrattava == obj) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Palauttaa pinon koon
     * @return pinon koko int muodossa
     */
    
    public int size() {
        return this.size;
    }
    
    /**
     * Tarkistaa onko pino tyhjä
     * @return true jos pino on tyhjä, false jos ei
     */
    
    public boolean isEmpty() {
        return this.size == 0;
    }
}
