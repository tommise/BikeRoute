
package components;

public class Heuristic {
    
    /**
     * Manhattan Distance
     * 
     * @param nyky - nykyinen solmu
     * @param tavoite - tavoitesolmu
     * 
     * @return Palauttaa heuristiikan manhattan distance tekniikan mukaisesti
     */    
    
    public int manhattanDistance(Solmu nyky, Solmu tavoite) {
        return Math.abs(nyky.getLatitude() - tavoite.getLatitude()) + Math.abs(nyky.getLongitude() - tavoite.getLongitude());
    }
}
