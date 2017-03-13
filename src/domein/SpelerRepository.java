package domein;

//import persistentie.SpelerMapper;
import java.util.ArrayList;
import java.util.List;


public class SpelerRepository {

    //private final SpelerMapper mapper;
    private List<Speler> spelers;

    public SpelerRepository() {
        //mapper = new SpelerMapper();
        spelers = new ArrayList<>();
    }
    
    public void voegToe(Speler speler) {
       spelers.add(speler);
    }
    
    public List<Speler> geefRepo() {
        return spelers;
    }
    
    public Speler getSpelerMetID(int id) {
        return spelers.get(id);
    }
    
    public Speler zoekSpeler(String naam, int geboortedatum) {
        for(Speler sp : spelers) {
            if(sp.getNaam().equals(naam) && sp.getGeboortejaar() == geboortedatum) { 
                return sp;
            }  
        }
        return null; //niet gevonden    
    }
}
