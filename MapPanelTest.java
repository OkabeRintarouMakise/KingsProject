

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.collections.ObservableList;

/**
 * The test class MapPanelTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MapPanelTest
{
    private DateSelector dateSele1;
    private MapPanel mapPanel1;


    /**
     * Default constructor for test class MapPanelTest
     */
    public MapPanelTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        dateSele1 = new DateSelector();
        mapPanel1 = new MapPanel(dateSele1);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }


    

    @Test
    public void CheckForVBox()
    {
        assertNotNull(mapPanel1.getMainPane());
    }

    @Test
    public void CheckForVBoxChildren()
    {
        javafx.scene.layout.VBox vBox1 = mapPanel1.getMainPane();
        assertNotNull(vBox1.getChildren());
    }

    @Test
    public void CheckForFirstLevelButtons()
    {
        javafx.scene.layout.VBox vBox1 = mapPanel1.getMainPane();
        javafx.collections.ObservableList<javafx.scene.Node> observList = vBox1.getChildren();
        assertEquals(false,observList.isEmpty());
        BoroughButton boroughB1 = (BoroughButton)observList.get(0);
        assertEquals("enfi", boroughB1.toString());
    }

    @Test
    public void CheckForSecondLevelButtons()
    {
        javafx.scene.layout.VBox vBox1 = mapPanel1.getMainPane();
        javafx.collections.ObservableList<javafx.scene.Node> observList = vBox1.getChildren();
        javafx.scene.layout.HBox hBox1 = (javafx.scene.layout.HBox)observList.get(1);
        javafx.collections.ObservableList<javafx.scene.Node> observList2 = hBox1.getChildren();
        BoroughButton boroughB1 = (BoroughButton)observList2.get(0);
        assertEquals("barn", boroughB1.toString());
        BoroughButton boroughB2 = (BoroughButton)observList2.get(1);
        assertEquals("hrgy", boroughB2.toString());
        BoroughButton boroughB3 = (BoroughButton)observList2.get(2);
        assertEquals("walt", boroughB3.toString());
    }

    @Test
    public void CheckForThirdLevelButtons()
    {
        javafx.scene.layout.VBox vBox1 = mapPanel1.getMainPane();
        javafx.collections.ObservableList<javafx.scene.Node> observList = vBox1.getChildren();
        javafx.scene.layout.HBox hBox1 = (javafx.scene.layout.HBox)observList.get(2);
        javafx.collections.ObservableList<javafx.scene.Node> observList2 = hBox1.getChildren();
        BoroughButton boroughB1 = (BoroughButton)observList2.get(0);
        assertEquals("hrrw", boroughB1.toString());
        BoroughButton boroughB2 = (BoroughButton)observList2.get(1);
        assertEquals("bren", boroughB2.toString());
        BoroughButton boroughB3 = (BoroughButton)observList2.get(2);
        assertEquals("camd", boroughB3.toString());
        BoroughButton boroughB4 = (BoroughButton)observList2.get(3);
        assertEquals("isli", boroughB4.toString());
        BoroughButton boroughB5 = (BoroughButton)observList2.get(4);
        assertEquals("hack", boroughB5.toString());
        BoroughButton boroughB6 = (BoroughButton)observList2.get(5);
        assertEquals("redb", boroughB6.toString());
        BoroughButton boroughB7 = (BoroughButton)observList2.get(6);
        assertEquals("have", boroughB7.toString());
    }
    
    @Test
    public void CheckForFourthLevelButtons()
    {
        javafx.scene.layout.VBox vBox1 = mapPanel1.getMainPane();
        javafx.collections.ObservableList<javafx.scene.Node> observList = vBox1.getChildren();
        javafx.scene.layout.HBox hBox1 = (javafx.scene.layout.HBox)observList.get(3);
        javafx.collections.ObservableList<javafx.scene.Node> observList2 = hBox1.getChildren();
        BoroughButton boroughB1 = (BoroughButton)observList2.get(0);
        assertEquals("hill", boroughB1.toString());
        BoroughButton boroughB2 = (BoroughButton)observList2.get(1);
        assertEquals("eali", boroughB2.toString());
        BoroughButton boroughB3 = (BoroughButton)observList2.get(2);
        assertEquals("kens", boroughB3.toString());
        BoroughButton boroughB4 = (BoroughButton)observList2.get(3);
        assertEquals("wstm", boroughB4.toString());
        BoroughButton boroughB5 = (BoroughButton)observList2.get(4);
        assertEquals("towh", boroughB5.toString());
        BoroughButton boroughB6 = (BoroughButton)observList2.get(5);
        assertEquals("newh", boroughB6.toString());
        BoroughButton boroughB7 = (BoroughButton)observList2.get(6);
        assertEquals("bark", boroughB7.toString());
    }
    
    @Test
    public void CheckForFifthLevelButtons()
    {
        javafx.scene.layout.VBox vBox1 = mapPanel1.getMainPane();
        javafx.collections.ObservableList<javafx.scene.Node> observList = vBox1.getChildren();
        javafx.scene.layout.HBox hBox1 = (javafx.scene.layout.HBox)observList.get(4);
        javafx.collections.ObservableList<javafx.scene.Node> observList2 = hBox1.getChildren();
        BoroughButton boroughB1 = (BoroughButton)observList2.get(0);
        assertEquals("houn", boroughB1.toString());
        BoroughButton boroughB2 = (BoroughButton)observList2.get(1);
        assertEquals("hamm", boroughB2.toString());
        BoroughButton boroughB3 = (BoroughButton)observList2.get(2);
        assertEquals("wand", boroughB3.toString());
        BoroughButton boroughB4 = (BoroughButton)observList2.get(3);
        assertEquals("city", boroughB4.toString());
        BoroughButton boroughB5 = (BoroughButton)observList2.get(4);
        assertEquals("gwch", boroughB5.toString());
        BoroughButton boroughB6 = (BoroughButton)observList2.get(5);
        assertEquals("bexl", boroughB6.toString());
    }
    
    @Test
    public void CheckForSixthLevelButtons()
    {
        javafx.scene.layout.VBox vBox1 = mapPanel1.getMainPane();
        javafx.collections.ObservableList<javafx.scene.Node> observList = vBox1.getChildren();
        javafx.scene.layout.HBox hBox1 = (javafx.scene.layout.HBox)observList.get(5);
        javafx.collections.ObservableList<javafx.scene.Node> observList2 = hBox1.getChildren();
        BoroughButton boroughB1 = (BoroughButton)observList2.get(0);
        assertEquals("rich", boroughB1.toString());
        BoroughButton boroughB2 = (BoroughButton)observList2.get(1);
        assertEquals("mert", boroughB2.toString());
        BoroughButton boroughB3 = (BoroughButton)observList2.get(2);
        assertEquals("lamb", boroughB3.toString());
        BoroughButton boroughB4 = (BoroughButton)observList2.get(3);
        assertEquals("sthw", boroughB4.toString());
        BoroughButton boroughB5 = (BoroughButton)observList2.get(4);
        assertEquals("lews", boroughB5.toString());
    }
    
    @Test
    public void CheckForLastLevelButtons()
    {
        javafx.scene.layout.VBox vBox1 = mapPanel1.getMainPane();
        javafx.collections.ObservableList<javafx.scene.Node> observList = vBox1.getChildren();
        javafx.scene.layout.HBox hBox1 = (javafx.scene.layout.HBox)observList.get(6);
        javafx.collections.ObservableList<javafx.scene.Node> observList2 = hBox1.getChildren();
        BoroughButton boroughB1 = (BoroughButton)observList2.get(0);
        assertEquals("king", boroughB1.toString());
        BoroughButton boroughB2 = (BoroughButton)observList2.get(1);
        assertEquals("sutt", boroughB2.toString());
        BoroughButton boroughB3 = (BoroughButton)observList2.get(2);
        assertEquals("croy", boroughB3.toString());
        BoroughButton boroughB4 = (BoroughButton)observList2.get(3);
        assertEquals("brom", boroughB4.toString());
    }
    
    @Test
    public void CheckForButtonProperties()
    {
        javafx.scene.layout.VBox vBox1 = mapPanel1.getMainPane();
        javafx.collections.ObservableList<javafx.scene.Node> observList = vBox1.getChildren();
        assertEquals(false,observList.isEmpty());
        BoroughButton boroughB1 = (BoroughButton)observList.get(0);
        assertEquals("enfi",boroughB1.getText());
        boroughB1.setTotalDeaths(0);
        String[] styles = boroughB1.getStyle().split(";");
        boolean rightColor = false;
        for(String style: styles)
        {
            if(style.contains("-fx-background-color:#FFFFFF"))rightColor = true;
        }
        assertEquals(true,rightColor);
        boroughB1.setTotalDeaths(149);
        String[] styles2 = boroughB1.getStyle().split(";");
        boolean rightColor2 = false;
        for(String style: styles2)
        {
            if(style.contains("-fx-background-color:#00FF00"))rightColor2 = true;
        }
        assertEquals(true,rightColor2);
        boroughB1.setTotalDeaths(201);
        String[] styles3 = boroughB1.getStyle().split(";");
        boolean rightColor3 = false;
        for(String style: styles3)
        {
            if(style.contains("-fx-background-color:#14EB00"))rightColor3 = true;
        }
        assertEquals(true,rightColor3);
        boroughB1.setTotalDeaths(251);
        String[] styles4 = boroughB1.getStyle().split(";");
        boolean rightColor4 = false;
        for(String style: styles4)
        {
            if(style.contains("-fx-background-color:#28D700"))rightColor4 = true;
        }
        assertEquals(true,rightColor4);
        boroughB1.setTotalDeaths(367);
        String[] styles5 = boroughB1.getStyle().split(";");
        boolean rightColor5 = false;
        for(String style: styles5)
        {
            if(style.contains("-fx-background-color:#3CC300"))rightColor5 = true;
        }
        assertEquals(true,rightColor5);
        boroughB1.setTotalDeaths(549);
        String[] styles6 = boroughB1.getStyle().split(";");
        boolean rightColor6 = false;
        for(String style: styles6)
        {
            if(style.contains("-fx-background-color:#50AF00"))rightColor6 = true;
        }
        assertEquals(true,rightColor6);
        boroughB1.setTotalDeaths(600);
        String[] styles7 = boroughB1.getStyle().split(";");
        boolean rightColor7 = false;
        for(String style: styles7)
        {
            if(style.contains("-fx-background-color:#649B00"))rightColor7 = true;
        }
        assertEquals(true,rightColor7);
        boroughB1.setTotalDeaths(725);
        String[] styles8 = boroughB1.getStyle().split(";");
        boolean rightColor8 = false;
        for(String style: styles8)
        {
            if(style.contains("-fx-background-color:#788700"))rightColor8 = true;
        }
        assertEquals(true,rightColor8);
        boroughB1.setTotalDeaths(775);
        String[] styles9 = boroughB1.getStyle().split(";");
        boolean rightColor9 = false;
        for(String style: styles9)
        {
            if(style.contains("-fx-background-color:#8C7300"))rightColor9 = true;
        }
        assertEquals(true,rightColor9);
        boroughB1.setTotalDeaths(900);
        String[] styles10 = boroughB1.getStyle().split(";");
        boolean rightColor10 = false;
        for(String style: styles10)
        {
            if(style.contains("-fx-background-color:#A05F00"))rightColor10 = true;
        }
        assertEquals(true,rightColor10);
        boroughB1.setTotalDeaths(1001);
        String[] styles11 = boroughB1.getStyle().split(";");
        boolean rightColor11 = false;
        for(String style: styles11)
        {
            if(style.contains("-fx-background-color:#B44B00"))rightColor11 = true;
        }
        assertEquals(true,rightColor11);
        boroughB1.setTotalDeaths(1140);
        String[] styles12 = boroughB1.getStyle().split(";");
        boolean rightColor12 = false;
        for(String style: styles12)
        {
            if(style.contains("-fx-background-color:#C83700"))rightColor12 = true;
        }
        assertEquals(true,rightColor12);
        boroughB1.setTotalDeaths(1249);
        String[] styles13 = boroughB1.getStyle().split(";");
        boolean rightColor13 = false;
        for(String style: styles13)
        {
            if(style.contains("-fx-background-color:#DC2300"))rightColor13 = true;
        }
        assertEquals(true,rightColor13);
        
        
    }
}








