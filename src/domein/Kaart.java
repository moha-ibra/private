package domein;

import java.util.List;

public class Kaart {
    
    private int type;
    private int waarde;
    private String omschrijving;
    private static List<Kaart> standaardStartStapel; //de standaard stapel moet niet telkens opnieuw gemaakt worden want is voor elk object hetzelfde. Vandaar static

    public Kaart(int type, int waarde) {
        this.type = type;
        this.waarde = waarde;
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
}
