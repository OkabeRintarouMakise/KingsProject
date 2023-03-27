import java.util.ArrayList;

/**
 * Write a description of class BoroughLogic here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
     * Creates all the required buttons  
     */
    /*private ArrayList<BoroughButton> createBoroughButtons()
    {
        ArrayList<BoroughButton> buttonArray = new ArrayList<BoroughButton>();
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
        return buttonArray;
    }*/
    
    /**
     * Actually instantiates the buttons and appends to the array
     * @param boroughName String name of the borough 
     */
    /*private void instantiateButton(String boroughName)
    {
        BoroughButton button = new BoroughButton(boroughName);
        button.setOnAction(this::openBoroughWindow);
        buttonArray.add(button);
    }*/
}
