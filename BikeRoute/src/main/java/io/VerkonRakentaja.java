
package io;

import components.Kaari;
import components.Solmu;
import components.Verkko;

import crosby.binary.osmosis.OsmosisReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

public class VerkonRakentaja {
    
    String polku;
    VerkonKasittelija verkko;
    
    /**
     * Konstruktori verkon rakentajalle joka asettaa kartalle polun
     */
    
    public VerkonRakentaja() {
        this.polku = "./maps/helsinki.osm.pbf";
    }
    
    /**
     * Alustaa verkon käyttöön ohjelmalle
     */
    
    public void luoVerkko() {
        
        InputStream inputStream = null;
        
        try {
            inputStream = new FileInputStream(polku);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        
        KartanKasittelija lukija = new KartanKasittelija();
        
        OsmosisReader osmosisLukija = new OsmosisReader(inputStream);
        osmosisLukija.setSink(lukija);
        osmosisLukija.run();
        
        this.verkko = new VerkonKasittelija();
        
        ArrayList<KarttaTie> tiet = lukija.getTiet();
        Map<Long, KarttaObjekti> objektit = lukija.getObjektit();
        
        this.verkko.kasitteleTiet(tiet, objektit);
    }
    
    /**
     * Palauttaa luodun verkon
     * @return palauttaa verkon
     */
    
    public VerkonKasittelija getVerkko() {
        return this.verkko;
    }
    
    /**
     * Luo valmiin verkoston testikäyttöön
     * @return palauttaa testikäyttöön tehdyn verkon
     */
    
    public static Verkko luoTestiVerkko() {   

        Solmu rantatie = new Solmu("Rantatie", 10, 20);
        Solmu koulutie = new Solmu("Koulutie", 20, 10);
        Solmu kirkkotie = new Solmu("Kirkkotie", 20, 40);        
        rantatie.addNaapuri(new Kaari(rantatie, koulutie, 210));
        rantatie.addNaapuri(new Kaari(rantatie, kirkkotie, 150));
        
        Solmu rinnetie = new Solmu("Rinnetie", 10, 10);
        Solmu teollisuustie = new Solmu("Teollisuustie", 30, 40);
        koulutie.addNaapuri(new Kaari(koulutie, rinnetie, 50));
        koulutie.addNaapuri(new Kaari(koulutie, teollisuustie, 120));

        Solmu kuusitie = new Solmu("Kuusitie", 40, 40);        
        kirkkotie.addNaapuri(new Kaari(kirkkotie, teollisuustie, 65));
        kirkkotie.addNaapuri(new Kaari(kirkkotie, kuusitie, 110));  
        
        Solmu myllytie = new Solmu("Myllytie", 20, 70);
        Solmu mantytie = new Solmu("Mäntytie", 50, 20);        
        teollisuustie.addNaapuri(new Kaari(teollisuustie, myllytie, 70));
        teollisuustie.addNaapuri(new Kaari(teollisuustie, mantytie, 80));
        
        Solmu keskustie = new Solmu("Keskustie", 40, 10);  
        rinnetie.addNaapuri(new Kaari(rinnetie, myllytie, 300));
        myllytie.addNaapuri(new Kaari(myllytie, keskustie, 40));
        kuusitie.addNaapuri(new Kaari(kuusitie, mantytie, 60));  
        mantytie.addNaapuri(new Kaari(mantytie, keskustie, 90));
        
        Verkko verkko = new Verkko(); 
        
        verkko.addSolmu(rantatie);
        verkko.addSolmu(koulutie);
        verkko.addSolmu(rinnetie);    
        verkko.addSolmu(kirkkotie); 
        verkko.addSolmu(teollisuustie); 
        verkko.addSolmu(myllytie); 
        verkko.addSolmu(kuusitie); 
        verkko.addSolmu(mantytie); 
        verkko.addSolmu(keskustie);         
        
        return verkko;
    }
}
