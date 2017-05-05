
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.TextFlow;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class StartLaadWedstrijdschermController extends GridPane {

    
    @FXML
    private Label lbMelding;

    public StartLaadWedstrijdschermController(){
    
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("StartLaadWedstrijdscherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try{
            loader.load();
        }catch(IOException ex){
            throw new RuntimeException(ex);
        }
        
        lbMelding.setText("Laad Wedstrijd: nog in constructie");
    }
    
}
