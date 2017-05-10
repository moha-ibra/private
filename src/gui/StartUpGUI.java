/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author timalenus
 */
public class StartUpGUI extends Application {
    private final DomeinController dc = new DomeinController();
    public static Stage stage;
    
    private static final Image plus2 = new Image("http://i.imgur.com/kZU1muH.png");
    private static final Image plus4 = new Image("http://i.imgur.com/lsUeMFk.png");
    private static final Image plus5 = new Image("http://i.imgur.com/X8r33Q2.png");
    private static final Image plus6 = new Image("http://i.imgur.com/SE72HKE.png");
    private static final Image plusmin1 = new Image("http://i.imgur.com/WRb0LGs.png");
    private static final Image plusmin3 = new Image("http://i.imgur.com/rReTkgd.png");
    private static final Image min1 = new Image("http://i.imgur.com/NysZLoV.png");
    private static final Image min2 = new Image("http://i.imgur.com/dHDCiOE.png");
    private static final Image min3 = new Image("http://i.imgur.com/l7yT7WR.png");
    private static final Image min5 = new Image("http://i.imgur.com/8cgT1rV.png");
    private static final Image plus1 = new Image("http://i.imgur.com/cEWVvHz.png");
    private static final Image plus3 = new Image("http://i.imgur.com/tYPlHRz.png");
    private static final Image plus7 = new Image("http://i.imgur.com/KXFbRyu.png");
    private static final Image plus8 = new Image("http://i.imgur.com/2Ytnd9F.png");
    private static final Image plus9 = new Image("http://i.imgur.com/fqfSEI9.png");
    private static final Image plus10 = new Image("http://i.imgur.com/P0cW59g.png");
   
    @Override
    public void start(Stage stage) {
        this.dc.maakSpeler("Louise", 1998);
        this.dc.maakSpeler("Bram", 1995);
        this.dc.maakSpeler("Lien", 1997);
        this.dc.maakSpeler("Roel", 2001);
        this.dc.maakSpeler("Tim", 1994);
        this.dc.maakSpeler("Lotje", 1996);
        System.out.println("Enkele spelers aangemaakt.");
        StartUpGUI.stage = stage;
        showWelkomscherm(StartUpGUI.stage);
       
    }

    public static void main(String[] args) {
        
        Application.launch(StartUpGUI.class, args);
        
    }
    
    public void showWelkomscherm(Stage stage){
        Scene scene1 = new Scene(new WelkomschermController(this.dc));
        
        
        stage.setScene(scene1);
        stage.setTitle("Pazaak G85");

        // The stage will not get smaller than its preferred (initial) size.
        stage.setOnShown(e -> {
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        });
        
        
        stage.show();
        
    }
    
    public void showStartPazaakscherm(Stage stage){
        
        Scene scene = new Scene(new StartPazaakschermController(this.dc));
  
        stage.setScene(scene);
        //stage.setTitle("Eerste voorbeeld");

        // The stage will not get smaller than its preferred (initial) size.
        stage.setOnShown(e -> {
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        });

        stage.show();
    }
    
    public static ImageView geefKaartAfbeelding(String name) {
        ImageView imageView = new ImageView();
        switch (name) {
            case "+2 kaart":
                imageView.setImage(plus2);
                break;
            case "+4 kaart":
                imageView.setImage(plus4);
                break;
            case "+5 kaart":
                imageView.setImage(plus5);
                break;
            case "+6 kaart":
                imageView.setImage(plus6);
                break;
            case "+-1 kaart":
                imageView.setImage(plusmin1);
                break;
            case "+-3 kaart":
                imageView.setImage(plusmin3);
                break;
            case "-1 kaart":
                imageView.setImage(min1);
                break;
            case "-2 kaart":
                imageView.setImage(min2);
                break;
            case "-3 kaart":
                imageView.setImage(min3);
                break;
            case "-5 kaart":
                imageView.setImage(min5);
                break;
            case "+1 kaart":
                imageView.setImage(plus1);
                break;
            case "+3 kaart":
                imageView.setImage(plus3);
                break;
            case "+7 kaart":
                imageView.setImage(plus7);
                break;
            case "+8 kaart":
                imageView.setImage(plus8);
                break;
            case "+9 kaart":
                imageView.setImage(plus9);
                break;
            case "+10 kaart":
                imageView.setImage(plus10);
                break;
            default:
                imageView.setImage(plus1);
                break;
        }
        
        return imageView;
    }
}
