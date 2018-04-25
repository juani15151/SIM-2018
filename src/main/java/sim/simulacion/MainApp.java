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
     * Ejecutar el programa en consola hasta que tengamos interfaz grafica.
     */
    public void consoleMode() {
        Scanner sc = new Scanner(System.in);
        int semilla, a, c = 0, m;

        // Configurar generador.
        System.out.println("Seleccione el generador a usar:");
        System.out.println("1- Generador Uniforme por metodo congruencial mixto.");
        System.out.println("2- Generador Uniforme por metodo congruencial.");
        int generador_choice = sc.nextInt();

        System.out.print("Ingrese el valor semilla:");
        semilla = sc.nextInt();
        System.out.print("Ingrese la constante multiplicativa A:");
        a = sc.nextInt();
        System.out.print("Ingrese el valor del modulo M:");
        m = sc.nextInt();

        if (generador_choice == 1) {
            System.out.print("Ingrese la constante aditiva C:");
            c = sc.nextInt();
        } else {
            c = 0;
        }
        
        GeneradorUniforme generador = new GeneradorUniforme(semilla, a, c, m,0,1);      

        // Mostrar primeros 20 valores.
        System.out.println("Primeros 20 valores: ");
        DecimalFormat df = new DecimalFormat();
        for (int i = 0; i < 20; i++) {
            System.out.print(Round.truncate(generador.nextDouble(), 4));
            System.out.print(", ");
        }
        System.out.println("");

        // Mostrar los siguientes de a uno.
        System.out.println("Presione enter para ver los siguientes valores uno a uno.");
        while (true) {
            sc.nextLine();
            System.out.print(Round.truncate(generador.nextDouble(), m));
        }
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
