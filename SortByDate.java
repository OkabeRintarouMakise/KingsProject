import java.util.Comparator;

/**
 * This is a helper class which sorts the records in the csv file in date order
 * @author Armaan Uddin
 * @version 17/03/2023
 */
public class SortByDate implements Comparator<CovidData>
{
    /**
     * This compares records with each other and swap their position in date order
     * from earliest date to latest date
     */
    public int compare(CovidData a, CovidData b)
    {
        return a.getDate().compareTo(b.getDate());
    }
}