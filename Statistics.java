import java.util.ArrayList;

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

    /**
     * Constructor for objects of class Statistics
     */
    public Statistics()
    {
        statList.add("Total deaths in all London boroughs: " + loadTotalDeaths());
        statList.add("Average cases per London borough: " + averageCases());
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
        loadStats();
        int totalLondonDeaths = 0;
        for(CovidData record : statisticsLoader.getData()){
            totalLondonDeaths += record.getNewDeaths();
        }
        return totalLondonDeaths;
    }

    private int averageCases(){
        loadStats();
        int averageCases = 0;
        for(CovidData record : statisticsLoader.getData()){
            averageCases += record.getNewCases();
        }
        averageCases = averageCases / 33;
        return averageCases;

    }
}
