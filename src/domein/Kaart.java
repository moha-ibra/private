package domein;

public class Kaart implements IKaart {
    
    private int type; //-1 of +1 of 0 (indien)
    private int waarde; //altijd positief getal
    private String omschrijving; 
    
   
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
    
   
}
