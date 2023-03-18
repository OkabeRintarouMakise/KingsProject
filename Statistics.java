import java.util.ArrayList;
import java.util.Date;

/**
 * Write a description of class Statistics here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Statistics
{
    ArrayList<String> statList = new ArrayList<String>();
    CovidDataLoader statisticsLoader = new CovidDataLoader();
    ArrayList<Date> dateRange = new ArrayList<Date>();

    /**
     * Constructor for objects of class Statistics
     */
    public Statistics()
    {
        loadStats();
        statList.add("Total deaths in all London boroughs: \n" + loadTotalDeaths());
        statList.add("Average cases per London borough: \n" + averageCases());
        statList.add("statistic 3");
        statList.add("statistic 4");
        statList.add("statistic 5");

    }
    /**
     * An example of a method - replace this comment with your own

     */
    public String getStat(int index){
        return statList.get(index);
    }

    private void loadStats(){
        statisticsLoader.load();
    }

    public int statListSize(){
        return statList.size();
    }

    private int loadTotalDeaths(){
  
        int totalLondonDeaths = 0;
        for(CovidData record : statisticsLoader.getData()){
            totalLondonDeaths += record.getNewDeaths();
        }
        return totalLondonDeaths;
    }

    private int averageCases(){
 
        int averageCases = 0;
        for(CovidData record : statisticsLoader.getData()){
            averageCases += record.getNewCases();
        }
        averageCases = averageCases / 33;
        return averageCases;

    }
}
