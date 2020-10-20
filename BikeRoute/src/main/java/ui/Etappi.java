
package ui;

import java.awt.Dimension;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

public class Etappi extends DefaultWaypoint {
    
    private String etapinTyyppi;
    private final JButton nappi;  
    private final double latitude;
    private final double longitude;
    
    private boolean valittu;
    
    /**
     * Kartan etappi
     * @param koordinaatit
     * @param etapinTyyppi 
     */
    
    public Etappi(GeoPosition koordinaatit, String etapinTyyppi) {
        
        super(koordinaatit);
        
        if (etapinTyyppi.equals("alku")) {
            this.etapinTyyppi = etapinTyyppi;
        } else if (etapinTyyppi.equals("loppu")) {
            this.etapinTyyppi = etapinTyyppi;
        } else {
            this.etapinTyyppi = "";
        }
        
        this.valittu = false;
        
        this.latitude = koordinaatit.getLatitude();
        this.longitude = koordinaatit.getLongitude();
        
        this.nappi = new JButton();
        nappi.setSize(10, 10);
        nappi.setPreferredSize(new Dimension(10, 10));
        nappi.setVisible(false);  
    }
    
    /**
     * Resetoi Javan Mouselistenerit
     */
    
    public void resetHiirenKuuntelijat() {
        
        for (MouseListener mL : nappi.getMouseListeners()) {
            nappi.removeMouseListener(mL);
        }
    }
    
    /**
     * Asettaa etapin tyypin
     * @param tyyppi 
     */
    
    public void setEtapinTyyppi(String tyyppi) {
        this.etapinTyyppi = tyyppi;
    }
    
    /**
     * Palauttaa etapin tyypin
     * @return etapin tyyppi String muodossa
     */
    
    public String getEtapinTyyppi() {
        return this.etapinTyyppi;
    }
    
    /**
     * Palauttaa latitudin
     * @return latitude double muodossa
     */
    
    public double getLatitude() {
        return this.latitude;
    }
    
    /**
     * Palauttaa longitudin
     * @return longitudi double muodossa
     */
    
    public double getLongitude() {
        return this.longitude;
    }
    
    /**
     * Asettaa kartalla olevan solmun napin näkyväksi
     */
    
    public void setNappiNakyviin() {
        this.nappi.setVisible(true);
    }
    
    /**
     * Asettaa kartalla olevan solmun napin piiloon
     */
    
    public void setNappiPiiloon() {
        this.nappi.setVisible(false);
    }
    
    /**
     * Asettaa tietyn etapin valituksi
     * @param arvo 
     */
    
    public void setValittu(boolean arvo) {
        this.valittu = arvo;
    }
    
    /**
     * Tarkastaa onko tietty etappi valittu
     * @return palauta true jos valittu, false jos ei
     */
    
    public boolean isValittu() {
        return this.valittu;
    }
    
    /**
     * Palauttaa etapille asetetun napin
     * @return Javan JButton muodossa oleva nappi
     */
    
    JButton getNappi() {
        return nappi;
    }
}
