
package io;

import components.Kaari;
import components.Solmu;
import components.Verkko;

import crosby.binary.osmosis.OsmosisReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class VerkonRakentaja {
    
    private final String polku;
    private KartanLukija lukija;
    private final ArrayList<Kaari> kaaret;
    private final HashMap<Long, Solmu> solmut;   
    
    /**
     * Konstruktori verkon rakentajalle joka asettaa kartalle polun
     */
    
    public VerkonRakentaja() {
        this.kaaret = new ArrayList<Kaari>();
        this.solmut = new HashMap<Long, Solmu>();
        
        this.polku = "./maps/helsinki.osm.pbf";        
    }
    
    /**
     * Lukee tiedoston polun perusteella ja alustaa Osmosiksen
     */
    
    public void lueTiedosto() {
        InputStream inputStream = null;
        
        try {
            inputStream = new FileInputStream(this.polku);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        
        this.lukija = new KartanLukija();
        
        OsmosisReader osmosisLukija = new OsmosisReader(inputStream);
        osmosisLukija.setSink(lukija);
        osmosisLukija.run();
    }
    
    /**
     * Luo uuden verkon karttadatan perusteella
     * @return luotu verkko Verkko oliona
     */

    public Verkko luoVerkko() {

        ArrayList<KarttaKaari> kaariLista = lukija.getKaaret();
        
        kasitteleKartta(kaariLista);
        
        List<Solmu> solmutVerkolle = new LinkedList<Solmu>(this.solmut.values());
        
        Verkko verkko = new Verkko(solmutVerkolle);
        
        return verkko;
    }
    
    /**
     * Käsittelee luodun kartan kaarilistan perusteella
     * @param karttaKaaret annettu kaarilista
     */
    
    public void kasitteleKartta(ArrayList<KarttaKaari> karttaKaaret) {
        
        for (KarttaKaari karttaKaari: karttaKaaret) {
            
            if (karttaKaari.getSolmut().size() > 1) {
                Solmu alku = valitseSolmu(karttaKaari.noudaEnsimmainen());
                Solmu loppu = valitseSolmu(karttaKaari.noudaViimeinen());
                String nimi = karttaKaari.getTunniste("name");
                String tienTyyppi = karttaKaari.getTunniste("highway");
                
                Kaari kaari = new Kaari(alku, loppu, nimi, tienTyyppi);
                this.kaaret.add(kaari);
            }
        }
        
        for (Kaari kaari: kaaret) {
            Solmu alku = kaari.getAlku();
            Solmu loppu = kaari.getLoppu();
            
            alku.addKaari(kaari);
            loppu.addKaari(kaari);
            
            kaari.laskeEtaisyys();
        }
    }
    
    /**
     * Valitsee tietyn solmun ID:n perusteella solmuista
     * @param karttaSolmu haettava solmu
     * @return palautetaan löydetty solmu
     */
    
    private Solmu valitseSolmu(KarttaSolmu karttaSolmu) {
        
        Solmu solmu = this.solmut.get(karttaSolmu.getID());
        
        if (solmu == null) {
            solmu = new Solmu(karttaSolmu);
            this.solmut.put(karttaSolmu.getID(), solmu);
        }
        
        return solmu;
    }
    
    /**
     * Luo valmiin verkoston testikäyttöön
     * @return palauttaa testikäyttöön tehdyn verkon
     */
    
    public static Verkko luoTestiVerkko() {   

        Solmu rantatie = new Solmu("Rantatie", 10, 20);
        Solmu koulutie = new Solmu("Koulutie", 20, 10);
        Solmu kirkkotie = new Solmu("Kirkkotie", 20, 40);        
        rantatie.addKaari(new Kaari(rantatie, koulutie, 210));
        rantatie.addKaari(new Kaari(rantatie, kirkkotie, 150));
        
        Solmu rinnetie = new Solmu("Rinnetie", 10, 10);
        Solmu teollisuustie = new Solmu("Teollisuustie", 30, 40);
        koulutie.addKaari(new Kaari(koulutie, rinnetie, 50));
        koulutie.addKaari(new Kaari(koulutie, teollisuustie, 120));

        Solmu kuusitie = new Solmu("Kuusitie", 40, 40);        
        kirkkotie.addKaari(new Kaari(kirkkotie, teollisuustie, 65));
        kirkkotie.addKaari(new Kaari(kirkkotie, kuusitie, 110));  
        
        Solmu myllytie = new Solmu("Myllytie", 20, 70);
        Solmu mantytie = new Solmu("Mäntytie", 50, 20);        
        teollisuustie.addKaari(new Kaari(teollisuustie, myllytie, 70));
        teollisuustie.addKaari(new Kaari(teollisuustie, mantytie, 80));
        
        Solmu keskustie = new Solmu("Keskustie", 40, 10);  
        rinnetie.addKaari(new Kaari(rinnetie, myllytie, 300));
        myllytie.addKaari(new Kaari(myllytie, keskustie, 40));
        kuusitie.addKaari(new Kaari(kuusitie, mantytie, 60));  
        mantytie.addKaari(new Kaari(mantytie, keskustie, 90));
        
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
