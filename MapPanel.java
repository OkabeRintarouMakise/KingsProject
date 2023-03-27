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
 * Map Panel is the second available panel to the user. It displays
 * a map of buttons of all the London borughs and allows them to 
 * be clicked for further functionality
 *
 * @author Sebastian Habram
 * @version 27/03/2023
 */
public class MapPanel  
{
    //Holds all the available boroughs that can be pressed to acces the data
    private ArrayList<BoroughButton> buttonArray;
        
    //Main window pane to add all buttons in correct layout, also passed into
    //the main GUI class as a switch to be the main pane
    private VBox windowPane; 
    
    
    //Sub logic unit for anything related to boroughs
    private BoroughLogic boroughLogic;
    
    /** 
     * Class constructor for Map Panel
     * @param dateSelector Pointer to the main logic unit
     */
    public MapPanel(DateSelector dateSelector)
    {
        boroughLogic = new BoroughLogic(dateSelector);
        
        windowPane = new VBox();
        windowPane.setAlignment(Pos.TOP_CENTER);
        
        buttonArray = boroughLogic.createBoroughButtons();
        
        constructWindowPane();
        
        windowPane.getStylesheets().add(getClass().getResource("MapPanel.css").toExternalForm());
    }
    
    /**
     * Constructs the structure of the pane/window and appends
     * all the required borough buttons
     */
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
        thirdLevel.setAlignment(Pos.CENTER);
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

    /**
     * @return Main pane of the class
     */
    public VBox getMainPane()
    {
        return windowPane;
    }
    
    /**
     * Updates the total deaths value in all buttons
     */
    public void updateButtons()
    {
        for(BoroughButton button: buttonArray)
        {
            button.setTotalDeaths(boroughLogic.getTotalDeaths(button.toString()));
        }
    }
}
