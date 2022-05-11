import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class that drives the gui application
 */
public class driver extends Application {
    /**
     * Method that gets the javafx resources and creates the gui application
     * @param stage new stage
     * @throws Exception if theres an exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root =
                FXMLLoader.load(getClass().getResource("notWordle.fxml"));

        Scene scene = new Scene(root); // attach scene graph to scene
        stage.setTitle("Not Wordle"); // displayed in window's title bar
        stage.setScene(scene); // attach scene to stage
        stage.show(); // display the stage
    }

    /**
     * Automatically called when driver.java is run
     * @param args Command Line arguments
     */
    public static void main(String[] args) {
        // create a TipCalculator object and call its start method
        launch(args);


    }
}
