package algorithms;

import components.Kaari;
import components.Solmu;
import data_structures.ArrayList;

import java.util.PriorityQueue;

/**
 * Dijkstran algoritmi hyödyntäen PriorityQueta sekä lyhyimmän reitin tallettaminen listaan
 */

public class Dijkstra {
    
    public void etsi(Solmu lahtoSolmu, Solmu tavoiteSolmu) {
        
        lahtoSolmu.setMinimiEtaisyys(0);
        PriorityQueue<Solmu> pq = new PriorityQueue<Solmu>();
        pq.add(lahtoSolmu);

        while (!pq.isEmpty()) {
            Solmu solmu = pq.poll();

            for (Kaari kaari : solmu.getKaaret()) {
                Solmu s = kaari.getLoppu();
                double uusiMinimiEtaisyys = solmu.getMinimiEtaisyys() + kaari.getEtaisyys();

                if (uusiMinimiEtaisyys < s.getMinimiEtaisyys()) {
                    pq.remove(solmu);
                    s.setEdellinenSolmu(solmu);
                    s.setMinimiEtaisyys(uusiMinimiEtaisyys);
                    pq.add(s);
                }
            }
        }
        
        luoReitti(tavoiteSolmu);
    }

    /**
     * Luodaan reitti saadun tuloksen perusteella
     * @param tavoiteSolmu solmu johon reitti päättyy
     * @return lyhyin reitti lista muodossa
     */    
    
    public ArrayList<Solmu> luoReitti(Solmu tavoiteSolmu) {
        ArrayList<Solmu> reitti = new ArrayList<Solmu>();

        for (Solmu solmu = tavoiteSolmu; solmu != null; solmu = solmu.getEdellinenSolmu()) {
            reitti.add(solmu);
        }
        
        ArrayList<Solmu> kaannettyReitti = new ArrayList<Solmu>(); 
        
        for (int i = reitti.size()-1; i >= 0; i--) { 
            kaannettyReitti.add(reitti.get(i)); 
        } 
        
        return kaannettyReitti; 
    }
}