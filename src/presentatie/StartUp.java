package presentatie;

import domein.DomeinController;

public class StartUp {
    
        public static void main(String[] args){
            DomeinController dc = new DomeinController();
            dc.maakSpeler("Jan", 1990);
            String[] info = dc.geefInfoSpeler();
            
            for (String info1 : info) {
                System.out.println(info1);
            }
            
            dc.geefKaarten();
    }
}
