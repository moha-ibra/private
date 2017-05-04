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
      
        Stapel.initialiseerStandaardStartStapel();
        Stapel.initialiseerStandaardSetStapel();
        
    }
    
    public void maakSpeler(String naam, int geboortejaar) {
        this.speler = new Speler(naam, geboortejaar);
        if(spelerRepository.geefSpelerMetNaam(naam) != null) {
            throw new SpelerBestaatAlException(String.format("Speler met naam %s bestaat reeds.", naam));
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
      
        if(speler == null)
            throw new SpelerNietGevondenException(String.format("De speler met naam %s kan niet gevonden worden.", naam));
        
        this.wedstrijd.registreerWedstrijdSpeler(speler);
       
            
        
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
    public List<IKaart> kaartenToevoegenActieveSpeler() {
        return this.speler.geefStartStapel();
        
    }
    
    public int geefAantalSelectieKaarten() {
        return Stapel.geefAantalSelectieKaarten();
    }
    
    public void selecteerKaartVoorActieveSpeler(IKaart ik) {
        this.wedstrijdspeler.voegKaartToeAanWedstrijdStapel(ik);
        this.wedstrijdspeler.verwijderStartstapelKaart(ik.getType(), ik.getWaarde());
        
    }
    
    public void maakWedstrijdStapelVoorActieveSpeler() {
        this.wedstrijdspeler.maakWedstrijdStapel();
        
    }
    
    public String geefWinnaar() {
        WedstrijdSpeler winnaar = this.wedstrijd.geefWinnaar();
        if(winnaar == null) return null;
        
        String winnaarInfo;
        Speler winnaarInRepo = this.spelerRepository.geefSpelerMetNaam(winnaar.getNaam());
        winnaarInRepo.voegKredietToe(5);
        winnaarInfo = String.format("Winnaar:%sKrediet:%s", winnaarInRepo.getNaam(), winnaarInRepo.getKrediet().toPlainString());
        return winnaarInfo;
    }
    
    public void startNieuweSet() {
        this.wedstrijd.startNieuweSet();
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
       situatie = String.format("Speler aan beurt:%s Score:%d\n%s", this.wedstrijdspeler.getNaam(), this.wedstrijdspeler.geefSetScore(), alleKaarten);               
       return situatie;
        
    }
     
    public void beeindigBeurtSpeler() {
        this.wedstrijdspeler.beeindigBeurt();
        if(this.wedstrijd.isSetGedaan() == null)
            this.wedstrijd.bepaalBeurt();
    }
    
    public void bevriesSpelbordSpeler() {
        this.wedstrijdspeler.bevriesSpelbord(true);
        this.wedstrijdspeler.beeindigBeurt();
        if(this.wedstrijd.isSetGedaan() == null)
            this.wedstrijd.bepaalBeurt();
    }
    
    public List<IKaart> geefWedstrijdstapelSpelerAanBeurt() {
        return this.wedstrijdspeler.geefWedstrijdStapel();
    }
    
    public void speelWedstrijdstapelKaart(IKaart ik) {
        Kaart k = this.wedstrijdspeler.geefWedstrijdstapelKaart(ik.getType(), ik.getWaarde());
        this.wedstrijdspeler.kaartOpSpelbord(k);
        this.wedstrijdspeler.verwijderWedstrijdstapelKaart(k);
        this.wedstrijdspeler.beeindigBeurt();
        if(this.wedstrijd.isSetGedaan() == null)
            this.wedstrijd.bepaalBeurt();
    }
    
    public void registreerWissel(IKaart ik, int nieuwType) {
        Kaart k = this.wedstrijdspeler.geefWedstrijdstapelKaart(ik.getType(), ik.getWaarde());
        k.setType(nieuwType);
    }
    
    public String geefSetWinnaar() {
        String winnaarNaam;
        winnaarNaam = this.wedstrijd.isSetGedaan();
        
        if(winnaarNaam != null) return winnaarNaam;
        return null;
    }  
}
