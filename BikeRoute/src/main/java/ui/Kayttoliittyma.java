
package ui;

import algorithms.AStar;
import algorithms.Dijkstra;
import components.Solmu;
import components.Verkko;
import datastructures.ArrayList;
import io.VerkonRakentaja;
import java.util.Scanner;

import util.ReitinTulostaja;

public class Kayttoliittyma {
    
    /**
     * Luokka käyttöliittymälle
     * @param args 
     */

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Tervetuloa!");
        
        System.out.println("");
        System.out.println("Luodaan polkupyöräverkosto...");
        System.out.println("");
        
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
        String lahtoSolmu = sc.nextLine();
        System.out.println("");
        System.out.println("Minne haluat:");
        String tavoiteSolmu = sc.nextLine();
        System.out.println("");
        
        ReitinTulostaja pathHelper = new ReitinTulostaja();
        
        Verkko verkko = VerkonRakentaja.luoTestiVerkko();
        
        Solmu lahto = verkko.getByName(lahtoSolmu);
        Solmu tavoite = verkko.getByName(tavoiteSolmu);
        
        Dijkstra dijkstra = new Dijkstra();
        AStar astar = new AStar();  

        astar.etsi(lahto, tavoite);
        
        ArrayList<Solmu> aStarReitti = astar.luoReitti(tavoite);
        pathHelper.tulostaAstarReitti(aStarReitti);        

        dijkstra.etsi(lahto, tavoite);
        
        ArrayList<Solmu> dijkstranReitti = dijkstra.luoReitti(tavoite);
        pathHelper.tulostaDijkstraReitti(dijkstranReitti);
        
    }
}
