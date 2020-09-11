package algorithms;

import components.Kaari;
import components.Solmu;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Dijkstran algoritmi hyödyntäen PriorityQueta
 * - Lopussa lyhyimmän reitin tallettaminen listaan
 */

public class Dijkstra {
    
    PriorityQueue<Solmu> pq;
    
    public List<Solmu> etsiLyhyinReitti(Solmu lahtoSolmu, Solmu tavoiteSolmu) {
        
        lahtoSolmu.setMinimiEtaisyys(0);
        pq = new PriorityQueue<Solmu>();
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
        
        List<Solmu> reitti = new ArrayList<Solmu>();

        for (Solmu solmu = tavoiteSolmu; solmu != null; solmu = solmu.getEdellinenSolmu()) {
            reitti.add(solmu);
        }

        reitti = kaannaReitti(reitti);
        
        return reitti;        
    }
    
    /**
     * Kääntää listarakenteen toisinpäin samaan tapaan kuin Collections.reverse()
     * 
     * @param reitti
     * @return käännetty reitti Listarakenteessa
     */
    
    public List<Solmu> kaannaReitti(List<Solmu> reitti) { 
        List<Solmu> kaannettyReitti = new ArrayList<Solmu>(); 
        
        for (int i = reitti.size()-1; i >= 0; i--) { 
            kaannettyReitti.add(reitti.get(i)); 
        } 
        
        return kaannettyReitti; 
    } 
    
    /**
     * Tulostaa reitin käyttöliittymälle
     * 
     * @param reitti tulostettava reitti
     */
    
    public void tulostaReitti(List<Solmu> reitti) {
        
        for (Solmu solmu : reitti) {
            System.out.println(solmu.getNimi() + " " + solmu.getMinimiEtaisyys() + "m");
        }
        
        System.out.println("");
        System.out.println("Kokonaisreitti yhteensä: " + reitti.get(reitti.size() - 1).getMinimiEtaisyys() + "m");
    }
}