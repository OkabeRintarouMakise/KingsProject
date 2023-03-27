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
 * @version (a version number or a date)
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
        boroughName = getBoroughFullName(givenName);
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
        TableColumn<CovidData,String> totalDeathColumn = new TableColumn<CovidData, String>("New Covid Deaths");

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        retailColumn.setCellValueFactory(new PropertyValueFactory<>("retailRecreationGMR"));
        groceryColumn.setCellValueFactory(new PropertyValueFactory<>("groceryPharmacyGMR"));
        parksColumn.setCellValueFactory(new PropertyValueFactory<>("parksGMR"));
        transitColumn.setCellValueFactory(new PropertyValueFactory<>("transitGMR"));
        workplaceColumn.setCellValueFactory(new PropertyValueFactory<>("workplacesGMR"));
        residentialColumn.setCellValueFactory(new PropertyValueFactory<>("residentialGMR"));
        newCaseColumn.setCellValueFactory(new PropertyValueFactory<>("newCases"));
        totalCaseColumn.setCellValueFactory(new PropertyValueFactory<>("totalCases"));
        totalDeathColumn.setCellValueFactory(new PropertyValueFactory<>("totalDeaths"));
        
        
        table.getColumns().setAll(dateColumn, retailColumn, groceryColumn, parksColumn, transitColumn, workplaceColumn,
                                    residentialColumn, newCaseColumn, totalCaseColumn, totalDeathColumn);
        
        table.setItems(dataList);
        pane.setCenter(table);
        
        
        
        Scene scene = new Scene(pane, 1000, 300);
        stage.setScene(scene);
        stage.setTitle(boroughName);
        stage.show();
    }

    /**
     * @return Full name of the borough
     * @param giveName Abbreviation of the full name
     */
    private String getBoroughFullName(String givenName)
    {
        switch(givenName)
        {
            case "enfi":
                return "Enfield";
            case "barn":
                return "Barnet";
            case "hrgy":
                return "Haringey";
            case "walt":
                return "Waltham Forest";
            case "hrrw":
                return "Harrow";
            case "bren":
                return "Brent";
            case "camd":
                return "Camden";
            case "isli":
                return "Islington";
            case "hack":
                return "Hackney";
            case "redb":
                return "Redbridge";
            case "have":
                return "Havering";
            case "hill":
                return "Hillingdon";
            case "eali":
                return "Ealing";
            case "kens":
                return "Kensington And Chelsea";
            case "wstm":
                return "Westminster";
            case "towh":
                return "Tower Hamlets";
            case "newh":
                return "Newham";
            case "bark":
                return "Barking And Dagenham";
            case "houn":
                return "Hounslow";
            case "hamm":
                return "Hammersmith And Fulham";
            case "wand":
                return "Wandsworth";
            case "city":
                return "City Of London";
            case "gwch":
                return "Greenwich";
            case "bexl":
                return "Bexley";
            case "rich":
                return "Richmond Upon Thames";
            case "mert":
                return "Merton";
            case "lamb":
                return "Lambeth";
            case "sthw":
                return "Southwark";
            case "lews":
                return "Lewisham";
            case "king":
                return "Kingston Upon Thames";
            case "sutt":
                return "Sutton";
            case "croy":
                return "Croydon";
            case "brom":
                return "Bromley";
            default:
                return givenName;
        
        }
    }
}
