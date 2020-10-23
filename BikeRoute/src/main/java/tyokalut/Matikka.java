
package tyokalut;

public class Matikka {
    
    final double pii = 3.14159265358979323846;
    
    /**
     * Korvaa funktion Math.abs()
     * @param luku
     * @return palauttaa luvun itseisarvon double muodossa
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
     * @return palauttaa kertoman double muodossa
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
     * @return palauttaa luvun potenssin double muodossa
     */
    
    public double potenssi(double kanta, double eksponentti) {
        
        double tulos = 1.0;
        
        for (int i = 0; i < itseisarvo(eksponentti); i++) {
            tulos *= kanta;
        }

        return tulos;
    }
    
    /**
     * Korvaa funktion Math.sqrt()
     * @param luku
     * @return palauttaa luvun neliöjuuren double muodossa
     */
    
    public double neliojuuri(double luku) {
        
        double apu = Double.MAX_VALUE;

        double neliojuuri = luku / 2;
        
        while ((apu - neliojuuri) != 0) {
            apu = neliojuuri;
            neliojuuri = (apu + (luku / apu)) / 2;
        }

        return neliojuuri;
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

        for (int i = 1; i <= 50; i += 2) {
            tulos += potenssi(-1, j++) * potenssi(luku, i) / itseisarvo(i);
        }

        return tulos;
    }
    
    /**
     * Korvaa osittain funktion Math.cos()
     * Toteutus tehty niin, että sopii projektin käyttötarkoitukseen
     * @param luku
     * @return palauttaa kosinin double muodossa
     */
    
    public double kosini(double luku) {

        double i = 0;
        
        double apu = 1;
        double tulos = 1;

        while (itseisarvo(apu / tulos) > 0) {
            i++;
            apu = (- apu * luku * luku) / ((2 * i - 1) * (2 * i));
            tulos += apu;
        }
        
        return tulos;
    }   
    
    /**
     * Korvaa osittain funktion Math.atan()
     * Toteutus tehty niin, että sopii projektin käyttötarkoitukseen
     * @param luku
     * @return palauttaa luvun arkustangentin 
     */
    public double arkustangentti(double luku) {

        boolean kaantyi = false;

        if (luku > 1) {
            luku = 1 / luku;
            kaantyi = true;
        }
        
        double tulos = ((0.55913709 / 1.4087812) + 0.60310579) * luku;

        if (kaantyi) {
            tulos = pii / 2 - tulos;
        }
        
        return tulos;
    }    
    
    /**
     * Korvaa osittain funktion Math.atan2()
     * Toteutus tehty niin, että sopii projektin käyttötarkoitukseen
     * @param x
     * @param y
     * @return Palauttaa koordinaattien arvojen käänteisen tangentin double muodossa
     */
    
    public double arkustangentti2(double x, double y) { 
        
        return arkustangentti(x / y);
    }    
}
