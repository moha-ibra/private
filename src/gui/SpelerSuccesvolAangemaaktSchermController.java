/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author timalenus
 *
 */

public class SpelerSuccesvolAangemaaktSchermController extends GridPane {
    
    private final DomeinController dc;
    
    @FXML
    private Label lbNaam;
    @FXML
    private Label lbKrediet;
    @FXML
    private TextArea txtStartstapel;
    @FXML
    private Button btnOK;
    @FXML
    private Label lbLabelNaam;
    @FXML
    private Label lbLabelKrediet;
    @FXML
    private ListView lstStartstapel;
    
    public SpelerSuccesvolAangemaaktSchermController(DomeinController dc) {
        this.dc = dc;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SpelerSuccesvolAangemaaktScherm.fxml"));
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
        
        lbLabelNaam.setText(messages.getString("lbNaam"));
        lbLabelKrediet.setText(messages.getString("lbKrediet"));
        
        List<String> info = dc.geefInfoSpeler();
        lbNaam.setText(info.get(0));
        lbKrediet.setText(info.get(1));
        
        lstStartstapel.setItems(FXCollections.observableArrayList(dc.geefKaarten()));    
        lstStartstapel.setCellFactory(param -> new ListCell<String>() {
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if(empty) {
                    setText(null);
                    setGraphic(null);
                }
                else {  
                    setText(null);
                    setGraphic(StartUpGUI.geefKaartAfbeelding(name));
                }
            }
        });
    }
    
    @FXML
    private void terugNaarPazaakScherm(ActionEvent event) {
        Stage stage;
        stage = (Stage) btnOK.getScene().getWindow(); 
        Scene scene = new Scene(new StartPazaakschermController(this.dc));
        stage.setScene(scene);
        stage.show();
    }

      
    
}
