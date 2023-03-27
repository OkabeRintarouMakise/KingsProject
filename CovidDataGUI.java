
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.stage.Stage;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 * This class creates the main BorderPane and assigns the different panes within
 * for different purposes.
 * @author Adil Kassam,
 * @version (a version number or a date)
 */
public class CovidDataGUI extends Application
{
    private Stage stage;
    Button leftButton = new Button(); // Left Button is created
    Button rightButton = new Button();
    DateSelector dateSelector = new DateSelector(); // Object of DateSelector created to access DateSelector methods
    MapPanel panel2; // The different panes are defined here
    StatisticsPanel panel3;
    GraphPanel panel4;
    private int counter = 0; // Counter initialised
    BorderPane borderPane = new BorderPane(); // Main BorderPane is created
    VBox vBox = new VBox(); // Central VBox is created
    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage) throws FileNotFoundException
    {
        //The different panels are linked with dateSelector here
        panel2 = new MapPanel(dateSelector);
        panel3 = new StatisticsPanel(dateSelector);
        panel4 = new GraphPanel();


        this.stage = stage;
        
        //From and To ComboBoxes are loaded from DateSelector
        dateSelector.collectionLoader(dateSelector.getFrom());
        dateSelector.collectionLoader(dateSelector.getTo());
        
        //Creation of Drop Down Box Labels
        Label fromLabel = new Label("From : ");
        Label toLabel = new Label(" To : ");
        
        //Using DateSelector object, current from and to values are selected
        //from the class and linked to their conditions via setOnAction()
        dateSelector.getFrom().setOnAction(this::dropDownBoxConditions);
        dateSelector.getTo().setOnAction(this::dropDownBoxConditions);
        
        //Creation of HBox within BorderPane and assembly of Drop Down Boxes with
        //respective labels
        HBox hbox = new HBox();
        borderPane.setTop(hbox);
        hbox.setAlignment(Pos.TOP_RIGHT);
        hbox.getChildren().add(fromLabel);
        hbox.getChildren().add(dateSelector.getFrom());
        hbox.getChildren().add(toLabel);
        hbox.getChildren().add(dateSelector.getTo());

        
        
        
        //Locaiton specified
        borderPane.setCenter(vBox);
        //Specific ID set for detail in CSS
        vBox.setId("pane");
        //Initial labels created and assembled along with respective IDs
        Label welcomeLabel = new Label("Welcome");
        welcomeLabel.setId("firstlabel");
        Label rightPrompt = new Label("Hit the right button to see the Map");
        rightPrompt.setId("secondlabel");
        Label leftPrompt = new Label("Hit left to go back");
        leftPrompt.setId("thirdlabel");
        Label boxPrompt = new Label("Choose a date range to start");
        boxPrompt.setId("fourthlabel");
        vBox.getChildren().addAll( welcomeLabel, rightPrompt, leftPrompt, boxPrompt);
        
        //AnchorPane for buttons created and placed
        AnchorPane bottomAnchorPane = new AnchorPane();
        borderPane.setBottom(bottomAnchorPane);
        
        //Both buttons given "arrows" and linked to their respective methods
        leftButton.setText("<");
        leftButton.setMaxWidth(Double.MAX_VALUE);
        leftButton.setOnAction(this::leftButtonClick);

        rightButton.setText(">");
        rightButton.setMaxWidth(Double.MAX_VALUE);
        rightButton.setOnAction(this::rightButtonClick);
        
        //Buttons are initially unclickable until a correct date range is selected
        disableButtons(true);
        
        //Anchoring of buttons to their respective sides and assembly within AnchorPane
        AnchorPane.setLeftAnchor(leftButton, 0d);
        AnchorPane.setRightAnchor(rightButton, 0d);
        bottomAnchorPane.getChildren().addAll(leftButton, rightButton);

        // JavaFX must have a Scene (window content) inside a Stage (window)
        // Dimensions chosen for scene based on resolution of Welcome image
        Scene scene = new Scene(borderPane, 1024, 683);
        scene.getStylesheets().add("CovidDataGUI.css");        
        stage.setScene(scene);
        stage.setTitle("Welcome");
        // Show the Stage (window)
        stage.show();
    }
    
    /**
     * Set of Conditions to check the validity of the date range being entered. First checks
     * that both the selected From and To values are not null and are valid inputs.
     * If the selected From value is greater than the To value, a dialogue is displayed
     * to the user prompting them to change their input
     * @param e of type Event ensures that the programme recognises an event occurring when
     * the user selects a From and To value
     */
    private void dropDownBoxConditions(Event e)
    {
        String currentFromValue = (String) dateSelector.getFromValue();
        String currentToValue = (String) dateSelector.getToValue();
        if((currentFromValue != null) && (currentToValue != null) ){
            if(dateSelector.getDateList().indexOf(currentFromValue) > dateSelector.getDateList().indexOf(currentToValue)){
                dropDownError();
                return;
            }else{
                disableButtons(false);
            }

        }
        dateSelector.getRequiredDates();
        panel3.updateStats();
    }
    
    /**
     * The Dialogue that is displayed to the user indicating that they should change their
     * From value to be less that their To value
     */
    private void dropDownError()
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Display Condition Error");
        alert.setHeaderText(null);
        alert.setContentText("Your From selection is greater than your To selection");
        alert.showAndWait();
    }
    
    /**
     * Disables the left and right buttons unless a valid date range is entered
     * @param state of type boolean is initially set to True to make the default state
     * of the buttons disabled, but under correct conditions is set to False to make 
     * buttons clickable
     */
    private void disableButtons(boolean state)
    {
        leftButton.setDisable(state);
        rightButton.setDisable(state);
    }

    /**
     * Method called when the right button is clicked. counter initially set to 0 with the 
     * fields, but increases with each button click. Current counter value matches specific
     * panel. When the counter reaches the number of panels it loops back round.
     * @param event ...
     */
    private void rightButtonClick(ActionEvent event)
    {
        if(counter == 3){
            counter = 0;
        }
        else
        {
            counter++;
        }
        if(counter == 0){
            borderPane.setCenter(vBox);
        }
        else if(counter == 1){
            borderPane.setCenter(panel2.getMainPane());
        }
        else if(counter == 2){
            borderPane.setCenter(panel3.getMainPane());
        }
        else if(counter == 3){
            borderPane.setCenter(panel4.getMainPane());
        }
    }
    
    /**
     * Method called when the left button is clicked. counter initially set to 0 with the 
     * fields, but decreases with each button click. Current counter value matches specific
     * panel. When counter runs out of panels to go back to, it loops back round.
     * @param event ...
     */
    private void leftButtonClick(ActionEvent event)
    {
        if(counter == 0){
            counter = 3;
        }
        else
        {
            counter--;
        }
        if(counter == 0){
            borderPane.setCenter(vBox);
        }
        else if(counter == 1){
            borderPane.setCenter(panel2.getMainPane());
        }
        else if(counter == 2){
            borderPane.setCenter(panel3.getMainPane());
        }
        else if(counter == 3){
            borderPane.setCenter(panel4.getMainPane());

        }
    }

}
