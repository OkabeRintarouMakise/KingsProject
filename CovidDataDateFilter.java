import java.util.ArrayList;

/**
 * Write a description of class CovidDataDateFilter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CovidDataDateFilter
{
    private ArrayList<CovidData> originalData;
    private ArrayList<CovidData> outputData;
    private ArrayList<String> dates;
    
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
        System.out.println(tempData.size());
    }
    
    public void setDataList(ArrayList<CovidData> data)
    {
        originalData = data;
    }
    
    public void setDateList(ArrayList<String> dates)
    {
        this.dates = dates;
        setDateAppropriateData();
    }
    
    public ArrayList<CovidData> getDataList()
    {
        if(outputData == null)return originalData;
        return outputData;
    }
}
