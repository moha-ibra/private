/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.ResourceBundle;


public class StartSpelerAanmakenschermController extends GridPane {

    @FXML
    private Label lbTitel;
    @FXML
    private Label lbNaam;
    @FXML
    private Label lbGeboorte;
    
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
        
        this.dc = dc;
       FXMLLoader loader = new FXMLLoader(getClass().getResource("StartSpelerAanmakenscherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
       
        Locale currentLocale = Locale.getDefault();                       
        ResourceBundle messages;
        messages = ResourceBundle.getBundle("resources.UiBundle", currentLocale);
        
        lbTitel.setText(messages.getString("lbNewPlayerTitel"));
        lbNaam.setText(messages.getString("lbNaam"));
        lbGeboorte.setText(messages.getString("lbGeboorte"));
        btnSubmit.setText(messages.getString("btnSubmit"));
        btnVorige.setText(messages.getString("bntVorige"));
        
        
    }
    
     @FXML
    private void maakSpeler(ActionEvent event) {
        
        LocalDate input = pckrGeboortejaar.getValue();
        String naam = txtNaam.getText();
        System.out.println(String.format("Naam %s, geboortejaar: %d", naam, input.getYear()));
        dc.maakSpeler(naam, input.getYear());
        
        Stage stage; 
        stage = (Stage) btnSubmit.getScene().getWindow();
        Scene scene = new Scene(new SpelerSuccesvolAangemaaktSchermController(this.dc));
        
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void vorige(ActionEvent event) {
        Stage stage;
            stage = (Stage) btnVorige.getScene().getWindow();
            
            Scene scene = new Scene(new StartPazaakschermController(this.dc));
        
            stage.setScene(scene);
            stage.show();
    }
    
   


}
