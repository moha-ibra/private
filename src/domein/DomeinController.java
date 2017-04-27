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
    

    public DomeinController(){
        spelerRepository = new SpelerRepository();
        wedstrijd = new Wedstrijd();
      
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
            this.wedstrijd.registreerWedstrijdSpeler(speler);
        else
            throw new SpelerNietGevondenException("Speler met naam " + naam + "kan niet gevonden worden.");
        
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
    
    public void selecteerKaartVoorActieveSpeler(IKaart ik) {
        Kaart k = this.wedstrijdspeler.geefStartstapelKaart(ik.getType(), ik.getWaarde());
        this.wedstrijdspeler.geefStartStapel().remove(k);
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
    
    public void startNieuweSet() {
        this.wedstrijd.maakSetStapel();
        this.wedstrijd.bepaalBeurt();
        this.wedstrijd.kaartSetStapelNaarSpelbord();
    }
    
    public String toonWedstrijdSituatie() {
       String situatie;
       this.wedstrijdspeler = this.wedstrijd.geefSpelerAanDeBeurt();
       this.speler = this.wedstrijdspeler;
       
       List<String> kaarten = new ArrayList<>();
       this.wedstrijdspeler.geefSpelbord().forEach((kaart)->{
           kaarten.add(kaart.toString());
       });
       
       String alleKaarten = String.join(" ,", kaarten);
       situatie = String.format("Speler aan beurt:%sScore:%d\n%s", this.wedstrijdspeler.getNaam(), this.wedstrijdspeler.geefSetScore(), alleKaarten);               
       return situatie;
        
    }
     
    public void beeindigBeurtSpeler() {
        this.wedstrijdspeler.beeindigBeurt();
        this.wedstrijd.bepaalBeurt();
    }
    
    public void bevriesSpelbordSpeler() {
        this.wedstrijdspeler.bevriesSpelbord(true);
        this.wedstrijdspeler.beeindigBeurt();
        this.wedstrijd.bepaalBeurt();
    }
    
    public List<IKaart> geefWedstrijdstapelSpelerAanBeurt() {
        return this.wedstrijdspeler.geefWedstrijdStapel();
    }
    
    public void speelWedstrijdstapelKaart(IKaart ik) {

        this.wedstrijdspeler.kaartOpSpelbord(ik);
        this.wedstrijdspeler.beeindigBeurt();
        this.wedstrijd.bepaalBeurt();
    }
    
    public void registreerWissel(int index, int type) {
        Kaart k = this.wedstrijdspeler.geefWedstrijdstapelKaart(index, type);
        k.setType(type);
    }
    
    public String geefSetWinnaar() {
        String winnaarNaam = this.wedstrijd.isSetGedaan();
        
        if(winnaarNaam != null) return winnaarNaam;
        
        return null;
    }
     
    
    
    
    
}
