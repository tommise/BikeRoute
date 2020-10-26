
package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import komponentit.Kaari;
import komponentit.Solmu;
import komponentit.Verkko;

import tietorakenteet.HashMap;

/**
 * Luokka lukee verkon solmut ja kaaret txt tiedostoista
 */

public class VerkonLukija {
    
    private HashMap<Integer, Solmu> solmut = new HashMap<>();
    
    /**
     * Lukee ja luo verkon talin siirtolapuutarhasta
     * @return luotu Verkko olio
     */

    public Verkko luoVerkkoTalista() {

        InputStream kaaritxt = haeTiedostoInputStreamina("talisolmut.txt");
        InputStream solmutxt = haeTiedostoInputStreamina("talikaaret.txt");

        lueSolmut(kaaritxt);
        lueKaaret(solmutxt);
        
        Verkko verkko = new Verkko(this.solmut.values());

        return verkko;
    }
    
    /**
     * Lukee ja luo verkon Kalifornian Davisin kaupunginosasta
     * @return luotu Verkko olio
     */
    
    public Verkko luoVerkkoDavisista() {        
        InputStream kaaritxt = haeTiedostoInputStreamina("daviskaaret.txt");
        InputStream solmutxt = haeTiedostoInputStreamina("davissolmut.txt");

        lueSolmut(solmutxt);
        lueKaaret(kaaritxt);
        
        Verkko verkko = new Verkko(this.solmut.values());

        return verkko;
    }
    
    /**
     * Lukee solmut verkolle parametrina annetusta polusta
     * @param inputStream solmutiedoston inputStream
     */
    
    public void lueSolmut(InputStream inputStream) {
        try (InputStreamReader streamReader =
                new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader lukija = new BufferedReader(streamReader)) {

            String seuraavaRivi;
            
            while ((seuraavaRivi = lukija.readLine()) != null) {
                String[] data = seuraavaRivi.split(", ");

                int id = 0;
                double latitudi = 0;
                double longitudi = 0;

                for (int i = 0; i < data.length; i++) {
                    String arvo = data[i];

                    if (i == 0) { // solmun id
                        id = Integer.valueOf(arvo);
                    } else if (i == 1) { // solmun latitudi
                        latitudi = Double.valueOf(arvo);
                    } else if (i == 2) { // solmun lognitudi
                        longitudi = Double.valueOf(arvo);
                    }
                }

                Solmu solmu = new Solmu(id, latitudi, longitudi);
                this.solmut.put(id, solmu);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    
    /**
     * Lukee kaaret verkolle parametrina annetusta polusta
     * @param inputStream solmutiedoston inputStream
     */
    
    public void lueKaaret(InputStream inputStream) {
        try (InputStreamReader streamReader =
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader lukija = new BufferedReader(streamReader)) {

            String seuraavaRivi;
            while ((seuraavaRivi = lukija.readLine()) != null) {
                String[] data = seuraavaRivi.split(",");

                int alkuid = 0;
                int loppuid = 0;

                for (int i = 0; i < data.length; i++) {
                    String arvo = data[i];

                    if (i == 0) { // solmun id
                        alkuid = Integer.valueOf(arvo);
                    } else if (i == 1) { // solmun latitudi
                        loppuid = Integer.valueOf(arvo);
                    }
                }

                Solmu alku = solmut.get(alkuid);
                Solmu loppu = solmut.get(loppuid);

                Kaari kaari = new Kaari(alku, loppu);
                alku.addKaari(kaari);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Lukee kaaret verkolle parametrina annetusta polusta
     * @param kaariTiedosto kaaritiedosto
     */
    
    private InputStream haeTiedostoInputStreamina(String tiedostonNimi) {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(tiedostonNimi);

        if (inputStream == null) {
            throw new IllegalArgumentException("Tiedostoa " + tiedostonNimi + " ei löytynyt!");
        } else {
            return inputStream;
        }
    }  
}
