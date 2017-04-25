/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author timalenus
 */
public class Wedstrijd {
    private static final int WEDSTRIJD_AANTAL = 2;

    private final List<WedstrijdSpeler> spelSpelers;
    private WedstrijdSpeler spelerAanBeurt;
    private List<Kaart> setStapel;
    private int aantalSetsGespeeld = 0;
    private int beurt = 0;
     
    public Wedstrijd() {
        this.spelSpelers = new ArrayList<>();
    }
    
    public WedstrijdSpeler geefSpelerMetNaam(String naam) {
        for(WedstrijdSpeler sp : spelSpelers) {
            if(sp.getNaam().equals(naam)) return sp;
        }
        return null;   
    }
    
    public void registreerWedstrijd(List<Speler> spelers) {
        spelers.forEach((speler) -> {
            spelSpelers.add(new WedstrijdSpeler(speler.getNaam(), speler.getGeboortejaar(), speler.getKrediet()));
        }) ;
        
    }
     
    public int geefMaximumAantalSpelers() {
        return WEDSTRIJD_AANTAL;
    }
     
    public List<WedstrijdSpeler> geefSpelersZonderWedstrijdStapel() {
        List<WedstrijdSpeler> spelersZonderWedstrijdStapel = new ArrayList<>();
       
        spelSpelers.forEach((item) -> {
            if(item.geefWedstrijdStapel().isEmpty()) spelersZonderWedstrijdStapel.add(item);
        });
        
        return spelersZonderWedstrijdStapel;
    }
    
    public int isWedstrijdGedaan() {
        int index = -1;
        for(WedstrijdSpeler speler : spelSpelers) {
            if(speler.geefAantalGewonnenSets() == 3) index = spelSpelers.indexOf(speler);
        }
       
        return index; 
    }
    
    public WedstrijdSpeler geefWinnaar() {
        int index = this.isWedstrijdGedaan();
        return spelSpelers.get(index);
        
    }
    
    public void maakSetStapel() {
        this.setStapel = Kaart.geefKopieVanStandaardSetStapel();
        this.schudSetStapel();
    }
    
    private void schudSetStapel() {
        Collections.shuffle(this.setStapel);
    }
    
    public void bepaalBeurt() {
        //eerste beurt oudste speler aan beurt
        if(beurt==0) {
            spelerAanBeurt = spelSpelers.get(0);
            for(WedstrijdSpeler speler : spelSpelers) {
                if(speler.getGeboortejaar() < spelerAanBeurt.getGeboortejaar())
                    spelerAanBeurt = speler;
            }
        }
        else {
            int index = spelSpelers.indexOf(spelerAanBeurt);
            int volgende = (index + 1) % WEDSTRIJD_AANTAL;
            spelerAanBeurt = spelSpelers.get(volgende);
        }
        spelerAanBeurt.startBeurt();
        beurt++;
    }
    
    public void kaartSetStapelNaarSpelbord() {
        //bovenste kaart van de setstapel naar het spelbord van de speler aan de beurt
        spelerAanBeurt.kaartOpSpelbord(setStapel.get(0));
        setStapel.remove(0);
    }
    
    public List<String> toonWedstrijdSituatie() {
        List<String> situatie = new ArrayList<>();
        
        situatie.add("Speler aan de beurt: " + spelerAanBeurt.getNaam());
        situatie.add("Setscore: " + Integer.toString(spelerAanBeurt.geefSetScore()));
        situatie.add("Spelbord:");
        spelerAanBeurt.geefSpelbord().forEach((kaart)->{
            situatie.add(kaart.getOmschrijving());
        });
        
        return situatie;
        
    }
   
}
