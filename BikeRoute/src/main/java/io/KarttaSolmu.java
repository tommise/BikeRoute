
package io;

import java.util.ArrayList;

public class KarttaSolmu {
    
    private long id;
    private double latitude;
    private double longitude;
    private ArrayList<KarttaKaari> kaaret;
    
    /**
     * Karttasolmun konstruktori
     * @param id karttasolmun id
     * @param lat karttasolmun latitude
     * @param lon karttasolmun longitude
     */
    
    public KarttaSolmu(long id, double lat, double lon) {
        this.id = id;
        this.latitude = lat;
        this.longitude = lon;
        this.kaaret = new ArrayList<KarttaKaari>();   
    }
    
    /**
     * Lisää kaaren listalle
     * @param kaari lisättävä kaari olio
     */
    
    public void addKaari(KarttaKaari kaari) {
        this.kaaret.add(kaari);
    }    
    
    /**
     * Palauttaa latituden
     * @return latitude
     */
    
    public double getLatitude() {
        return this.latitude;
    }
    
    /**
     * Palauttaa longituden
     * @return longitude
     */
    
    public double getLongitude() {
        return this.longitude;
    }
    
    /**
     * Palauttaa id:n
     * @return id
     */
    
    public long getID() {
        return this.id;
    }
    
    /**
     * Asettaa latituden
     * @param lat asetettava latitude
     */
    
    public void setLatitude(double lat) {
        this.latitude = lat;
    }
    
    /**
     * Asettaa longituden
     * @param lon asetettava longitude
     */
    
    public void setLongitude(double lon) {
        this.longitude = lon;
    }
}