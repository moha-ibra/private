package domein;

public class DomeinController {
    
    private Speler speler;
    private SpelerRepository spelerRepo;
    private Spel spel;

    public DomeinController(){
        spelerRepo = new SpelerRepository();
        Kaart.initialiseerStandaardStartStapel();
        
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
        String kaartenInfo = "";
        for(Kaart k : speler.geefKaarten()) {
            kaartenInfo = kaartenInfo.concat(k.toString());
        }
        
        //System.out.println(naam + ", geboren in " + jaar + ", heeft " + krediet + " krediet");
        String[] info = {naam, jaar, krediet, kaartenInfo};
        return info;
    }
    
    public String[][] geefSpelerRepo() {
        //deze methode geeft een tabel met alle info van alle spelers die momenteel in de repository zitten.
        //het is dus een uitgebreide versie van geefInfoSpeler
        String[][] info = new String[spelerRepo.geefRepo().size()][4];
        int i=0;
        
        for(Speler sp : spelerRepo.geefRepo()) {
            this.speler = sp;
            info[i] = this.geefInfoSpeler();
            i++;
        }
        return info;
    }
    
    //maakt een nieuw spel aan met de ids die verkregen zijn uit de input en returnt een tabel met de geregistreerde gebruikers
    public String[][] nieuwSpel(int[] spelerIDs) {
        spel = new Spel();
      
        //tabel waar we de info in zullen opslaan van de spelers die meedoen aan het spel
        String[][] info = new String[spel.geefWedstrijdAantal()][4];
        
        //registreer elke speler en steek de info over deze gebruiker in de tabel
        for(int i=0; i<spel.geefWedstrijdAantal(); i++) {
            Speler sp = spelerRepo.getSpelerMetID(spelerIDs[i]);
            spel.registreerSpelerInSpel(sp);
            this.speler = sp;
            info[i] = geefInfoSpeler();
        } 
        
        return info;
        
        
    }
    
    // Maak een array van <aantalKaarten> kaarten, en print type en waarde
    //Deze originele implementatie mag weg volgens de feedback van Malfait, niet? DC is niet verantwoordelijke voor dit?
   /* public Kaart[] geefKaarten() {
        int aantalKaarten = 10;
        Kaart[] kaarten = new Kaart[aantalKaarten];
        for(int i = 0; i < aantalKaarten; i++) {
            kaarten[i] = speler.geefKaart();
            System.out.println("Type: " + kaarten[i].getType() + ", Waarde: " + kaarten[i].getWaarde());
        }
        return kaarten;
    }*/
    
}
