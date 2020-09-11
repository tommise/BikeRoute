
package domain;

import components.Kaari;
import components.Verkko;
import components.Solmu;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Luokka graphBuilder
 * 
 * Luo verkon annetun karttadatan perusteella
 */

public class GraphBuilder {
    
    /**
     * Luo valmiin verkoston testikäyttöön
     * @return palauttaa testikäyttöön tehdyn verkon
     */
    
    public static Verkko luoPolkupyoraVerkosto() {
        Verkko verkko = new Verkko();
        
        Solmu rantatie = new Solmu("Rantatie");
        Solmu koulutie = new Solmu("Koulutie");
        Solmu rinnetie = new Solmu("Rinnetie");
        Solmu kirkkotie = new Solmu("Kirkkotie");
        Solmu teollisuustie = new Solmu("Teollisuustie");
        Solmu myllytie = new Solmu("Myllytie");
        Solmu kuusitie = new Solmu("Kuusitie");
        Solmu mäntytie = new Solmu("Mäntytie");
        Solmu keskustie = new Solmu("Keskustie");      

        rantatie.addNaapuri(new Kaari(rantatie, koulutie, 210));
        rantatie.addNaapuri(new Kaari(rantatie, kirkkotie, 150));
        
        koulutie.addNaapuri(new Kaari(koulutie, rinnetie, 50));
        koulutie.addNaapuri(new Kaari(koulutie, teollisuustie, 120));
        
        kirkkotie.addNaapuri(new Kaari(kirkkotie, teollisuustie, 65));
        kirkkotie.addNaapuri(new Kaari(kirkkotie, kuusitie, 110));  
        
        teollisuustie.addNaapuri(new Kaari(teollisuustie, myllytie, 70));
        teollisuustie.addNaapuri(new Kaari(teollisuustie, mäntytie, 80));
        
        rinnetie.addNaapuri(new Kaari(rinnetie, myllytie, 300));
        
        myllytie.addNaapuri(new Kaari(myllytie, keskustie, 40));
        
        kuusitie.addNaapuri(new Kaari(kuusitie, mäntytie, 60));  
        
        mäntytie.addNaapuri(new Kaari(mäntytie, keskustie, 90));
        
        verkko.addSolmu(rantatie);
        verkko.addSolmu(koulutie);
        verkko.addSolmu(rinnetie);    
        verkko.addSolmu(kirkkotie); 
        verkko.addSolmu(teollisuustie); 
        verkko.addSolmu(myllytie); 
        verkko.addSolmu(kuusitie); 
        verkko.addSolmu(mäntytie); 
        verkko.addSolmu(keskustie);         
        
        return verkko;
    }
}
