import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.time.LocalDate;

/**
 * Write a description of class Statistics here.
 *
 * @author Harun Abukar
 * @version (a version number or a date)
 */
public class Statistics
{
    // list of all the statistics
    ArrayList<String> statList = new ArrayList<>();
    ArrayList<CovidData> data = new ArrayList<>();

    //instance of the class CovidDataLoader so that we can import data
    DateSelector dateSelector;
    /**
     * Constructor for objects of class Statistics
     */
    public Statistics(DateSelector dateSelector)
    {
        data = dateSelector.getFilter().getDataList();
        Collections.sort(data, new SortByDate());

        this.dateSelector = dateSelector;
        addStats();
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
        if(dateSelector.getFromValue() != null && dateSelector.getToValue() != null){
            LocalDate start = LocalDate.parse(dateSelector.getFromValue());
            LocalDate end = LocalDate.parse(dateSelector.getToValue());

            for(CovidData data : data){
                LocalDate date = LocalDate.parse(data.getDate());
                if (date.isEqual(start) || date.isEqual(end) || (date.isAfter(start) && date.isBefore(end))){
                    if(data.getNewDeaths() < 0){
                        int deaths = data.getNewDeaths() * -1;
                        totalLondonDeaths += deaths;
                    }
                    else{
                        totalLondonDeaths += data.getNewDeaths();
                    }
                }
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

        if(dateSelector.getFromValue() != null && dateSelector.getToValue() != null){
            LocalDate start = LocalDate.parse(dateSelector.getFromValue());
            LocalDate end = LocalDate.parse(dateSelector.getToValue());

            for(CovidData data : data){
                LocalDate date = LocalDate.parse(data.getDate());
                if (date.isEqual(start) || date.isEqual(end) || (date.isAfter(start) && date.isBefore(end))){
                    if(data.getNewDeaths() < 0){
                        int deaths = data.getNewCases() * -1;
                        averageCases += deaths;
                    }
                    else{
                        averageCases += data.getNewCases();
                    }
                }
            }
        }

        averageCases = averageCases / 33;
        return averageCases;

    }

    private int averageParksGMR(){
        int averageParksGMR = 0;
        int size = 0;
        if(dateSelector.getFromValue() != null && dateSelector.getToValue() != null){
            LocalDate start = LocalDate.parse(dateSelector.getFromValue());
            LocalDate end = LocalDate.parse(dateSelector.getToValue());
            for(CovidData data : data){
                LocalDate date = LocalDate.parse(data.getDate());
                if (date.isEqual(start) || date.isEqual(end) || (date.isAfter(start) && date.isBefore(end))){
                    size++;    
                   
                        averageParksGMR += data.getParksGMR();
                    
                }
            }
        }
        if(size > 0){
            averageParksGMR = averageParksGMR / size;
        }
        return averageParksGMR;
    }

    private int averageWorkplacesGMR(){
        int averageWorkplacesGMR = 0;
        int size = 0;
        if(dateSelector.getFromValue() != null && dateSelector.getToValue() != null){
            LocalDate start = LocalDate.parse(dateSelector.getFromValue());
            LocalDate end = LocalDate.parse(dateSelector.getToValue());
            for(CovidData data : data){
                LocalDate date = LocalDate.parse(data.getDate());
                if (date.isEqual(start) || date.isEqual(end) || (date.isAfter(start) && date.isBefore(end))){
                    size++;  
                    averageWorkplacesGMR += data.getWorkplacesGMR();
                }
            }
        }
        if(size > 0){
            averageWorkplacesGMR = averageWorkplacesGMR / size;
        }
        return averageWorkplacesGMR;
    }

    private String getHighestDeathDate(){
        CovidData maxValue = null;
        int maxDeaths = 0;
        for(CovidData data: data){
            if(data.getTotalDeaths() > maxDeaths){
                maxDeaths = data.getTotalDeaths();
                maxValue = data;
            }
        }
        return maxValue.getDate();
    }

    /*private void setDates(){
    dateSelector.getFromValue() = dateSelectorGUI.getFrom();
    dateSelector.getToValue() = dateSelectorGUI.getTo();
    }*/

    public ArrayList<CovidData> getData()
    {
        return data;
    }

    public void addStats(){
        data = dateSelector.getFilter().getDataList();
        statList.add("Total deaths in all London boroughs: \n" + loadTotalDeaths());
        statList.add("Average cases per London borough: \n" + averageCases());
        statList.add("Average parks GMR: \n" + averageParksGMR() + "%");
        statList.add("Average Workplaces GMR: \n" + averageWorkplacesGMR() + "%");
        statList.add("This date has the highest \n number of total deaths \n"
            + getHighestDeathDate());

    }

    public void removeStats(){
        statList.removeAll(statList);
    }

}

