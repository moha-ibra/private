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
    private int[] gewonnenSets;
    private Speler winnaar;
    
    public Wedstrijd() {
        this.spelSpelers = new ArrayList<>();
        this.gewonnenSets = new int[WEDSTRIJD_AANTAL];
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
        gewonnenSets[index]++;
    }
    
    public List<Speler> geefSpelersZonderWedstrijdStapel() {
        List<Speler> spelersZonderWedstrijdStapel = new ArrayList<>();
       
        spelSpelers.forEach((item) -> {
            if(item.geefWedstrijdStapel().isEmpty()) spelersZonderWedstrijdStapel.add(item);
        });
        
        return spelersZonderWedstrijdStapel;
    }
}
