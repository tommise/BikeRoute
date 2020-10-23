package algoritmit;

import komponentit.Heuristiikka;
import komponentit.Kaari;
import komponentit.Solmu;

import tietorakenteet.ArrayList;

/**
 * Etsii lyhimmän reitin alkusolmusta loppusolmuun ja rakentaa reitin tämän jälkeen
 * Pseudokoodia https://en.wikipedia.org/wiki/Iterative_deepening_A* mukaillen
*/

public class IDAStar {

    private final Heuristiikka heuristiikka;
    private final double maali;
    
    /**
     * IDA* algoritmin konstruktori jossa alustetaan heuristiikkaolio ja maali arvo
     */
    public IDAStar() {
        this.heuristiikka = new Heuristiikka();
        this.maali = Double.MIN_VALUE;
    }
    
    /**
     * Etsitään solmua niin kauan kunnes tulos löytyy
     * @param alku
     * @param loppu
     * @return 
     */
    
    public boolean etsi(Solmu alku, Solmu loppu) {
        
        boolean found = false;
        
        double bound = heuristiikka.manhattanEtaisyys(alku, loppu);

        ArrayList<Solmu> path = new ArrayList<>();
        path.add(alku);
        
        while (bound != Double.MAX_VALUE) {

            double t = rekursiivinenHaku(path, 0, bound, loppu);

            if (t == maali) {
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

    private double rekursiivinenHaku(ArrayList<Solmu> path, double g, double bound, Solmu loppu) {

        Solmu solmu = path.getLast();

        double h = heuristiikka.manhattanEtaisyys(solmu, loppu);
        double f = g + h;

        if (f > bound) {
            return f;
        }
        
        solmu.setH(h);
        solmu.setG(g);
        solmu.setF(f);

        if (solmu == loppu) {
            return maali;
        }

        double min = Double.MAX_VALUE;

        ArrayList<Kaari> kaaret = solmu.getKaaret();

        for (int i = 0; i < kaaret.size(); i++) {
            Kaari kaari = kaaret.get(i);
            Solmu lapsi = kaari.getLoppu();

            if (!path.contains(lapsi)) {

                path.add(lapsi);
                lapsi.setEdellinenSolmu(solmu);
                
                double uusiMin = rekursiivinenHaku(path, solmu.getG() + kaari.getEtaisyys(), bound, loppu);

                if (uusiMin == maali) {
                    return maali;
                } else if (uusiMin < min) {
                    min = uusiMin;
                }
                
                path.removeLast();
            }
        }

        return min;
    }
    
    /**
     * Luodaan reitti loppusolmun perusteella ja käännetään tämä
     * @param loppu
     * @return reitti ArrayList olion muodossa
     */

    public ArrayList<Solmu> luoReitti(Solmu loppu) {
        ArrayList<Solmu> reitti = new ArrayList<>();

        for (Solmu solmu = loppu; solmu != null; solmu = solmu.getEdellinenSolmu()) {
            reitti.add(solmu);
        }
        
        ArrayList<Solmu> kaannettyReitti = new tietorakenteet.ArrayList<>(); 
        
        for (int i = reitti.size() - 1; i >= 0; i--) {
            Solmu solmu = reitti.get(i);
            kaannettyReitti.add(solmu);
        }
        
        return kaannettyReitti;
    }
}