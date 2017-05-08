
package gui;

import domein.DomeinController;
import java.io.IOException;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class StartWedstrijdschermController extends GridPane {
    
    private final DomeinController dc;
    
    @FXML
    private Button btnSubmit;
    @FXML
    private ListView lstBeschikbaar;
    @FXML
    private ListView lstGeselecteerd;
    @FXML
    private Button btnVorige;
    @FXML
    private Button btnSelecteer;
    @FXML
    private Button btnDeselecteer;
    
   
    public StartWedstrijdschermController(DomeinController dc){
        this.dc = dc;
        
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StartWedstrijdscherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
            
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }  
        
        List<String> alleSpelers = dc.geefLijstSpelers();
        
        alleSpelers.forEach((item) -> { 
            System.out.println(item);
        });
       
        lstBeschikbaar.setItems(FXCollections.observableArrayList(alleSpelers));
        lstGeselecteerd.setItems(FXCollections.observableArrayList());
        
        
        
       
    } 
    
    @FXML
    private void maakWedstrijd(ActionEvent event) {
        
    }
    
    @FXML
    private void selecteerSpeler(ActionEvent event) {
       int index = lstBeschikbaar.getSelectionModel().getSelectedIndex();
       String naam = lstBeschikbaar.getItems().get(index).toString();
       
       lstGeselecteerd.getItems().add(naam);
       lstBeschikbaar.getItems().remove(index);
        
       
        
       
        
        
    }
    
    @FXML
    private void deselecteerSpeler(ActionEvent event) {
        int index = lstGeselecteerd.getSelectionModel().getSelectedIndex();
       String naam = lstGeselecteerd.getItems().get(index).toString();
       
       lstBeschikbaar.getItems().add(naam);
       lstGeselecteerd.getItems().remove(index);
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
