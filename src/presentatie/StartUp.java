package presentatie;

import domein.DomeinController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


//In deze klasse komt de console implementatie van Pazaak.
public class StartUp {
    
        public static void main(String[] args){
            System.out.println("Welkom");
            DomeinController dc = new DomeinController();
            System.out.println("We gaan eerst een aantal gebruikers aanmaken:");
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for(int i=0; i<3; i++) {
                String naam; 
                int geboortedatum;
                try {
                    System.out.println("Typ in naam voor gebruiker" + (i+1) + ":");
                    naam = br.readLine();
                    System.out.println("Typ in geboortedatum voor " + naam + ":");
                    geboortedatum = Integer.parseInt(br.readLine());
                    dc.maakSpeler(naam, geboortedatum);
                    
                    String[] info = dc.geefInfoSpeler();
                    System.out.println("Speler succesvol aangemaakt.");
                    for (String info1 : info) {
                        System.out.println(info1);
                    }
                    
                }
                catch(NumberFormatException nfe) {
                    System.out.println("Error. Dit is geen getal");
                } catch (IOException ex) {
                    Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            /*System.out.println("We maken een eerste speler");
            dc.maakSpeler("Jan", 1990);
            String[] info = dc.geefInfoSpeler(); //geeft enkel output van laatst gecreëerde speler
            
            System.out.println("Speler succesvol aangemaakt.");
            for (String info1 : info) {
                System.out.println(info1);
            }
            
            System.out.println("We maken een tweede speler.");
            dc.maakSpeler("Piet", 1992);
            info = dc.geefInfoSpeler(); //geeft enkel output van laatst gecreëerde speler
            
            System.out.println("Speler succesvol aangemaakt.");
            for (String info1 : info) {
                System.out.println(info1);
            }*/
            
            System.out.println("\n\n\nWe starten een spel pazaak.");
            System.out.println("Hier zijn alle beschikbare spelers:");
           
            String[][] alleSpelers = dc.geefSpelerRepo();
            int i=1;
            for (String[] alleSpeler : alleSpelers) {
                System.out.println(Integer.toString(i) + ": " + alleSpeler[0] + " " +  alleSpeler[1] + " " +alleSpeler[2]);
                i++;
            }
            
            
            int[] ids = new int[2];
            br = new BufferedReader(new InputStreamReader(System.in));
            for(i=0; i<2; i++) {
                System.out.println("Typ in getal voor gebruiker" + (i+1) + ":");
                try {
                    ids[i] = Integer.parseInt(br.readLine()) - 1;
                }
                catch(NumberFormatException nfe) {
                    System.out.println("Error. Dit is geen getal");
                } catch (IOException ex) {
                    Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            String[][] deelnemers = dc.nieuwSpel(ids);
           
            System.out.println("\n\n\nEr is een spel pazaak aangemaakt met volgende spelers:");
            
            i=1;
            for (String[] deelnemer : deelnemers) {
                System.out.println(Integer.toString(i) + ": " + deelnemer[0] + " " +  deelnemer[1] + " " + deelnemer[2] + "\nHandkaarten:" + deelnemer[3] + "\n\n");
                i++;
            }
            
            
    }
}
