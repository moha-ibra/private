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
            //geef de speler een aantal handkaarten. Zie ook UC4. Dit staat gelijk aan een aantal kaarten verwijderen uit de spelers wedstrijdstapel
            //we geven de speler nu 4 handkaarten == 6 kaarten verwijderen uit de startstapel
            speler.geefHandKaarten();    
        }
    }  
    
    public List<Speler> geefGeregistreerdeSpelers() {
       return geregistreerdeSpelers;
    }
    
    public int geefWedstrijdAantal() {
        return WEDSTRIJD_AANTAL;
    }
}
