import javafx.scene.layout.Pane;

/**
 * This is an interface for the different panels that are in the application.
 * It contains a method which is implemented in every other method.
 * @author Armaan Uddin
 * @version 28/03/2023
 */
public interface Panel
{
    /**
     * Will retrieve the main pane in the panel.
     * @return the root pane in the panel.
     */
    Pane getMainPane();
}
