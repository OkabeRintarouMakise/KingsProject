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

/**
 * Write a description of class mapPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class mapPanel extends Application 
{
    private ArrayList<BoroughButton> buttonArray;
    private VBox windowPane; 

    public void start(Stage stage)
    {
        windowPane = new VBox();
        
        setBoroughButtons();
        constructWindowPane();
        
        Scene scene = new Scene(windowPane);
        stage.setScene(scene);
        stage.show();
        
    }
    
    private void constructWindowPane()
    {
        windowPane.getChildren().add(buttonArray.get(0));
        
        HBox secondLevel = new HBox();
        HBox thirdLevel = new HBox();
        HBox fourthLevel = new HBox();
        HBox fifthLevel = new HBox();
        HBox sixthLevel = new HBox();
        HBox lastLevel = new HBox();

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
        appendButton("enfi.png");
        appendButton("barn.png");
        appendButton("hrgy.png");
        appendButton("walt.png");
        appendButton("hrrw.png");
        appendButton("bren.png");
        appendButton("camd.png");
        appendButton("isli.png");
        appendButton("hack.png");
        appendButton("redb.png");
        appendButton("have.png");
        appendButton("hill.png");
        appendButton("eali.png");
        appendButton("kens.png");
        appendButton("wstm.png");
        appendButton("towh.png");
        appendButton("newh.png");
        appendButton("bark.png");
        appendButton("houn.png");
        appendButton("hamm.png");
        appendButton("wand.png");
        appendButton("city.png");
        appendButton("gwch.png");
        appendButton("bexl.png");
        appendButton("rich.png");
        appendButton("mert.png");
        appendButton("lamb.png");
        appendButton("sthw.png");
        appendButton("lews.png");
        appendButton("king.png");
        appendButton("sutt.png");
        appendButton("croy.png");
        appendButton("brom.png");
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

}



