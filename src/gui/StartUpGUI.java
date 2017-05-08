/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author timalenus
 */
public class StartUpGUI extends Application {
    private final DomeinController dc = new DomeinController();
    public static Stage stage;
    
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
}
