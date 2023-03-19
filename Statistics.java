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
    // list of all the statistics
    ArrayList<String> statList = new ArrayList<String>();
    
    //instance of the class CovidDataLoader so that we can import data
    CovidDataLoader statisticsLoader = new CovidDataLoader();


    /**
     * Constructor for objects of class Statistics
     */
    public Statistics()
    {
        statisticsLoader.load();
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

    /**
     * @return the number of statistics in the statistics list
     */
    public int statListSize(){
        return statList.size();
    }

    /**
    @return the total number of deaths in London during the time period
     */
    private int loadTotalDeaths(){

        int totalLondonDeaths = 0;
        for(CovidData record : statisticsLoader.getData()){
            totalLondonDeaths += record.getNewDeaths();
        }
        return totalLondonDeaths;
    }

    /**
    @return the number of average cases in all London boroughs
    during the time period
     */
    private int averageCases(){

        int averageCases = 0;
        for(CovidData record : statisticsLoader.getData()){
            averageCases += record.getNewCases();
        }
        averageCases = averageCases / 33;
        return averageCases;

    }
}
