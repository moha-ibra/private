/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import java.util.Locale;
import javafx.stage.Stage;

public class WelkomschermController extends GridPane{
    @FXML
    private Button btnNL;
    @FXML
    private Button btnEN;
    @FXML
    private Button btnFR;
    @FXML
    private TextField txtTest;

    private final DomeinController domeinController;
   

    public WelkomschermController() {  

        this.domeinController = new DomeinController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Welkomscherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void language(ActionEvent event) {
        
        //get de substring uit event met id van de actieve knop
        String sub= event.toString().substring(event.toString().indexOf("[id=btn") + 7);
        sub = sub.substring(0, sub.indexOf(","));
        Button button; 
        
        //txtTest.setText(sub);        
        
        switch (sub){
            case "NL": Locale.setDefault(new Locale("nl","NL"));
                        button = btnNL;
                        break;                
            case "FR": Locale.setDefault(new Locale("fr","FR"));
                        button = btnFR;
                        break;                     
            case "EN": Locale.setDefault(new Locale("en","US")); 
                        button = btnEN;
                        break;  
            default: Locale.setDefault(new Locale("nl","NL"));
                        button = btnNL;
                        break;
        }
       
            Stage stage;
            stage = (Stage) button.getScene().getWindow();
            
            Scene scene = new Scene(new StartPazaakschermController(domeinController));
        
            stage.setScene(scene);
            stage.show();
        
        
        
    } 
}
