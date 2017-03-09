package domein;

import exceptions.VerplichtVeldException;
import exceptions.VerplichtGeenLeestekensException;
import exceptions.VerplichtPositiefGetalException;

public class Speler {
    
    private String naam;
    private int geboortejaar;
    private double krediet;

    public Speler(String naam, int geboortejaar) {
        setNaam(naam);
        setGeboortejaar(geboortejaar);
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
    
    public Kaart geefKaart() {
        int type = (int)(Math.random()*4+1);
        int waarde = (int)(Math.random()*13+1);
        Kaart kaart = new Kaart(type, waarde);
        return kaart;
    }
}
