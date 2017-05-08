/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.IKaart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author timalenus
 */
public class SelecteerWedstrijdstapelSchermController extends GridPane {
    
    private final DomeinController dc;
    private final List<IKaart> kaarten;
    
    @FXML
    private Label lbTitel;
    @FXML
    private Button btnBeneden;
    @FXML
    private Button btnOmhoog;
    @FXML
    private Button btnSubmit;
    @FXML
    private ListView lstStartstapel;
    @FXML
    private ListView lstWedstrijdstapel;
    @FXML 
    private Label lbResterend;
    

    public SelecteerWedstrijdstapelSchermController(DomeinController dc, String speler) {
        this.dc = dc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelecteerWedstrijdstapelScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
            
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }  
        
        int aantal = this.dc.geefAantalSelectieKaarten();
        lbTitel.setText(String.format("Selecteer %d kaarten voor %s", aantal, speler));
        lbResterend.setText(String.format("Selecteer nog %d kaarten", aantal));
        this.kaarten = dc.kaartenToevoegenActieveSpeler();
        List<String> omschrijvingen = new ArrayList<>();
        kaarten.forEach((kaart)->{
            omschrijvingen.add(kaart.toString());
        });
        lstStartstapel.setItems(FXCollections.observableArrayList(omschrijvingen));
        lstWedstrijdstapel.setItems(FXCollections.observableArrayList());
        
        
    }
    
    @FXML
    private void selecteer(ActionEvent event) {
        
        int index = lstStartstapel.getSelectionModel().getSelectedIndex();
        String kaart = lstStartstapel.getItems().get(index).toString();
        lstWedstrijdstapel.getItems().add(kaart);
        lstStartstapel.getItems().remove(index);
        int rest = dc.geefAantalSelectieKaarten() - lstWedstrijdstapel.getItems().size();
        lbResterend.setText(String.format("Selecteer nog %d kaarten",rest));
        
    }
    
    @FXML 
    private void deselecteer(ActionEvent event) {
        int index = lstWedstrijdstapel.getSelectionModel().getSelectedIndex();
        String kaart = lstWedstrijdstapel.getItems().get(index).toString();
        lstStartstapel.getItems().add(kaart);
        lstWedstrijdstapel.getItems().remove(index);
        int rest = dc.geefAantalSelectieKaarten() - lstWedstrijdstapel.getItems().size();
        lbResterend.setText(String.format("Selecteer nog %d kaarten",rest));
    }
    
    @FXML
    private void maakWedstrijdstapel(ActionEvent event) {
        int aantal = this.dc.geefAantalSelectieKaarten();
      
        if(lstWedstrijdstapel.getItems().size() != aantal) {
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Selecteer juiste aantal kaarten.");
            alert.setContentText(String.format("Selecteer %d kaarten om een wedstrijdstapel te maken.", aantal));
            alert.showAndWait();
        }
        else {
        
            lstWedstrijdstapel.getItems().forEach((omschrijving)-> {
                dc.selecteerKaartVoorActieveSpeler(this.geefInterface(omschrijving.toString()));
            });

            Stage stage;
            Scene scene;
            stage = (Stage) btnSubmit.getScene().getWindow();
            if(dc.geefSpelersZonderWedstrijdStapel().isEmpty()) 
                scene = new Scene(new WedstrijdSpeelSchermController(this.dc));
            else
                scene = new Scene(new SpelersZonderWedstrijdstapelSchermController(this.dc));

            stage.setScene(scene);
            stage.show();  
        }
    }  
    
    private IKaart geefInterface(String omschrijving) {
        IKaart result = null;
        for(IKaart ik : this.kaarten) {
            if(ik.toString().equals(omschrijving)) result = ik;
        }
        
        return result;
    }
}
