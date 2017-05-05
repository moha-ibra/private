
package gui;

//import java.net.URL;
//import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import domein.DomeinController;
import java.io.IOException;
import java.util.Locale;

public class StartPazaakschermController extends GridPane {

    @FXML
    private Button btnNieuweSpeler;
    @FXML
    private Button btnNieuweWadstrijd;
    @FXML
    private Button btnLaadWedstrijd;
    @FXML
    private TextField txtStartKeuze;
    
    private final DomeinController dc;

    public StartPazaakschermController(DomeinController domeinController){
        
        this.dc = domeinController;
        
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("StartPazaakscherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try{
            loader.load();
        }catch(IOException ex){
            throw new RuntimeException(ex);
        }
    
    }


    @FXML
    private void startWedstrijdAanmaken(ActionEvent event) {
        //txtStartKeuze.setText("Start wedstrijd");
        
        
        
    }

    @FXML
    private void startLaadWedstrijd(ActionEvent event) {
        //txtStartKeuze.setText("Start wedstrijd");
        
        Stage stage;
            
            
        stage = (Stage) btnLaadWedstrijd.getScene().getWindow();
            
        Scene scene = new Scene(new StartLaadWedstrijdschermController());
        
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void startSpelerAanmaken(ActionEvent event) {
      
        Stage stage; 
        stage = (Stage) btnNieuweWadstrijd.getScene().getWindow();
        Scene scene = new Scene(new StartSpelerAanmakenschermController(this.dc));
        
        stage.setScene(scene);
        stage.show();
    }
 
}
