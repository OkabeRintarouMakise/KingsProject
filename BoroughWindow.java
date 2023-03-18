import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;


/**
 * Write a description of class BoroughWindow here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BoroughWindow extends Application
{
    private String boroughName;
    private ObservableList<CovidData> dataList;
    
    public BoroughWindow(String givenName)
    {
        boroughName = getBoroughFullName(givenName);
        dataList = FXCollections.observableArrayList();
        start(new Stage());
    }
    
    @Override
    public void start(Stage stage)
    {
        BorderPane pane = new BorderPane();
        
        TableView<CovidData> table = new TableView<>();
        
        
        TableColumn<CovidData,String> dateColumn = new TableColumn<CovidData, String>("Date");
        
        table.setItems(dataList);
        pane.setCenter(table);
        
        
        
        
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle(boroughName);
        stage.show();
    }

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
                return "Kensington and Chelsea";
            case "wstm":
                return "Westminster";
            case "towh":
                return "Tower Hamlets";
            case "newh":
                return "Newham";
            case "bark":
                return "Barking and Dagenham";
            case "houn":
                return "Hounslow";
            case "hamm":
                return "Hammersmith and Fulham";
            case "wand":
                return "Wandsworth";
            case "city":
                return "City of Londons";
            case "gwch":
                return "Greenwich";
            case "bexl":
                return "Bexley";
            case "rich":
                return "Richmond upon Thames";
            case "mert":
                return "Merton";
            case "lamb":
                return "Lambeth";
            case "sthw":
                return "Southwark";
            case "lews":
                return "Lewisham";
            case "king":
                return "Kingston upon Thames";
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
