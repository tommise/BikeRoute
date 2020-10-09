
package ui;

import algoritmit.AStar;
import algoritmit.Dijkstra;
import algoritmit.FringeSearch;
import io.VerkonRakentaja;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

import komponentit.Kaari;
import komponentit.Solmu;
import komponentit.Verkko;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.cache.FileBasedLocalCache;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import suorituskyky.SuorituskykyTestaus;

public class Kayttoliittyma {
    
    /**
     * Luokka käyttöliittymälle sisältäen alkeellisen tekstikäyttöliittymän
     * @param args 
     */

    public static void main(String[] args) {

        System.out.println("Luodaan verkko karttadatan pohjalta... (odota hetki)");     
        
        VerkonRakentaja rakentaja = new VerkonRakentaja();
        Verkko verkko = rakentaja.luoVerkko();
        List<Solmu> solmut = verkko.getSolmut();        
        
        System.out.println("Kaikki valmista!");

        Scanner lukija = new Scanner(System.in);
        
        System.out.println("");
        System.out.println("Tervetuloa!");
        System.out.println("");
        System.out.println("Valitse seuraavista:");
        System.out.println("-------------------------");
        System.out.println("1 Tekstipohjainen käyttöliittymä");
        System.out.println("2 Visuaalinen käyttöliittymä (huom. ks. alla)");
        System.out.println("");
        System.out.println("- Visuaalinen käyttöliittymä vielä kesken, karttanäkymä avautuu");
        System.out.println("- Seuraavaksi implementointiin reitinhaku karttanäkymällä");
        System.out.println("");
        
        String komento = lukija.nextLine();
        
        if (komento.equals("1")) {
            tekstiKayttoliittyma(lukija, solmut);
        } else if (komento.equals("2")) {
            visuaalinenKayttoliittyma();
        } else {
            System.out.println("Väärä komento, yritä uudelleen");
        }
    }   
    
    /**
     * Tekstikäyttöliittymä ohjelmalle
     * @param lukija
     * @param solmut verkon kaikki solmut
     */
    
    public static void tekstiKayttoliittyma(Scanner lukija, List<Solmu> solmut) {
        while (true) {
            System.out.println("");
            System.out.println("-------------------------");
            System.out.println("");
            System.out.println("Tervetuloa!");
            System.out.println("");
            System.out.println("1 Kasittele testiverkko");
            System.out.println("2 Suorituskykytestaus");
            System.out.println("3 Lue kartan tiet");
            System.out.println("");
            System.out.println("x Poistu");
            System.out.println("");
            System.out.println("-------------------------");
            System.out.println("");
            
            String komento = lukija.nextLine();
            System.out.println("");
            
            if (komento.equals("1")) {
                kasitteleTestiVerkko();
            } else if (komento.equals("2")) {
                suoritusKykyTestaus();
            } else if (komento.equals("3")) {
                lueKartanTiet(solmut);
            } else if (komento.equals("x")) {
                System.out.println("Kiitos ja näkemiin!");
                break;
            } else {
                System.out.println("Väärä komento.");
            }
        }
    }
    
    /**
     * Käsittelee testiverkon kolme eri reittiä kaikilla algoritmeillä
     */
    
    public static void kasitteleTestiVerkko() {
        
        VerkonRakentaja verkonRakentaja = new VerkonRakentaja();
        Verkko verkko = verkonRakentaja.luoTestiVerkko();
        
        List<Solmu> solmut = verkko.getSolmut();
        
        System.out.println("Reitti 1:");
        System.out.println("-------");
        
        Solmu alku1 = solmut.get(0);
        Solmu loppu1 = solmut.get(solmut.size() - 1);
        
        tulostaKaikkiReitit(alku1, loppu1);
        
        System.out.println("");
        System.out.println("Reitti 2:");
        System.out.println("-------");

        Solmu alku2 = solmut.get(3);
        Solmu loppu2 = solmut.get(12);
        
        tulostaKaikkiReitit(alku2, loppu2);

        System.out.println("");
        System.out.println("Reitti 3:");
        System.out.println("-------");   
        
        Solmu alku3 = solmut.get(10);
        Solmu loppu3 = solmut.get(18);
        
        tulostaKaikkiReitit(alku3, loppu3);
        
    }
    
    /**
     * Suoritetaan suorituskykytestaus hyödyntäen SuoritusKykyTestaus luokkaa
     */    
    
    public static void suoritusKykyTestaus() {
        SuorituskykyTestaus suoritus = new SuorituskykyTestaus();
        
        int kierroksia = 5000000;
        
        System.out.println("");
        System.out.println("Suorituskykytestaus:");
        System.out.println("Kierroksia: " + kierroksia);
        System.out.println("");
        System.out.println("(odota hetki)");
        System.out.println("");
        
        System.out.println("Dijkstra");
        double dijkstraAika = suoritus.dijkstra(kierroksia);
        System.out.println("Kokonaisaika " + dijkstraAika + " s");
        System.out.println("Keskiarvo " + dijkstraAika / kierroksia);
        System.out.println("");
        
        System.out.println("A*");
        double astarAika = suoritus.astar(kierroksia);
        System.out.println("Kokonaisaika " + astarAika + " s");
        System.out.println("Keskiarvo " + astarAika / kierroksia);
        System.out.println("");
        
        System.out.println("Fringe Search");
        double fringeAika = suoritus.fringe(kierroksia);
        System.out.println("Kokonaisaika " + fringeAika + " s");
        System.out.println("Keskiarvo " + fringeAika / kierroksia);
        System.out.println("");    
        
    }
    
    /**
     * Tulostetaan kaikkien algoritmien reitit
     * @param alku alkusolmu algoritmeille
     * @param loppu alkusolmu algoritmeille
     */
    
    public static void tulostaKaikkiReitit(Solmu alku, Solmu loppu) {
        System.out.println("");
        
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.etsi(alku, loppu);
        
        tietorakenteet.ArrayList<Solmu> dijkstranReitti = dijkstra.luoReitti(loppu);
        tulostaDijkstraReitti(dijkstranReitti); 
        resetoiKaytetytSolmut(dijkstranReitti);           
        
        AStar astar = new AStar();
        astar.etsi(alku, loppu);
        
        tietorakenteet.ArrayList<Solmu> aStarReitti = astar.luoReitti(loppu);
        tulostaAstarReitti(aStarReitti); 
        resetoiKaytetytSolmut(aStarReitti);    
        
        FringeSearch fringe = new FringeSearch();
        fringe.etsi(alku, loppu);
        
        ArrayList<Solmu> fringeReitti = fringe.luoReitti(loppu);        
        tulostaFringeReitti(fringeReitti); 
        resetoiKaytetytSolmutFringe(fringeReitti);       
        
    }
    
    /**
     * Resetoi käytetyt solmut jotta gluvut ja minimietäisyydet eivät jää solmuille edellisestä kierroksesta
     * @param solmut 
     */
    
    public static void resetoiKaytetytSolmut(tietorakenteet.ArrayList<Solmu> solmut) {
        for (int i = 0; i < solmut.size(); i++) {
            Solmu solmu = solmut.get(i);
            solmu.resetSolmu();
        }  
    }
    
    public static void resetoiKaytetytSolmutFringe(ArrayList<Solmu> solmut) {
        for (int i = 0; i < solmut.size(); i++) {
            Solmu solmu = solmut.get(i);
            solmu.resetSolmu();
        }  
    }    
    
    /**
     * Tulostetaan kartan tiet
     * @param solmut annettu solmulista
     */
    
    public static void lueKartanTiet(List<Solmu> solmut) {
        
        for (int i = 0; i < solmut.size(); i++) {
            Solmu solmu = solmut.get(i);
            
            tietorakenteet.ArrayList<Kaari> kaaret = solmu.getKaaret();
            
            for (int j = 0; j < kaaret.size(); j++) {
                
                Kaari kaari = kaaret.get(j);
                
                System.out.println("Käsiteltävän solmun ID: " + solmu.getID());
                System.out.println("Tien nimi: " + kaari.getNimi());
                System.out.println("Tien tyyppi: " + kaari.getTienTyyppi());
                System.out.println("Tien pituus: " + kaari.getEtaisyys() + "m");
                System.out.println("");
            }
        }
    }  
    
    /**
     * Tulostetaan Dijkstran algoritmin tuottama reitti
     * @param reitti reitti solmulistassa
     */
    
    public static void tulostaDijkstraReitti(tietorakenteet.ArrayList<Solmu> reitti) {
        
        System.out.println("Reitti Dijkstra:");
        System.out.println("");
        
        for (int i = 0; i < reitti.size(); i++) {
            Solmu solmu = reitti.get(i);
            System.out.println(solmu.getMinimiEtaisyys() + "m");
        }        
        
        System.out.println("");
        System.out.println("Kokonaisreitti yhteensä: " + reitti.get(reitti.size() - 1).getMinimiEtaisyys() + "m");
        System.out.println(""); 
    }
    
    /**
     * Tulostetaan A* algoritmin tuottama reitti
     * @param reitti reitti solmulistassa
     */
    
    public static void tulostaAstarReitti(tietorakenteet.ArrayList<Solmu> reitti) {
        
        System.out.println("Reitti A*:");
        System.out.println("");      
        
        for (int i = 0; i < reitti.size(); i++) {
            Solmu solmu = reitti.get(i);
            System.out.println(solmu.getG() + "m");
        }
        
        System.out.println("");
        System.out.println("Kokonaisreitti yhteensä: " + reitti.get(reitti.size() - 1).getG() + "m");
        System.out.println("");         
    } 
    
    /**
     * Tulostetaan Fringe algoritmin tuottama reitti
     * @param reitti reitti solmulistassa
     */
    
    public static void tulostaFringeReitti(ArrayList<Solmu> reitti) { 
        
        System.out.println("Reitti Fringe Search:");
        System.out.println(""); 
        
        if (reitti.isEmpty()) {
            System.out.println("Fringe ei nyt toiminut!");
            return;
        }   
        
        for (int i = 0; i < reitti.size(); i++) {
            Solmu solmu = reitti.get(i);
            System.out.println(solmu.getG() + "m");
        }     
        
        System.out.println("");
        System.out.println("Kokonaisreitti yhteensä: " + reitti.get(reitti.size() - 1).getG() + "m");
        System.out.println("");         
    }
    
    /**
     * Visuaalinen käyttöliittymä
     */
    
    public static void visuaalinenKayttoliittyma() { 
        
        TileFactoryInfo osmTile = new OSMTileFactoryInfo();
        DefaultTileFactory tiles = new DefaultTileFactory(osmTile);

        File cache = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
        tiles.setLocalCache(new FileBasedLocalCache(cache, false));

        JXMapViewer kartta = new JXMapViewer();
        kartta.setTileFactory(tiles);     

        GeoPosition lahtoSolmu = new GeoPosition(60.219099, 24.863034);
        GeoPosition maaliSolmu = new GeoPosition(60.214286, 24.859588);
        
        GeoPosition talinSiirtolaPuutarha = new GeoPosition(60.217283, 24.860603);
        
        kartta.setZoom(3);
        kartta.setAddressLocation(talinSiirtolaPuutarha);

        MouseInputListener mouse = new PanMouseInputListener(kartta);
        kartta.addMouseListener(mouse);
        kartta.addMouseMotionListener(mouse);
        kartta.addMouseListener(new CenterMapListener(kartta));
        kartta.addMouseWheelListener(new ZoomMouseWheelListenerCenter(kartta));
        kartta.addKeyListener(new PanKeyListener(kartta));

        Waypoint alku = new Waypoint() {
            @Override
            public GeoPosition getPosition() {
                return lahtoSolmu;
            }
        };
        
        Waypoint loppu = new Waypoint() {
            @Override
            public GeoPosition getPosition() {
                return maaliSolmu;
            }
        };
        
        Set<Waypoint> karttaMerkit = new HashSet<>();
        karttaMerkit.add(alku);
        karttaMerkit.add(loppu);

        WaypointPainter<Waypoint> merkkienAsettaja = new WaypointPainter<Waypoint>();
        merkkienAsettaja.setWaypoints(karttaMerkit);

        kartta.setOverlayPainter(merkkienAsettaja);  

        JFrame ikkuna = new JFrame("Talin siirtolapuutarha -testi");
        ikkuna.getContentPane().add(kartta);
        ikkuna.setSize(1000, 800);
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ikkuna.setVisible(true); 
    }
}
