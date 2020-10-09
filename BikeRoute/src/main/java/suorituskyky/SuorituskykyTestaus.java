
package suorituskyky;

import algoritmit.AStar;
import algoritmit.Dijkstra;
import algoritmit.FringeSearch;

import io.VerkonRakentaja;
import komponentit.Solmu;
import komponentit.Verkko;

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
        
        VerkonRakentaja rakentaja = new VerkonRakentaja();
        Verkko verkko1 = rakentaja.luoTestiVerkko();
        
        int i = 0;
        
        while (i <= kierroksia) {
            
            Solmu alku = verkko1.getSolmut().get(0);
            Solmu loppu = verkko1.getSolmut().get(verkko1.getSolmut().size() - 1);    
            
            Dijkstra dijkstra = new Dijkstra();
            
            aloita();
            dijkstra.etsi(alku, loppu);
            lopeta();
            
            reset();
            i++;
        }
        
        Verkko verkko2 = rakentaja.luoTestiVerkko();
        
        int j = 0;
        
        while (j <= kierroksia) {
            
            Solmu alku = verkko2.getSolmut().get(10);
            Solmu loppu = verkko2.getSolmut().get(18);   
            
            Dijkstra dijkstra = new Dijkstra();
            
            aloita();
            dijkstra.etsi(alku, loppu);
            lopeta();
            
            reset();
            j++;
        }
        
        Verkko verkko3 = rakentaja.luoTestiVerkko();
        
        int k = 0;
        
        while (k <= kierroksia) {

            Solmu alku = verkko3.getSolmut().get(3);
            Solmu loppu = verkko3.getSolmut().get(12);    
            
            Dijkstra dijkstra = new Dijkstra();
            
            aloita();
            dijkstra.etsi(alku, loppu);
            lopeta();
            
            reset();
            k++;
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
        
        VerkonRakentaja rakentaja = new VerkonRakentaja();
        Verkko verkko1 = rakentaja.luoTestiVerkko();
        
        int i = 0;
        
        while (i <= kierroksia) {

            Solmu alku = verkko1.getSolmut().get(0);
            Solmu loppu = verkko1.getSolmut().get(verkko1.getSolmut().size() - 1);    
            
            AStar astar = new AStar();
            
            aloita();
            astar.etsi(alku, loppu);
            lopeta();
            
            reset();
            i++;
        }
        
        Verkko verkko2 = rakentaja.luoTestiVerkko();
        
        int j = 0;
        
        while (j <= kierroksia) {

            Solmu alku = verkko2.getSolmut().get(10);
            Solmu loppu = verkko2.getSolmut().get(18);    
            
            AStar astar = new AStar();
            
            aloita();
            astar.etsi(alku, loppu);
            lopeta();
            
            reset();
            j++;
        }   
        
        Verkko verkko3 = rakentaja.luoTestiVerkko();
        
        int k = 0;
        
        while (k <= kierroksia) {

            Solmu alku = verkko3.getSolmut().get(3);
            Solmu loppu = verkko3.getSolmut().get(12);    
            
            AStar astar = new AStar();
            
            aloita();
            astar.etsi(alku, loppu);
            lopeta();
            
            reset();
            k++;
        }          
        
        return kokonaisaika;
    }
    
    /**
     * Fringe search algoritmin testailun metodi
     * @param kierroksia kierrosten lukumäärä
     * @return kokonaisaika double muodossa
     */
    
    public double fringe(int kierroksia) {
        
        this.kokonaisaika = 0;
        
        VerkonRakentaja rakentaja = new VerkonRakentaja();
        Verkko verkko1 = rakentaja.luoTestiVerkko();
        
        int i = 0;
        
        while (i <= kierroksia) {
            
            Solmu alku = verkko1.getSolmut().get(0);
            Solmu loppu = verkko1.getSolmut().get(verkko1.getSolmut().size() - 1);    
            
            FringeSearch fringe = new FringeSearch();
            
            aloita();
            fringe.etsi(alku, loppu);
            lopeta();
            
            reset();
            i++;
        }
        
        Verkko verkko2 = rakentaja.luoTestiVerkko();
        
        int j = 0;
        
        while (j <= kierroksia) {
            
            Solmu alku = verkko2.getSolmut().get(10);
            Solmu loppu = verkko2.getSolmut().get(18);   
            
            FringeSearch fringe = new FringeSearch();
            
            aloita();
            fringe.etsi(alku, loppu);
            lopeta();
            
            reset();
            j++;
        }
        
        Verkko verkko3 = rakentaja.luoTestiVerkko();
        
        int k = 0;
        
        while (k <= kierroksia) {

            Solmu alku = verkko3.getSolmut().get(3);
            Solmu loppu = verkko3.getSolmut().get(12);    
            
            FringeSearch fringe = new FringeSearch();
            
            aloita();
            fringe.etsi(alku, loppu);
            lopeta();
            
            reset();
            k++;
        }             
        
        return kokonaisaika;
    }
}
