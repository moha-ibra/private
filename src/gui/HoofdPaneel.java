package gui;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HoofdPaneel extends BorderPane
{
    private final DomeinController controller;
    private final RegistratiePaneel registratie;

    public HoofdPaneel(DomeinController controller)
    {
        this.controller = controller;
        this.registratie = new RegistratiePaneel(controller, this);
        voegComponentenToe();
    }
    
    private final Label status = new Label();
    
    private void voegComponentenToe()
    {
        Text titel = new Text("Pazaak");
        titel.setId("titel");
        
        HBox titelBox = new HBox(titel);
        titelBox.setSpacing(10);
        titelBox.setPadding(new Insets(10));
        
        status.setText("Welkom bij Pazaak! Gelieve twee gebruikers te registreren.");
        status.setId("status");
        status.setMaxWidth(Double.MAX_VALUE);
        
        VBox hoofding = new VBox(titelBox, status);
        setTop(hoofding);
        
        setCenter(registratie);
        
    }
}
