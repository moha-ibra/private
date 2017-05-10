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
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author timalenus
 */
public class WedstrijdSpeelSchermController extends GridPane {
    
    private final DomeinController dc;
    
    @FXML
    private Label lbSpeler;
    @FXML
    private Label lbScore;
    @FXML
    private ListView lstSpelbord;
    @FXML
    private Button btnBeeindigBeurt;
    @FXML
    private Button btnBevries;
    @FXML
    private Button btnWedstrijdkaart;
    @FXML
    private ListView lstWedstrijdstapel;
    @FXML
    private Button btnSelecteer;
    

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
        this.dc.startNieuweSet();
        this.toonWedstrijdSituatie();
    }
    
    private void toonWedstrijdSituatie() {
        //TODO debuggen
        String wedstrijdWinnaar = this.dc.geefWinnaar();
        System.out.println(wedstrijdWinnaar);
        String setWinnaar = this.dc.geefSetWinnaar();
        System.out.println(String.format("SetWinnaar: %s", setWinnaar));
        
        if(wedstrijdWinnaar != null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Wedstrijd gedaan");
            alert.setContentText(wedstrijdWinnaar);
            alert.showAndWait();
            
            Stage stage;
            stage = (Stage) lbSpeler.getScene().getWindow();
            Scene scene = new Scene(new StartPazaakschermController(this.dc));
            stage.setScene(scene);
            stage.show();    
        }
        else if(setWinnaar != null) {
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Set gedaan");
            alert.setContentText(String.format("Set gewonnen door %s", setWinnaar));
            alert.showAndWait();
            this.dc.startNieuweSet();
        }
        
        List<String> situatie = this.dc.toonWedstrijdSituatie2();
        System.out.println(this.dc.toonWedstrijdSituatie());
        lbSpeler.setText(String.format("Speler aan de beurt: %s", situatie.get(0)));
        lbScore.setText(String.format("Setscore: %s", situatie.get(1)));
        lstSpelbord.setItems(FXCollections.observableArrayList(situatie.subList(2, situatie.size())));
        lstSpelbord.setCellFactory(param -> new ListCell<String>() {
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
    private void beeindigBeurt(ActionEvent event) {
        lstWedstrijdstapel.setVisible(false);
        btnSelecteer.setVisible(false);
        this.dc.beeindigBeurtSpeler();
        this.toonWedstrijdSituatie();
        
    }
    @FXML
    private void bevriesSpelbord(ActionEvent event) {
        lstWedstrijdstapel.setVisible(false);
        btnSelecteer.setVisible(false);
        this.dc.bevriesSpelbordSpeler();
        this.toonWedstrijdSituatie();
    }
    @FXML
    private void speelWedstrijdkaart(ActionEvent event) {
        
        lstWedstrijdstapel.setVisible(true);
        btnSelecteer.setVisible(true);
        
        List<IKaart> wedstrijdstapel = this.dc.geefWedstrijdstapelSpelerAanBeurt();
        List<String> omschrijvingen = new ArrayList<>();
        wedstrijdstapel.forEach((kaart)->{
            omschrijvingen.add(kaart.toString());
        });
        lstWedstrijdstapel.setItems(FXCollections.observableArrayList(omschrijvingen));
        lstWedstrijdstapel.setCellFactory(param -> new ListCell<String>() {
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
    private void wedstrijdKaartNaarSpelbord(ActionEvent event) {
        List<IKaart> wedstrijdstapel = this.dc.geefWedstrijdstapelSpelerAanBeurt();
        int index = lstWedstrijdstapel.getSelectionModel().getSelectedIndex();
        IKaart kaart = wedstrijdstapel.get(index);
       
        if(kaart.getType() == 0) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("+- kaart");
            alert.setContentText("Kies of deze kaart positief of negatief moet zijn.");

            ButtonType buttonType1 = new ButtonType("+");
            ButtonType buttonType2 = new ButtonType("-");
            alert.getButtonTypes().setAll(buttonType1, buttonType2);
            
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == buttonType1) 
                this.dc.registreerWissel(kaart, 1); 
            else 
                this.dc.registreerWissel(kaart, -1);   
        }
        
        this.dc.speelWedstrijdstapelKaart(kaart);
        lstWedstrijdstapel.setVisible(false);
        btnSelecteer.setVisible(false);
        this.toonWedstrijdSituatie();
        
    }   
}
