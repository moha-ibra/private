package domein;

import exceptions.SpelerBestaatAlException;
import exceptions.SpelerNietGevondenException;
import java.util.ArrayList;
import java.util.List;

public class DomeinController {
    
    private Speler speler; //de geselecteerde speler
    private WedstrijdSpeler wedstrijdspeler; //de geselecteerde wedstrijdspeler
    private final SpelerRepository spelerRepository; //alle spelers
    private final Wedstrijd wedstrijd; //de wedstrijd
    private final List<Speler> spelSpelers; //de geselecteerde spelerS 

    public DomeinController(){
        spelerRepository = new SpelerRepository();
        wedstrijd = new Wedstrijd();
        spelSpelers = new ArrayList<>();
        Kaart.initialiseerStandaardStartStapel();
        Kaart.initialiseerStandaardSetStapel();
        
    }
    
    public void maakSpeler(String naam, int geboortejaar) {
        this.speler = new Speler(naam, geboortejaar);
        if(spelerRepository.geefSpelerMetNaam(naam) != null) {
            throw new SpelerBestaatAlException("Speler met naam " + naam + " bestaat reeds.");
        }
        spelerRepository.voegToe(speler);
    }
    
    //maakt een lijst van de info van de actieve speler
    public List<String> geefInfoSpeler() {
        ArrayList<String> info = new ArrayList<>();
        
        String naam = speler.getNaam();
        String krediet = speler.getKrediet().toPlainString();
     
        info.add(naam);
        info.add(krediet);
        return info;
    }
    
    public List<String> geefKaarten() {
        List<String> startStapel = new ArrayList<>();
        this.speler.geefStartStapel().forEach((kaart) -> {
            startStapel.add(kaart.toString());
        });
        
        return startStapel;
    }
    
    public List<String> geefLijstSpelers() {
        List<Speler> alleSpelers = spelerRepository.geefLijstSpelers();
        ArrayList<String> info = new ArrayList<>();
        
        alleSpelers.forEach((item) -> {
            info.add(item.getNaam());
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
            throw new SpelerNietGevondenException("Speler met naam " + naam + "kan niet gevonden worden.");
        
    }
    
    public void nieuweWedstrijd() {
        this.wedstrijd.registreerWedstrijd(spelSpelers); 
    }
    
    public List<String> geefSpelersZonderWedstrijdStapel() {
        List<WedstrijdSpeler> spelersZonderWedstrijdStapel = this.wedstrijd.geefSpelersZonderWedstrijdStapel();
       
        List<String> namenSpelersZonderWedstrijdStapel = new ArrayList<>();
        spelersZonderWedstrijdStapel.forEach((item) -> {
            namenSpelersZonderWedstrijdStapel.add(item.getNaam());
        });
        
        return namenSpelersZonderWedstrijdStapel;
        
    }
    public void selecteerActieveSpelerVoorWedstrijdStapel(String naam) {
        this.wedstrijdspeler = this.wedstrijd.geefSpelerMetNaam(naam);
        this.speler = this.wedstrijdspeler; 
        
    }
    public List<String> kaartenToevoegenActieveSpeler() {
        List<String> startStapel = new ArrayList<>();
        this.speler.geefStartStapel().forEach((kaart) -> {
            startStapel.add(kaart.toString());
        });
        return startStapel;
    }
    
    public int geefAantalSelectieKaarten() {
        return Kaart.geefAantalSelectieKaarten();
    }
    
    public void selecteerKaartVoorActieveSpeler(int index) {
        Kaart k = this.wedstrijdspeler.geefStartStapel().get(index);
        this.wedstrijdspeler.geefStartStapel().remove(index);
        this.wedstrijdspeler.voegKaartToeAanWedstrijdStapel(k);
    }
    
    public void maakWedstrijdStapelVoorActieveSpeler() {
        this.wedstrijdspeler.maakWedstrijdStapel();
        
    }
    
    public List<String> geefWinnaar() {
        if(this.wedstrijd.isWedstrijdGedaan() == -1) return null;
        
        List<String> winnaarInfo = new ArrayList<>();
        WedstrijdSpeler winnaar = this.wedstrijd.geefWinnaar();
        Speler winnaarInRepo = this.spelerRepository.geefSpelerMetNaam(winnaar.getNaam());
        winnaarInRepo.voegKredietToe(5);
        winnaarInfo.add(winnaarInRepo.getNaam());
        winnaarInfo.add(winnaarInRepo.getKrediet().toPlainString());
        
        return winnaarInfo;
    }
}
