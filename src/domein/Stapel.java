/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author timalenus
 */
public class Stapel {
    
    private static List<Kaart> standaardStartStapel; //de standaard stapel moet niet telkens opnieuw gemaakt worden want is voor elk object hetzelfde. Vandaar static
    private static List<Kaart> standaardSetStapel; //de standaard setstapel. 4x10 kaart van 1 tot 10. 
    private static int AANTAL_SELECTIE_KAARTEN = 6;
    
     public static void initialiseerStandaardStartStapel() {
        //de standaard startstapel
        //we houden hier een statische kopie van bij want voor elk object is deze hetzelfde
        
        standaardStartStapel = new ArrayList<>();
        standaardStartStapel.add(new Kaart(1,2, "+2"));
        standaardStartStapel.add(new Kaart(1,4, "+4"));
        standaardStartStapel.add(new Kaart(1,5, "+5"));
        standaardStartStapel.add(new Kaart(1,6, "+6"));
        standaardStartStapel.add(new Kaart(0,1, "+-1"));
        standaardStartStapel.add(new Kaart(0,3, "+-3"));
        standaardStartStapel.add(new Kaart(-1,1, "-1"));
        standaardStartStapel.add(new Kaart(-1,2, "-2"));
        standaardStartStapel.add(new Kaart(-1,3, "-3"));
        standaardStartStapel.add(new Kaart(-1,5, "-5"));
        
    }
    
    public static List<Kaart> geefKopieVanStandaardStartStapel() {
        return new ArrayList<>(standaardStartStapel);
    }
    
    public static void initialiseerStandaardSetStapel() {
        standaardSetStapel = new ArrayList<>();
        
        for(int i=0; i<4; i++) {
            for(int j=1; j<=10; j++) {
                standaardSetStapel.add(new Kaart(1, j));
            }
        }
    }
    
    public static List<Kaart> geefKopieVanStandaardSetStapel() {
        return new ArrayList<>(standaardSetStapel);
    }
    
    public static int geefAantalSelectieKaarten() {
        return AANTAL_SELECTIE_KAARTEN;
    }
    
}
