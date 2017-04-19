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

    private List<Speler> geselecteerdeSpelers;
    
    public Wedstrijd() {
        this.geselecteerdeSpelers = new ArrayList<>();
    }
    
    public void registreerWedstrijd(List<Speler> spelers) {
        geselecteerdeSpelers.addAll(spelers);
    }
     
    public int geefMaximumAantalSpelers() {
        return WEDSTRIJD_AANTAL;
    }
    
    public List<Speler> geefSpelersZonderWedstrijdStapel() {
        List<Speler> spelersZonderWedstrijdStapel = new ArrayList<>();
       
        geselecteerdeSpelers.forEach((item) -> {
            if(item.geefWedstrijdStapel().isEmpty()) spelersZonderWedstrijdStapel.add(item);
        });
        
        return spelersZonderWedstrijdStapel;
    }
}
