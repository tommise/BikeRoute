
package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.geom.Point2D;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.GeoPosition;
import tietorakenteet.ArrayList;

public class ReitinPiirtaja implements Painter<JXMapViewer> {
    
    private ArrayList<GeoPosition> reitti;
    private final Color reitinVari = Color.CYAN;
    
    /**
     * Reitinpiirtäjän konstruktori jolle annetaan reitti parametrina
     * @param reitti 
     */
    
    public ReitinPiirtaja(ArrayList<GeoPosition> reitti) {
        this.reitti = reitti;
    }
    
    /**
     * Nollaa käytetyn reitin
     */
    
    public void nollaaReitti() {
        this.reitti = new ArrayList<>();
    }    
    
    @Override
    public void paint(Graphics2D grafiikka, JXMapViewer kartta, int paint1, int paint2) {
        grafiikka = (Graphics2D) grafiikka.create();

        Rectangle ruutu = kartta.getViewportBounds();
        grafiikka.translate(- ruutu.x, - ruutu.y);
        
        Key avain = RenderingHints.KEY_ANTIALIASING;
        Object arvo = RenderingHints.VALUE_ANTIALIAS_ON;

        grafiikka.setRenderingHint(avain, arvo);

        grafiikka.setColor(Color.BLACK);
        grafiikka.setStroke(new BasicStroke(4));

        piirraReitti(grafiikka, kartta);

        grafiikka.setColor(reitinVari);
        grafiikka.setStroke(new BasicStroke(2));

        piirraReitti(grafiikka, kartta);

        grafiikka.dispose();
    }
    
    /**
     * Piirtää kartalle käytettyjen solmujen solmujen perusteella
     * @param grafiikka
     * @param kartta 
     */

    private void piirraReitti(Graphics2D grafiikka, JXMapViewer kartta) {
        
        int edellinenX = 0;
        int edellinenY = 0;

        boolean eka = true;
        
        for (int i = 0; i < reitti.size(); i++) {
            GeoPosition geoPiste = reitti.get(i);
            
            Point2D piste = kartta.getTileFactory().geoToPixel(geoPiste, kartta.getZoom());

            if (eka) {
                eka = false;
            } else {
                grafiikka.drawLine(edellinenX, edellinenY, (int) piste.getX(), (int) piste.getY());
            }

            edellinenX = (int) piste.getX();
            edellinenY = (int) piste.getY();
        }
    }
}
