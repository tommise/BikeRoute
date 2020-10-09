
package tyokalut;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MatikkaTest {
    
    Matikka mat;
    double epsilon = 0.001;
    
    @Before
    public void setUp() {
        this.mat = new Matikka();
    }
    
    @Test
    public void itseisarvoPalauttaaOikein() {
        
        double nykyLatitude = 60.2124473;
        double tavoiteLatitude = 60.2140061;
        
        double luku = nykyLatitude - tavoiteLatitude;
        
        double javanItseisarvo = java.lang.Math.abs(luku);
        double omaItseisarvo = mat.itseisarvo(luku);
        
        assertEquals(javanItseisarvo, omaItseisarvo, epsilon);
    }
    
    @Test
    public void eksponenttiPalauttaaOikein() {
        double kanta = 9.642944117723704E-7;
        double eksponentti = 2;
        
        double javanEksponentti = java.lang.Math.pow(kanta, eksponentti);
        double omaEksponentti = mat.potenssi(kanta, eksponentti);
        
        assertEquals(javanEksponentti, omaEksponentti, epsilon);
    }
    
    @Test
    public void neliojuuriPalauttaaOikein() {
        double luku = 7.016249543428773E-7;
        
        double javanNeliojuuri = java.lang.Math.sqrt(luku);
        double omaNeliojuuri = mat.neliojuuri(luku);
        
        assertEquals(javanNeliojuuri, omaNeliojuuri, epsilon);
    }
    
    @Test
    public void radiaaniPalauttaaOikein() {
        double luku = 60.2143224;
        
        double javanRadiaani = java.lang.Math.toRadians(luku);
        double omaRadiaani = mat.radiaani(luku);
        
        assertEquals(javanRadiaani, omaRadiaani, epsilon);
    }
    
    @Test
    public void siniPalauttaaOikein() {
        double luku = 4.924446484507483E-6;
        
        double javanSini = java.lang.Math.sin(luku);
        double omaSini = mat.sini(luku);
        
        assertEquals(javanSini, omaSini, epsilon);
    }

    // --- VALMIIT TESTIT KOSINILLE JA ARKUSTANGENTILLE --- //
    
    /*
    @Test
    public void kosiniPalauttaaOikein() {
        double luku = 1.0517082586552682;
        
        double javanKosini = java.lang.Math.cos(luku);
        double omaKosini = mat.kosini(luku);
        
        assertEquals(javanKosini, omaKosini, epsilon);
    }
    
    @Test
    public void arkustangenttiPalauttaaOikein() {
        
        double y = 0.9999999999976636;
        double x = 1.5284936435707238E-6;
        
        double javanAtan2 = java.lang.Math.atan2(y, x);
        double omaAtan2 = mat.arkustangentti2(y, x);
        
        assertEquals(javanAtan2, omaAtan2, epsilon);
    }
    */
    
}
