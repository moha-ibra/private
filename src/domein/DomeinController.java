package domein;

public class DomeinController {
    
    private Speler speler;

    public DomeinController(){
        
    }
    
    public void maakSpeler(String naam, int geboortejaar) {
        speler = new Speler(naam, geboortejaar);
    }
    
    // Maak een array van speler info, en print in een zin
    public String[] geefInfoSpeler() {
        String naam = speler.getNaam();
        String jaar = Integer.toString(speler.getGeboortejaar());
        String krediet = Double.toString(speler.getKrediet());
        System.out.println(naam + ", geboren in " + jaar + ", heeft " + krediet + " krediet");
        String[] info = {naam, jaar, krediet};
        return info;
    }
    
    // Maak een array van <aantalKaarten> kaarten, en print type en waarde
    public Kaart[] geefKaarten() {
        int aantalKaarten = 10;
        Kaart[] kaarten = new Kaart[aantalKaarten];
        for(int i = 0; i < aantalKaarten; i++) {
            kaarten[i] = speler.geefKaart();
            System.out.println("Type: " + kaarten[i].getType() + ", Waarde: " + kaarten[i].getWaarde());
        }
        return kaarten;
    }
    
}