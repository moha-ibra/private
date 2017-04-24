/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author timalenus
 */
public class Wedstrijd {
    private static final int WEDSTRIJD_AANTAL = 2;

    private List<WedstrijdSpeler> spelSpelers;
    private WedstrijdSpeler spelerAanBeurt;
    private List<List<Kaart>> spelBord;
    private int aantalSetsGespeeld = 0;
     
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
}
