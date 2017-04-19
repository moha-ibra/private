package domein;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DomeinController {
    
    private Speler speler; //de geselecteerde speler
    private SpelerRepository spelerRepository; //alle spelers
    private Wedstrijd wedstrijd; //de wedstrijd
    private List<Speler> spelSpelers;

    public DomeinController(){
        spelerRepository = new SpelerRepository();
        wedstrijd = new Wedstrijd();
        spelSpelers = new ArrayList<>();
        Kaart.initialiseerStandaardStartStapel();
        
    }
    
    public void maakSpeler(String naam, int geboortejaar) {
        this.speler = new Speler(naam, geboortejaar);
        spelerRepository.voegToe(speler);
    }
    
    //maakt een lijst van de info van de actieve speler
    public List<String> geefInfoSpeler() {
        ArrayList<String> info = new ArrayList<String>();
        String naam = speler.getNaam();
        String jaar = Integer.toString(speler.getGeboortejaar());
        String krediet = speler.getKrediet().toPlainString();
     
        info.add(naam);
        info.add(jaar);
        info.add(krediet);
        
        
        //System.out.println(naam + ", geboren in " + jaar + ", heeft " + krediet + " krediet");
        
        return info;
    }
    
    public List<String> geefLijstSpelers() {
        List<Speler> alleSpelers = spelerRepository.geefLijstSpelers();
        ArrayList<String> info = new ArrayList<>();
        
        alleSpelers.forEach((item) -> {
            info.add(item.getNaam());
            info.add(Integer.toString(item.getGeboortejaar()));
        });
        
        return info;
      
    }
    
    public int geefMaximumAantalSpelers() {
        return wedstrijd.geefMaximumAantalSpelers();
    }
    
    public void selecteerSpeler(String naam) {
        this.speler = spelerRepository.geefSpelerMetNaam(naam);
      
        if(speler != null)
            spelSpelers.add(speler);
        else
            System.out.println("ERROR. De naam van de speler werd niet gevonden in de speler repository.");
    }
    
    public void nieuweWedstrijd() {
        this.wedstrijd.registreerWedstrijd(spelSpelers); 
    }
    
    public List<String> geefSpelersZonderWedstrijdStapel() {
        List<Speler> spelersZonderWedstrijdStapel = this.wedstrijd.geefSpelersZonderWedstrijdStapel();
       
        List<String> namenSpelersZonderWedstrijdStapel = new ArrayList<>();
        spelersZonderWedstrijdStapel.forEach((item) -> {
            namenSpelersZonderWedstrijdStapel.add(item.getNaam());
        });
        
        return namenSpelersZonderWedstrijdStapel;
        
    }
    public void selecteerActieveSpelerVoorWedstrijdStapel(String naam) {
        this.speler = this.spelerRepository.geefSpelerMetNaam(naam); 
    }
    public List<String> kaartenToevoegenActieveSpeler() {
        List<String> startStapel = new ArrayList<>();
        this.speler.geefNietGeselecteerdeKaarten().forEach((kaart) -> {
            startStapel.add(kaart.toString());
        });
        return startStapel;
    }
    
    public int geefAantalSelectieKaarten() {
        return Kaart.geefAantalSelectieKaarten();
    }
    
    public void selecteerKaartVoorActieveSpeler(int index) {
        Kaart k = this.speler.geefNietGeselecteerdeKaarten().get(index);
        this.speler.voegKaartToeAanWedstrijdStapel(k);
    }
    
    public void maakWedstrijdStapelVoorActieveSpeler() {
        this.speler.maakWedstrijdStapel();
        
    }
//    public String[] selecteerKaartenSpeler(String naam, int geboortedatum, int[] kaarten) {
//        Speler sp = spelerRepo.zoekSpeler(naam, geboortedatum);
//        sp.geefHandKaarten(kaarten);
//        
//        String[] info;
//        this.speler = sp;
//        info = geefInfoSpeler();
//        
//        return info;
//        
//    }
        
}
