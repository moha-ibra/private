package domein;

import exceptions.VerplichtVeldException;
import exceptions.VerplichtGeenLeestekensException;
import exceptions.VerplichtPositiefGetalException;
import java.util.ArrayList;
import java.util.List;

public class Speler {
    
    private String naam;
    private int geboortejaar;
    private double krediet;
    private List<Kaart> wedstrijdStapel; //om te beginnen de 10 startkaarten die de Speler krijgt
  

    public Speler(String naam, int geboortejaar) {
        setNaam(naam);
        setGeboortejaar(geboortejaar);
        wedstrijdStapel = Kaart.geefKopieVanStandaardStartStapel(); //niet zeker of dit nu hier moet gebeuren of niet. In UC1 staat beschreven van wel, maar dan in UC3 vermelden ze nog eens het initialiseren van de wedstrijdstapel.
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if (naam == null || naam.length() == 0) {
            throw new VerplichtVeldException("Elke speler heeft verplicht een naam.");
        }
        if (!naam.matches("[A-z]*")) {
            throw new VerplichtGeenLeestekensException("Naam mag geen leestekens bevatten.");
        }

        this.naam = naam;
    }

    public int getGeboortejaar() {
        return geboortejaar;
    }

    public void setGeboortejaar(int geboortejaar) {
        if (geboortejaar < 0) {
            throw new VerplichtPositiefGetalException("Geboortejaar mag niet negatief zijn.");
        }
        this.geboortejaar = geboortejaar;
    }

    public double getKrediet() {
        return krediet;
    }

    public void setKrediet(double krediet) {
        this.krediet = krediet;
    }
    
    public List<Kaart> geefKaarten() {
        return wedstrijdStapel;
    }
    
    public void geefHandKaarten(int[] zesKaarten) {
        //de speler kiest 6 kaarten van de 10 die hij wil behouden. Hiervan worden er 4 at random gekozen. 
        List<Kaart> wedstrijdStapel_temp = new ArrayList<>();
        
        for(int i=0; i<zesKaarten.length; i++) {
            wedstrijdStapel_temp.add(wedstrijdStapel.get(zesKaarten[i])); //we maken een tijdelijke lijst van de 6 kaarten
        }
        wedstrijdStapel = new ArrayList<>(wedstrijdStapel_temp); //nieuwe wedstrijdstapel is gelijk aan de 6 kaarten
        
        //we verwijderen at random 2 kaarten om met 4 kaarten over te schieten.
        for(int i=0; i<2; i++) {
            int index = (int) (Math.random()*wedstrijdStapel.size());
            wedstrijdStapel.remove(index);
        }
    }
   
    
    //deze moeten toch niet random? Of was dit gewoon als testmethode? Ik heb de tabel die staat in UC1 met de kaarten nu toegevoegd in Kaart.java
    public Kaart geefKaart() {
        int type = (int)(Math.random()*4+1);
        int waarde = (int)(Math.random()*13+1);
        Kaart kaart = new Kaart(type, waarde);
        return kaart;
    }
}
