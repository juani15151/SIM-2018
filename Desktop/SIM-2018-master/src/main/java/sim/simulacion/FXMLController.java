package sim.simulacion;

import generadores.GeneradorUniforme;
import generadores.IGenerador;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.converter.NumberStringConverter;
import utils.Round;

public class FXMLController implements Initializable {

    private final IntegerProperty parametroA;
    private final IntegerProperty parametroM;
    private final IntegerProperty parametroC;

    @FXML
    private TextField fieldA;
    @FXML
    private TextField fieldM;
    @FXML
    private TextField fieldC;

    public FXMLController() {
        this.parametroA = new SimpleIntegerProperty(0);
        this.parametroM = new SimpleIntegerProperty(0);
        this.parametroC = new SimpleIntegerProperty(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fieldA.textProperty().bindBidirectional(this.parametroA, new NumberStringConverter());
        fieldM.textProperty().bindBidirectional(this.parametroM, new NumberStringConverter());
        fieldC.textProperty().bindBidirectional(this.parametroC, new NumberStringConverter());
    }

    @FXML
    private void generarPorConsola(ActionEvent event) {
        IGenerador generador = new GeneradorUniforme(100, this.parametroA.get(), this.parametroC.get(), this.parametroM.get());

        // Mostrar primeros 20 valores.
        System.out.println("Primeros 20 valores: ");
        DecimalFormat df = new DecimalFormat();
        for (int i = 0; i < 20; i++) {
            System.out.print(Round.truncate(generador.nextDouble(), 4));
            System.out.print(", ");
        }
        System.out.println("");
    }
}
