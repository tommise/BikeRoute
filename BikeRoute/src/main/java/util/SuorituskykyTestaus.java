
package util;

import algorithms.AStar;
import algorithms.Dijkstra;
import algorithms.JPS;
import components.Solmu;
import components.Verkko;
import io.VerkonRakentaja;

public class SuorituskykyTestaus {
    
    private long alkuAika = 0;
    private long loppuAika = 0;
    private double kokonaisaika = 0;
    
    /**
     * Aloittaa ajanoton
     */
    
    public void aloita() {
        this.alkuAika = System.nanoTime();
    }
    
    /**
     * Lopettaa ajanoton
     */
    
    public void lopeta() {
        this.loppuAika = System.nanoTime();
        
        double tulos = ((loppuAika - alkuAika) / 1e9);
        
        this.kokonaisaika += tulos;
        
        reset();
    }
    
    /**
     * Nollaa alkuajan sekä loppuajan
     */
    
    public void reset() {
        this.alkuAika = 0;
        this.loppuAika = 0;
    }
    
    /**
     * Dijkstran algoritmin testailun metodi
     * @param kierroksia kierrosten lukumäärä
     * @return kokonaisaika double muodossa
     */
    
    public double dijkstra(int kierroksia) {
        
        this.kokonaisaika = 0;
        
        int i = 0;
        
        while (i <= kierroksia) {
            Verkko verkko = VerkonRakentaja.luoTestiVerkko();

            Solmu alku = verkko.getSolmut().get(0);
            Solmu loppu = verkko.getSolmut().get(verkko.getSolmut().size() - 1);    
            
            Dijkstra dijkstra = new Dijkstra();
            
            aloita();
            dijkstra.etsi(alku, loppu);
            lopeta();
            
            reset();
            i++;
        }
        
        return kokonaisaika;
    }
    
    /**
     * AStar algoritmin testailun metodi
     * @param kierroksia kierrosten lukumäärä
     * @return kokonaisaika double muodossa
     */
    
    public double astar(int kierroksia) {
        
        this.kokonaisaika = 0;
        
        int i = 0;
        
        while (i <= kierroksia) {
            Verkko verkko = VerkonRakentaja.luoTestiVerkko();

            Solmu alku = verkko.getSolmut().get(0);
            Solmu loppu = verkko.getSolmut().get(verkko.getSolmut().size() - 1);    
            
            AStar astar = new AStar();
            
            aloita();
            astar.etsi(alku, loppu);
            lopeta();
            
            reset();
            i++;
        }
        
        return kokonaisaika;
    }
    
    /**
     * JPS algoritmin testailun metodi
     * @param kierroksia kierrosten lukumäärä
     * @return kokonaisaika double muodossa
     */
    
    public double jps(int kierroksia) {
        
        this.kokonaisaika = 0;
        
        int i = 0;
        
        while (i <= kierroksia) {
            Verkko verkko = VerkonRakentaja.luoTestiVerkko();

            Solmu alku = verkko.getSolmut().get(0);
            Solmu loppu = verkko.getSolmut().get(verkko.getSolmut().size() - 1);    
            
            JPS jps = new JPS();
            
            aloita();
            jps.etsi(alku, loppu);
            lopeta();
            
            reset();
            i++;
        }
        
        return kokonaisaika;
    }
}
