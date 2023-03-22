import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.event.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

/**
 * Write a description of class mapPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MapPanel  
{
    private ArrayList<BoroughButton> buttonArray;
    private VBox windowPane; 
    private ArrayList<String> dateList;

    public MapPanel()
    {
        windowPane = new VBox();
        windowPane.setAlignment(Pos.TOP_CENTER);
        
        setBoroughButtons();
        constructWindowPane();
        
        windowPane.getStylesheets().add(getClass().getResource("MapPanel.css").toExternalForm());
        
        //dateList = givenDataSet;
    }
    
    /*public void start(Stage stage)
    {
        windowPane = new VBox();
        windowPane.setAlignment(Pos.TOP_CENTER);
        
        setBoroughButtons();
        constructWindowPane();
        
        Scene scene = new Scene(windowPane);
        windowPane.getStylesheets().add(getClass().getResource("MapPanel.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }*/
    
    private void constructWindowPane()
    {
        windowPane.getChildren().add(buttonArray.get(0));
        
        HBox secondLevel = new HBox();
        HBox thirdLevel = new HBox();
        HBox fourthLevel = new HBox();
        HBox fifthLevel = new HBox();
        HBox sixthLevel = new HBox();
        HBox lastLevel = new HBox();

        secondLevel.setAlignment(Pos.TOP_CENTER);
        thirdLevel.setAlignment(Pos.CENTER_LEFT);
        fourthLevel.setAlignment(Pos.CENTER);
        fifthLevel.setAlignment(Pos.CENTER);
        sixthLevel.setAlignment(Pos.BOTTOM_CENTER);
        lastLevel.setAlignment(Pos.BOTTOM_CENTER);
        
        secondLevel.setSpacing(2);
        thirdLevel.setSpacing(2);
        fourthLevel.setSpacing(2);
        fifthLevel.setSpacing(2);
        sixthLevel.setSpacing(2);
        lastLevel.setSpacing(2);
        
        int i = 0;
        for(BoroughButton button : buttonArray)
        {
            if(i < 4 && i > 0){secondLevel.getChildren().add(button);}            
            if(i >= 4 && i < 11){thirdLevel.getChildren().add(button);}
            if(i >= 11 && i < 18){fourthLevel.getChildren().add(button);}
            if(i >= 18 && i < 24){fifthLevel.getChildren().add(button);}
            if(i >= 24 && i < 29){sixthLevel.getChildren().add(button);}
            if(i >= 29 && i < 33){lastLevel.getChildren().add(button);}
            i++;
        }
        
        windowPane.getChildren().addAll(secondLevel, thirdLevel, fourthLevel, 
                                            fifthLevel, sixthLevel, lastLevel);
    }
    
    private void setBoroughButtons()
    {
        buttonArray = new ArrayList<BoroughButton>();
        appendButton("enfi");
        appendButton("barn");
        appendButton("hrgy");
        appendButton("walt");
        appendButton("hrrw");
        appendButton("bren");
        appendButton("camd");
        appendButton("isli");
        appendButton("hack");
        appendButton("redb");
        appendButton("have");
        appendButton("hill");
        appendButton("eali");
        appendButton("kens");
        appendButton("wstm");
        appendButton("towh");
        appendButton("newh");
        appendButton("bark");
        appendButton("houn");
        appendButton("hamm");
        appendButton("wand");
        appendButton("city");
        appendButton("gwch");
        appendButton("bexl");
        appendButton("rich");
        appendButton("mert");
        appendButton("lamb");
        appendButton("sthw");
        appendButton("lews");
        appendButton("king");
        appendButton("sutt");
        appendButton("croy");
        appendButton("brom");
    }
    
    private void appendButton(String fileName)
    {
        BoroughButton button = new BoroughButton(fileName);
        button.setOnAction(this::openBoroughWindow);
        buttonArray.add(button);
    }
    
    private void openBoroughWindow(ActionEvent event)
    {
        new BoroughWindow(event.getSource().toString());
    }

    public VBox getMainPane()
    {
        return windowPane;
    }
}
