import java.util.ArrayList;
import javafx.event.ActionEvent;

/**
 *
 * Handles the logic of all Borough related classes
 *
 * @author Sebastian Habram
 * @version 27/03/2023
 */
public class BoroughLogic
{
    //A reference to the main logic unit
    private DateSelector dateSelector;    

    /**
     * Constructor for objects of class BoroughLogic
     */
    public BoroughLogic(DateSelector dateSelector)
    {
        this.dateSelector = dateSelector;
    }

    /**
     * @return ArrayList of data appropriate to the borough
     * @param dataListToFilter ArrayList of all the data on all boroughs 
     */
    public ArrayList<CovidData> filterDataList(String boroughName)
    {
        ArrayList<CovidData> dataListTemp = new ArrayList<CovidData>();
        ArrayList<CovidData> dataListToFilter = dateSelector.getFilter().getDataList();
        
        for(CovidData data: dataListToFilter)
        {
            if(data.getBorough().equals(boroughName))
            {
                dataListTemp.add(data);
            }
        }
        
        return dataListTemp;
    }
    
    /**
     * @return ArrayList of data appropriate to the borough
     * @param dataListToFilter ArrayList of all the data on all boroughs 
     */
    public int getTotalDeaths(String boroughName)
    {
        ArrayList<CovidData> dataListToFilter = dateSelector.getFilter().getDataList();
        int deaths = 0;
        boroughName = getBoroughFullName(boroughName);
        
        for(CovidData data: dataListToFilter)
        {
            if(data.getBorough().equals(boroughName) && data.getDate().equals(dateSelector.getToValue()))
            {
                deaths = data.getTotalDeaths();
            }
        }
        
        return deaths;
    }
    
    /**
     * Creates all the required buttons  
     */
    public ArrayList<BoroughButton> createBoroughButtons()
    {
        ArrayList<BoroughButton> buttonArray = new ArrayList<BoroughButton>();
        buttonArray.add(instantiateButton("enfi"));
        buttonArray.add(instantiateButton("barn"));
        buttonArray.add(instantiateButton("hrgy"));
        buttonArray.add(instantiateButton("walt"));
        buttonArray.add(instantiateButton("hrrw"));
        buttonArray.add(instantiateButton("bren"));
        buttonArray.add(instantiateButton("camd"));
        buttonArray.add(instantiateButton("isli"));
        buttonArray.add(instantiateButton("hack"));
        buttonArray.add(instantiateButton("redb"));
        buttonArray.add(instantiateButton("have"));
        buttonArray.add(instantiateButton("hill"));
        buttonArray.add(instantiateButton("eali"));
        buttonArray.add(instantiateButton("kens"));
        buttonArray.add(instantiateButton("wstm"));
        buttonArray.add(instantiateButton("towh"));
        buttonArray.add(instantiateButton("newh"));
        buttonArray.add(instantiateButton("bark"));
        buttonArray.add(instantiateButton("houn"));
        buttonArray.add(instantiateButton("hamm"));
        buttonArray.add(instantiateButton("wand"));
        buttonArray.add(instantiateButton("city"));
        buttonArray.add(instantiateButton("gwch"));
        buttonArray.add(instantiateButton("bexl"));
        buttonArray.add(instantiateButton("rich"));
        buttonArray.add(instantiateButton("mert"));
        buttonArray.add(instantiateButton("lamb"));
        buttonArray.add(instantiateButton("sthw"));
        buttonArray.add(instantiateButton("lews"));
        buttonArray.add(instantiateButton("king"));
        buttonArray.add(instantiateButton("sutt"));
        buttonArray.add(instantiateButton("croy"));
        buttonArray.add(instantiateButton("brom"));
        return buttonArray;
    }
    
    /**
     * Actually instantiates the buttons and appends to the array
     * @param boroughName String name of the borough 
     */
    private BoroughButton instantiateButton(String boroughName)
    {
        BoroughButton button = new BoroughButton(boroughName);
        button.setOnAction(this::openBoroughWindow);
        return button;
    }
    
    /**
     * On button click event, results in a borough window being 
     * opened and loaded
     * @param event Event handler appropriate event
     */
    private void openBoroughWindow(ActionEvent event)
    {
        new BoroughWindow(getBoroughFullName(event.getSource().toString()), this);
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
