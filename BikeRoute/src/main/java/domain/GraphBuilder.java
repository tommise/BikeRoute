
package domain;

import components.Kaari;
import components.Verkko;
import components.Solmu;

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
        
        Solmu rantatie = new Solmu("Rantatie", 10, 20);
        Solmu koulutie = new Solmu("Koulutie", 20, 10);
        Solmu rinnetie = new Solmu("Rinnetie", 10, 10);
        Solmu kirkkotie = new Solmu("Kirkkotie", 20, 40);
        Solmu teollisuustie = new Solmu("Teollisuustie", 30, 40);
        Solmu myllytie = new Solmu("Myllytie", 20, 70);
        Solmu kuusitie = new Solmu("Kuusitie", 40, 40);
        Solmu mäntytie = new Solmu("Mäntytie", 50, 20);
        Solmu keskustie = new Solmu("Keskustie", 40, 10);      

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
