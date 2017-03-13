package presentatie;

import domein.DomeinController;


//In deze klasse komt de console implementatie van Pazaak.
public class StartUp {
    
        public static void main(String[] args){
            System.out.println("Welkom");
            DomeinController dc = new DomeinController();
            
            System.out.println("We maken een eerste speler");
            dc.maakSpeler("Jan", 1990);
            String[] info = dc.geefInfoSpeler(); //geeft enkel output van laatst gecreëerde speler
            
            System.out.println("Speler succesvol aangemaakt.");
            for (String info1 : info) {
                System.out.println(info1);
            }
            
            System.out.println("We maken een tweede speler.");
            dc.maakSpeler("Piet", 1992);
            info = dc.geefInfoSpeler(); //geeft enkel output van laatst gecreëerde speler
            
            System.out.println("Speler succesvol aangemaakt.");
            for (String info1 : info) {
                System.out.println(info1);
            }
            
            System.out.println("\n\n\nWe starten een spel pazaak.");
            System.out.println("Hier zijn alle beschikbare spelers:");
           
            String[][] alleSpelers = dc.geefSpelerRepo();
            int i=1;
            for (String[] alleSpeler : alleSpelers) {
                System.out.println(Integer.toString(i) + ": " + alleSpeler[0] + " " +  alleSpeler[1] + " " +alleSpeler[2]);
                i++;
            }
            
            //selectie via console moet nog worden toegevoegd. nu hard incoderen
            int[] ids = new int[2];
            ids[0] = 0; //Jan (id==0)
            ids[1] = 1; //Piet (id==1)
            String[][] deelnemers = dc.nieuwSpel(ids);
           
            System.out.println("\n\n\nEr is een spel pazaak aangemaakt met volgende spelers:");
            
            i=1;
            for (String[] deelnemer : deelnemers) {
                System.out.println(Integer.toString(i) + ": " + deelnemer[0] + " " +  deelnemer[1] + " " + deelnemer[2] + "\nHandkaarten:" + deelnemer[3] + "\n\n");
                i++;
            }
            
            
    }
}
