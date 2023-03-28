import java.util.HashSet;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import java.util.Collections;
/**
 * Implementation of much of the methods found in CovidDataGUI. Bulk of the class
 * deals with the acquiring and manipulating of the Date list found in CovidDataLoader
 *
 * @author Adil Kassam, Sebastian Habram
 * @version 27/03/2023
 */
public class DateSelector
{
    private CovidDataLoader dateFetcher  = new CovidDataLoader(); // Object created to access date list
    private HashSet<String> dateCollection = new HashSet<>(); // HashSet created to remove repeats in date list
    private ArrayList<String> orderedDates = new ArrayList<String>(); // ArrayList created to order dates chronologically
    private ComboBox from = new ComboBox(); // From ComboBox created
    private ComboBox to = new ComboBox(); // To ComboBox created
    private CovidDataDateFilter filter = new CovidDataDateFilter();// Acquires copy of all data
    
    /**
     * Constructor for Date Selector objects
     */
    public DateSelector()
    {
        dateFetcher.load();
        filter.setDataList(dateFetcher.getData());
        this.addSet();
        
    }

    
    /**
     * Adds all of the elements of the arraylist orderedDates to the comboboxes
     * @param combo The combobox that the dates will be stored in for a drop down box menu
     */
    public void collectionLoader(ComboBox combo){
        for(String date: orderedDates){
            combo.getItems().add(date);
        }
    }
    
    /**
     * Fetches all dates from CovidDataLoader via dateFetcher object,and returns a fully
     * chronologically sorted list of dates ready to be selected from.
     */
    public void addSet(){
        for(CovidData record : dateFetcher.getData()){
            dateCollection.add(record.getDate());
        }
        orderedDates.addAll(dateCollection);
        Collections.sort(orderedDates);
    }
    
    /**
     * @return Returns the ComboBox From
     */
    public ComboBox getFrom()
    {
        return from;
    }
    
    /**
     * @return Returns the ComboBox To
     */
    public ComboBox getTo()
    {
        return to;
    }
    
    /**
     * @return Returns the user selected current From value
     */
    public String getFromValue()
    {
        return (String) from.getValue();
    }
    
    /**
     * @return Returns the user selected current To value
     */
    public String getToValue()
    {
        return (String) to.getValue();
    }
    
    public void getRequiredDates()
    {
        System.out.println(getToValue() + " " + getFromValue());
        if(this.getToValue() == null || this.getFromValue() == null)return;
        int startIndex = 0;
        int endIndex = 0;
        boolean append = false;
        ArrayList<String> datesRequired = new ArrayList<String>();
        for(int i = 0; i < orderedDates.size() ; i++)
        {
            if(this.getFromValue().equals(orderedDates.get(i)))
            {
                startIndex = i;
                append = true;
            }
            if(append)
            {
                datesRequired.add(orderedDates.get(i));
            }
            if(this.getToValue().equals(orderedDates.get(i)))
            {
                endIndex = i;
                append = false;
            }
        }
        filter.setDateList(datesRequired);
    }
    
    /**
     * @return Shared between classes data by date filter object
     */
    public CovidDataDateFilter getFilter()
    {   
        return filter;
    }
    
    /**
     * @return Returns a fully sorted orderedDates arraylist
     */
    public ArrayList<String> getDateList()
    {
        return orderedDates;
    }
}

