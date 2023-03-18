

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
        
        BorderPane borderPane = new BorderPane();
        MenuBar menu = new MenuBar();
        HBox hbox = new HBox();
        AnchorPane anchorPane = new AnchorPane();
        
        borderPane.setTop(menu);
        borderPane.setCenter(hbox);
        borderPane.setBottom(anchorPane);
        
        leftButton.setText("<");
        leftButton.setMaxWidth(Double.MAX_VALUE);
        
        rightButton.setText(">");
        rightButton.setMaxWidth(Double.MAX_VALUE);
        
        
        
        
        AnchorPane.setLeftAnchor(leftButton, 0d);
        AnchorPane.setRightAnchor(rightButton, 0d);
        
        anchorPane.getChildren().addAll(leftButton, rightButton);
        
        
        
        
        
        
        
        
        

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(borderPane, 550, 400);
        stage.setScene(scene);
        stage.setTitle("Welcome");
        // Show the Stage (window)
        stage.show();
    }

    
}
