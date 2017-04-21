package domein;

import java.util.ArrayList;
import java.util.List;

public class Kaart {
    
    private int type; //-1 of +1 of 0 (indien)
    private int waarde; //altijd positief getal
    private String omschrijving; 
    private static List<Kaart> standaardStartStapel; //de standaard stapel moet niet telkens opnieuw gemaakt worden want is voor elk object hetzelfde. Vandaar static
    private static int AANTAL_SELECTIE_KAARTEN = 6;
   
    public Kaart(int type, int waarde) {
        
        this.type = type;
        this.waarde = waarde;
        String t;
       
        switch (type) {
            case 1:
                t = "+";
                break;
            case 0:
                t = "+-";
                break;
            default:
                t = "-";
                break;
        }
        this.omschrijving = t.concat(Integer.toString(waarde));
        
    }
    
    public Kaart(int type, int waarde, String omschrijving) {
        this.type = type;
        this.waarde = waarde;
        this.omschrijving = omschrijving;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWaarde() {
        return waarde;
    }

    public void setWaarde(int waarde) {
        this.waarde = waarde;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
    
    @Override
    public String toString() {
        //return "\nType:" + Integer.toString(type) + "\nWaarde:" + Integer.toString(waarde) + "\nOmschrijving" + omschrijving;
        return omschrijving + " kaart";
    }
    
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
    
    public static int geefAantalSelectieKaarten() {
        return AANTAL_SELECTIE_KAARTEN;
    }
}
