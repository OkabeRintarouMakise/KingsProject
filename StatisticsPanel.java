
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
/**
 * StatisticsPanel is responsible for displaying the statistics and providing the functionality
 * for the buttons which when clicked, display different statistics
 *
 * @author Harun Abukar
 * @version 26/03/2023
 */
public class StatisticsPanel 
{
    // index of statistics
    private int statIndex = 0;
    
    // instance of Statistics
    Statistics statistics;
    
    // instance of DateSelector
    DateSelector dateSelector;
    
    // instance of label
    Label middleLabel;
    
    // instance of BorderPane
    BorderPane bPane;
    
    // instance of 2 Buttons (left and right)
    Button leftButton;
    Button rightButton;
    /**
     * Constructor for StatisticsPanel 
     * initialise statistics and adds all the relevant panes and buttons to the panel 
     * also adds design to the panel
     */
    public StatisticsPanel(DateSelector dateSelector)
    { 
        statistics = new Statistics(dateSelector);
        middleLabel = new Label(statistics.getStat(statIndex));
        bPane = new BorderPane();
        bPane.setId("pane");
        leftButton = new Button();
        rightButton = new Button();
        
        setButtonDesign();

        middleLabel.setPadding(new Insets(0, 50, 250, 50));

        BorderPane.setMargin(leftButton, new Insets(2,0,2,5));
        BorderPane.setMargin(rightButton, new Insets(2,5,2,0));

        rightButton.setOnAction(this::rightButtonClick);
        leftButton.setOnAction(this::leftButtonClick);

        setPane();
    
        this.dateSelector = dateSelector;
    }
    /**
     * updates statistics when button is clicked
     * when the end is reached and button is clicked,
     * the statistic is set to the first statistic
     */
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
    /**
     * updates statistics when button is clicked
     * when the beginning is reached and button is clicked,
     * the statistics is set to the last statistic
     */
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
    /**
     * @return the main pain, which is of type BorderPane
     */
    public BorderPane getMainPane(){
        return bPane;
    }
    
    /**
     * update the statistic by removing all the previous statistics then
     * add all the new statistics
     * then the label at the centre is updated to display the new statistic
     */
    public void updateStats()
    {
        statistics.removeStats();
        statistics.addStats();
        middleLabel.setText(statistics.getStat(statIndex));
    }
    /**
     * initialise the buttons and design them
     */
    private void setButtonDesign(){
        leftButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        rightButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        leftButton.setPadding(new Insets(50, 20, 50, 20));
        rightButton.setPadding(new Insets(50, 20, 50, 20));
        leftButton.setText("<");
        rightButton.setText(">");
    }
    /**
     * initialise the pane and add styling
     */
    private void setPane(){
        bPane.setBackground(new Background
        (new BackgroundFill(Color.web("CFFCFF"), CornerRadii.EMPTY, Insets.EMPTY)));
        bPane.setLeft(leftButton);
        bPane.setCenter(middleLabel);
        bPane.setRight(rightButton);
        bPane.getStylesheets().add("statisticsPanel.css");
    }
}
