package algoritmit;

import komponentit.Kaari;
import komponentit.Solmu;

import tietorakenteet.ArrayList;
import tietorakenteet.Stack;

/**
 * Luokka IDA* algoritmille
*/

public class IDAStar {

    private final double tavoite;
    
    /**
     * IDA* algoritmin konstruktori jossa alustetaan loppusolmulle haluttu "tavoite" arvo eli 0
     */
    
    public IDAStar() {
        this.tavoite = Double.MIN_VALUE;
    }
    
    /**
     * Etsii lyhyimmän reitin halutusta alkusolmusta loppuun IDA* algoritmin mukaisesti
     * @param alku alkusolmu mistä lähdetään liikkeelle
     * @param loppu tavoitesolmu mihin halutaan päätyä
     * @return true jos reitti löytyy, false jos ei löydy
     */
    
    public boolean etsi(Solmu alku, Solmu loppu) {
        
        boolean found = false;
        
        double bound = alku.euklidinenHeuristiikka(loppu);

        Stack<Solmu> path = new Stack<>();
        path.push(alku);
        
        while (bound != Double.MAX_VALUE) {

            double t = rekursiivinenHaku(path, 0, bound, loppu);

            if (t == tavoite) {
                found = true;
                break;
            } else if (t == Double.MAX_VALUE) {
                return false;
            }

            bound = t;
        }
        
        return found;
    }
    
    /**
     * Etsii rekursiivisesti lyhimmän reitin boundin ehdoilla
     * Palauttaa saadun gluvun negaation, josta tiedetään onko reitti löydetty
     * @param loppu loppusolmu
     * @param g kasaantuva etäisyys
     * @param bound maksimi sallittu etäisyys
     * @param path reitti
     * @return Double.MIN_VALUE jos reitti löytyi, muuten min
     */

    private double rekursiivinenHaku(Stack<Solmu> path, double g, double bound, Solmu loppu) {

        Solmu solmu = path.peek();

        double h = solmu.euklidinenHeuristiikka(loppu);
        double f = g + h;

        if (f > bound) {
            return f;
        }

        solmu.setG(g);
        solmu.setF(f);

        if (solmu == loppu) {
            return tavoite;
        }

        double min = Double.MAX_VALUE;

        ArrayList<Kaari> kaaret = solmu.getKaaret();

        for (int i = 0; i < kaaret.size(); i++) {
            Kaari kaari = kaaret.get(i);
            Solmu lapsi = kaari.getLoppu();

            if (!path.contains(lapsi)) {

                path.push(lapsi);
                lapsi.setEdellinenSolmu(solmu);
                
                double uusiMin = rekursiivinenHaku(path, solmu.getG() + kaari.getEtaisyys(), bound, loppu);

                if (uusiMin == tavoite) {
                    return tavoite;
                } else if (uusiMin < min) {
                    min = uusiMin;
                }
                
                path.pop();
            }
        }

        return min;
    }
    
    /**
     * Luodaan reitti saadun tuloksen perusteella
     * @param loppu solmu johon reitti päättyy
     * @return luotu lyhyin reitti lista muodossa
     */ 

    public ArrayList<Solmu> luoReitti(Solmu loppu) {
        ArrayList<Solmu> reitti = new ArrayList<>();

        for (Solmu solmu = loppu; solmu != null; solmu = solmu.getEdellinenSolmu()) {
            reitti.add(solmu);
        }
        
        ArrayList<Solmu> kaannettyReitti = new ArrayList<>(); 
        
        for (int i = reitti.size() - 1; i >= 0; i--) {
            Solmu solmu = reitti.get(i);
            kaannettyReitti.add(solmu);
        }
        
        return kaannettyReitti;
    }
}