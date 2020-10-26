
package komponentit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Testit mittaavat kahden geopisteen etaiden tarkkuutta oman toteutuksen 
 * ja tiedetyn tuloksen välillä
 * Vertailutulos laskettu seuraavan työkalun avulla:
 * http://www.meridianoutpost.com/resources/etools/calculators/calculator-latitude-longitude-distance.php?
 * 
 * Koska omassa Haversine Formulan toteutuksessa on käytetty ainoastaan omia
 * Math.x luokan toteutuksia, on etäisyyksille annettu epsilon arvoksi 4m
 * 
 * Haversine Formulaa käytetään vain ja ainostaan kaarien etäisyyksien laskentaan
 * GeoPisteestä A pisteeseen B, joten Haversine Formulan oman toteutuksen metrillinen tarkkuus 
 * ei vaikuta projektissa käytettävien algoritmien toimintaan
 */

public class HeuristiikkaTest {
    
    Heuristiikka heuristiikka;
    double epsilon = 4;
    
    @Before
    public void setUp() {
        this.heuristiikka = new Heuristiikka();
    }
    
    @Test
    public void haversinePalauttaaOikeinReitti1() {
        
        // Gurula - Klusteri
        
        Solmu alku = new Solmu(1, 60.2042304, 24.9614875);
        Solmu loppu = new Solmu(2, 60.1696052, 24.921479993131108);
        
        double alkulat = alku.getLatitude();
        double alkulon = alku.getLongitude();
        
        double loppulat = loppu.getLatitude();
        double loppulon = loppu.getLongitude();
        
        double etaisyys = heuristiikka.haversineFormula(alkulat, alkulon, loppulat, loppulon);
        
        double oikeaEtaisyys = 4444;
        
        assertEquals(oikeaEtaisyys, etaisyys, epsilon);
    }
    
    @Test
    public void haversinePalauttaaOikeinReitti2() {
        
        // Kaivopuiston tähtitorni - Kahvila Regatta
        
        Solmu alku = new Solmu(1, 60.155494, 24.955444);
        Solmu loppu = new Solmu(2, 60.180171, 24.911750);
        
        double alkulat = alku.getLatitude();
        double alkulon = alku.getLongitude();
        
        double loppulat = loppu.getLatitude();
        double loppulon = loppu.getLongitude();
        
        double etaisyys = heuristiikka.haversineFormula(alkulat, alkulon, loppulat, loppulon);
        
        double oikeaEtaisyys = 3660;
        
        assertEquals(oikeaEtaisyys, etaisyys, epsilon);
        
    }

    @Test
    public void haversinePalauttaaOikeinReitti3() {
        
        // Meilahden liikuntakeskus - Narinkkatori
        
        Solmu alku = new Solmu(1, 60.190278, 24.897023);
        Solmu loppu = new Solmu(2, 60.169670, 24.934863);
        
        double alkulat = alku.getLatitude();
        double alkulon = alku.getLongitude();
        
        double loppulat = loppu.getLatitude();
        double loppulon = loppu.getLongitude();
        
        double etaisyys = heuristiikka.haversineFormula(alkulat, alkulon, loppulat, loppulon);
        
        double oikeaEtaisyys = 3100;
        
        assertEquals(oikeaEtaisyys, etaisyys, epsilon);
    }    
}
