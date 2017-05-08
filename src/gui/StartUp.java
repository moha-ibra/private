package gui;

import domein.DomeinController;
import domein.IKaart;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//In deze klasse komt de console implementatie van Pazaak.
public class StartUp {
     
    public static void main(String... args){
        
        // In main enkel deze lijn toegevoegd
       // Application.launch(StartUp.class, args);

        System.out.println("Welkom");
        DomeinController dc = new DomeinController();
        System.out.println("We gaan eerst een aantal gebruikers aanmaken:");
        dc.maakSpeler("Louise", 1998);
        dc.maakSpeler("Hilke", 1997);
        dc.maakSpeler("Bram", 1995);
        dc.maakSpeler("Roel", 2001);
        dc.maakSpeler("Marc", 1968);
        dc.maakSpeler("Lieve", 1970);
        
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
                System.out.println("Selecteer " + aantalSelectieKaarten + " kaarten uit voor " + speler);
                
                List<IKaart> kaarten;
                int tellerKaart = 0;
                
                for(int i=0; i<aantalSelectieKaarten; i++) {
                    boolean vlag = true;
                    System.out.println("Nog beschikbare kaarten voor " + speler);
                    kaarten = dc.kaartenToevoegenActieveSpeler();
                    int teller = 1;
                    
                    for(IKaart item : kaarten) {
                        System.out.println(String.format("%d:%s", teller, item.toString()));
                        teller++;
                     }
                    
                    while(vlag) {
                        System.out.println("Selecteer kaart" + (i+1) + " voor " + speler);
                        tellerKaart = Integer.parseInt(br.readLine());
                        if(tellerKaart > 0 && tellerKaart <= kaarten.size()) vlag = false;
                        if(vlag) System.out.println("Geen geldige selectie.");
                    }
                    dc.selecteerKaartVoorActieveSpeler(kaarten.get(tellerKaart-1));   
                }
                dc.maakWedstrijdStapelVoorActieveSpeler();
            } catch (IOException | NumberFormatException ex) {
                Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } while(aantalSpelers != 0);    
        
        
        System.out.println("\n\n\nStart!");
        //we starten met het spelen van de wedstrijd
        String winnaarInfo;
        
        do {
            winnaarInfo = dc.geefWinnaar();
            String setWinnaarInfo = dc.geefSetWinnaar();
            if(setWinnaarInfo != null) {
                System.out.println("Set gewonnen door:" + setWinnaarInfo);
                dc.startNieuweSet();
            }
            else {
                System.out.println("We starten een set.");
                dc.startNieuweSet();
            }
  
            while(setWinnaarInfo == null) {
                
                System.out.println(dc.toonWedstrijdSituatie());
                br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Selecteer actie:");
                System.out.println(String.format("%s\n%s\n%s", "1:Beeindig beurt", "2:Speel wedstrijdkaart", "3:Bevries spelbord"));
                try {
                    int actie = Integer.parseInt(br.readLine());
                    switch(actie) {
                        case 1:
                            dc.beeindigBeurtSpeler();
                        break;
                        case 2:
                            List<IKaart> wedstrijdstapel = dc.geefWedstrijdstapelSpelerAanBeurt();
                            if(wedstrijdstapel.isEmpty()) break;
                            int teller = 1;
                            for(IKaart item : wedstrijdstapel) {
                                System.out.println(String.format("%d:%s", teller, item.toString()));
                                teller++;
                            }
                            int menuItem = Integer.parseInt(br.readLine());
                            IKaart geselecteerdeKaart = wedstrijdstapel.get(menuItem-1);

                            if(geselecteerdeKaart.getType() == 0) {
                                System.out.println(String.format("%s\n%s\n%s\n%s", geselecteerdeKaart.toString(), "Kies teken:", "1:+", "2:-"));
                                int keuze;
                                switch(Integer.parseInt(br.readLine())) {
                                    case 2:
                                        keuze = -1;
                                        break;
                                    default:
                                        keuze = 1;
                                        break;
                                }
                                dc.registreerWissel(geselecteerdeKaart, keuze);
                            }

                            dc.speelWedstrijdstapelKaart(geselecteerdeKaart); 
                            break;
                        case 3:
                            dc.bevriesSpelbordSpeler();
                            break;
                        default:
                            System.out.println("Geen geldige actie.");
                            break;  
                    }

                }
                catch(IOException | NumberFormatException ex) {
                    Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
                } 
                setWinnaarInfo = dc.geefSetWinnaar();
            }
        } while(winnaarInfo==null); //er is nog geen winnaar
        
       System.out.println(winnaarInfo);
        
        
    }
}
