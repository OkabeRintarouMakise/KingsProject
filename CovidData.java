import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
 

/**
 * Represents one record in the COVID dataset.
 * This is essentially one row in the data table. Each column
 * has a corresponding field.
 */ 

public class CovidData {

    /*
    The date the COVID information (cases & deaths) was collected
    */
    SimpleStringProperty date;
    
    /*
    The COVID information is organised by (London) borough
    */
    SimpleStringProperty borough;
    
    
    /*
    The COVID information that's collected daily for each London borough
    */
    SimpleIntegerProperty newCases;
    SimpleIntegerProperty totalCases;
    SimpleIntegerProperty newDeaths;
    SimpleIntegerProperty totalDeaths;
    
    
    /*
    Google analysed location data from Android smartphones to measure movement
    in London.  The data shows percent change from the baseline.  For example, 
    a negative value means there's less human traffic compared to the baseline.
    */
    SimpleIntegerProperty retailRecreationGMR;
    SimpleIntegerProperty groceryPharmacyGMR;
    SimpleIntegerProperty parksGMR;
    SimpleIntegerProperty transitGMR;
    SimpleIntegerProperty workplacesGMR;
    SimpleIntegerProperty residentialGMR;



    public CovidData(String date, String borough, int retailRecreationGMR, int groceryPharmacyGMR, 
                        int parksGMR, int transitGMR, int workplacesGMR, int residentialGMR, 
                        int newCases, int totalCases, int newDeaths, int totalDeaths) {

        this.date = new SimpleStringProperty(date); 
        this.borough = new SimpleStringProperty(borough);
        this.retailRecreationGMR = new SimpleIntegerProperty(retailRecreationGMR);
        this.groceryPharmacyGMR = new SimpleIntegerProperty(groceryPharmacyGMR);
        this.parksGMR = new SimpleIntegerProperty(parksGMR);
        this.transitGMR = new SimpleIntegerProperty(transitGMR);
        this.workplacesGMR = new SimpleIntegerProperty(workplacesGMR);
        this.residentialGMR = new SimpleIntegerProperty(residentialGMR);
        this.newCases = new SimpleIntegerProperty(newCases);
        this.totalCases = new SimpleIntegerProperty(totalCases);
        this.newDeaths = new SimpleIntegerProperty(newDeaths);
        this.totalDeaths = new SimpleIntegerProperty(totalDeaths);
    }


    public String getDate() {
        return date.get();
    }


    public String getBorough() {
        return borough.get();
    }


    public int getRetailRecreationGMR() {
        return retailRecreationGMR.get();
    }


    public int getGroceryPharmacyGMR() {
        return groceryPharmacyGMR.get();
    }


    public int getParksGMR() {
        return parksGMR.get();
    }


    public int getTransitGMR() {
        return transitGMR.get();
    }


    public int getWorkplacesGMR() {
        return workplacesGMR.get();
    }


    public int getResidentialGMR() {
        return residentialGMR.get();
    }


    public int getNewCases() {
        return newCases.get();
    }


    public int getTotalCases() {
        return totalCases.get();
    }


    public int getNewDeaths() {
        return newDeaths.get();
    }


    public int getTotalDeaths() {
        return totalDeaths.get();
    }

    @Override
    public String toString() {
        return "Covid Record {" + 
        " date='" + date.get() +'\'' +
        ", borough='" + borough.get() +'\'' +
        ", retailRecreationGMR=" + retailRecreationGMR.get() + 
        ", groceryPharmacyGMR=" + groceryPharmacyGMR.get() + 
        ", parksGMR=" + parksGMR.get() + 
        ", transitGMR=" + transitGMR.get() + 
        ", workplacesGMR=" + workplacesGMR.get() + 
        ", residentialGMR=" + residentialGMR.get() + 
        ", newCases=" + newCases.get() + 
        ", totalCases=" + totalCases.get() + 
        ", newDeaths=" + newDeaths.get() + 
        ", totalDeaths=" + totalDeaths.get() + 
        "}";
    }
}
