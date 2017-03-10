package domein;

public class DomeinController {
    
    private Speler speler;
    private SpelerRepository spelerRepo;

    public DomeinController(){
        spelerRepo = new SpelerRepository();
        
    }
    
    public void maakSpeler(String naam, int geboortejaar) {
        this.speler = new Speler(naam, geboortejaar);
        spelerRepo.voegToe(speler);
    }
    
    // Maak een array van speler info
    public String[] geefInfoSpeler() {
        String naam = speler.getNaam();
        String jaar = Integer.toString(speler.getGeboortejaar());
        String krediet = Double.toString(speler.getKrediet());
        //System.out.println(naam + ", geboren in " + jaar + ", heeft " + krediet + " krediet");
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
