

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
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
        
        BorderPane borderPane = new BorderPane();
        MenuBar menu = new MenuBar();
        AnchorPane anchorPane = new AnchorPane();
        HBox hbox = new HBox();

        borderPane.setTop(menu);
        borderPane.setBottom(anchorPane);
        borderPane.setCenter(hbox);

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle("hello");
        // Show the Stage (window)
        stage.show();
    }

    
}
