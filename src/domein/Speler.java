package domein;

import exceptions.VerplichtVeldException;
import exceptions.VerplichtGeenLeestekensException;
import exceptions.VerplichtPositiefGetalException;
import java.util.List;

public class Speler {
    
    private String naam;
    private int geboortejaar;
    private double krediet;
    private List<Kaart> wedstrijdStapel; //de 10 startkaarten die de Speler krijgt

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
    
    
    //deze moeten toch niet random? Of was dit gewoon als testmethode? Ik heb de tabel die staat in UC1 met de kaarten nu toegevoegd in Kaart.java
    public Kaart geefKaart() {
        int type = (int)(Math.random()*4+1);
        int waarde = (int)(Math.random()*13+1);
        Kaart kaart = new Kaart(type, waarde);
        return kaart;
    }
}
