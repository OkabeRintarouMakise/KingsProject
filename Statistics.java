import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
/**
 * Write a description of class Statistics here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Statistics
{
    // list of all the statistics
    ArrayList<String> statList = new ArrayList<>();
    ArrayList<CovidData> allData = new ArrayList<>();
    //instance of the class CovidDataLoader so that we can import data
    CovidDataLoader statisticsLoader = new CovidDataLoader();

    private String first;
    private String last;
    CovidDataGUI mainGUI = new CovidDataGUI();

    /**
     * Constructor for objects of class Statistics
     */
    public Statistics()
    {
        setDates();
        statisticsLoader.load();

        allData = statisticsLoader.getData();
        Collections.sort(allData, new SortByDate());

        statList.add("Total deaths in all London boroughs: \n" + loadTotalDeaths());
        statList.add("Average cases per London borough: \n" + averageCases());
        statList.add("Average parks GMR: \n" + averageParksGMR() + "%");
        statList.add("Average Workplaces GMR: \n" + averageWorkplacesGMR() + "%");
        statList.add("This date has the highest \n number of total deaths \n"
        + getHighestDeathDate());

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

        for(int i = 0; i < allData.size(); i++){
            if(first == null || last == null){
                totalLondonDeaths += allData.get(i).getNewDeaths();
            }

            else if(i >= allData.indexOf(first) && i <= allData.indexOf(last)){
                totalLondonDeaths += allData.get(i).getNewDeaths();
            }
        }
        return totalLondonDeaths;
    }

    /**
    @return the number of average cases in all London boroughs
    during the time period
     */
    private int averageCases(){

        int averageCases = 0;

        for(int i = 0; i < allData.size(); i++){
            if(first == null || last == null){
                averageCases += allData.get(i).getNewCases();
            }
            else if(i >= allData.indexOf(first) && i <= allData.indexOf(last)){
                averageCases += allData.get(i).getNewCases();
            }
        }

        averageCases = averageCases / 33;
        return averageCases;

    }

    private int averageParksGMR(){

        int averageParksGMR = 0;

        for(int i = 0; i < allData.size(); i++){
            if(first == null || last == null){
                averageParksGMR += allData.get(i).getParksGMR();
            }
            else if(i >= allData.indexOf(first) && i <= allData.indexOf(last)){
                averageParksGMR += allData.get(i).getParksGMR();
            }
        }
        averageParksGMR = averageParksGMR / allData.size();
        return averageParksGMR;

    }

    private int averageWorkplacesGMR(){

        int averageWorkplacesGMR = 0;

        for(int i = 0; i < allData.size(); i++){
            if(first == null || last == null){
                averageWorkplacesGMR += allData.get(i).getWorkplacesGMR();
            }
            else if(i >= allData.indexOf(first) && i <= allData.indexOf(last)){
                averageWorkplacesGMR += allData.get(i).getWorkplacesGMR();
            }
        }
        averageWorkplacesGMR = averageWorkplacesGMR / allData.size();
        return averageWorkplacesGMR;

    }

    private String getHighestDeathDate(){
        CovidData maxValue = null;
        int maxDeaths = 0;
        for(CovidData data: allData){
            if(data.getTotalDeaths() > maxDeaths){
            maxDeaths = data.getTotalDeaths();
            maxValue = data;
            }
        }
        return maxValue.getDate();
    }

    private void setDates(){
        first = mainGUI.getFrom();
        last = mainGUI.getTo();
    }

    public ArrayList<CovidData> getAllData()
    {
        return allData;
    }
}

