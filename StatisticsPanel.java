
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import java.util.ArrayList;
import javafx.scene.paint.Color;
/**
 * Write a description of JavaFX class StatisticsPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StatisticsPanel //extends Application
{
    private int statIndex = 0;
    Statistics statistics = new Statistics();
    Button leftButton = new Button();
    Button rightButton = new Button();
    Label middleLabel = new Label(statistics.getStat(statIndex));
    BorderPane bPane = new BorderPane();
    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     */
    //@Override
    public StatisticsPanel()
    {
       

        leftButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        rightButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        leftButton.setPadding(new Insets(50, 20, 50, 20));
        rightButton.setPadding(new Insets(50, 20, 50, 20));
        leftButton.setText("<");
        rightButton.setText(">");

        middleLabel.setPadding(new Insets(50, 50, 50, 50));
        bPane.setBackground(new Background
        (new BackgroundFill(Color.web("CFFCFF"), CornerRadii.EMPTY, Insets.EMPTY)));
        

        BorderPane.setMargin(leftButton, new Insets(2,0,2,5));
        BorderPane.setMargin(rightButton, new Insets(2,5,2,0));

        rightButton.setOnAction(this::rightButtonClick);
        leftButton.setOnAction(this::leftButtonClick);

        bPane.setLeft(leftButton);
        bPane.setCenter(middleLabel);
        bPane.setRight(rightButton);
        bPane.getStylesheets().add("statisticsPanel.css");
        // JavaFX must have a Scene (window content) inside a Stage (window)
        //Scene scene = new Scene(bPane, 800, 500);
        
        //stage.setTitle("Statistics");
        //stage.setScene(scene);

        // Show the Stage (window)
        //stage.show();
    }

    private void rightButtonClick(ActionEvent event){
        statIndex++;
        if(statIndex < 5) {
            middleLabel.setText(statistics.getStat(statIndex));
        }
        else{
            statIndex = 0;
            middleLabel.setText(statistics.getStat(statIndex));

        }

    }

    private void leftButtonClick(ActionEvent event){
        statIndex--;
        if(statIndex > -1) {
            middleLabel.setText(statistics.getStat(statIndex));
        }
        else{
            statIndex = 4;
            middleLabel.setText(statistics.getStat(statIndex));

        }

    }
    
    public Pane getMainPane(){
        return bPane;
    }
}
