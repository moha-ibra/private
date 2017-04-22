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

    private List<Speler> spelSpelers;
    private int[] gewonnenSetsPerSpeler;
     
    public Wedstrijd() {
        this.spelSpelers = new ArrayList<>();
        this.gewonnenSetsPerSpeler = new int[WEDSTRIJD_AANTAL];
        
        this.gewonnenSetsPerSpeler[0] = 3;
        this.gewonnenSetsPerSpeler[1] = 1;
    }
    
    public Speler geefSpelerMetNaam(String naam) {
        for(Speler sp : spelSpelers) {
            if(sp.getNaam().equals(naam)) return sp;
        }
        return null;   
    }
    
    public void registreerWedstrijd(List<Speler> spelers) {
        spelSpelers.addAll(spelers);
    }
     
    public int geefMaximumAantalSpelers() {
        return WEDSTRIJD_AANTAL;
    }
    
    private void verhoogGewonnenSetsSpeler(Speler speler) {
        int index = spelSpelers.indexOf(speler);
        gewonnenSetsPerSpeler[index]++;
    }
    
    public List<Speler> geefSpelersZonderWedstrijdStapel() {
        List<Speler> spelersZonderWedstrijdStapel = new ArrayList<>();
       
        spelSpelers.forEach((item) -> {
            if(item.geefWedstrijdStapel().isEmpty()) spelersZonderWedstrijdStapel.add(item);
        });
        
        return spelersZonderWedstrijdStapel;
    }
    
    public int isWedstrijdGedaan() {
        for(int i=0; i<gewonnenSetsPerSpeler.length; i++) {
            if(gewonnenSetsPerSpeler[i]==3) return i;
        }
        
        return -1; 
    }
    
    public Speler geefWinnaar() {
        int index = this.isWedstrijdGedaan();
        return spelSpelers.get(index);
        
    }
}
