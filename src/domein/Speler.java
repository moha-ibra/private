package domein;

import exceptions.VerplichtVeldException;
import exceptions.VerplichtGeenLeestekensException;
import exceptions.VerplichtPositiefGetalException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Speler {
    
    private String naam;
    private int geboortejaar;
    private BigDecimal krediet;
    private List<Kaart> wedstrijdStapel =  new ArrayList<>();
  

    public Speler(String naam, int geboortejaar) {
        setNaam(naam);
        setGeboortejaar(geboortejaar);
        krediet = new BigDecimal(0);
        
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

    public BigDecimal getKrediet() {
        return krediet;
    }
    
   
    public void setKrediet(double krediet) {
        this.krediet.add(new BigDecimal(krediet));
    }
    
    public List<Kaart> geefWedstrijdStapel() {
        return wedstrijdStapel;
    }
    
    public List<Kaart> geefNietGeselecteerdeKaarten() {
        return Kaart.geefKopieVanStandaardStartStapel();
    }
    
    public void voegKaartToeAanWedstrijdStapel(Kaart k) {
        this.wedstrijdStapel.add(k);
    }
    
    public void maakWedstrijdStapel() {
        //er worden er 4 at random geselecteerd uit de geselecteerde kaarten (6). Dit is hetzelfde als er 2 verwijderen.
        
        int aantalTeVerwijderen = wedstrijdStapel.size()-4;
       
        for(int i=0; i<aantalTeVerwijderen; i++) {
            int rand = (int) (Math.random()*wedstrijdStapel.size());
            this.wedstrijdStapel.remove(rand);
        }
        
        
        //deze output is enkel voor debuggen. is niet nodig volgens use case om de uiteindelijke wedstrijdstapel te printen
        System.out.println("De wedstrijdstapel voor " + this.getNaam());
        wedstrijdStapel.forEach((kaart)->{
            System.out.println(kaart.toString());
        });
        
    }
}
