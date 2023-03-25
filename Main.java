import java.util.HashSet;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import java.util.Collections;

/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main
{
    private CovidDataLoader dateFetcher  = new CovidDataLoader();
    private HashSet<String> dateCollection = new HashSet<>();
    private ArrayList<String> orderedDates = new ArrayList<String>();
    private ComboBox from = new ComboBox();
    private ComboBox to = new ComboBox();
    private CovidDataDateFilter filter = new CovidDataDateFilter();
    
    public Main()
    {
        dateFetcher.load();
        filter.setDataList(dateFetcher.getData());
        this.addSet();
        
    }


    public void collectionLoader(ComboBox combo){
        for(String date: orderedDates){
            combo.getItems().add(date);
        }
    }

    public void addSet(){
        for(CovidData record : dateFetcher.getData()){
            dateCollection.add(record.getDate());
        }
        orderedDates.addAll(dateCollection);
        Collections.sort(orderedDates);
    }
    
    public ComboBox getFrom()
    {
        return from;
    }
    
    public ComboBox getTo()
    {
        return to;
    }
    
    public String getFromValue()
    {
        return (String) from.getValue();
    }

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
    
    public CovidDataDateFilter getFilter()
    {   
        return filter;
    }
    
    public ArrayList<String> getDateList()
    {
        return orderedDates;
    }
}

