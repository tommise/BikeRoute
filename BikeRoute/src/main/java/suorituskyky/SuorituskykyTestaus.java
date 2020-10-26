
package suorituskyky;

import algoritmit.AStar;
import algoritmit.Dijkstra;
import algoritmit.IDAStar;
import io.VerkonLukija;

import komponentit.Solmu;
import komponentit.Verkko;

/**
 * Luokka suorituskyvyn testaamiseen eri algoritmien välillä
 */

public class SuorituskykyTestaus {
    
    private long alkuAika = 0;
    private long loppuAika = 0;
    private double kokonaisaika = 0;
    
    private boolean dijkstraValittu = false;
    private boolean astarValittu = false;
    private boolean idaStarValittu = false;
    
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
    }
    
    /**
     * Nollaa alkuajan sekä loppuajan
     */
    
    public void reset() {
        this.alkuAika = 0;
        this.loppuAika = 0;
    } 
    
    /**
     * Laskee kuinka kauan käytetyllä algoritmilla menee aikaa etsiä
     * lyhin reitti reitilla 1
     * @param algoritmi haluttu algoritmi
     * @param kierroksia kierrosten määrä
     * @return käytetty aika double muodossa
     */
    
    public double reitti1(String algoritmi, int kierroksia) {
        this.kokonaisaika = 0;
        
        VerkonLukija reader = new VerkonLukija();
        Verkko verkko = reader.luoVerkkoDavisista();
        
        int alkuSolmunIndeksi = 2;
        int loppuSolmunIndeksi = 89;
        
        paivitaKaytettyAlgoritmi(algoritmi);  
        
        int i = 0;
        
        while (i <= kierroksia) {
            
            Solmu alku = verkko.getSolmut().get(alkuSolmunIndeksi);
            Solmu loppu = verkko.getSolmut().get(loppuSolmunIndeksi);  
            
            if (astarValittu) {
                AStar astar = new AStar();

                aloita();
                astar.etsi(alku, loppu);
                lopeta();
                reset();
                
            } else if (dijkstraValittu) {
                Dijkstra dijkstra = new Dijkstra();

                aloita();
                dijkstra.etsi(alku, loppu);
                lopeta();
                reset();
                
            } else if (idaStarValittu) {
                IDAStar ida = new IDAStar();

                aloita();
                ida.etsi(alku, loppu);
                lopeta();
                reset();
            }

            i++;
        }      
        
        resetAlgoritmit();
        
        return this.kokonaisaika;
    }
    
    /**
     * Laskee kuinka kauan käytetyllä algoritmilla menee aikaa etsiä
     * lyhin reitti reitilla 2
     * @param algoritmi haluttu algoritmi
     * @param kierroksia kierrosten määrä
     * @return käytetty aika double muodossa
     */
    
    public double reitti2(String algoritmi, int kierroksia) {
        this.kokonaisaika = 0;
        
        VerkonLukija reader = new VerkonLukija();
        Verkko verkko = reader.luoVerkkoDavisista();
        
        int alkuSolmunIndeksi = 151;
        int loppuSolmunIndeksi = 42;
        
        paivitaKaytettyAlgoritmi(algoritmi);  
        
        int i = 0;
        
        while (i <= kierroksia) {
            
            Solmu alku = verkko.getSolmut().get(alkuSolmunIndeksi);
            Solmu loppu = verkko.getSolmut().get(loppuSolmunIndeksi);  
            
            if (astarValittu) {
                AStar astar = new AStar();

                aloita();
                astar.etsi(alku, loppu);
                lopeta();
                reset();
            } else if (dijkstraValittu) {
                Dijkstra dijkstra = new Dijkstra();

                aloita();
                dijkstra.etsi(alku, loppu);
                lopeta();
                reset();
                
            } else if (idaStarValittu) {
                IDAStar ida = new IDAStar();

                aloita();
                ida.etsi(alku, loppu);
                lopeta();
                reset();
            }
            i++;
        }      
        
        resetAlgoritmit();
        
        return this.kokonaisaika;
    }
    
    /**
     * Laskee kuinka kauan käytetyllä algoritmilla menee aikaa etsiä
     * lyhin reitti reitilla 3
     * @param algoritmi haluttu algoritmi
     * @param kierroksia kierrosten määrä
     * @return käytetty aika double muodossa
     */
    
    public double reitti3(String algoritmi, int kierroksia) {
        this.kokonaisaika = 0;
        
        VerkonLukija reader = new VerkonLukija();
        Verkko verkko = reader.luoVerkkoDavisista();
        
        int alkuSolmunIndeksi = 88;
        int loppuSolmunIndeksi = 184;
        
        paivitaKaytettyAlgoritmi(algoritmi);  
        
        int i = 0;
        
        while (i <= kierroksia) {
            
            Solmu alku = verkko.getSolmut().get(alkuSolmunIndeksi);
            Solmu loppu = verkko.getSolmut().get(loppuSolmunIndeksi);  
            
            if (astarValittu) {
                AStar astar = new AStar();

                aloita();
                astar.etsi(alku, loppu);
                lopeta();
                reset();
                
            } else if (dijkstraValittu) {
                Dijkstra dijkstra = new Dijkstra();

                aloita();
                dijkstra.etsi(alku, loppu);
                lopeta();
                reset();
                
            } else if (idaStarValittu) {
                IDAStar ida = new IDAStar();

                aloita();
                ida.etsi(alku, loppu);
                lopeta();
                reset();
            }
            
            i++;
        }      
        
        resetAlgoritmit();
        
        return this.kokonaisaika;
    }   
    
    /**
     * Laskee kuinka kauan käytetyllä algoritmilla menee aikaa etsiä
     * lyhin reitti reitilla 4
     * @param algoritmi haluttu algoritmi
     * @param kierroksia kierrosten määrä
     * @return käytetty aika double muodossa
     */
    
    public double reitti4(String algoritmi, int kierroksia) {
        this.kokonaisaika = 0;
        
        VerkonLukija reader = new VerkonLukija();
        Verkko verkko = reader.luoVerkkoDavisista();
        
        int alkuSolmunIndeksi = 17;
        int loppuSolmunIndeksi = 37;
        
        paivitaKaytettyAlgoritmi(algoritmi);  
        
        int i = 0;
        
        while (i <= kierroksia) {
            
            Solmu alku = verkko.getSolmut().get(alkuSolmunIndeksi);
            Solmu loppu = verkko.getSolmut().get(loppuSolmunIndeksi);  
            
            if (astarValittu) {
                AStar astar = new AStar();

                aloita();
                astar.etsi(alku, loppu);
                lopeta();
                reset();
                
            } else if (dijkstraValittu) {
                Dijkstra dijkstra = new Dijkstra();

                aloita();
                dijkstra.etsi(alku, loppu);
                lopeta();
                reset();
                
            } else if (idaStarValittu) {
                IDAStar ida = new IDAStar();

                aloita();
                ida.etsi(alku, loppu);
                lopeta();
                reset();
            }

            i++;
        }      
        
        resetAlgoritmit();
        
        return this.kokonaisaika;
    } 
    
    /**
     * Laskee kuinka kauan käytetyllä algoritmilla menee aikaa etsiä
     * lyhin reitti reitilla 5
     * @param algoritmi haluttu algoritmi
     * @param kierroksia kierrosten määrä
     * @return käytetty aika double muodossa
     */
    
    public double reitti5(String algoritmi, int kierroksia) {
        this.kokonaisaika = 0;
        
        VerkonLukija reader = new VerkonLukija();
        Verkko verkko = reader.luoVerkkoTalista();
        
        int alkuSolmunIndeksi = 0;
        int loppuSolmunIndeksi = 23;
        
        paivitaKaytettyAlgoritmi(algoritmi);  
        
        int i = 0;
        
        while (i <= kierroksia) {
            
            Solmu alku = verkko.getSolmut().get(alkuSolmunIndeksi);
            Solmu loppu = verkko.getSolmut().get(loppuSolmunIndeksi);  
            
            if (astarValittu) {
                AStar astar = new AStar();

                aloita();
                astar.etsi(alku, loppu);
                lopeta();
                reset();
                
            } else if (dijkstraValittu) {
                Dijkstra dijkstra = new Dijkstra();

                aloita();
                dijkstra.etsi(alku, loppu);
                lopeta();
                reset();
                
            } else if (idaStarValittu) {
                IDAStar ida = new IDAStar();

                aloita();
                ida.etsi(alku, loppu);
                lopeta();
                reset();
            }

            i++;
        }      
        
        resetAlgoritmit();
        
        return this.kokonaisaika;
    }    
    
    /**
     * Päivittää käyttöön halutun algoritmin
     * @param algoritmi parametrina annettu haluttu algoritmi
     */
    
    public void paivitaKaytettyAlgoritmi(String algoritmi) {
        if (algoritmi.equals("Dijkstra")) {
            this.dijkstraValittu = true;
        } else if (algoritmi.equals("IDA Star")) {
            this.idaStarValittu = true;
        } else if (algoritmi.equals("A Star")) {
            this.astarValittu = true;
        }
    }
    
    /**
     * Resetoi käytetyn algoritmin
     */
    
    public void resetAlgoritmit() {
        this.astarValittu = false;
        this.dijkstraValittu = false;
        this.idaStarValittu = false;
    }
}
