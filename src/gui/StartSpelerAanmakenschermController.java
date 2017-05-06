/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;


public class StartSpelerAanmakenschermController extends GridPane {

    @FXML
    private Label lbTitel;
    @FXML
    private Label lbNaam;
    @FXML
    private Label lbGeboorte;
    @FXML
    private Label lbSpelerAangemaakt;
    @FXML
    private TextField txtNaam;
    @FXML
    private DatePicker pckrGeboortejaar;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnVorige;
    
    private final DomeinController dc;

    public StartSpelerAanmakenschermController(DomeinController dc){
        
        
       FXMLLoader loader = new FXMLLoader(getClass().getResource("StartSpelerAanmakenscherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.dc = dc;
        
    }
    
     @FXML
    private void maakSpeler(ActionEvent event) {
        
        LocalDate input = pckrGeboortejaar.getValue();
        String naam = txtNaam.getText();
        dc.maakSpeler(naam, input.getYear());
        
        List<String> info = dc.geefInfoSpeler();
        lbSpelerAangemaakt.setText(String.format("%s is succesvol aangemaakt. Krediet: %s", info.get(0), info.get(1)));
        lbSpelerAangemaakt.setVisible(true);
        
        
    }

    @FXML
    private void vorige(ActionEvent event) {
    }
    
   


}
