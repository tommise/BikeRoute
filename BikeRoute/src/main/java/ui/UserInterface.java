
package ui;

import components.Verkko;
import components.Solmu;
import domain.GraphBuilder;
import algorithms.Dijkstra;
import java.util.*;

public class UserInterface {

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
        
        Verkko verkko = GraphBuilder.luoPolkupyoraVerkosto();
        
        Solmu lahto = verkko.getByName(lahtoSolmu);
        Solmu tavoite = verkko.getByName(tavoiteSolmu);

        Dijkstra dijkstra = new Dijkstra();
        List<Solmu> reitti = dijkstra.etsiLyhyinReitti(lahto, tavoite);

        System.out.println("Lyhyin reitti Dijkstralla kohteesta " + lahto.getNimi() + " kohteeseen " + tavoite.getNimi() + ":");
        System.out.println("");
        
        dijkstra.tulostaReitti(reitti);
    }
}
