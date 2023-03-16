

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
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.CheckMenuItem;
import java.text.DateFormatSymbols;
import java.util.Comparator;


/**
 * Write a description of JavaFX class GraphPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GraphPanel extends Application
{
    private BorderPane root = new BorderPane();
    private ArrayList<CovidData> records;
    private String boroughValue;
    private String gmrValue;
    private String graphValue;
    private String yearValue;
    
    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage)
    {
        ComboBox graphTypeBox = new ComboBox();
        ComboBox gmrBox = new ComboBox();
        ComboBox boroughBox = new ComboBox();
        
        CheckMenuItem year1 = new CheckMenuItem("2020");
        year1.setSelected(true);
        CheckMenuItem year2 = new CheckMenuItem("2021");
        CheckMenuItem year3 = new CheckMenuItem("2022");
        CheckMenuItem year4 = new CheckMenuItem("2023");
        
        MenuButton yearsButton = new MenuButton("Year", null, year1, year2, year3, year4);
        
        HBox hbox = new HBox();
        hbox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        root.setTop(hbox);
        hbox.getChildren().addAll(boroughBox, gmrBox, graphTypeBox, yearsButton);
        
        graphTypeBox.getItems().add("Line Graph");
        graphTypeBox.getItems().add("Bar Chart");
        graphTypeBox.getItems().add("Scatter Graph");
        graphTypeBox.getSelectionModel().selectFirst();
        
        graphTypeBox.setOnAction(e -> buttonClick(e));
        
        gmrBox.getItems().add("Grocery And Pharmacy");
        gmrBox.getItems().add("Parks");
        gmrBox.getItems().add("Residential");
        gmrBox.getItems().add("Retail and Recreation");
        gmrBox.getItems().add("Transit Stations");
        gmrBox.getItems().add("Workplaces");
        gmrBox.getSelectionModel().selectFirst();
        
        // JavaFX must have a Scene (window content) inside a Stage (window)
        stage.setTitle("Scatter Chart Example");
        final NumberAxis yAxis = new NumberAxis();
        final CategoryAxis xAxis = new CategoryAxis();
        
        final LineChart lineChart = new LineChart(xAxis, yAxis);
        root.setCenter(lineChart);
        yAxis.setLabel("Activity change %");
        xAxis.setLabel("Day/Month");
        lineChart.setTitle("Google Mobility Measures");
        
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Parks");
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Transit");
        
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
        
        for(CovidData record: records)
        {
           if(record.getDate().equals("2022-10-15"))
           {
               series1.getData().add(new XYChart.Data(record.getBorough(), record.getParksGMR())); 
               series2.getData().add(new XYChart.Data(record.getBorough(), record.getTransitGMR())); 
           }
        }
        
        lineChart.getData().addAll(series1, series2);
        Scene scene = new Scene(root, 600,500);
        stage.setScene(scene);

        // Show the Stage (window)
        stage.show();
    }

    private void buttonClick(Event event)
    {
       root.setCenter(null);
       ComboBox graphType = (ComboBox) event.getSource();
       graphValue = (String) graphType.getValue();
       
       final NumberAxis yAxis = new NumberAxis();
       final CategoryAxis xAxis = new CategoryAxis();
       yAxis.setLabel("Activity change %");
       xAxis.setLabel("Month");
       
       XYChart.Series series1 = new XYChart.Series();
       series1.setName("Parks");
        
       XYChart.Series series2 = new XYChart.Series();
       series2.setName("Transit");
       
       for(CovidData record: records)
       {
           if(record.getDate().startsWith("2021") && record.getBorough().equals(boroughValue))
           {
               int month = Integer.parseInt(record.getDate().substring(5,7));
               String monthString = new DateFormatSymbols().getMonths()[month-1];
               monthString = monthString.substring(0,3);
               series1.getData().add(new XYChart.Data(monthString, record.getParksGMR())); 
               series2.getData().add(new XYChart.Data(monthString, record.getTransitGMR())); 
           }
       }

       if(graphValue.equals("Bar Chart"))
       {
           final BarChart barChart = new BarChart(xAxis, yAxis);
           root.setCenter(barChart);
           barChart.setTitle("Google Mobility Measures");    
           barChart.getData().addAll(series1, series2);
       }
       else if(graphValue.equals("Line Graph"))
       {
           final LineChart lineChart = new LineChart(xAxis, yAxis);
           root.setCenter(lineChart);
           lineChart.setTitle("Google Mobility Measures");  
           lineChart.getData().addAll(series1, series2);
       }
       else if(graphValue.equals("Scatter Graph"))
       {
           final ScatterChart scatterChart = new ScatterChart(xAxis, yAxis);
           root.setCenter(scatterChart);
           scatterChart.setTitle("Google Mobility Measures");  
           scatterChart.getData().addAll(series1, series2);
       }
    
    }
}

class SortByDate implements Comparator<CovidData>
{
    public int compare(CovidData a, CovidData b)
    {
        return a.getDate().compareTo(b.getDate());
    }
}
