package sim.simulacion;

import generadores.GeneradorUniformeMixto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        this.consoleMode();
        
        /* // Codigo por defecto del proyecto JavaFX. Se usa para ventanas. 
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
        // */
    }
    
    /**
     * Ejecutar el programa en consola hasta que tengamos interfaz grafica.
     * @throws IOException
     */
    public void consoleMode() throws IOException {        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        int raiz, a, c, m;
        
        System.out.print("Ingrese el valor semilla:");
        raiz = sc.nextInt();
        System.out.print("Ingrese la constante multiplicativa A:");
        a = sc.nextInt();
        System.out.print("Ingrese la constante aditiva C:");
        c = sc.nextInt();
        System.out.print("Ingrese el valor del modulo M:");
        m = sc.nextInt();
        
        GeneradorUniformeMixto generador = new GeneradorUniformeMixto(raiz,a, c, m);
        List<Float> r = generador.generar();
        
        System.out.print("presione enter para ver valores uno a uno.");
        for (int i = 0; i < r.size(); i++) {
            String enter = br.readLine();
            System.out.println("valor " + (i+1)+": " + r.get(i)); 
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
