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
public class Spel {
    private static final int WEDSTRIJD_AANTAL = 2;

    private List<Speler> geregistreerdeSpelers;
    
    public Spel() {
        geregistreerdeSpelers = new ArrayList<>();
 
    }
    
    
    public void registreerSpelerInSpel(Speler speler) {
        if(geregistreerdeSpelers.size() != WEDSTRIJD_AANTAL) {
            geregistreerdeSpelers.add(speler);
        }
    }  
    
    public List<Speler> geefGeregistreerdeSpelers() {
       return geregistreerdeSpelers;
    }
}
