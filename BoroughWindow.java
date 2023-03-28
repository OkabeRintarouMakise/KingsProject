import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;



/**
 * Loads in the window with all the appropriate data on the 
 * certain borough. Uses a table display to potray the data
 *
 * @author Sebastian Habram 
 * @version 27/03/2023
 */
public class BoroughWindow extends Application
{
    //Name of the borough
    private String boroughName;
    
    //An automatically updating list of data to be displayed
    private ObservableList<CovidData> dataList;
    
    /**
     * Constructor of Borough Window
     * @param givenName Abbreviated name of borough 
     * @param dateSelector Pointer to the main logic unit
     */
    public BoroughWindow(String givenName, BoroughLogic boroughLogic)
    {
        boroughName = givenName;
        dataList = FXCollections.observableArrayList(boroughLogic.filterDataList(boroughName));
        start(new Stage());
    }
    
    /**
     * Creates a new window with all its components to be displayed
     * @param stage Reference to the window being shown
     */
    @Override
    public void start(Stage stage)
    {
        BorderPane pane = new BorderPane();
        
        TableView<CovidData> table = new TableView<>();
        
        
        TableColumn<CovidData,String> dateColumn = new TableColumn<CovidData, String>("Date");
        TableColumn<CovidData,String> retailColumn = new TableColumn<CovidData, String>("Retail and Recreation");
        TableColumn<CovidData,String> groceryColumn = new TableColumn<CovidData, String>("Grocery and Pharmacy");
        TableColumn<CovidData,String> parksColumn = new TableColumn<CovidData, String>("Parks");
        TableColumn<CovidData,String> transitColumn = new TableColumn<CovidData, String>("Transit Stations");
        TableColumn<CovidData,String> workplaceColumn = new TableColumn<CovidData, String>("Workplace");
        TableColumn<CovidData,String> residentialColumn = new TableColumn<CovidData, String>("Residential");
        TableColumn<CovidData,String> newCaseColumn = new TableColumn<CovidData, String>("New Covid Cases");
        TableColumn<CovidData,String> totalCaseColumn = new TableColumn<CovidData, String>("Total Covid Cases");
        TableColumn<CovidData,String> newDeathColumn = new TableColumn<CovidData, String>("New Covid Deaths");

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        retailColumn.setCellValueFactory(new PropertyValueFactory<>("retailRecreationGMR"));
        groceryColumn.setCellValueFactory(new PropertyValueFactory<>("groceryPharmacyGMR"));
        parksColumn.setCellValueFactory(new PropertyValueFactory<>("parksGMR"));
        transitColumn.setCellValueFactory(new PropertyValueFactory<>("transitGMR"));
        workplaceColumn.setCellValueFactory(new PropertyValueFactory<>("workplacesGMR"));
        residentialColumn.setCellValueFactory(new PropertyValueFactory<>("residentialGMR"));
        newCaseColumn.setCellValueFactory(new PropertyValueFactory<>("newCases"));
        totalCaseColumn.setCellValueFactory(new PropertyValueFactory<>("totalCases"));
        newDeathColumn.setCellValueFactory(new PropertyValueFactory<>("newDeaths"));
        
        
        table.getColumns().setAll(dateColumn, retailColumn, groceryColumn, parksColumn, transitColumn, workplaceColumn,
                                    residentialColumn, newCaseColumn, totalCaseColumn, newDeathColumn);
        
        table.setItems(dataList);
        pane.setCenter(table);
        
        
        
        Scene scene = new Scene(pane, 1000, 300);
        stage.setScene(scene);
        stage.setTitle(boroughName);
        stage.show();
    }
}
