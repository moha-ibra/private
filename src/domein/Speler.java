package domein;

import exceptions.NaamTekortException;
import exceptions.TeOudOfTeJongException;
import exceptions.VerplichtVeldException;
import exceptions.VerplichtGeenLeestekensException;
import exceptions.VerplichtPositiefGetalException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Speler {
    
    private String naam;
    private int geboortejaar;
    private BigDecimal krediet;
    private final List<Kaart> startStapel = new ArrayList<>();
    
   
    public Speler(String naam, int geboortejaar) {
        setNaam(naam);
        setGeboortejaar(geboortejaar);
        krediet = BigDecimal.ZERO;
        startStapel.addAll(Kaart.geefKopieVanStandaardStartStapel());
        
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        
        if (naam == null || naam.length() == 0) {
            throw new VerplichtVeldException("Elke speler heeft verplicht een naam.");
        }
        else if(naam.length() < 3 ) {
            throw new NaamTekortException("De naam moet verplicht 3 leestekens lang zijn.");
        }
        else if (!naam.matches("[A-z]*")) {
            throw new VerplichtGeenLeestekensException("Naam mag geen leestekens bevatten.");
        }

        this.naam = naam;
    }

    public int getGeboortejaar() {
        return geboortejaar;
    }

    public void setGeboortejaar(int geboortejaar) {
        int huidigJaar = Calendar.getInstance().get(Calendar.YEAR);
        
        if (geboortejaar < 0) {
            throw new VerplichtPositiefGetalException("Geboortejaar mag niet negatief zijn.");
        }
        else if((huidigJaar - geboortejaar) < 6 || (huidigJaar - geboortejaar > 99))
            throw new TeOudOfTeJongException("Het spel mag enkel gespeeld worden door spelers tussen de 6-99 jaar oud.");
       
        this.geboortejaar = geboortejaar;
    }

    public BigDecimal getKrediet() {
        return krediet;
    }
    
   
    public void voegKredietToe(double delta) {
        this.krediet = this.krediet.add(new BigDecimal(delta));
        
    }
    
    protected Kaart zoekKaart(List<Kaart> lijst, int type, int waarde) {
         for(Kaart k : lijst) {
            if(k.getType() == type && k.getWaarde() == waarde) return k;
        }
        
        return null; //kaart bestaat niet in de lijst
    }
    
    public Kaart geefStartstapelKaart(int type, int waarde) {
        return this.zoekKaart(this.startStapel, type, waarde);
    }
    public void verwijderStartstapelKaart(int type, int waarde) {
        this.startStapel.remove(this.zoekKaart(this.startStapel, type, waarde));
    }
    
    public List<IKaart> geefStartStapel() {
        List<IKaart> stapel = new ArrayList<>();
        stapel.addAll(this.startStapel);
        return stapel;
    }
    
    
}
