
package komponentit;

import tyokalut.Matikka;

public class Heuristiikka {
    
    Matikka mat = new Matikka();
    
    /**
     * Laskee Haversine tekniikan mukaisesti suoran pituuden kahden geopisteen välillä
     * - Käytetään kahden kaaren etaisyyden mittaamiseen
     * 
     * @param alkuLat alkupisteen latitude
     * @param alkuLon alkupisteen longitude
     * @param loppuLat loppupisteen latitude
     * @param loppuLon loppupisteen longitude
     * 
     * @return palauttaa etaisyyden metreissä double muodossa
     */
    
    public double haversineFormula(double alkuLat, double alkuLon, double loppuLat, double loppuLon) {
        int maapallonSadeKm = 6371;
        
        double latEtaisyys = mat.radiaani(loppuLat - alkuLat);
        double lonEtaisyys = mat.radiaani(loppuLon - alkuLon);

        alkuLat = mat.radiaani(alkuLat);
        loppuLat = mat.radiaani(loppuLat);

        double apuLat = mat.potenssi(mat.sini(latEtaisyys / 2), 2);
        double apuLon = mat.potenssi(mat.sini(lonEtaisyys / 2), 2);

        double a = apuLat + mat.kosini(alkuLat) * mat.kosini(loppuLat) * apuLon;
        
        double c = 2 * mat.arkustangentti2(mat.neliojuuri(a), mat.neliojuuri(1 - a));
        
        double etaisyysMetreissa = maapallonSadeKm * c * 1000;

        return etaisyysMetreissa;
    } 
    
    /**
     * Euclidean etaisyys
     * 
     * @param nyky nykyinen solmu
     * @param tavoite tavoite solmu
     * 
     * @return Palauttaa heuristiikan Euclidean etaisyyden mukaisesti
     */
    
    public double euklidinenEtaisyys(Solmu nyky, Solmu tavoite) {
        double nykyLat = nyky.getLatitude();        
        double nykyLon = nyky.getLongitude();
        
        double tavoiteLat = tavoite.getLatitude();
        double tavoiteLon = tavoite.getLongitude();

        double latEtaisyys = tavoiteLat - nykyLat;
        double lonEtaisyys = tavoiteLon - nykyLon;
        
        double tulos = 0;
        
        tulos += latEtaisyys * latEtaisyys;
        tulos += lonEtaisyys * lonEtaisyys;
        
        return mat.nopeampiNeliojuuri(tulos);
    }    
}
