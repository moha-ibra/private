package presentatie;

import domein.DomeinController;

public class StartUp {
    
        public static void main(String[] args){
            DomeinController dc = new DomeinController();
            dc.maakSpeler("Jan", 1990);
            dc.geefInfoSpeler();
            dc.geefKaarten();
    }
}
