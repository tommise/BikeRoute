
package ui;

import algoritmit.AStar;
import algoritmit.Dijkstra;
import algoritmit.FringeSearch;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
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

public class Kayttoliittyma {

    
    /**
     * Luokka käyttöliittymälle sisältäen alkeellisen tekstikäyttöliittymän
     * @param args 
     */

    public static void main(String[] args) {

        System.out.println("Luodaan verkko karttadatan pohjalta... (odota hetki)");     
        
        VerkonRakentaja rakentaja = new VerkonRakentaja();
        Verkko verkko = rakentaja.luoVerkko();
        List<Solmu> solmut = verkko.getSolmut();       
        
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
        private List<Solmu> solmut;       
        private List<Painter<JXMapViewer>> piirtajat;
        private Set<Etappi> karttaMerkit;         
        
        private JXMapViewer kartta;
        private ReitinPiirtaja kartanPiirtaja;
        private WaypointPainter<Etappi> merkkienAsettaja;        
        
        private JSlider zoomLiukuSaadin;        
        private boolean zoomMuuttuu = false;
        private final boolean liukuSaadinKaannetty = false;
        
        private boolean dijkstraKaytossa;
        private boolean astarKaytossa;
        private boolean fringeKaytossa;
        
        private Solmu alku;
        private Solmu loppu;
        private JLabel tulos;        
        
        private Etappi alkuEtappi;
        private Etappi loppuEtappi;
        
        @Override
        public void run() {
            
            /**
             * Alustetaan kartta
             */

            TileFactoryInfo osmTile = new OSMTileFactoryInfo();
            DefaultTileFactory tiles = new DefaultTileFactory(osmTile);

            File cache = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
            tiles.setLocalCache(new FileBasedLocalCache(cache, false));

            this.kartta = new JXMapViewer();

            kartta.setTileFactory(tiles);
            
            /**
             * Rakennetaan verkko
             */
            
            // Testiverkko
            
            VerkonRakentaja rakentaja = new VerkonRakentaja();
            Verkko verkko = rakentaja.luoTestiVerkko();
            this.solmut = verkko.getSolmut();
            
            GeoPosition talinSiirtolaPuutarha = new GeoPosition(60.217407, 24.860599);
            kartta.setAddressLocation(talinSiirtolaPuutarha);
            
            /*
            // Karttaverkko osm.pbf tiedoston pohjalta
            
            VerkonRakentaja rakentaja = new VerkonRakentaja();
            Verkko verkko = rakentaja.luoVerkko();
            this.solmut = verkko.getSolmut(); 
            
            GeoPosition davis = new GeoPosition(38.556964, -121.743357);
            kartta.setAddressLocation(davis);
            */         
            
            kartta.setZoom(3);
            
            MouseInputListener hiiri = new PanMouseInputListener(kartta);
            kartta.addMouseListener(new CenterMapListener(kartta));
            kartta.addMouseWheelListener(new ZoomMouseWheelListenerCenter(kartta));            
            kartta.addMouseListener(hiiri);
            kartta.addMouseMotionListener(hiiri);
            kartta.addKeyListener(new PanKeyListener(kartta));

            this.karttaMerkit = new HashSet<>();
            this.reitti = new ArrayList<>();
            
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
                public void actionPerformed(ActionEvent e) {
                    valitseAlku();
                }
            });

            loppuBtn.addActionListener(new ActionListener() {
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
                public void actionPerformed(ActionEvent e) {
                    
                    String[] valinnat = new String[] {"Dijkstra", "A*", "Fringe Search"};
                    
                    int valinta = JOptionPane.showOptionDialog(kartta, "Valitse algoritmi", 
                            "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, valinnat, valinnat[0]);
                    
                    if (valinta == 0) {
                        dijkstraKaytossa = true;
                        astarKaytossa = false;
                        fringeKaytossa = false;
                    } else if (valinta == 1) {
                        dijkstraKaytossa = false;
                        astarKaytossa = true;
                        fringeKaytossa = false;
                    } else if (valinta == 2) {
                        dijkstraKaytossa = false;
                        astarKaytossa = false;
                        fringeKaytossa = true;
                    }
                    
                    JOptionPane.showMessageDialog(kartta, "Algoritmi valittu!");
                }
            }); 
            
            haeReittiBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    haeReitti();
                    paivitaKartta();
                }
            });

            JPanel paneeli3 = new JPanel(new BorderLayout(30, 30));
            paneeli3.setBorder(new EmptyBorder(10, 10, 10, 10));
            
            this.tulos = new JLabel("Kokonaisreitti: ");
            JButton pyyhiReittiBtn = new JButton("Pyyhi reitti");
            
            paneeli3.add(tulos, BorderLayout.PAGE_START);
            paneeli3.add(pyyhiReittiBtn, BorderLayout.PAGE_END);  

            pyyhiReittiBtn.addActionListener(new ActionListener() {
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

            JFrame frame = new JFrame("Talin siirtolapuutarha");

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
            
            if (dijkstraKaytossa) {
                Dijkstra dj = new Dijkstra();
                dj.etsi(alku, loppu);

                ArrayList<Solmu> reitti = dj.luoReitti(loppu);

                paivitaReitti(reitti);
                
                this.tulos.setText("Kokonaisreitti: " + reitti.get(reitti.size() - 1).getMinimiEtaisyys() + " m");
                
            } else if (astarKaytossa) {
                AStar asta = new AStar();
                asta.etsi(alku, loppu);
                
                ArrayList<Solmu> reitti = asta.luoReitti(loppu);
                
                paivitaReitti(reitti);
                
                this.tulos.setText("Kokonaisreitti: " + reitti.get(reitti.size() - 1).getG() + " m");
                
            } else if (fringeKaytossa) {
                FringeSearch fringeSearch = new FringeSearch();
                fringeSearch.etsi(alku, loppu);
            }
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
        
        public void pyyhiReitti() {
            karttaMerkit.clear();
            reitti.clear();
            
            alkuEtappi = null;
            loppuEtappi = null;
            
            alku = null;
            loppu = null;
            
            dijkstraKaytossa = false;
            astarKaytossa = false;
            fringeKaytossa = false;
            
            tulos.setText("Kokonaisreitti: ");
            
            resetoiKaytetytSolmut();
            paivitaKarttaMerkit();
            paivitaKartta();
            
            //JOptionPane.showMessageDialog(kartta, "Reitti pyyhitty!");
        }
        
        public void resetoiKaytetytSolmut() {
            for (int i = 0; i < solmut.size(); i++) {
                Solmu solmu = solmut.get(i);
                solmu.resetSolmu();
            }  
        }
        
        public void paivitaKartta() {
            
            this.merkkienAsettaja = new WaypointPainter<Etappi>();
            merkkienAsettaja.setWaypoints(karttaMerkit);
            merkkienAsettaja.setRenderer(new EtappienKasittelija());

            this.kartanPiirtaja = new ReitinPiirtaja(reitti);

            this.piirtajat = new ArrayList<Painter<JXMapViewer>>();
            piirtajat.add(kartanPiirtaja);
            piirtajat.add(merkkienAsettaja);  

            kartta.setOverlayPainter(merkkienAsettaja); 

            CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(piirtajat);
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
                    Etappi uusi = new Etappi(new GeoPosition(solmu.getLatitude(), solmu.getLongitude()), "default");
                    kartta.add(uusi.getNappi());
                    karttaMerkit.add(uusi);
                }                
            }
        }
        
        public void naytaAlkuNapit() {
            for (Etappi etappi : this.karttaMerkit) {
                
                etappi.resetHiirenKuuntelijat();
                
                etappi.getNappi().addMouseListener(new alkuPointListener(etappi));
                etappi.setNappiNakyviin();
            }
        }
        
        public void naytaLoppuNapit() {
            for (Etappi etappi : this.karttaMerkit) {
                
                etappi.resetHiirenKuuntelijat();
                etappi.getNappi().addMouseListener(new loppuPointListener(etappi));
                
                if (!etappi.isValittu()) {
                    etappi.setNappiNakyviin();
                }
            }
        }
        
        public void piilotaKarttaNapit() {
            for (Etappi etappi : this.karttaMerkit) {
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
                
                //JOptionPane.showMessageDialog(nappi, "Alku valittu!");
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
                
                //JOptionPane.showMessageDialog(nappi, "Loppu valittu!");
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
    
    public static void tekstiKayttoliittyma(Scanner lukija, List<Solmu> solmut) {
        
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
     * Käsittelee testiverkon kolme eri reittiä kaikilla algoritmeillä
     */
    
    public static void kasitteleTestiVerkko() {
        
        VerkonRakentaja verkonRakentaja = new VerkonRakentaja();
        Verkko verkko = verkonRakentaja.luoTestiVerkko();
        
        List<Solmu> solmut = verkko.getSolmut();
        
        System.out.println("Reitti 1:");
        System.out.println("-------");
        
        Solmu alku1 = solmut.get(0);
        Solmu loppu1 = solmut.get(solmut.size() -1);
        
        Solmu alku2 = solmut.get(3);
        Solmu loppu2 = solmut.get(12);
        
        tulostaKaikkiReitit(alku1, loppu1);
        
        System.out.println("");
        System.out.println("Reitti 2:");
        System.out.println("-------");
        
        tulostaKaikkiReitit(alku2, loppu2);

        System.out.println("");
        System.out.println("Reitti 3:");
        System.out.println("-------");   
        
        Solmu alku3 = solmut.get(10);
        Solmu loppu3 = solmut.get(18);
        
        tulostaKaikkiReitit(alku3, loppu3);        
    }
    
    /**
     * Suoritetaan suorituskykytestaus hyödyntäen SuoritusKykyTestaus luokkaa
     */    
    
    public static void suoritusKykyTestaus() {
        SuorituskykyTestaus suoritus = new SuorituskykyTestaus();
        
        int kierroksia = 5000000;
        
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
        
        System.out.println("Fringe Search");
        double fringeAika = suoritus.fringe(kierroksia);
        System.out.println("Kokonaisaika " + fringeAika + " s");
        System.out.println("Keskiarvo " + fringeAika / kierroksia);
        System.out.println("");    
        
    }
    
    /**
     * Tulostetaan kaikkien algoritmien reitit
     * @param alku alkusolmu algoritmeille
     * @param loppu alkusolmu algoritmeille
     */
    
    public static void tulostaKaikkiReitit(Solmu alku, Solmu loppu) {
        System.out.println("");

        Dijkstra dijkstra = new Dijkstra();
        dijkstra.etsi(alku, loppu);
        
        ArrayList<Solmu> dijkstranReitti = dijkstra.luoReitti(loppu);
        tulostaDijkstraReitti(dijkstranReitti); 
        resetoiKaytetytSolmut(dijkstranReitti);           
        
        
        AStar astar = new AStar();
        astar.etsi(alku, loppu);
        
        ArrayList<Solmu> aStarReitti = astar.luoReitti(loppu);
        tulostaAstarReitti(aStarReitti); 
        resetoiKaytetytSolmut(aStarReitti);    
        
        FringeSearch fringe = new FringeSearch();
        fringe.etsi(alku, loppu);
        
        ArrayList<Solmu> fringeReitti = fringe.luoReitti();        
        tulostaFringeReitti(fringeReitti); 
        resetoiKaytetytSolmutFringe(fringeReitti);
    }
    
    /**
     * Resetoi käytetyt solmut jotta gluvut ja minimietäisyydet eivät jää solmuille edellisestä kierroksesta
     * @param solmut 
     */
    
    public static void resetoiKaytetytSolmut(ArrayList<Solmu> solmut) {
        for (int i = 0; i < solmut.size(); i++) {
            Solmu solmu = solmut.get(i);
            solmu.resetSolmu();
        }  
    }
    
    public static void resetoiKaytetytSolmutFringe(ArrayList<Solmu> solmut) {
        for (int i = 0; i < solmut.size(); i++) {
            Solmu solmu = solmut.get(i);
            solmu.resetSolmu();
        }  
    }    
    
    /**
     * Tulostetaan kartan tiet
     * @param solmut annettu solmulista
     */
    
    public static void lueKartanTiet(List<Solmu> solmut) {
        
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
    
    /**
     * Tulostetaan Dijkstran algoritmin tuottama reitti
     * @param reitti reitti solmulistassa
     */
    
    public static void tulostaDijkstraReitti(ArrayList<Solmu> reitti) {
        
        System.out.println("Reitti Dijkstra:");
        System.out.println("");
        
        for (int i = 0; i < reitti.size(); i++) {
            Solmu solmu = reitti.get(i);
            System.out.println(solmu.getMinimiEtaisyys() + "m");
        }        
        
        System.out.println("");
        System.out.println("Kokonaisreitti yhteensä: " + reitti.get(reitti.size() - 1).getMinimiEtaisyys() + "m");
        System.out.println(""); 
    }
    
    /**
     * Tulostetaan A* algoritmin tuottama reitti
     * @param reitti reitti solmulistassa
     */
    
    public static void tulostaAstarReitti(ArrayList<Solmu> reitti) {
        
        System.out.println("Reitti A*:");
        System.out.println("");      
        
        for (int i = 0; i < reitti.size(); i++) {
            Solmu solmu = reitti.get(i);
            System.out.println(solmu.getG() + "m");
        }
        
        System.out.println("");
        System.out.println("Kokonaisreitti yhteensä: " + reitti.get(reitti.size() - 1).getG() + "m");
        System.out.println("");         
    } 
    
    /**
     * Tulostetaan Fringe algoritmin tuottama reitti
     * @param reitti reitti solmulistassa
     */
    
    public static void tulostaFringeReitti(ArrayList<Solmu> reitti) { 
        
        System.out.println("Reitti Fringe Search:");
        System.out.println(""); 
        
        if (reitti.isEmpty()) {
            System.out.println("Fringe ei nyt toiminut!");
            return;
        }   
        
        for (int i = 0; i < reitti.size(); i++) {
            Solmu solmu = reitti.get(i);
            System.out.println(solmu.getG() + "m");
        }     
        
        System.out.println("");
        System.out.println("Kokonaisreitti yhteensä: " + reitti.get(reitti.size() - 1).getG() + "m");
        System.out.println("");         
    }
}