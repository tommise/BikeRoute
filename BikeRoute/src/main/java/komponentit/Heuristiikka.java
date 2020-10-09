
package components;

public class Heuristiikka {
    
    /**
     * Manhattan Distance
     * 
     * @param nyky - nykyinen solmu
     * @param tavoite - tavoitesolmu
     * 
     * @return Palauttaa heuristiikan manhattan etaisyys tekniikan mukaisesti
     */    
    
    public double manhattanDistance(Solmu nyky, Solmu tavoite) {
        double latitudes = Math.abs(nyky.getLatitude() - tavoite.getLatitude());
        double longitudes = Math.abs(nyky.getLongitude() - tavoite.getLongitude());
        
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
    
    public double haversineMethod(double alkuLat, double alkuLon, double loppuLat, double loppuLon) {
        int maapallonSade = 6371; /// Maapallon säde 6371km
        double latEtaisyys = Math.toRadians(loppuLat - alkuLat);
        double lonEtaisyys = Math.toRadians(loppuLon - alkuLon);

        alkuLat = Math.toRadians(alkuLat);
        loppuLat = Math.toRadians(loppuLat);
        
        double apuLat = Math.pow(Math.sin(latEtaisyys / 2), 2);
        double apuLon = Math.pow(Math.sin(lonEtaisyys / 2), 2);

        double a = apuLat + Math.cos(alkuLat) * Math.cos(loppuLat) * apuLon;
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        double etaisyys = maapallonSade * c * 1000; /// Muutetaan metreiksi

        return etaisyys;
    } 
}
