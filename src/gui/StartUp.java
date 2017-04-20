package gui;

import domein.DomeinController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


//In deze klasse komt de console implementatie van Pazaak.
public class StartUp extends Application {
    
    @Override
    public void start(Stage stage)
    {
        DomeinController controller = new DomeinController();
        Scene scene = new Scene(new HoofdPaneel(controller), 600, 400);
        scene.getStylesheets().add("/gui/styles.css");
        stage.setScene(scene);
        stage.show();
    }

    
    public static void main(String... args){
        
        // In main enkel deze lijn toegevoegd
       // Application.launch(StartUp.class, args);

        System.out.println("Welkom");
        DomeinController dc = new DomeinController();
        System.out.println("We gaan eerst een aantal gebruikers aanmaken:");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        for(int i=0; i<2; i++) {
            String naam; 
            int geboortedatum;
            try {
                System.out.println("Typ in naam voor gebruiker" + (i+1) + ":");
                naam = br.readLine();
                System.out.println("Typ in geboortedatum voor " + naam + ":");
                geboortedatum = Integer.parseInt(br.readLine());
                dc.maakSpeler(naam, geboortedatum);

                List<String> info = dc.geefInfoSpeler();
                System.out.println("Speler succesvol aangemaakt.");
               
                info.forEach((info1) -> {
                    System.out.println(info1);
                });
                List<String> kaarten = dc.geefKaarten();
                kaarten.forEach((kaartOmschrijving)->{
                    System.out.println(kaartOmschrijving);
                });
            }
            catch(NumberFormatException nfe) {
                System.out.println("Error. Dit is geen getal");
                nfe.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("\n\n\nWe starten een spel pazaak.");
        System.out.println("Hier zijn alle beschikbare spelers:");

        List<String> alleSpelers = dc.geefLijstSpelers();
        alleSpelers.forEach((item) -> { 
            System.out.println(item);
        });

        int aantal = dc.geefMaximumAantalSpelers();
        ArrayList<String> geselecteerdeSpelers = new ArrayList<>();
        System.out.println("We spelen het spel met " + aantal + " spelers.");
        
        br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<aantal; i++) {
            System.out.println("Typ in naam voor gebruiker" + (i+1) + ":");
            try {
                String naam = br.readLine();
                dc.selecteerSpeler(naam);
                geselecteerdeSpelers.add(naam);
            } catch (IOException ex) {
                Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("\n\nGeselecteerde spelers:");
        geselecteerdeSpelers.forEach((item) -> {
            System.out.println(item);
        
        });
        System.out.println("Bevestig wedstrijd? (JA/NEE)");
        try {
            String keuze = br.readLine();
            if(keuze.equals("NEE")) return;
        } catch (IOException ex) {
            Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dc.nieuweWedstrijd();
        System.out.println("Er is een spel pazaak aangemaakt.\n\n");
        
        int aantalSpelers;
        do {
            System.out.println("Dit zijn de spelers zonder wedstrijdstapel:");
            List<String> namenSpelersZonderWedstrijdStapel = dc.geefSpelersZonderWedstrijdStapel();
            aantalSpelers = namenSpelersZonderWedstrijdStapel.size() - 1;
            namenSpelersZonderWedstrijdStapel.forEach((item) -> {
                System.out.println(item);
            });
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Selecteer gebruiker om wedstrijdstapel te geven:");
            try {
                String speler = br.readLine();
                dc.selecteerActieveSpelerVoorWedstrijdStapel(speler);
                
                int aantalSelectieKaarten = dc.geefAantalSelectieKaarten();
                System.out.println("Dit is de startstapel. Selecteer hier " + aantalSelectieKaarten + " kaarten uit voor " + speler);
                
                List<String> omschrijvingenKaarten = dc.kaartenToevoegenActieveSpeler();
                omschrijvingenKaarten.forEach((item) -> {
                    System.out.println(item);
                });
                
                for(int i=0; i<aantalSelectieKaarten; i++) {
                    int index = -1;
                    while(index == -1) {
                        System.out.println("Selecteer kaart" + (i+1) + " voor " + speler);
                        index = omschrijvingenKaarten.indexOf(br.readLine());
                        if(index == -1) System.out.println("Geen geldige selectie.");
                    }
                    dc.selecteerKaartVoorActieveSpeler(index);
                }
                dc.maakWedstrijdStapelVoorActieveSpeler();
            } catch (IOException ex) {
                Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } while(aantalSpelers != 0);     
    }
}
