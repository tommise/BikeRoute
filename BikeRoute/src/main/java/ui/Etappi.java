
package ui;

import java.awt.Dimension;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

public class Etappi extends DefaultWaypoint {
    
    private String etapinTyyppi;
    private JButton nappi;  
    private double latitude;
    private double longitude;
    
    private boolean valittu;
    
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
    
    public void resetHiirenKuuntelijat() {
        
        for (MouseListener mL : nappi.getMouseListeners()) {
            nappi.removeMouseListener(mL);
        }
    }
    
    public void setEtapinTyyppi(String tyyppi) {
        this.etapinTyyppi = tyyppi;
    }
    
    public String getEtapinTyyppi() {
        return this.etapinTyyppi;
    }
    
    public double getLatitude() {
        return this.latitude;
    }
    
    public double getLongitude() {
        return this.longitude;
    }
    
    public void setNappiNakyviin() {
        this.nappi.setVisible(true);
    }
    
    public void setNappiPiiloon() {
        this.nappi.setVisible(false);
    }
    
    public void setValittu(boolean arvo) {
        this.valittu = arvo;
    }
    
    public boolean isValittu() {
        return this.valittu;
    }
    
    JButton getNappi() {
        return nappi;
    }
}
