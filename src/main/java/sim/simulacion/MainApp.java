package sim.simulacion;

import generadores.GeneradorUniforme;
import java.text.DecimalFormat;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Round;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //this.consoleMode();

        // Codigo por defecto del proyecto JavaFX. Se usa para ventanas. 
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("Trabajo Practico 1");
        stage.setScene(scene);
        stage.show();
        // */
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
