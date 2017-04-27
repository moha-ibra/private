/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author timalenus
 */
public class WedstrijdSpeler extends Speler {
    
    private List<Kaart> wedstrijdStapel =  new ArrayList<>();
    private List<Kaart> spelBord = new ArrayList<>(9); //spelbord is 9 kaarten groot
    private int setScore = 0;
    private int aantalGewonnenSets = 0;
    private boolean aanBeurt = false;
    private boolean spelBordBevroren = false;
    
    public WedstrijdSpeler(String naam, int geboortejaar, BigDecimal krediet) {
        super(naam, geboortejaar);
        super.voegKredietToe(krediet.doubleValue());
    }
    
    public void resetSetVariabelen() {
        this.spelBord.clear();
        this.setScore = 0;
        this.aanBeurt = false;
        this.spelBordBevroren = false;
    }
    
    public List<IKaart> geefWedstrijdStapel() {
        List<IKaart> stapel = new ArrayList<>();
        stapel.addAll(this.wedstrijdStapel);
        return stapel;
    }
      
    public void voegKaartToeAanWedstrijdStapel(IKaart ik) {
        this.wedstrijdStapel.add(this.geefStartstapelKaart(ik.getType(), ik.getWaarde()));
    }
    
    public void maakWedstrijdStapel() {
        Collections.shuffle(wedstrijdStapel);
        wedstrijdStapel.subList(4, wedstrijdStapel.size()).clear();     
    }  
    
    public int geefSetScore() {
        return this.setScore;
    }
    
    public void verhoogSetScore(int delta) {
        this.setScore += delta;
    }
    
    public boolean isSpelerAanBeurt() {
        return this.aanBeurt;
    }
    
    public void beeindigBeurt() {
        this.aanBeurt = false;
    }
    
    public void startBeurt() {
        this.aanBeurt = true;
    }
    
    public Kaart geefWedstrijdstapelKaart(int type, int waarde) {
        return this.zoekKaart(this.wedstrijdStapel, type, waarde);
    }
    
    public void verwijderWedstrijdstapelKaart(Kaart k) {
        this.wedstrijdStapel.remove(k);
    }
    
    public void kaartOpSpelbord(Kaart k) {
        
        this.spelBord.add(k);
        
        //bereken setscore
        this.setScore = 0;
        spelBord.forEach((kaart)->{
            int type = kaart.getType();
           
            switch (type) {
            case -1:
                this.setScore -= kaart.getWaarde();
                break;     
            default:
                this.setScore += kaart.getWaarde();
                break;
             }
        }); 
    }
    
    public boolean isSpelbordBevroren() {
        return this.spelBordBevroren;
    }
    
    public void bevriesSpelbord(boolean b) {
        this.spelBordBevroren = b;
    }
    
    public List<IKaart> geefSpelbord() {
        List<IKaart> bord = new ArrayList<>();
        bord.addAll(this.spelBord);
        return bord;
    }
    
    public int geefAantalGewonnenSets() {
        return this.aantalGewonnenSets;
    }
    
    public void setGewonnen() {
        this.aantalGewonnenSets++;
    }
}
