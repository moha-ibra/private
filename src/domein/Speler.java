package domein;

public class Speler {
    
    private String naam;
    private int geboortejaar;
    private double krediet;

    public Speler(String naam, int geboortejaar) {
        this.naam = naam;
        this.geboortejaar = geboortejaar;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getGeboortejaar() {
        return geboortejaar;
    }

    public void setGeboortejaar(int geboortejaar) {
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
