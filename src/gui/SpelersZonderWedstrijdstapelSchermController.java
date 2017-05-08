/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import domein.DomeinController;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author timalenus
 */
public class SpelersZonderWedstrijdstapelSchermController extends GridPane {
    
    private final DomeinController dc;
    
    @FXML
    private Button btnWedstrijdstapel;
    @FXML
    private ListView lstSpelers;
   
    

    public SpelersZonderWedstrijdstapelSchermController(DomeinController dc) {
        
        this.dc = dc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SpelersZonderWedstrijdstapelScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
            
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }  
        
        lstSpelers.setItems(FXCollections.observableArrayList(dc.geefSpelersZonderWedstrijdStapel()));     
    }
    
    @FXML
    private void geefStapel(ActionEvent event) {
    
        String speler = lstSpelers.getSelectionModel().getSelectedItem().toString();
        this.dc.selecteerActieveSpelerVoorWedstrijdStapel(speler);
        
        Stage stage;
        stage = (Stage) btnWedstrijdstapel.getScene().getWindow();
        Scene scene = new Scene(new SelecteerWedstrijdstapelSchermController(this.dc, speler));
        stage.setScene(scene);
        stage.show();
    }

    
}
