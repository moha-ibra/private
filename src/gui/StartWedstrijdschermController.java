
package gui;

import domein.DomeinController;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
    @FXML
    private Label lbTitel;
    @FXML 
    private Label lbBeschikbaar;
    @FXML
    private Label lbGeselecteerd;
    
   
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
        lbTitel.setText(String.format("Selecteer %d spelers", dc.geefMaximumAantalSpelers()));
        lbBeschikbaar.setText(String.format("Beschikbare spelers: %d", alleSpelers.size()));
        lstBeschikbaar.setItems(FXCollections.observableArrayList(alleSpelers));
        lstGeselecteerd.setItems(FXCollections.observableArrayList()); 
    } 
    
    @FXML
    private void selecteerSpeler(ActionEvent event) {
       int index = lstBeschikbaar.getSelectionModel().getSelectedIndex();
       String naam = lstBeschikbaar.getItems().get(index).toString();
       
       lstGeselecteerd.getItems().add(naam);
       lstBeschikbaar.getItems().remove(index);  
       updateTellers();
    }
    
    @FXML
    private void deselecteerSpeler(ActionEvent event) {
        int index = lstGeselecteerd.getSelectionModel().getSelectedIndex();
       String naam = lstGeselecteerd.getItems().get(index).toString();
       
       lstBeschikbaar.getItems().add(naam);
       lstGeselecteerd.getItems().remove(index);
       updateTellers();
    }
    
    private void updateTellers() {
        lbBeschikbaar.setText(String.format("Beschikbare spelers: %d", lstBeschikbaar.getItems().size()));
        lbGeselecteerd.setText(String.format("Geselecteerde spelers: %d", lstGeselecteerd.getItems().size()));
    }
    
    @FXML
    private void maakWedstrijd(ActionEvent event) {
        Alert alert;
        int max = dc.geefMaximumAantalSpelers();
        
        if(lstGeselecteerd.getItems().size() == max) {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Bevestig wedstrijd?");
            StringBuilder spelers = new StringBuilder();
            lstGeselecteerd.getItems().forEach((naam)-> {
                spelers.append(naam.toString());
                spelers.append("\n");
            });
            alert.setContentText(String.format("Wedstrijd aanmaken met de geselecteerde spelers? %s", spelers));
            
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
                lstGeselecteerd.getItems().forEach((speler)-> {
                    dc.selecteerSpeler(speler.toString());
                });  
                
                Stage stage;
                stage = (Stage) btnVorige.getScene().getWindow();
                Scene scene = new Scene(new SpelersZonderWedstrijdstapelSchermController(this.dc));
                stage.setScene(scene);
                stage.show();   
            }
        } 
        else {
            alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Selecteer juiste aantal spelers.");
            alert.setContentText(String.format("Selecteer %d spelers om de wedstrijd te bevestigen.", max));
            alert.showAndWait();
        }
        
        
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
