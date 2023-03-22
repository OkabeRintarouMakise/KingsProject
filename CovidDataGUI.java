
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
    CovidDataLoader dateFetcher  = new CovidDataLoader();
    private HashSet<String> dateCollection = new HashSet<>();
    ArrayList<String> orderedDates = new ArrayList<String>();
    ComboBox from = new ComboBox();
    ComboBox to = new ComboBox();
    MapPanel panel2 = new MapPanel();
    StatisticsPanel panel3 = new StatisticsPanel();
    GraphPanel panel4 = new GraphPanel();
    private int counter = 0;
    BorderPane borderPane = new BorderPane();
    AnchorPane topAnchorPane = new AnchorPane();

    //ArrayList<Pane> panelCollection = new ArrayList<Panel>();
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

        this.stage = stage;

        dateFetcher.load();
        addSet();
        collectionLoader(from);
        collectionLoader(to);
        Label fromLabel = new Label("From");
        Label toLabel = new Label("To");

        from.setOnAction(this::dropDownBoxConditions);
        to.setOnAction(this::dropDownBoxConditions);

        
        //BorderPane borderPane = new BorderPane();
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_RIGHT);
        hbox.getChildren().add(fromLabel);
        hbox.getChildren().add(from);
        hbox.getChildren().add(toLabel);
        hbox.getChildren().add(to);

        AnchorPane bottomAnchorPane = new AnchorPane();
        borderPane.setTop(hbox);
        borderPane.setCenter(topAnchorPane);
        borderPane.setBottom(bottomAnchorPane);

        leftButton.setText("<");
        leftButton.setMaxWidth(Double.MAX_VALUE);

        rightButton.setText(">");
        rightButton.setMaxWidth(Double.MAX_VALUE);

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

    private void collectionLoader(ComboBox combo){
        for(String date: orderedDates){
            combo.getItems().add(date);
        }
    }

    private void addSet(){
        for(CovidData record : dateFetcher.getData()){
            dateCollection.add(record.getDate());
        }
        orderedDates.addAll(dateCollection);
        Collections.sort(orderedDates);
    }

    private void dateAddition (ComboBox combo)
    {

    }

    private void dropDownBoxConditions(Event e)
    {
        String currentFromValue = (String) from.getValue();
        String currentToValue = (String) to.getValue();
        if((currentFromValue != null) && (currentToValue != null) ){
            if(orderedDates.indexOf(currentFromValue) > orderedDates.indexOf(currentToValue)){
                dropDownError();
                return;
            }else{
                disableButtons(false);
            }

        }
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

    public String getFrom()
    {
        return (String) from.getValue();
    }

    public String getTo()
    {
        return (String) to.getValue();
    }

    private void rightButtonClick(ActionEvent event)
    {
        switch(counter){
            case 0:
                borderPane.setCenter(topAnchorPane);
            case 1:
                borderPane.setCenter(panel2.getMainPane());
            case 2:
                borderPane.setCenter(panel3.getMainPane());
            

        }

    
    }

}
