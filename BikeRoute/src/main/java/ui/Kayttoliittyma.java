
package ui;

import algoritmit.AStar;
import algoritmit.Dijkstra;
import algoritmit.IDAStar;

import io.VerkonRakentaja;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.File;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputListener;

import komponentit.Kaari;
import komponentit.Solmu;
import komponentit.Verkko;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.cache.FileBasedLocalCache;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;

import suorituskyky.SuorituskykyTestaus;
import tietorakenteet.ArrayList;


public class Kayttoliittyma {
    
    /**
     * Luokka käyttöliittymälle sisältäen alkeellisen tekstikäyttöliittymän
     * @param args 
     */

    public static void main(String[] args) {

        System.out.println("Luodaan verkko karttadatan pohjalta... (odota hetki)");     
        
        VerkonRakentaja rakentaja = new VerkonRakentaja();
        Verkko verkko = rakentaja.luoVerkko();
        ArrayList<Solmu> solmut = verkko.getSolmut();
        
        System.out.println("Kaikki valmista!");

        Scanner lukija = new Scanner(System.in);
        
        System.out.println("");
        System.out.println("Tervetuloa!");
        System.out.println("");
        System.out.println("Valitse seuraavista:");
        System.out.println("-------------------------");
        System.out.println("1 Tekstipohjainen käyttöliittymä");
        System.out.println("2 Visuaalinen käyttöliittymä");
        System.out.println("");
        System.out.println("x Poistu");
        System.out.println("");
        
        while (true) {
            String komento = lukija.nextLine();
        
            if (komento.equals("1")) {
                tekstiKayttoliittyma(lukija, solmut);
            } else if (komento.equals("2")) {
                Thread kayttis = new Thread(new Kayttoliittyma().new VisuaalinenKayttoliittyma()); 
                kayttis.start();
            } else if (komento.equals("x")) {
                break;
            } else {
                System.out.println("Väärä komento, yritä uudelleen");
            } 
        }
    }   
    
    /**
     * Visuaalinen käyttöliittymä
     */
    
    private class VisuaalinenKayttoliittyma implements Runnable {
        
        private ArrayList<GeoPosition> reitti;
        private ArrayList<Solmu> solmut;       
        private ArrayList<Painter<JXMapViewer>> piirtajat;
        private ArrayList<Etappi> karttaMerkit;   
        
        private JXMapViewer kartta;
        private ReitinPiirtaja kartanPiirtaja;
        private WaypointPainter<Etappi> merkkienAsettaja;        
        
        private JSlider zoomLiukuSaadin;        
        private boolean zoomMuuttuu = false;
        private final boolean liukuSaadinKaannetty = false;
        
        private boolean dijkstraValittu;
        private boolean astarValittu;
        private boolean idaStarValittu;
        
        private Solmu alku;
        private Solmu loppu;
        private JLabel tulosLabel;        
        
        private Etappi alkuEtappi;
        private Etappi loppuEtappi;
        
        @Override
        public void run() {
            
            /**
             * Alustetaan kartta
             */

            TileFactoryInfo osmTile = new OSMTileFactoryInfo();
            DefaultTileFactory tiles = new DefaultTileFactory(osmTile);

            String cachePath = System.getProperty("user.home") + File.separator + ".jxmapviewer2";
            File cache = new File(cachePath);
            tiles.setLocalCache(new FileBasedLocalCache(cache, false));

            this.kartta = new JXMapViewer();

            kartta.setTileFactory(tiles);
            
            MouseInputListener hiiri = new PanMouseInputListener(kartta);
            kartta.addMouseListener(new CenterMapListener(kartta));
            kartta.addMouseWheelListener(new ZoomMouseWheelListenerCenter(kartta));            
            kartta.addMouseListener(hiiri);
            kartta.addMouseMotionListener(hiiri);
            kartta.addKeyListener(new PanKeyListener(kartta));
            
            kartta.setZoom(3);
            
            this.karttaMerkit = new ArrayList<>();
            this.reitti = new ArrayList<>();
            
            VerkonRakentaja rakentaja = new VerkonRakentaja();
            
            Verkko verkko = rakentaja.luoTestiVerkko();
            // Verkko verkko = rakentaja.luoVerkko();
            this.solmut = verkko.getSolmut();
            
            GeoPosition talinSiirtolaPuutarha = new GeoPosition(60.217407, 24.860599);
            kartta.setAddressLocation(talinSiirtolaPuutarha);
            
            // GeoPosition davis = new GeoPosition(38.556964, -121.743357);
            // kartta.setAddressLocation(davis); // davis.osm.pbf            
            
            paivitaKarttaMerkit();
            paivitaKartta();

            /**
             * Paneelit: sivupaneeli, kartallinen keskipaneeli ja toinen sivupaneeli
             */

            /**
             * Oikea sivupaneeli
             */

            JPanel paneeli1 = new JPanel(new BorderLayout(5, 5));
            paneeli1.setBorder(new EmptyBorder(10, 10, 10, 10));

            JButton aloitaBtn = new JButton("Valitse alku");
            JButton loppuBtn = new JButton("Valitse loppu");

            paneeli1.add(aloitaBtn, BorderLayout.PAGE_START); 
            paneeli1.add(loppuBtn, BorderLayout.PAGE_END);

            aloitaBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    valitseAlku();
                }
            });

            loppuBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    valitseLoppu();
                }
            });
            
            JPanel paneeli2 = new JPanel(new BorderLayout(5, 5));
            paneeli2.setBorder(new EmptyBorder(10, 10, 10, 10));

            JButton valitseAlgoBtn = new JButton("Valitse algoritmi");
            JButton haeReittiBtn = new JButton("Hae reitti!");
            
            paneeli2.add(valitseAlgoBtn, BorderLayout.PAGE_START);
            paneeli2.add(haeReittiBtn, BorderLayout.PAGE_END);
            
            valitseAlgoBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    String[] valinnat = new String[] {"Dijkstra", "A*", "IDA*"};
                    String teksti = "Valitse algoritmi";

                    int valinta = JOptionPane.showOptionDialog(kartta, teksti, "", 
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, 
                            null, valinnat, valinnat[0]);
                    
                    valitseAlgoritmi(valinta);
                }
            }); 
            
            haeReittiBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    haeReitti();
                    paivitaKartta();
                }
            });

            JPanel paneeli3 = new JPanel(new BorderLayout(30, 30));
            paneeli3.setBorder(new EmptyBorder(10, 10, 10, 10));
            
            this.tulosLabel = new JLabel("Kokonaisreitti: ");
            JButton pyyhiReittiBtn = new JButton("Pyyhi reitti");
            
            paneeli3.add(tulosLabel, BorderLayout.PAGE_START);
            paneeli3.add(pyyhiReittiBtn, BorderLayout.PAGE_END);  

            pyyhiReittiBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pyyhiReitti();
                }
            });

            JPanel oikeaSivuPaneeli = new JPanel(new BorderLayout());
            oikeaSivuPaneeli.setBorder(new EmptyBorder(100, 5, 100, 5));
            oikeaSivuPaneeli.setPreferredSize(new Dimension(300, 300));

            oikeaSivuPaneeli.add(paneeli1, BorderLayout.PAGE_START);
            oikeaSivuPaneeli.add(paneeli2, BorderLayout.CENTER);
            oikeaSivuPaneeli.add(paneeli3, BorderLayout.PAGE_END);            
            
            /**
             * Vasen sivupaneeli
             */
            
            JPanel vasenSivuPaneeli = new JPanel(new BorderLayout());
            vasenSivuPaneeli.setBorder(new EmptyBorder(100, 20, 100, 20));
            vasenSivuPaneeli.setPreferredSize(new Dimension(90, 90));

            zoomLiukuSaadin = new JSlider();
            zoomLiukuSaadin.setValue(3);
            zoomLiukuSaadin.setOrientation(SwingConstants.VERTICAL);
            zoomLiukuSaadin.setPaintTicks(true);
            zoomLiukuSaadin.setSnapToTicks(true);
            zoomLiukuSaadin.setMajorTickSpacing(1);            
            zoomLiukuSaadin.setMinimum(3);
            zoomLiukuSaadin.setMaximum(10);            
            zoomLiukuSaadin.setMinorTickSpacing(1);            
            zoomLiukuSaadin.setMinimumSize(new Dimension(35, 35));
            zoomLiukuSaadin.setPreferredSize(new Dimension(35, 35));
            
            zoomLiukuSaadin.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    if (!zoomMuuttuu) {
                        asetaZoom(zoomLiukuSaadin.getValue());
                    }
                }
            });
            
            kartta.addPropertyChangeListener("zoom", new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    zoomLiukuSaadin.setValue(kartta.getZoom());
                }
            });
            
            JLabel zoomLabel = new JLabel("Zoom:");
            vasenSivuPaneeli.add(zoomLabel, BorderLayout.PAGE_START);
            vasenSivuPaneeli.add(zoomLiukuSaadin, BorderLayout.CENTER);

            /**
             * Asetetaan lopuksi paneelit ikkunaan
             */

            JFrame frame = new JFrame("BikeRoute");

            frame.getContentPane().setLayout(new BorderLayout());
            frame.getContentPane().add(vasenSivuPaneeli, BorderLayout.WEST);
            frame.getContentPane().add(kartta, BorderLayout.CENTER); 
            frame.getContentPane().add(oikeaSivuPaneeli, BorderLayout.EAST);

            frame.setSize(new Dimension(1400, 800));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true); 
        }      
        
        public void asetaZoom(int zoom) {
            zoomMuuttuu = true;
            
            kartta.setZoom(zoom - 1);
            
            if (liukuSaadinKaannetty) {
                zoomLiukuSaadin.setValue(zoomLiukuSaadin.getMaximum() - zoom);
            } else {
                zoomLiukuSaadin.setValue(zoom);
            }
            
            zoomMuuttuu = false;
        }        
        
        public void valitseAlku() {
            JOptionPane.showMessageDialog(kartta, "Valitse reitille alku");
            naytaAlkuNapit();
        }
        
        public void valitseLoppu() {
            JOptionPane.showMessageDialog(kartta, "Valitse reitille loppu");
            naytaLoppuNapit();
        }   
        
        public void haeReitti() {
            
            alku = etsiSolmuEtapinPerusteella(this.alkuEtappi);
            loppu = etsiSolmuEtapinPerusteella(this.loppuEtappi);
            
            if (alku == null || loppu == null) {
                return;
            }
            
            double tulos = 0;
            
            if (dijkstraValittu) {
                Dijkstra dj = new Dijkstra();
                dj.etsi(alku, loppu);

                ArrayList<Solmu> dijkstraReitti = dj.luoReitti(loppu);

                paivitaReitti(dijkstraReitti);
                tulos = dijkstraReitti.get(dijkstraReitti.size() - 1).getG();
                
            } else if (astarValittu) {
                AStar asta = new AStar();
                asta.etsi(alku, loppu);
                
                ArrayList<Solmu> aStarReitti = asta.luoReitti(loppu);
                
                paivitaReitti(aStarReitti);
                tulos = aStarReitti.get(aStarReitti.size() - 1).getG();
                
            } else if (idaStarValittu) {
                IDAStar idastar = new IDAStar();
                idastar.etsi(alku, loppu);
                
                ArrayList<Solmu> idaStarReitti = idastar.luoReitti(loppu);
                
                paivitaReitti(idaStarReitti);
                tulos = idaStarReitti.get(idaStarReitti.size() - 1).getG();         
                
            }
            
            this.tulosLabel.setText("Kokonaisreitti: " + tulos + " m");
        }
        
        public void paivitaReitti(ArrayList<Solmu> reitti) {
            this.reitti = new ArrayList<>();

            for (int i = 0; i < reitti.size(); i++) {
                Solmu solmu = reitti.get(i);

                this.reitti.add(new GeoPosition(solmu.getLatitude(), solmu.getLongitude()));
            }
        }
        
        public Solmu etsiSolmuEtapinPerusteella(Etappi etappi) {
            
            for (int i = 0; i < solmut.size(); i++) {
                Solmu solmu = solmut.get(i);
                
                double etappiLatitude = etappi.getLatitude();
                double etappiLongitude = etappi.getLongitude();
                double solmuLatitude = solmu.getLatitude();
                double solmuLongitude = solmu.getLongitude();
                
                if (etappiLatitude == solmuLatitude && etappiLongitude == solmuLongitude) {
                    return solmu;
                }
            }
            
            return null;
        }
        
        public void valitseAlgoritmi(int valinta) {
            if (valinta == 0) {
                dijkstraValittu = true;
                JOptionPane.showMessageDialog(kartta, "Dijkstra valittu!");
            } else if (valinta == 1) {
                astarValittu = true;
                JOptionPane.showMessageDialog(kartta, "A* valittu!");
            } else if (valinta == 2) {
                idaStarValittu = true;
                JOptionPane.showMessageDialog(kartta, "IDA* valittu!");
            }
        }
        
        public void pyyhiReitti() {
            karttaMerkit = new ArrayList();
            reitti = new ArrayList<>();
            
            alkuEtappi = null;
            loppuEtappi = null;
            alku = null;
            loppu = null;
            
            dijkstraValittu = false;
            astarValittu = false;
            idaStarValittu = false;
            
            tulosLabel.setText("Kokonaisreitti: ");
            
            resetoiKaytetytSolmut();
            paivitaKarttaMerkit();
            paivitaKartta();
        }
        
        public void resetoiKaytetytSolmut() {
            for (int i = 0; i < solmut.size(); i++) {
                Solmu solmu = solmut.get(i);
                solmu.resetSolmu();
            }  
        }
        
        public void paivitaKartta() {
            
            /**
             * JXMapViewerin WaypointPainter tarvitsee "Waypointikseen" java.util.Set rakenteen
             */
            
            this.merkkienAsettaja = new WaypointPainter<>();
            
            java.util.Set jxMapKarttaMerkit = new java.util.HashSet();
            
            for (int i = 0; i < karttaMerkit.size(); i++) {
                Etappi etappi = karttaMerkit.get(i);
                jxMapKarttaMerkit.add(etappi);
            }
            
            merkkienAsettaja.setWaypoints(jxMapKarttaMerkit);
            merkkienAsettaja.setRenderer(new EtappienKasittelija());

            this.kartanPiirtaja = new ReitinPiirtaja(reitti);

            this.piirtajat = new ArrayList<>();
            piirtajat.add(kartanPiirtaja);
            piirtajat.add(merkkienAsettaja);  

            kartta.setOverlayPainter(merkkienAsettaja);
            
            /**
             * JXMapViewerin compoundPainter tarvitsee parametrikseen java.util.List rakenteen
             */
            
            java.util.List jxMapPainterLista = new java.util.ArrayList();
            jxMapPainterLista.add(piirtajat.get(0));
            jxMapPainterLista.add(piirtajat.get(1));
            
            CompoundPainter<JXMapViewer> painter = new CompoundPainter<>();
            painter.setPainters(jxMapPainterLista);
            
            kartta.setOverlayPainter(painter);
        }
        
        public void paivitaKarttaMerkit() {
            for (int i = 0; i < solmut.size(); i++) {
                Solmu solmu = solmut.get(i);

                if (alkuEtappi != null) {
                    karttaMerkit.add(alkuEtappi);
                    kartta.add(alkuEtappi.getNappi());
                } else if (loppuEtappi != null) {
                    karttaMerkit.add(loppuEtappi);
                    kartta.add(loppuEtappi.getNappi());
                } else {
                    double latitude = solmu.getLatitude();
                    double longitude = solmu.getLongitude();
                    GeoPosition positio = new GeoPosition(latitude, longitude);
                    Etappi uusi = new Etappi(positio, "default");
                    kartta.add(uusi.getNappi());
                    karttaMerkit.add(uusi);
                }                
            }
        }
        
        public void naytaAlkuNapit() {
            
            for (int i = 0; i < karttaMerkit.size(); i++) {
                Etappi etappi = karttaMerkit.get(i);
                
                etappi.resetHiirenKuuntelijat();
                
                etappi.getNappi().addMouseListener(new alkuPointListener(etappi));
                etappi.setNappiNakyviin();
            }
        }
        
        public void naytaLoppuNapit() {
            
            for (int i = 0; i < karttaMerkit.size(); i++) {
                Etappi etappi = karttaMerkit.get(i);
                
                etappi.resetHiirenKuuntelijat();
                etappi.getNappi().addMouseListener(new loppuPointListener(etappi));
                
                if (!etappi.isValittu()) {
                    etappi.setNappiNakyviin();
                }
            }
        }
        
        public void piilotaKarttaNapit() {
            
            for (int i = 0; i < karttaMerkit.size(); i++) {
                Etappi etappi = karttaMerkit.get(i);
                
                etappi.setNappiPiiloon();
            }
        }
        
        public void paivitaAlku(Etappi etappi) {
            this.alkuEtappi = etappi;
        }
        
        public void paivitaLoppu(Etappi etappi) {
            this.loppuEtappi = etappi;
        }
        
        private class alkuPointListener implements MouseListener {
            
            Etappi etappi;
            JButton nappi;
            
            public alkuPointListener(Etappi etappi) {
                this.etappi = etappi;
                this.nappi = etappi.getNappi();
                paivitaAlku(etappi);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                paivitaAlku(etappi);
                etappi.setValittu(true);
                etappi.setEtapinTyyppi("alku");
                
                piilotaKarttaNapit();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        }   
        
        private class loppuPointListener implements MouseListener {
            
            Etappi etappi;
            JButton nappi;
            
            public loppuPointListener(Etappi w) {
                this.etappi = w;
                this.nappi = w.getNappi();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                paivitaLoppu(etappi);
                etappi.setValittu(true);
                etappi.setEtapinTyyppi("loppu");

                piilotaKarttaNapit();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        }           
    }
    
    /**
     * Tekstikäyttöliittymä ohjelmalle
     * @param lukija
     * @param solmut verkon kaikki solmut
     */
    
    public static void tekstiKayttoliittyma(Scanner lukija, ArrayList<Solmu> solmut) {
        
        while (true) {
            System.out.println("");
            System.out.println("-------------------------");
            System.out.println("");
            System.out.println("Tervetuloa!");
            System.out.println("");
            System.out.println("1 Kasittele testiverkko");
            System.out.println("2 Suorituskykytestaus");
            System.out.println("3 Lue kartan tiet");
            System.out.println("");
            System.out.println("x Poistu");
            System.out.println("");
            System.out.println("-------------------------");
            System.out.println("");
            
            String komento = lukija.nextLine();
            System.out.println("");
            
            if (komento.equals("1")) {
                kasitteleTestiVerkko();
            } else if (komento.equals("2")) {
                suoritusKykyTestaus();
            } else if (komento.equals("3")) {
                lueKartanTiet(solmut);
            } else if (komento.equals("x")) {
                System.out.println("Kiitos ja näkemiin!");
                break;
            } else {
                System.out.println("Väärä komento.");
            }
        }
    }
    
    /**
     * Suoritetaan suorituskykytestaus hyödyntäen SuoritusKykyTestaus luokkaa
     */    
    
    public static void suoritusKykyTestaus() {
        SuorituskykyTestaus suoritus = new SuorituskykyTestaus();
        
        int kierroksia = 10000;
        
        System.out.println("");
        System.out.println("Suorituskykytestaus:");
        System.out.println("Kierroksia: " + kierroksia);
        System.out.println("");
        System.out.println("(odota hetki)");
        System.out.println("");
        
        System.out.println("Dijkstra");
        double dijkstraAika = suoritus.dijkstra(kierroksia);
        System.out.println("Kokonaisaika " + dijkstraAika + " s");
        System.out.println("Keskiarvo " + dijkstraAika / kierroksia);
        System.out.println("");
        
        System.out.println("A*");
        double astarAika = suoritus.astar(kierroksia);
        System.out.println("Kokonaisaika " + astarAika + " s");
        System.out.println("Keskiarvo " + astarAika / kierroksia);
        System.out.println("");
        
        
        System.out.println("IDA*");
        double idaStarAika = suoritus.idaStar(1000);
        System.out.println("Kokonaisaika " + idaStarAika + " s");
        System.out.println("Keskiarvo " + idaStarAika / kierroksia);
        System.out.println("");
        
    }
    
    /**
     * Käsittelee testiverkon kolme eri reittiä kaikilla algoritmeillä
     */
    
    public static void kasitteleTestiVerkko() {
        
        VerkonRakentaja verkonRakentaja = new VerkonRakentaja();
        System.out.println("-----------------");
        System.out.println("Reitti 1: (802.8367857851956m)");
        System.out.println("-----------------");
        System.out.println("");
        Verkko verkko = verkonRakentaja.luoTestiVerkko();
        ArrayList<Solmu> solmut = verkko.getSolmut();
        tulostaKaikkiReitit(solmut.get(solmut.size() - 1), solmut.get(0));
        
        System.out.println("-----------------");
        System.out.println("Reitti 2: (375.823394569141m)");
        System.out.println("-----------------");
        System.out.println("");
        Verkko verkko2 = verkonRakentaja.luoTestiVerkko();
        ArrayList<Solmu> solmut2 = verkko2.getSolmut();
        tulostaKaikkiReitit(solmut2.get(3), solmut2.get(12));
        
        System.out.println("-----------------");
        System.out.println("Reitti 3: (293.860036810901m)");
        System.out.println("-----------------");
        System.out.println("");
        Verkko verkko3 = verkonRakentaja.luoTestiVerkko();
        ArrayList<Solmu> solmut3 = verkko3.getSolmut();
        tulostaKaikkiReitit(solmut3.get(10), solmut3.get(18));
    }
    
    /**
     * Tulostetaan kaikkien algoritmien reitit
     * @param alku alkusolmu algoritmeille
     * @param loppu alkusolmu algoritmeille
     */
    
    public static void tulostaKaikkiReitit(Solmu alku, Solmu loppu) {

        Dijkstra dijkstra = new Dijkstra();
        dijkstra.etsi(alku, loppu);
        ArrayList<Solmu> dijkstranReitti = dijkstra.luoReitti(loppu);
        System.out.println("Reitti Dijkstra:");
        System.out.println("");         
        tulostaReitti(dijkstranReitti);      
        
        AStar astar = new AStar();
        astar.etsi(alku, loppu);
        ArrayList<Solmu> aStarReitti = astar.luoReitti(loppu);
        System.out.println("Reitti A*:");
        System.out.println("");
        tulostaReitti(aStarReitti); 
        
        IDAStar idastar = new IDAStar();
        idastar.etsi(alku, loppu);
        ArrayList<Solmu> idastarReitti = idastar.luoReitti(loppu);
        System.out.println("Reitti IDA*:");
        System.out.println("");
        tulostaReitti(idastarReitti);
    }
    
    /**
     * Tulostetaan algortitmin tuottama reitti konsoliin
     * @param reitti reitti solmulistassa
     */
    
    public static void tulostaReitti(ArrayList<Solmu> reitti) {
        
        for (int i = 0; i < reitti.size(); i++) {
            Solmu solmu = reitti.get(i);
            System.out.println(solmu.getG() + "m");
        }
        
        double tulos = reitti.get(reitti.size() - 1).getG();
        
        System.out.println("");
        System.out.println("Kokonaisreitti yhteensä: " + tulos + "m");
        System.out.println("");         
    }    
    
    /**
     * Tulostetaan kartan tiet
     * @param solmut annettu solmulista
     */
    
    public static void lueKartanTiet(ArrayList<Solmu> solmut) {
        
        for (int i = 0; i < solmut.size(); i++) {
            Solmu solmu = solmut.get(i);
            
            ArrayList<Kaari> kaaret = solmu.getKaaret();
            
            for (int j = 0; j < kaaret.size(); j++) {
                
                Kaari kaari = kaaret.get(j);
                
                System.out.println("Käsiteltävän solmun ID: " + solmu.getID());
                System.out.println("Tien nimi: " + kaari.getNimi());
                System.out.println("Tien tyyppi: " + kaari.getTienTyyppi());
                System.out.println("Tien pituus: " + kaari.getEtaisyys() + "m");
                System.out.println("");
            }
        }
    }
}