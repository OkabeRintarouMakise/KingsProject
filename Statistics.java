import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;

/**
 * Statistics is responsible for calculating the values for each statistics and storing
 * each statistic into an arraylist called "statList"
 *
 * @author Harun Abukar
 * @version 26/03/2023
 */
public class Statistics
{
    // list of all the statistics
    ArrayList<String> statList = new ArrayList<>();
    ArrayList<CovidData> data = new ArrayList<>();

    //instance of the class CovidDataLoader so that we can import data
    DateSelector dateSelector;
    /**
     * Constructor for Statistics
     * loads the collection of covid data then sorts the data in increasing date order
     * statistics added to arraylist "statlist"
     */
    public Statistics(DateSelector dateSelector)
    {
        data = dateSelector.getFilter().getDataList();
        Collections.sort(data, new SortByDate());

        this.dateSelector = dateSelector;
        addStats();
    }

    /**
     * @return an entry in statList using @param index
     *
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
     * @return the total number of deaths in London during the time period
     */
    private int loadTotalDeaths(){
        int totalLondonDeaths = 0;
        for(CovidData data : data){
            if(data.getDate().equals(dateSelector.getToValue())){
                totalLondonDeaths += data.getTotalDeaths();
            }
        }
        if(totalLondonDeaths <= 0){
            totalLondonDeaths = 0;
        }
        return totalLondonDeaths;
    }

    /**
     * @return the number of average cases in all London boroughs
     */
    private int averageCases(){
        int averageCases = 0;
        for(CovidData data : data){
            if(data.getDate().equals(dateSelector.getToValue())){
                averageCases += data.getTotalCases();
            }
        }
        averageCases = averageCases / 33;
        return averageCases;
    }

    /**
     * @return the percentage change in the parks google mobility measure
     * during the time period
     */
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

    /**
     * @return the percentage change in the workplaces google mobility measure
     * during the time period
     */
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

    /**
     * @return the date with the highest number of covid deaths up to date, which is the
     * latest date selected
     */
    private String getHighestDeathDate(){
        return dateSelector.getToValue();
    }

    /**
     * @return the arraylist of all the covid data
     */
    public ArrayList<CovidData> getData()
    {
        return data;
    }

    /**
     * initialise arraylist "statlist" by adding the statistics 
     */
    public void addStats(){
        data = dateSelector.getFilter().getDataList();
        statList.add("Total deaths in all London boroughs: \n" + loadTotalDeaths());
        statList.add("Average cases per London borough: \n" + averageCases());
        statList.add("Average parks GMR: \n" + averageParksGMR() + "%");
        statList.add("Average Workplaces GMR: \n" + averageWorkplacesGMR() + "%");
        statList.add("This date has the highest \n number of total deaths \n"
            + getHighestDeathDate());

    }
    /**
     * remove all entries from arraylist "statlist"
     */
    public void removeStats(){
        statList.removeAll(statList);
    }

}

