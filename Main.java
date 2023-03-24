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
    CovidDataLoader dateFetcher  = new CovidDataLoader();
    private HashSet<String> dateCollection = new HashSet<>();
    ArrayList<String> orderedDates = new ArrayList<String>();
    ComboBox from = new ComboBox();
    ComboBox to = new ComboBox();
    
    /**
     * Constructor for objects of class Main
     */
    public Main()
    {
    
    
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
}

