import javafx.scene.control.Button;
import java.awt.Color;


/**
 * Extension class of the JavaFX button class. Holds extra functionality 
 * like changing color based on conditions. Represents each borough that
 * needs to be displayed
 *
 * @author Sebastian Habram
 * @version 27/03/2023
 */
public class BoroughButton extends Button
{
    //Abbreviated Name of the borough to be displayed
    private String name;
    
    //Number of total deaths in that borough 
    private int totalDeaths = 0;
    
    /**
     * Constructor for objects of class BoroughButtons
     * @param boroughName Abbreviated name of the borough
     */
    public BoroughButton(String boroughName)
    {
        this.setPrefSize(75, 75);
        this.setText(boroughName);
        name = boroughName;
    }
    
    /**
     * Sets the total deaths of the borough in a given period of time
     * @param givenDeaths The total number of deaths
     */
    public void setTotalDeaths(int givenDeaths)
    {
        totalDeaths = Math.round(givenDeaths/100) * 100;
        decideColor();
    }
    
    /**
     * Decides which color the button should be based on its total deaths
     */
    private void decideColor()
    {
        switch(totalDeaths)
        {
            case 100:
                filterImage("00FF00");
                break;
            case 200:
                filterImage("14EB00");
                break;
            case 300:
                filterImage("28D700");
                break;
            case 400:
                filterImage("3CC300");
                break;
            case 500:
                filterImage("50AF00");
                break;
            case 600:
                filterImage("649B00");
                break;
            case 700:
                filterImage("788700");
                break;
            case 800:
                filterImage("8C7300");
                break;
            case 900:
                filterImage("A05F00");
                break;
            case 1000:
                filterImage("B44B00");
                break;
            case 1100:
                filterImage("C83700");
                break;
            case 1200:
                filterImage("DC2300");
                break;
            default:
                return;
        
        
        }
        

    }  

    /**
     * Sets the color of the button to a given color 
     * @param rgbValue The RGB value of the color the button should be
     */
    private void filterImage(String rgbValue)
    {
        this.setStyle("-fx-background-color:#" + rgbValue);
    }
    
    /**
     * Overriden version of toString method for Borough Buttons
     * @return Name of the borough associated with the button
     */
    public String toString()
    {
        return name;
    }
    
}
