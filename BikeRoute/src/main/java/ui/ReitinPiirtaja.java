
package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.painter.Painter;

public class ReitinPiirtaja implements Painter<JXMapViewer> {
    
    private List<GeoPosition> reitti;
    private Color reitinVari = Color.CYAN;
    private boolean antiAlias = true;
    
    public ReitinPiirtaja(List<GeoPosition> track) {
        this.reitti = new ArrayList<GeoPosition>(track);
    }
    
    @Override
    public void paint(Graphics2D grafiikka, JXMapViewer kartta, int paint1, int paint2) {
        grafiikka = (Graphics2D) grafiikka.create();

        Rectangle ruutu = kartta.getViewportBounds();
        grafiikka.translate(-ruutu.x, -ruutu.y);

        if (antiAlias)
            grafiikka.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        grafiikka.setColor(Color.BLACK);
        grafiikka.setStroke(new BasicStroke(4));

        piirraReitti(grafiikka, kartta);

        grafiikka.setColor(reitinVari);
        grafiikka.setStroke(new BasicStroke(2));

        piirraReitti(grafiikka, kartta);

        grafiikka.dispose();
    }
    
    public void nollaaReitti() {
        this.reitti = new ArrayList<GeoPosition>();
    }

    private void piirraReitti(Graphics2D grafiikka, JXMapViewer kartta) {
        
        int edellinenX = 0;
        int edellinenY = 0;

        boolean eka = true;

        for (GeoPosition geoPiste : reitti) {
            
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
