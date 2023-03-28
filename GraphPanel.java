
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
public class GraphPanel
{
    private BorderPane root = new BorderPane();
    private ArrayList<CovidData> records;
    private ArrayList<String> years = new ArrayList<>();
    private HashSet<XYChart.Series> yearSeries = new HashSet<>();
    private String boroughValue;
    private String gmrValue;
    private String graphValue;

    public GraphPanel()
    {
       generatePanel(); 
    }
    
    private void generatePanel()
    {
        ComboBox graphTypeBox = new ComboBox();
        ComboBox gmrBox = new ComboBox();
        ComboBox boroughBox = new ComboBox();
        
        graphTypeBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gmrBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        boroughBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        CheckMenuItem year1 = new CheckMenuItem("2020");
        year1.setSelected(true);
        years.add(year1.getText());
        XYChart.Series year1Series = new XYChart.Series();
        year1Series.setName(year1.getText());
        yearSeries.add(year1Series);
        CheckMenuItem year2 = new CheckMenuItem("2021");
        CheckMenuItem year3 = new CheckMenuItem("2022");
        
        MenuButton yearsButton = new MenuButton("Year", null, year1, year2, year3);
        yearsButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        year1.setOnAction(this::selectSeries);
        year2.setOnAction(this::selectSeries);
        year3.setOnAction(this::selectSeries);
        
        HBox hbox = new HBox();
        hbox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        hbox.setSpacing(10);
        root.setTop(hbox);
        hbox.getChildren().addAll(boroughBox, gmrBox, graphTypeBox, yearsButton);
        
        graphTypeBox.getItems().add("Line Graph");
        graphTypeBox.getItems().add("Bar Chart");
        graphTypeBox.getItems().add("Scatter Graph");
        graphTypeBox.getSelectionModel().selectFirst();
        graphValue = (String) graphTypeBox.getValue();
        
        
        graphTypeBox.setOnAction(e -> graphClick(e));
        
        gmrBox.getItems().add("Grocery And Pharmacy");
        gmrBox.getItems().add("Parks");
        gmrBox.getItems().add("Residential");
        gmrBox.getItems().add("Retail and Recreation");
        gmrBox.getItems().add("Transit Stations");
        gmrBox.getItems().add("Workplaces");
        gmrBox.getSelectionModel().selectFirst();
        gmrValue = (String) gmrBox.getValue();
        
        gmrBox.setOnAction(e -> gmrClick(e));
        
        CovidDataLoader loader = new CovidDataLoader();
        loader.load();
        
        records = loader.getData();
        
        Collections.sort(records, new SortByDate());
        
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
        
        boroughBox.getSelectionModel().selectFirst();
        boroughValue = (String) boroughBox.getValue();
        
        boroughBox.setOnAction(e -> boroughClick(e));
        generateGraph();
        
        root.setId("root");
        root.getStylesheets().add("GraphPanel.css");

    }

    private void selectSeries(ActionEvent event)
    {
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
        
        generateGraph();
    }
    
    private void addSeries(XYChart.Series series)
    {
        yearSeries.add(series); 
    }
    
    private void removeSeries(XYChart.Series series)
    {
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
    
    private void generateGraph()
    {
       final NumberAxis yAxis = new NumberAxis();
       final CategoryAxis xAxis = new CategoryAxis();
       yAxis.setTickLabelFill(Color.WHITE);
       xAxis.setTickLabelFill(Color.WHITE);
       String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", 
           "Aug", "Sep", "Oct", "Nov", "Dec"}; 
       ObservableList<String> categories = FXCollections.observableArrayList();
       categories.addAll(months);
       xAxis.setCategories(categories);
       yAxis.setLabel("Activity change %");
       xAxis.setLabel("Month");
       
       clearSeriesData();
       
       yearSeries.clear();
      
       for(String year: years)
       {
           XYChart.Series series = new XYChart.Series();
           series.setName(year);
           addSeries(series);
           
       }
   
       
       for(CovidData record: records)
       {
           if(record.getBorough().equals(boroughValue) && record.getDate().substring(8,10).equals("15"))
           {
               int month = Integer.parseInt(record.getDate().substring(5,7));
               String monthString = new DateFormatSymbols().getMonths()[month-1];
               monthString = monthString.substring(0,3);
            
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
    
    private void graphClick(Event event)
    {
       root.setCenter(null);
       ComboBox graphType = (ComboBox) event.getSource();
       graphValue = (String) graphType.getValue();
       clearSeriesData();
       generateGraph();
    
    }
    
    private void boroughClick(Event event)
    {
       root.setCenter(null);
       ComboBox boroughBox = (ComboBox) event.getSource();
       boroughValue = (String) boroughBox.getValue();
       clearSeriesData();
       generateGraph();    
    }
    
    private void gmrClick(Event event)
    {
       root.setCenter(null);
       ComboBox gmrBox = (ComboBox) event.getSource();
       gmrValue = (String) gmrBox.getValue();
       clearSeriesData();
       generateGraph();
        
    }
    
  
    private void clearSeriesData()
    {
        for(XYChart.Series series: yearSeries)
        {
            series.getData().clear();
        }
    }
    
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
    
    public BorderPane getMainPane()
    {
        return root;
    }
}