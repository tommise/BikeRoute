
package ui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.WaypointRenderer;

public final class EtappienKasittelija implements WaypointRenderer<Etappi> {
    
    private BufferedImage alku;
    private BufferedImage loppu;
    private BufferedImage piste;
    
    public EtappienKasittelija() {

        try {
            Image alkuImage = ImageIO.read(new File("./images/alku.png"));
            Image loppuImage = ImageIO.read(new File("./images/loppu.png"));
            Image pisteImage = ImageIO.read(new File("./images/piste.png"));
            
            alku = muunnaBufferoiduksiKuvaksi(alkuImage);
            loppu = muunnaBufferoiduksiKuvaksi(loppuImage);
            piste = muunnaBufferoiduksiKuvaksi(pisteImage);
            
        } catch (IOException ex) {
            Logger.getLogger(EtappienKasittelija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BufferedImage muunnaBufferoiduksiKuvaksi(Image kuva) {
        
        if (kuva instanceof BufferedImage) {
            return (BufferedImage) kuva;
        }

        BufferedImage bufferedKuva = new BufferedImage(kuva.getWidth(null), kuva.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D grafiikka = bufferedKuva.createGraphics();
        grafiikka.drawImage(kuva, 0, 0, null);
        grafiikka.dispose();

        return bufferedKuva;
    }

    @Override
    public void paintWaypoint(Graphics2D grafiikka, JXMapViewer kartta, Etappi etappi) {
        
        if (alku == null || loppu == null) {
            return;
        }
        
        grafiikka = (Graphics2D)grafiikka.create();        
        
        Point2D kohta = kartta.getTileFactory().geoToPixel(etappi.getPosition(), kartta.getZoom());

        int kohtaX = (int)kohta.getX();
        int kohtaY = (int)kohta.getY();
        
        Rectangle ruutu = kartta.getViewportBounds();
        
        int ruutuX = (int) ruutu.getX();
        int ruutuY = (int) ruutu.getY();
        
        int nappiX = kohtaX - ruutuX;
        int nappiY = kohtaY - ruutuY;   

        if (etappi.getEtapinTyyppi().equals("alku")) {
            grafiikka.drawImage(alku, kohtaX -alku.getWidth() / 2, kohtaY -alku.getHeight(), null);
        } else if (etappi.getEtapinTyyppi().equals("loppu")) {
            grafiikka.drawImage(loppu, kohtaX -loppu.getWidth() / 2, kohtaY -loppu.getHeight(), null);
        } else {           
            grafiikka.drawImage(piste, kohtaX -piste.getWidth() / 2, kohtaY -piste.getHeight() / 2, null);
            
            JButton nappi = etappi.getNappi();
            nappi.setLocation(nappiX - nappi.getWidth() / 2, nappiY - nappi.getHeight() / 2);
        }
        
        grafiikka.dispose();
    }
}