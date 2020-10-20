
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
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.WaypointRenderer;

public final class EtappienKasittelija implements WaypointRenderer<Etappi> {
    
    private BufferedImage alku;
    private BufferedImage loppu;
    private BufferedImage piste;
    
    /**
     * Etappienkäsittelijän konstruktori
     * Konstuktorissa luetaan alku, loppu ja piste kuvat
     */
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
    
    /**
     * Muuntaa Image muodossa olevan tiedoston BufferedImageksi
     * @param kuva
     * @return palauttaa bufferedimage olion
     */
    
    public BufferedImage muunnaBufferoiduksiKuvaksi(Image kuva) {
        
        if (kuva instanceof BufferedImage) {
            return (BufferedImage) kuva;
        }
        
        int leveys = kuva.getWidth(null);
        int korkeus = kuva.getHeight(null);
        int argb = BufferedImage.TYPE_INT_ARGB;
        
        BufferedImage bufferedKuva = new BufferedImage(leveys, korkeus, argb);
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
        
        GeoPosition positio = etappi.getPosition();
        int zoomi = kartta.getZoom();
        
        Point2D kohta = kartta.getTileFactory().geoToPixel(positio, zoomi);
        Rectangle ruutu = kartta.getViewportBounds();
        
        int nappiX = (int)kohta.getX() - (int)ruutu.getX();
        int nappiY = (int)kohta.getY() - (int)ruutu.getY();   
        
        int kohtaXMiinusAlkuLeveys = (int)kohta.getX() - alku.getWidth() / 2;
        int kohtaYMiinusAlkuKorkeus = (int)kohta.getY() - alku.getHeight();
        int kohtaXMiinusLoppuLeveys = (int)kohta.getX() - loppu.getWidth() / 2;
        int kohtaYMiinusLoppuKorkeus = (int)kohta.getY() - loppu.getHeight();
        int kohtaXMiinusPisteLeveys = (int)kohta.getX() - piste.getWidth() / 2;
        int kohtaYMiinusPisteKorkeus = (int)kohta.getY() - piste.getHeight() / 2;
        
        JButton nappi = etappi.getNappi();
        int nappiXMiinusNappiLeveys = nappiX - nappi.getWidth() / 2;
        int nappiYMiinusNappiKorkeus = nappiY - nappi.getHeight() / 2;

        if (etappi.getEtapinTyyppi().equals("alku")) {
            grafiikka.drawImage(alku, kohtaXMiinusAlkuLeveys, kohtaYMiinusAlkuKorkeus, null);
        } else if (etappi.getEtapinTyyppi().equals("loppu")) {
            grafiikka.drawImage(loppu, kohtaXMiinusLoppuLeveys, kohtaYMiinusLoppuKorkeus, null);
        } else {           
            grafiikka.drawImage(piste, kohtaXMiinusPisteLeveys, kohtaYMiinusPisteKorkeus, null);
            nappi.setLocation(nappiXMiinusNappiLeveys, nappiYMiinusNappiKorkeus);
        }
        
        grafiikka.dispose();
    }
}