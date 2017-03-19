package gui;

import domein.DomeinController;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

public class RegistratiePaneel extends GridPane
{
    private final DomeinController controller;
    private final HoofdPaneel hoofdPaneel;

    public RegistratiePaneel(DomeinController controller, HoofdPaneel hoofdPaneel)
    {
        this.controller = controller;
        this.hoofdPaneel = hoofdPaneel;
        
        configureerGrid();
        voegComponentenToe();
    }
    
    private void configureerGrid()
    {
        setPadding(new Insets(10));
        setHgap(10);
        setVgap(10);
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.RIGHT);
        
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHalignment(HPos.RIGHT);
        
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setHgrow(Priority.ALWAYS);
        
        getColumnConstraints().addAll(col1, col2, col3, col4);
    }
    
    private final TextField naam1 = new TextField();
    private final TextField jaar1 = new TextField();
    private final TextField naam2 = new TextField();
    private final TextField jaar2 = new TextField();
    private final Label foutbericht = new Label();
    
    private void voegComponentenToe()
    {
        Text header = new Text("Registratie");
        header.getStyleClass().add("hoofding");
        GridPane.setHalignment(header, HPos.LEFT);
        add(header, 0, 0, 4, 1);
        
        add(new Label("Gebruiker 1:"), 0, 1, 1, 1);
        add(naam1, 1, 1, 1, 1);
        add(new Label("Geboortejaar:"), 0, 2, 1, 1);
        add(jaar1, 1, 2, 1, 1);
        
        add(new Label("Gebruiker 2:"), 0, 3, 1, 1);
        add(naam2, 1, 3, 1, 1);
        add(new Label("Geboortejaar:"), 0, 4, 1, 1);
        add(jaar2, 1, 4, 1, 1);
                
        Button registreer = new Button("Registreer");
        registreer.setOnAction(this::registreer);
        registreer.setDefaultButton(true);
        foutbericht.getStyleClass().add("foutbericht");
        HBox controls = new HBox(registreer, foutbericht);
        controls.setSpacing(10);
        add(controls, 0, 6, 4, 1);
    }
    
    private void registreer(ActionEvent event) {
        controller.maakSpeler(
                naam1.getText().trim(),
                Integer.parseInt(jaar1.getText().trim())
        );
        controller.maakSpeler(
                naam2.getText().trim(), 
                Integer.parseInt(jaar2.getText().trim())
        );
    }
}
