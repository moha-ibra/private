package domein;

//import persistentie.SpelerMapper;
import exceptions.SpelerNietGevondenException;
import java.util.ArrayList;
import java.util.List;


public class SpelerRepository {

    //private final SpelerMapper mapper;
    private List<Speler> spelers;

    public SpelerRepository() {
        //mapper = new SpelerMapper();
        spelers = new ArrayList<>();
        //bij het aanmaken van de repo moeten de spelers die in de database zitten nog automatisch worden ingeladen.
    }
    
    public void voegToe(Speler speler) {
       
       spelers.add(speler);
    }
    
    public List<Speler> geefLijstSpelers() {
        return spelers;
    }
    
    public Speler geefSpelerMetNaam(String naam) {
        for(Speler sp : spelers) {
            if(sp.getNaam().equals(naam)) { 
                return sp;
            }  
        }
        throw new SpelerNietGevondenException("ERROR. Speler niet gevonden."); 
    }
}
