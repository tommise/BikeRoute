
package io;

import komponentit.Kaari;
import komponentit.Solmu;
import komponentit.Verkko;

public class VerkonRakentaja {
    
    private KartanLukija kartanLukija;
    
    /**
     * Luo verkon ohjelmalle karttadatan perusteella
     * @return luotu verkko olio
     */
    
    public Verkko luoVerkko() {
        kartanLukija = new KartanLukija();
        kartanLukija.lueTiedosto();
        kartanLukija.kasitteleVerkko();
        
        Verkko verkko = kartanLukija.getVerkko();
        
        return verkko;
    }
    
    /**
     * Luo testiverkon ohjelmalle Talin siirtolapuutarhan mukaisesti
     * @return luotu verkko olio
     */

    public Verkko luoTestiVerkko() {  

        Verkko verkko = new Verkko();
        
        Solmu solmu1 = new Solmu(1, 60.219675, 24.861553);
        Solmu solmu2 = new Solmu(2, 60.219099, 24.863034);
        Solmu solmu3 = new Solmu(3, 60.218539, 24.864386);
        Solmu solmu4 = new Solmu(4, 60.219276, 24.860955);
        Solmu solmu5 = new Solmu(5, 60.218999, 24.861717);
        Solmu solmu6 = new Solmu(6, 60.218722, 24.862522);
        Solmu solmu7 = new Solmu(7, 60.218440, 24.863219);
        Solmu solmu8 = new Solmu(8, 60.218179, 24.863991);
        Solmu solmu9 = new Solmu(9, 60.217929, 24.859206);
        Solmu solmu10 = new Solmu(10, 60.217700, 24.859828);
        Solmu solmu11 = new Solmu(11, 60.217402, 24.860590);
        Solmu solmu12 = new Solmu(12, 60.217157, 24.861341);
        Solmu solmu13 = new Solmu(13, 60.216877, 24.862087);
        Solmu solmu14 = new Solmu(14, 60.216605, 24.862827);
        Solmu solmu15 = new Solmu(15, 60.216461, 24.857334);
        Solmu solmu16 = new Solmu(16, 60.216274, 24.857774);
        Solmu solmu17 = new Solmu(17, 60.216008, 24.858493);
        Solmu solmu18 = new Solmu(18, 60.215742, 24.859287);
        Solmu solmu19 = new Solmu(19, 60.215465, 24.860006);
        Solmu solmu20 = new Solmu(20, 60.215342, 24.860339);
        Solmu solmu21 = new Solmu(21, 60.215203, 24.860811);
        Solmu solmu22 = new Solmu(22, 60.214654, 24.858944);
        Solmu solmu23 = new Solmu(23, 60.214286, 24.859588);

        Kaari kaari1 = new Kaari(solmu1, solmu2, "Metsäpolku", "hiekka");
        Kaari kaari2 = new Kaari(solmu2, solmu3, "Peltotie", "hiekka");
        Kaari kaari3 = new Kaari(solmu1, solmu4, "Metsäpolku", "hiekka");
        Kaari kaari4 = new Kaari(solmu2, solmu6, "Valtatie", "hiekka");
        Kaari kaari5 = new Kaari(solmu3, solmu8, "Peltotie", "hiekka");
        Kaari kaari6 = new Kaari(solmu4, solmu5, "Lähdetie", "hiekka");
        Kaari kaari7 = new Kaari(solmu5, solmu6, "Lähdetie", "hiekka");
        Kaari kaari8 = new Kaari(solmu6, solmu7, "Lähdetie", "hiekka");
        Kaari kaari9 = new Kaari(solmu7, solmu8, "Lähdetie", "hiekka");
        Kaari kaari10 = new Kaari(solmu4, solmu9, "Metsäpolku", "hiekka");
        Kaari kaari11 = new Kaari(solmu5, solmu10, "Kotipolku", "hiekka");
        Kaari kaari12 = new Kaari(solmu6, solmu11, "Valtatie", "hiekka");
        Kaari kaari13 = new Kaari(solmu7, solmu12, "Niittypolku", "hiekka");
        Kaari kaari14 = new Kaari(solmu8, solmu13, "Peltotie", "hiekka");
        Kaari kaari15 = new Kaari(solmu9, solmu10, "Rantatie", "hiekka");
        Kaari kaari16 = new Kaari(solmu10, solmu11, "Rantatie", "hiekka");
        Kaari kaari17 = new Kaari(solmu11, solmu12, "Rantatie", "hiekka");
        Kaari kaari18 = new Kaari(solmu12, solmu13, "Rantatie", "hiekka");
        Kaari kaari19 = new Kaari(solmu13, solmu14, "Rantatie", "hiekka");
        Kaari kaari20 = new Kaari(solmu9, solmu15, "Metsäpolku", "hiekka");
        Kaari kaari21 = new Kaari(solmu10, solmu16, "Kotipolku", "hiekka");
        Kaari kaari22 = new Kaari(solmu11, solmu17, "Valtatie", "hiekka");
        Kaari kaari23 = new Kaari(solmu12, solmu18, "Niittypolku", "hiekka");
        Kaari kaari24 = new Kaari(solmu13, solmu19, "Peltotie", "hiekka");
        Kaari kaari25 = new Kaari(solmu14, solmu21, "Rantatie", "hiekka");
        Kaari kaari26 = new Kaari(solmu15, solmu16, "Kaivotie", "hiekka");
        Kaari kaari27 = new Kaari(solmu16, solmu17, "Kaivotie", "hiekka");
        Kaari kaari28 = new Kaari(solmu17, solmu18, "Kaivotie", "hiekka");
        Kaari kaari29 = new Kaari(solmu18, solmu19, "Kaivotie", "hiekka");
        Kaari kaari30 = new Kaari(solmu19, solmu20, "Kaivotie", "hiekka");
        Kaari kaari31 = new Kaari(solmu20, solmu21, "Kaivotie", "hiekka");
        Kaari kaari32 = new Kaari(solmu20, solmu22, "Onnentie", "hiekka");
        Kaari kaari33 = new Kaari(solmu21, solmu23, "Kaivotie", "hiekka");

        solmu1.addKaari(kaari1);
        solmu1.addKaari(kaari3);
        
        solmu2.addKaari(kaari1);
        solmu2.addKaari(kaari2);
        solmu2.addKaari(kaari4);
        
        solmu3.addKaari(kaari2);
        solmu3.addKaari(kaari5);
        
        solmu4.addKaari(kaari3);
        solmu4.addKaari(kaari6);
        solmu4.addKaari(kaari10);

        solmu5.addKaari(kaari6);
        solmu5.addKaari(kaari7);
        solmu5.addKaari(kaari11);

        solmu6.addKaari(kaari4);
        solmu6.addKaari(kaari7);
        solmu6.addKaari(kaari8);
        solmu6.addKaari(kaari12);

        solmu7.addKaari(kaari8);
        solmu7.addKaari(kaari9);
        solmu7.addKaari(kaari13);

        solmu8.addKaari(kaari5);
        solmu8.addKaari(kaari9);
        solmu8.addKaari(kaari14);

        solmu9.addKaari(kaari10);
        solmu9.addKaari(kaari15);
        solmu9.addKaari(kaari20);

        solmu10.addKaari(kaari11);
        solmu10.addKaari(kaari15);
        solmu10.addKaari(kaari16);
        solmu10.addKaari(kaari21);

        solmu11.addKaari(kaari12);
        solmu11.addKaari(kaari16);
        solmu11.addKaari(kaari17);
        solmu11.addKaari(kaari22);

        solmu12.addKaari(kaari13);
        solmu12.addKaari(kaari17);
        solmu12.addKaari(kaari18);
        solmu12.addKaari(kaari23);

        solmu13.addKaari(kaari14);
        solmu13.addKaari(kaari18);
        solmu13.addKaari(kaari19);
        solmu13.addKaari(kaari24);

        solmu14.addKaari(kaari19);
        solmu14.addKaari(kaari25);

        solmu15.addKaari(kaari20);
        solmu15.addKaari(kaari26);

        solmu16.addKaari(kaari21);
        solmu16.addKaari(kaari26);
        solmu16.addKaari(kaari27);

        solmu17.addKaari(kaari22);
        solmu17.addKaari(kaari27);
        solmu17.addKaari(kaari28);

        solmu18.addKaari(kaari23);
        solmu18.addKaari(kaari28);
        solmu18.addKaari(kaari29);

        solmu19.addKaari(kaari24);
        solmu19.addKaari(kaari29);
        solmu19.addKaari(kaari30);

        solmu20.addKaari(kaari30);
        solmu20.addKaari(kaari31);
        solmu20.addKaari(kaari32);

        solmu21.addKaari(kaari25);
        solmu21.addKaari(kaari31);
        solmu21.addKaari(kaari33);

        solmu22.addKaari(kaari32);

        solmu23.addKaari(kaari33);

        verkko.addSolmu(solmu1);
        verkko.addSolmu(solmu2);
        verkko.addSolmu(solmu3);
        verkko.addSolmu(solmu4);
        verkko.addSolmu(solmu5);
        verkko.addSolmu(solmu6);
        verkko.addSolmu(solmu7);
        verkko.addSolmu(solmu8);
        verkko.addSolmu(solmu9);
        verkko.addSolmu(solmu10);
        verkko.addSolmu(solmu11);
        verkko.addSolmu(solmu12);
        verkko.addSolmu(solmu13);
        verkko.addSolmu(solmu14);
        verkko.addSolmu(solmu15); 
        verkko.addSolmu(solmu16);
        verkko.addSolmu(solmu17);
        verkko.addSolmu(solmu18); 
        verkko.addSolmu(solmu19);
        verkko.addSolmu(solmu20);
        verkko.addSolmu(solmu21); 
        verkko.addSolmu(solmu22);
        verkko.addSolmu(solmu23);

        return verkko;
    }  
}
