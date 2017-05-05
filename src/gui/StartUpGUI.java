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
    public DomeinController domeinController = new DomeinController();
    public static Stage stage;
    
    @Override
    public void start(Stage stage) {
        
        StartUpGUI.stage = stage;
        showWelkomscherm(StartUpGUI.stage);
       
    }

    public static void main(String[] args) {
        Application.launch(StartUpGUI.class, args);
    }
    
    public void showWelkomscherm(Stage stage){
        Scene scene1 = new Scene(new WelkomschermController());
        
        
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
        Scene scene = new Scene(new StartPazaakschermController(domeinController));
  
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
