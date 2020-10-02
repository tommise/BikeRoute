
package ui;

import algorithms.AStar;
import algorithms.Dijkstra;
import components.Kaari;
import components.Solmu;
import components.Verkko;
import io.VerkonRakentaja;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import util.SuorituskykyTestaus;

public class Kayttoliittyma {
    
    /**
     * Luokka käyttöliittymälle sisältäen alkeellisen tekstikäyttöliittymän
     * @param args 
     */

    public static void main(String[] args) {
        
        Scanner lukija = new Scanner(System.in);
        System.out.println("Luodaan polkupyöräverkosto... (odota hetki)");        
        
        VerkonRakentaja verkonRakentaja = new VerkonRakentaja();
        verkonRakentaja.lueTiedosto();
        
        Verkko verkko = verkonRakentaja.luoVerkko();
        
        System.out.println("Kaikki valmista!");
        
        List<Solmu> solmut = verkko.getSolmut();
        
        while (true) {
            System.out.println("");
            System.out.println("-------------------------");
            System.out.println("");
            System.out.println("Tervetuloa!");
            System.out.println("");
            System.out.println("1 Luo testiverkko");
            System.out.println("2 Suorituskykytestaus");
            System.out.println("3 Lue kartan tiet");
            System.out.println("4 Testaa kartan verkkoa");
            System.out.println("x Poistu");
            System.out.println("");
            System.out.println("-------------------------");
            System.out.println("");
            
            String komento = lukija.nextLine();
            System.out.println("");
            
            if (komento.equals("1")) {
                luoTestiVerkko(lukija);
            } else if (komento.equals("2")) {
                suoritusKykyTestaus();
            } else if(komento.equals("3")) {
                lueKartanTiet(solmut);
            } else if (komento.equals("4")) {
                testaaKartanVerkkoa(verkko);
            } else if (komento.equals("x")) {
                System.out.println("Kiitos ja näkemiin!");
                break;
            } else {
                System.out.println("Väärä komento.");
            }
        }
        
    }
    
    /**
     * Luodaan testiverkko tulostettavaksi
     * @param lukija käyttäjän syöte
     */
    
    public static void luoTestiVerkko(Scanner lukija) {
        System.out.println("Rantatie");
        System.out.println("Koulutie");
        System.out.println("Rinnetie");
        System.out.println("Kirkkotie");
        System.out.println("Teollisuustie");
        System.out.println("Myllytie");
        System.out.println("Kuusitie");
        System.out.println("Mäntytie");
        System.out.println("Keskustie");
        
        System.out.println("");        
        System.out.println("Mistä lähdet:");
        String lahtoSolmu = lukija.nextLine();
        System.out.println("");
        System.out.println("Minne haluat:");
        String tavoiteSolmu = lukija.nextLine();
        System.out.println("");

        Verkko verkko = VerkonRakentaja.luoTestiVerkko();
        
        Solmu alku = verkko.getSolmuByNimi(lahtoSolmu);
        Solmu loppu = verkko.getSolmuByNimi(tavoiteSolmu);
        
        tulostaKaikkiReitit(alku, loppu);
    }
    
    /**
     * Suoritetaan suorituskykytestaus hyödyntäen SuoritusKykyTestaus luokkaa
     */    
    
    public static void suoritusKykyTestaus() {
        SuorituskykyTestaus suoritus = new SuorituskykyTestaus();
        
        int kierroksia = 50000;
        
        System.out.println("");
        System.out.println("Suorituskykytestaus:");
        System.out.println("Kierroksia: " + kierroksia);
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
    }
    
    /**
     * Tulostetaan kaikkien algoritmien reitit
     * @param alku alkusolmu algoritmeille
     * @param loppu alkusolmu algoritmeille
     */
    
    public static void tulostaKaikkiReitit(Solmu alku, Solmu loppu) {
        System.out.println("");
        
        Dijkstra dijkstra = new Dijkstra();
        AStar astar = new AStar();  
        
        dijkstra.etsi(alku, loppu);
        
        ArrayList<Solmu> dijkstranReitti = dijkstra.luoReitti(loppu);
        tulostaDijkstraReitti(dijkstranReitti); 
        
        astar.etsi(alku, loppu);
        
        ArrayList<Solmu> aStarReitti = astar.luoReitti(loppu);
        tulostaAstarReitti(aStarReitti);        
    }
    
    /**
     * Tulostetaan kartan tiet
     * @param solmut annettu solmulista
     */
    
    public static void lueKartanTiet(List<Solmu> solmut) {
        for (Solmu solmu : solmut) {
            List<Kaari> kaaret = solmu.getKaaret();

            for (Kaari kaari : kaaret) {
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
    
    public static void tulostaDijkstraReitti(ArrayList<Solmu> reitti) {
        
        System.out.println("Reitti Dijkstralla:");
        System.out.println("");
        
        for (int i = 0; i < reitti.size(); i++) {
            Solmu solmu = reitti.get(i);
            System.out.println(solmu.getNimi() + " " + solmu.getMinimiEtaisyys() + "m");
        }        
        
        System.out.println("");
        System.out.println("Kokonaisreitti yhteensä: " + reitti.get(reitti.size() - 1).getMinimiEtaisyys() + "m");
        System.out.println(""); 
    }
    
    /**
     * Tulostetaan A* algoritmin tuottama reitti
     * @param reitti reitti solmulistassa
     */
    
    public static void tulostaAstarReitti(ArrayList<Solmu> reitti) {
        
        System.out.println("Reitti AStarilla:");
        System.out.println("");      
        
        for (int i = 0; i < reitti.size(); i++) {
            Solmu solmu = reitti.get(i);
            System.out.println(solmu.getNimi() + " " + solmu.getG() + "m");
        }
        
        System.out.println("");
        System.out.println("Kokonaisreitti yhteensä: " + reitti.get(reitti.size() - 1).getG() + "m");
        System.out.println("");         
    }    
    
    /**
     * Tulostetaan JPS algoritmin tuottama reitti
     * @param reitti reitti solmulistassa
     */
    
    public static void tulostaJPSReitti(ArrayList<Solmu> reitti) {
        
    }
    
    /**
     * Testataan verkon toimivuutta
     * @param verkko käytössä oleva verkko
     */
    
    public static void testaaKartanVerkkoa(Verkko verkko) {
        
        Solmu alku = verkko.getSolmuByID("25584498");
        Solmu loppu = verkko.getSolmuByID("1363676294");
        
        String alkuNimi = alku.getKaaret().get(0).getNimi();
        String loppuNimi = alku.getKaaret().get(1).getNimi();        
        
        System.out.println(alkuNimi + " - " + loppuNimi);
        
        tulostaKaikkiReitit(alku, loppu);       
    }
}
