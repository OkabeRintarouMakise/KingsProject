import java.util.ArrayList;

/**
 * Class which sorts the data based on the given dates and feeds it
 * back to the panels that need it. The "middle man" between CovidDataLoader
 * and all the panels, minus the graph panel
 *
 * @author Sebastian Habram
 * @version 27/03/2023
 */
public class CovidDataDateFilter
{
    //An array list of all the avaiablle data from the csv file
    private ArrayList<CovidData> originalData;
    
    //An array list of the filtered by date data from originalData
    private ArrayList<CovidData> outputData;
    
    //An array list of string values of the range of dates the data should be in
    private ArrayList<String> dates;
    
    /**
     * Filters all the data from the CSV and only keeps the data which
     * is in a given range of dates
     */
    public void setDateAppropriateData()
    {
        ArrayList<CovidData> tempData = new ArrayList<CovidData>();
        if(dates == null)return;
        for(CovidData dataItem : originalData)
        {
            if(dates.contains(dataItem.getDate()))
            {
                tempData.add(dataItem);
            }
        }
        outputData = tempData;
    }
    
    /**
     * Sets the given data to a variable
     * @param data A list of all available data
     */
    public void setDataList(ArrayList<CovidData> data)
    {
        originalData = data;
    }
    
    /**
     * Sets the given range of dates to a variable
     * @param dates Given range of dates selected by a user
     */
    public void setDateList(ArrayList<String> dates)
    {
        this.dates = dates;
        setDateAppropriateData();
    }
    
    /**
     * @returns All loaded data if filtered data is null, otherwise returns data occuring in the given date range
     */
    public ArrayList<CovidData> getDataList()
    {
        if(outputData == null)return originalData;
        return outputData;
    }
}
