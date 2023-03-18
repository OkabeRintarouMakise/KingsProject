import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.event.*;

/**
 * Write a description of class mapPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class mapPanel extends Application 
{
    private ArrayList<BoroughButton> buttonArray;
    
    public void start(Stage stage)
    {
        TilePane topPane = new TilePane();
        topPane.setPrefColumns(7);
        topPane.setPrefRows(7);
        
        setBoroughButtons();
        
        for(int i = 0; i < buttonArray.size(); i++)
        {
            topPane.getChildren().add(buttonArray.get(i));
        }
        
        Scene scene = new Scene(topPane);
        stage.setScene(scene);
        stage.show();
        
    }
    
    public void setBoroughButtons()
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
        /*appendButton("hamm.png");
        appendButton("wand.png");
        appendButton("city.png");
        appendButton("gwch.png");
        appendButton("bexl.png");
        appendButton("rich.png");
        appendButton("mert.png");
        appendButton("lamb.png");
        appendButton("swth.png");
        appendButton("lews.png");
        appendButton("king.png");
        appendButton("sutt.png");
        appendButton("croy.png");
        appendButton("brom.png");*/
    }
    
    public void appendButton(String fileName)
    {
        BoroughButton button = new BoroughButton(fileName);
        button.setOnAction(this::openBoroughWindow);
        buttonArray.add(button);
    }
    
    public void openBoroughWindow(ActionEvent event)
    {
        new BoroughWindow(event.getSource().toString());
    }
}



