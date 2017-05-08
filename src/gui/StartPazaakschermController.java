
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
import java.util.ResourceBundle;

public class StartPazaakschermController extends GridPane {

    @FXML
    private Button btnNieuweSpeler;
    
    @FXML
    private Button btnLaadWedstrijd;
        
    @FXML
    private Button btnNieuweWedstrijd;
    
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
        
        Locale currentLocale = Locale.getDefault();                       
        ResourceBundle messages;
        messages = ResourceBundle.getBundle("resources.UiBundle", currentLocale);
        
        btnNieuweSpeler.setText(messages.getString("menu1"));
        btnNieuweWedstrijd.setText(messages.getString("menu2"));
        btnLaadWedstrijd.setText(messages.getString("menu3"));
    
    }


    @FXML
    private void startWedstrijdAanmaken(ActionEvent event) {
         Stage stage;
            
            
        stage = (Stage) btnNieuweWedstrijd.getScene().getWindow();
            
        Scene scene = new Scene(new StartWedstrijdschermController(this.dc));
        
        stage.setScene(scene);
        stage.show();
        
        
        
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
        stage = (Stage) btnNieuweWedstrijd.getScene().getWindow();
        Scene scene = new Scene(new StartSpelerAanmakenschermController(this.dc));
        
        stage.setScene(scene);
        stage.show();
    }
 
}
