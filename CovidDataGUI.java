
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

/**
 * Write a description of JavaFX class CovidDataGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CovidDataGUI extends Application
{
    private Stage stage;
    Button leftButton = new Button();
    Button rightButton = new Button();
    DateSelector dateSelector = new DateSelector();
    MapPanel panel2;
    StatisticsPanel panel3;
    GraphPanel panel4 = new GraphPanel();
    private int counter = 0;
    BorderPane borderPane = new BorderPane();
    AnchorPane topAnchorPane = new AnchorPane();
    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage)
    {

        panel2 = new MapPanel(dateSelector);
        panel3 = new StatisticsPanel(dateSelector);

        this.stage = stage;

        dateSelector.collectionLoader(dateSelector.getFrom());
        dateSelector.collectionLoader(dateSelector.getTo());

        Label fromLabel = new Label("From");
        Label toLabel = new Label("To");

        dateSelector.getFrom().setOnAction(this::dropDownBoxConditions);
        dateSelector.getTo().setOnAction(this::dropDownBoxConditions);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_RIGHT);
        hbox.getChildren().add(fromLabel);
        hbox.getChildren().add(dateSelector.getFrom());
        hbox.getChildren().add(toLabel);
        hbox.getChildren().add(dateSelector.getTo());

        AnchorPane bottomAnchorPane = new AnchorPane();
        borderPane.setTop(hbox);
        borderPane.setCenter(topAnchorPane);
        borderPane.setBottom(bottomAnchorPane);

        leftButton.setText("<");
        leftButton.setMaxWidth(Double.MAX_VALUE);
        leftButton.setOnAction(this::leftButtonClick);

        rightButton.setText(">");
        rightButton.setMaxWidth(Double.MAX_VALUE);
        rightButton.setOnAction(this::rightButtonClick);

        disableButtons(true);

        AnchorPane.setLeftAnchor(leftButton, 0d);
        AnchorPane.setRightAnchor(rightButton, 0d);
        bottomAnchorPane.getChildren().addAll(leftButton, rightButton);

        

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(borderPane, 550, 400);
        stage.setScene(scene);
        stage.setTitle("Welcome");
        // Show the Stage (window)
        stage.show();
    }

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

    private void dropDownError()
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Display Condition Error");
        alert.setHeaderText(null);
        alert.setContentText("Your From selection is greater than your To selection");
        alert.showAndWait();
    }

    private void disableButtons(boolean state)
    {
        leftButton.setDisable(state);
        rightButton.setDisable(state);
    }


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
            borderPane.setCenter(topAnchorPane);
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
            borderPane.setCenter(topAnchorPane);
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
