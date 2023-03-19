import javafx.scene.control.Button;
import javafx.scene.image.*;
import java.awt.Color;

//import javafx.scene.image.PixelReader;
import java.awt.image.BufferedImage;
import javafx.embed.swing.*;

/**
 * Write a description of class BoroughButtons here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BoroughButton extends Button
{
    private Image image;
    private ImageView view;
    private String name;
    private int totalDeaths;

    /**
     * Constructor for objects of class BoroughButtons
     */
    public BoroughButton(String filePath)
    {
        image = new Image(filePath);
        decideColor();
        //filterImage(24,76);
        name = filePath.substring(0,4);
        view = new ImageView(image);
        this.setGraphic(view);
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
                filterImage(0,255);
                break;
            case 200:
                filterImage(20,235);
                break;
            case 300:
                filterImage(40,215);
                break;
            case 400:
                filterImage(60,195);
                break;
            case 500:
                filterImage(80,175);
                break;
            case 600:
                filterImage(100,155);
                break;
            case 700:
                filterImage(120,135);
                break;
            case 800:
                filterImage(140,115);
                break;
            case 900:
                filterImage(160,95);
                break;
            case 1000:
                filterImage(180,75);
                break;
            case 1100:
                filterImage(200,55);
                break;
            case 1200:
                filterImage(220,35);
                break;
            default:
                return;
        
        
        }
        

    }    

    private void filterImage(int red, int green)
    {
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        for(int i = 0;i < bImage.getWidth(); i++)
        {
            for(int j = 0;j < bImage.getHeight(); j++)
            {   
                Color color = new Color(bImage.getRGB(i, j));
                if(!(color.getRed() == 255 && color.getGreen() == 255 && color.getBlue() == 255))
                {
                    Color newColor = new Color(red, green, 0);
                    bImage.setRGB(i, j, newColor.getRGB());
                }
            }
        }
        image = SwingFXUtils.toFXImage(bImage, null);
    }
    
    public String toString()
    {
        return name;
    }

}
