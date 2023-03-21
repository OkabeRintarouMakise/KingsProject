import javafx.scene.control.Button;
import java.awt.Color;


/**
 * Write a description of class BoroughButtons here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BoroughButton extends Button
{
    private String name;
    private int totalDeaths = 0;
    
    /**
     * Constructor for objects of class BoroughButtons
     */
    public BoroughButton(String boroughName)
    {
        this.setPrefSize(75, 75);
        this.setText(boroughName);
        name = boroughName;
        decideColor();
    }
    
    public void setTotalDeaths(int givenDeaths)
    {
        totalDeaths = Math.round(givenDeaths/100) * 100;
    }
    
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

    private void filterImage(String rgbValue)
    {
        this.setStyle("-fx-background-color:#" + rgbValue);
    }
    
    public String toString()
    {
        return name;
    }

}
