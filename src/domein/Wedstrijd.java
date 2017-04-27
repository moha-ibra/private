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
    private WedstrijdSpeler setWinnaar = null;
    private List<Kaart> setStapel; //40 kaarten 
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
    
    public void registreerWedstrijdSpeler(Speler speler) {
        
        spelSpelers.add(new WedstrijdSpeler(speler.getNaam(), speler.getGeboortejaar(), speler.getKrediet()));
      
        
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
     
    public WedstrijdSpeler geefWinnaar() {
       
        for(WedstrijdSpeler speler : spelSpelers) {
            if(speler.geefAantalGewonnenSets() == 3) return speler;
        }
        
        return null;
        
    }
    
    public void resetSetVariabelen() {
        this.setWinnaar = null;
        this.beurt = 0;
        
        this.spelSpelers.forEach((speler) -> {
            speler.resetSetVariabelen();
        });
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
            int aantalSpelbordenBevroren = 0;
            do {
                int index = spelSpelers.indexOf(spelerAanBeurt);
                int volgende = (index + 1) % WEDSTRIJD_AANTAL;
                spelerAanBeurt = spelSpelers.get(volgende);
                
            } while(spelerAanBeurt.isSpelbordBevroren());
        }
        spelerAanBeurt.startBeurt();
        this.kaartSetStapelNaarSpelbord();
        beurt++;
    }
    
    public void kaartSetStapelNaarSpelbord() {
        //bovenste kaart van de setstapel naar het spelbord van de speler aan de beurt
        spelerAanBeurt.kaartOpSpelbord(setStapel.get(0));
        setStapel.remove(0);
    }
    
    public WedstrijdSpeler geefSpelerAanDeBeurt() {
        return this.spelerAanBeurt;
    }
    
    public String isSetGedaan() {
        int aantalBevrorenSpelborden = 0;
        
        if(this.setWinnaar != null) return setWinnaar.getNaam();
        
        for(WedstrijdSpeler speler : spelSpelers) {
            
            if(speler.geefSetScore() > 20) {
                aantalSetsGespeeld++;
                return bepaalWinnaar();
            }
            else if(speler.geefSpelbord().size() == 9) {
                aantalSetsGespeeld++;
                return bepaalWinnaar();
            }
            else if(speler.isSpelbordBevroren()) aantalBevrorenSpelborden++;
            
            if(aantalBevrorenSpelborden == WEDSTRIJD_AANTAL) {
                aantalSetsGespeeld++;
                return bepaalWinnaar();
            }
               
        }
        
        return null; //set is nog niet gedaan
    }
    
    private String bepaalWinnaar() {
        WedstrijdSpeler winnaar = spelSpelers.get(0);
        boolean gelijkspel = false;
        
        //elke speler 1x met elkaar vergelijken
        for(int i=0; i<WEDSTRIJD_AANTAL; i++) {
            for(int j=i+1; j<WEDSTRIJD_AANTAL; j++) {
                WedstrijdSpeler s1 = spelSpelers.get(i);
                WedstrijdSpeler s2 = spelSpelers.get(j);
                
                if(s1.geefSetScore() <=20 && s1.geefSpelbord().size()==9) winnaar = s1; //<=20 en spelbord vol voor s1
                else if(s2.geefSetScore() <=20 && s2.geefSpelbord().size()==9) winnaar = s2; //<=20 en spelbord vol voor s2
                else if(s1.geefSetScore() > 20 && s2.geefSetScore() <= 20) winnaar = s2; //s1 bust en s2 <=20
                else if(s1.geefSetScore() <=20 && s2.geefSetScore() > 20) winnaar = s1; //s2 bust en s1 <=20
                else if(s1.geefSetScore() > 20 && s2.geefSetScore() > 20) gelijkspel = true; //beide bust
                else if(s1.geefSetScore() > s2.geefSetScore()) winnaar = s1; //beide niet bust en geen spelbord vol, s1 hoogste score
                else if(s1.geefSetScore() < s2.geefSetScore()) winnaar = s2; //beide niet bust en geen spelbord vol, s2 hoogste score
                else if(s1.geefSetScore() == s2.geefSetScore()) gelijkspel = true; //gelijke scores
                else gelijkspel = true;
                
            }
        }
        
        if(!gelijkspel) {
            winnaar.setGewonnen();
            this.setWinnaar = winnaar;
            return winnaar.getNaam();
        }
        else return "Gelijkspel!";
       
        
        
    } 
}
