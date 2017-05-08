/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author timalenus
 */
public class WedstrijdSpeelSchermController extends GridPane {
    
    private final DomeinController dc;

    public WedstrijdSpeelSchermController(DomeinController dc) {
        this.dc = dc;
     
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WedstrijdSpeelScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
            
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }    
    }

    
    
}
