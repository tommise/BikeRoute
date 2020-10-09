
package tyokalut;

public class Matikka {
    
    final double pii = 3.14159265358979323846;
    
    /**
     * Korvaa funktion Math.abs()
     * @param luku
     * @return 
     */
    
    public double itseisarvo(double luku) {
        if (luku < 0) {
            luku *= -1;
        }
        
        return luku;
    }
    
    /**
     * Laskee luvulle kertoman
     * @param luku
     * @return palauttaa kertoman
     */
    
    public double kertoma(double luku) {
        
        if (luku == 0) {
            return 1;
        }
        
        return luku * (kertoma(luku - 1));
    }
    
    /**
     * Korvaa funktion Math.pow()
     * @param kanta
     * @param eksponentti
     * @return 
     */
    
    public double potenssi(double kanta, double eksponentti) {
        
        double tulos = 1.0;
        
        if (eksponentti < 0) {
            return 1 / tulos;
        }
        
        for (int i = 0; i < itseisarvo(eksponentti); i++) {
            tulos *= kanta;
        }

        return tulos;
    }
    
    /**
     * Korvaa funktion Math.sqrt()
     * @param luku
     * @return 
     */
    
    public double neliojuuri(double luku) {
        double ala = 0;
        double yla = luku;
        double keski = 0;
        
        for (int i = 0; i < 1000; i++) {
            keski = (ala + yla) / 2;
            
            if (keski * keski == luku) {
                return keski;
            } else if (keski * keski > luku) {
                yla = keski;
            } else {
                ala = keski;
            } 
        }
        
        return keski;
    }
    
    /**
     * Korvaa funktion Math.toRadians()
     * @param luku
     * @return palauttaa radiaanin double muodossa
     */
    
    public double radiaani(double luku) {
        return luku * pii / 180;
    }    
    
    /**
     * Korvaa funktion Math.sin()
     * @param luku
     * @return palauttaa sinin double muodossa
     */
        
    public double sini(double luku) {

        int j = 0;
        double tulos = 0;

        for (int i = 1; i <= 30; i += 2) {
            tulos += potenssi(-1, j++) * potenssi(luku, i) / itseisarvo(i);
        }

        return tulos;
    }
    
    // --- KESKEN --- //
    
    /**
     * Korvaa funktion Math.cos()
     * @param luku
     * @return palauttaa kosinin double muodossa
     */
    
    public double kosini(double luku) {
        return 0;
    }   
    
    /**
     * Korvaa funktion Math.atan2()
     * @param x
     * @param y
     * @return Palauttaa koordinaattien arvojen käänteisen tangentin double muodossa
     */
    
    public double arkustangentti2(double y, double x) { 
        return 0;
    }
}
