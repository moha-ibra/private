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
    
}
