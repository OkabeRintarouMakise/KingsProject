

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

import javafx.scene.control.*;

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
    //private HashSet<String> dateCollection = new HashSet<>();
    ComboBox from = new ComboBox();
    ComboBox to = new ComboBox();

    
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
        collectionLoader(from);
        collectionLoader(to);
        

        

        
        
        BorderPane borderPane = new BorderPane();
        HBox hbox = new HBox();
        
        hbox.getChildren().addAll(from, to);
        
        AnchorPane topAnchorPane = new AnchorPane();
        AnchorPane bottomAnchorPane = new AnchorPane();
        
        borderPane.setTop(hbox);
        borderPane.setCenter(topAnchorPane);
        borderPane.setBottom(bottomAnchorPane);
        
        leftButton.setText("<");
        leftButton.setMaxWidth(Double.MAX_VALUE);
        
        rightButton.setText(">");
        rightButton.setMaxWidth(Double.MAX_VALUE);
        
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
        for(CovidData record : dateFetcher.getData()){
            combo.getItems().add(record.getDate());
        }
        
    }
    
    
    private void setUpMenu(Pane parent){
        
        
    
    
    }

    
}
