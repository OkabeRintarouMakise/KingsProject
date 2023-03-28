
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.text.DateFormatSymbols;


/**
 * This class represents the 4th panel.
 * For this panel, we have decided to implement a graph generator.
 * This graph generator will display all the covid data google mobility measures (gmr),
 * per borough, and can be filtered by the type of graph you want, the gmr you want
 * and it will plot the activity change percentage of each gmr based on the years 2020, 2021 and 2022.
 * @author Armaan Uddin
 * @version 27/03/2023
 */
public class GraphPanel implements Panel
{
    private BorderPane root = new BorderPane(); //the root pane of this panel
    private ArrayList<CovidData> records; //holds the entire covid data records
    private ArrayList<String> years = new ArrayList<>(); //used to track which years are in the series
    private HashSet<XYChart.Series> yearSeries = new HashSet<>(); //used to hold series and the data inside the series
    private String boroughValue; //current borough displayed on graph
    private String gmrValue; //current gmr displayed on graph
    private String graphValue; //current graph type displayed

    /**
     * Creates a GraphPanel object.
     */
    public GraphPanel()
    {
       generatePanel(); 
    }
    
    /**
     * Generates the graph panel. 
     * Creates a panel with the drop down buttons for filtering the graph.
     */
    private void generatePanel()
    {
        //create drop down buttons
        ComboBox graphTypeBox = new ComboBox();
        ComboBox gmrBox = new ComboBox();
        ComboBox boroughBox = new ComboBox();
        
        //make sure drop down buttons stay same size as you rescale window
        graphTypeBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gmrBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        boroughBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        //create drop down for series (each year is a series)
        CheckMenuItem year1 = new CheckMenuItem("2020");
        year1.setSelected(true); //intialise the first value so a graph is generated straight away
        years.add(year1.getText());
        XYChart.Series year1Series = new XYChart.Series();
        year1Series.setName(year1.getText());
        yearSeries.add(year1Series);
        CheckMenuItem year2 = new CheckMenuItem("2021");
        CheckMenuItem year3 = new CheckMenuItem("2022");
        
        //put drop down for series as a button which calls a method to select the series when clicked
        MenuButton yearsButton = new MenuButton("Year", null, year1, year2, year3);
        yearsButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        year1.setOnAction(this::selectSeries);
        year2.setOnAction(this::selectSeries);
        year3.setOnAction(this::selectSeries);
        
        //add all drop down buttons to the top of the panel
        HBox hbox = new HBox();
        hbox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        hbox.setSpacing(10);
        root.setTop(hbox);
        hbox.getChildren().addAll(boroughBox, gmrBox, graphTypeBox, yearsButton);
        
        //add different graph type options and initialise graph with the first value
        graphTypeBox.getItems().add("Line Graph");
        graphTypeBox.getItems().add("Bar Chart");
        graphTypeBox.getItems().add("Scatter Graph");
        graphTypeBox.getSelectionModel().selectFirst();
        graphValue = (String) graphTypeBox.getValue();
        
        //when clicked, call the method graphClick which will set the graph type
        graphTypeBox.setOnAction(e -> graphClick(e));
        
        //add different gmr options and initialise graph with the first value
        gmrBox.getItems().add("Grocery And Pharmacy");
        gmrBox.getItems().add("Parks");
        gmrBox.getItems().add("Residential");
        gmrBox.getItems().add("Retail and Recreation");
        gmrBox.getItems().add("Transit Stations");
        gmrBox.getItems().add("Workplaces");
        gmrBox.getSelectionModel().selectFirst();
        gmrValue = (String) gmrBox.getValue();
        
        //when clicked, call the method gmrClick which will set the gmr
        gmrBox.setOnAction(e -> gmrClick(e));
        
        //load the covid data records into an ArrayList and sort the arraylist in date order using a helper class
        CovidDataLoader loader = new CovidDataLoader();
        loader.load();
        records = loader.getData();
        Collections.sort(records, new SortByDate());
        
        //alphabetically sort boroughs so easier to find when scrolling down on the drop down button for borough
        String[] boroughs = new String[33];
        
        for(int i = 0; i < boroughs.length; i++)
        {
            boroughs[i] = records.get(i).getBorough();
        }
        
        Arrays.sort(boroughs);
        
        for(int i = 0; i < boroughs.length; i++)
        {
            boroughBox.getItems().add(boroughs[i]);
        }
        
        //initialise graph with the first value
        boroughBox.getSelectionModel().selectFirst();
        boroughValue = (String) boroughBox.getValue();
        
        //when clicked, call the method boroughClick which will set the borough value
        boroughBox.setOnAction(e -> boroughClick(e));
        
        //add stylesheet for the graph to style it
        root.setId("root");
        root.getStylesheets().add("GraphPanel.css");
        
        //generate graph, using initial values as initialised above
        generateGraph();

    }

    /**
     * When a checkmenuitem in the year drop down box is clicked, 
     * check if it is selected or deselected, and create a new
     * series with the year as the name or if deselected remove
     * the series.
     * @param event Records a button click.
     */
    private void selectSeries(ActionEvent event)
    {
        //gets the value of the checkmenuitem clicked
        CheckMenuItem year = (CheckMenuItem) event.getSource();   
        XYChart.Series series = new XYChart.Series();
        series.setName(year.getText());
        
        if(year.isSelected())
        {
            years.add(series.getName());
            addSeries(series);
        }
        
        if(!year.isSelected())
        {
            years.remove(series.getName());
            removeSeries(series);
        }
        
        //regenerate graph to reflect changes
        generateGraph();
    }
    
    /**
     * Add a series to the graph.
     * @param series A series whose name corresponds to a year between 2020 and 2022.
     */
    private void addSeries(XYChart.Series series)
    {
        yearSeries.add(series); 
    }
    
    /**
     * Remove a series from the graph.
     * @param series A series whose name corresponds to a year between 2020 and 2022.
     */
    private void removeSeries(XYChart.Series series)
    {
        //use iterator to remove as you're iterating over a collection and modifying it
        Iterator<XYChart.Series> it = yearSeries.iterator();
           
        while(it.hasNext())
        {
            XYChart.Series serie = it.next();
            if(serie.getName().equals(series.getName()))
            {
                serie.getData().clear();
                it.remove();
            }
        }  
        
    }
    
    /**
     * Generates a graph.
     */
    private void generateGraph()
    {
       //create axis for data
       final NumberAxis yAxis = new NumberAxis(); //will measure activity change
       final CategoryAxis xAxis = new CategoryAxis(); //will determine month of year
       yAxis.setTickLabelFill(Color.WHITE); //colour the ticks of the graph was white
       xAxis.setTickLabelFill(Color.WHITE);
       
       //create month categories and add them to xAxis
       String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", 
           "Aug", "Sep", "Oct", "Nov", "Dec"}; 
       ObservableList<String> categories = FXCollections.observableArrayList();
       categories.addAll(months);
       xAxis.setCategories(categories);
       yAxis.setLabel("Activity change %");
       xAxis.setLabel("Month");
    
       /* remove all of the series from the graph
        * as otherwise previous data points will skew graph
        */
       yearSeries.clear();
      
       //add all of the series back to the graph, note they are new objects
       for(String year: years)
       {
           XYChart.Series series = new XYChart.Series();
           series.setName(year);
           addSeries(series);
           
       }
   
       //iterate through the data to add data to the series based on conditions
       for(CovidData record: records)
       {
           //get relevant data to the borough and data from the 15th of each month for median
           if(record.getBorough().equals(boroughValue) && record.getDate().substring(8,10).equals("15"))
           {
               //convert the numbers 1-12, into the months January-December
               //shorten months to first three letters, i.e. Jan-Dec
               int month = Integer.parseInt(record.getDate().substring(5,7));
               String monthString = new DateFormatSymbols().getMonths()[month-1];
               monthString = monthString.substring(0,3);
            
               //add data to the relevant year
               for(XYChart.Series series: yearSeries)
               {
                
                   if(series.getName().equals(record.getDate().substring(0,4)))
                   {
                       XYChart.Data data = new XYChart.Data(monthString, getGMR(record));
                       series.getData().add(data);
                   }
               }
        
           }
       }
       
       //generate graph based on the type of graph
       if(graphValue.equals("Bar Chart"))
       {
           final BarChart barChart = new BarChart(xAxis, yAxis);
           root.setCenter(barChart);
           barChart.setTitle(boroughValue); 
           
           for(XYChart.Series series: yearSeries)
           {
               barChart.getData().add(series);
           }
           
           barChart.setAnimated(false);
       }
       else if(graphValue.equals("Line Graph"))
       {
           final LineChart lineChart = new LineChart(xAxis, yAxis);
           root.setCenter(lineChart);
           lineChart.setTitle(boroughValue);  
           
           for(XYChart.Series series: yearSeries)
           {
               lineChart.getData().add(series);
           }
           
           lineChart.setAnimated(false);
           lineChart.setCreateSymbols(false);
        }
       else if(graphValue.equals("Scatter Graph"))
       {
           final ScatterChart scatterChart = new ScatterChart(xAxis, yAxis);
           root.setCenter(scatterChart);
           scatterChart.setTitle(boroughValue);  
           
           for(XYChart.Series series: yearSeries)
           {
               scatterChart.getData().add(series);
           }
           
           scatterChart.setAnimated(false);
       }
       
    }
    
    /**
     * Choose the selected graph type and regenerate graph as such.
     * @param event Records a button click.
     */
    private void graphClick(Event event)
    {
       root.setCenter(null);
       ComboBox graphType = (ComboBox) event.getSource();
       graphValue = (String) graphType.getValue();
       clearSeriesData();
       generateGraph();
    
    }
    
    /**
     * Choose the selected borough and regenerate graph as such.
     * @param event Records a button click.
     */
    private void boroughClick(Event event)
    {
       root.setCenter(null);
       ComboBox boroughBox = (ComboBox) event.getSource();
       boroughValue = (String) boroughBox.getValue();
       clearSeriesData();
       generateGraph();    
    }
    
    /**
     * Choose the selected gmr and regenerate graph as such.
     * @param event Records a button click.
     */
    private void gmrClick(Event event)
    {
       root.setCenter(null);
       ComboBox gmrBox = (ComboBox) event.getSource();
       gmrValue = (String) gmrBox.getValue();
       clearSeriesData();
       generateGraph();
        
    }
    
    /**
     * Clear of all the data in each series.
     */
    private void clearSeriesData()
    {
        for(XYChart.Series series: yearSeries)
        {
            series.getData().clear();
        }
    }
    
    /**
     * Check which gmr is needed from the CovidData record
     * and call the relevant method to fetch that data.
     * @param record The record in the csv file that the gmr is being extracted out of.
     * @return The activity change percentage for the gmr selected in the record being checked.
     */
    private int getGMR(CovidData record)
    {
        int activityChangePercentage = 0;
        
        if(gmrValue.equals("Grocery And Pharmacy"))
        {
            activityChangePercentage = record.getGroceryPharmacyGMR(); 
        }
        else if(gmrValue.equals("Parks"))
        {
            activityChangePercentage = record.getParksGMR(); 
        }
        else if(gmrValue.equals("Residential"))
        {
            activityChangePercentage = record.getResidentialGMR();     
        }
        else if(gmrValue.equals("Retail and Recreation"))
        {
            activityChangePercentage = record.getRetailRecreationGMR();     
        }
        else if(gmrValue.equals("Transit Stations"))
        {
            activityChangePercentage = record.getTransitGMR();     
        }
        else if(gmrValue.equals("Workplaces"))
        {
            activityChangePercentage = record.getWorkplacesGMR();     
        }
            
        return(activityChangePercentage);
    }
    
    /**
     * Will return the panel when called.
     * @return the root pane.
     */
    @Override 
    public BorderPane getMainPane()
    {
        return root;
    }
}