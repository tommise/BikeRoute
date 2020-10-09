
package komponentit;

import tyokalut.Matikka;

public class Heuristiikka {
    
    Matikka mat = new Matikka();
    
    /**
     * Manhattan Etaisyys
     * 
     * @param nyky - nykyinen solmu
     * @param tavoite - tavoitesolmu
     * 
     * @return Palauttaa heuristiikan manhattan etaisyys tekniikan mukaisesti
     */    
    
    public double manhattanEtaisyys(Solmu nyky, Solmu tavoite) {
        
        double latitudes = mat.itseisarvo(nyky.getLatitude() - tavoite.getLatitude());
        double longitudes = mat.itseisarvo(nyky.getLongitude() - tavoite.getLongitude());
        
        return latitudes + longitudes;
    }
    
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
    
    public double haversineMetodi(double alkuLat, double alkuLon, double loppuLat, double loppuLon) {
        int maapallonSade = 6371; /// Kilometreissä
        
        double latEtaisyys = mat.radiaani(loppuLat - alkuLat);
        double lonEtaisyys = mat.radiaani(loppuLon - alkuLon);

        alkuLat = mat.radiaani(alkuLat);
        loppuLat = mat.radiaani(loppuLat);

        double apuLat = mat.potenssi(mat.sini(latEtaisyys / 2), 2);
        double apuLon = mat.potenssi(mat.sini(lonEtaisyys / 2), 2);

        double a = apuLat + Math.cos(alkuLat) * Math.cos(loppuLat) * apuLon;
        
        double c = 2 * Math.atan2(Math.sqrt(a), mat.neliojuuri(1 - a));
        
        double etaisyys = maapallonSade * c * 1000; /// Muutetaan metreiksi

        return etaisyys;
    } 
}
