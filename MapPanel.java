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
 * @version 24/03/2023
 */
public class MapPanel  
{
    //Holds all the available boroughs that can be pressed to acces the data
    private ArrayList<BoroughButton> buttonArray;
    
    //Main window pane to add all buttons in correct layout, also passed into
    //the main GUI class as a switch to be the main pane
    private VBox windowPane; 
    
    //
    private ArrayList<String> dateList;
    
    //Reference to the main logic unit
    private DateSelector dateSelector;
    
    /** 
     * Class constructor for Map Panel
     */
    public MapPanel(DateSelector dateSelector)
    {
        this.dateSelector = dateSelector;
        
        windowPane = new VBox();
        windowPane.setAlignment(Pos.TOP_CENTER);
        
        createBoroughButtons();
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
     * Creates all the required buttons  
     */
    private void createBoroughButtons()
    {
        buttonArray = new ArrayList<BoroughButton>();
        instantiateButton("enfi");
        instantiateButton("barn");
        instantiateButton("hrgy");
        instantiateButton("walt");
        instantiateButton("hrrw");
        instantiateButton("bren");
        instantiateButton("camd");
        instantiateButton("isli");
        instantiateButton("hack");
        instantiateButton("redb");
        instantiateButton("have");
        instantiateButton("hill");
        instantiateButton("eali");
        instantiateButton("kens");
        instantiateButton("wstm");
        instantiateButton("towh");
        instantiateButton("newh");
        instantiateButton("bark");
        instantiateButton("houn");
        instantiateButton("hamm");
        instantiateButton("wand");
        instantiateButton("city");
        instantiateButton("gwch");
        instantiateButton("bexl");
        instantiateButton("rich");
        instantiateButton("mert");
        instantiateButton("lamb");
        instantiateButton("sthw");
        instantiateButton("lews");
        instantiateButton("king");
        instantiateButton("sutt");
        instantiateButton("croy");
        instantiateButton("brom");
    }
    
    /**
     * Actually instantiates the buttons and appends to the array
     */
    private void instantiateButton(String fileName)
    {
        BoroughButton button = new BoroughButton(fileName);
        button.setOnAction(this::openBoroughWindow);
        buttonArray.add(button);
    }
    
    /**
     * On button click event, results in a borough window being 
     * opened and loaded
     */
    private void openBoroughWindow(ActionEvent event)
    {
        new BoroughWindow(event.getSource().toString(), dateSelector);
    }

    /**
     * @return VBox main pane
     */
    public VBox getMainPane()
    {
        return windowPane;
    }
}
