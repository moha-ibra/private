package domein;

public class Kaart {
    
    private int type;
    private int waarde;
    private String omschrijving;

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
