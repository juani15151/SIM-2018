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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.util.converter.NumberStringConverter;
import javax.swing.JOptionPane;
import utils.Round;
import utils.Validaciones;

public class FXMLController implements Initializable {

    private final IntegerProperty parametroA;
    private final IntegerProperty parametroM;
    private final IntegerProperty parametroC;
    private final IntegerProperty semillaParam;
    private ObservableList<Double> listaNumeros = FXCollections.observableArrayList();
    private int c=20;

    @FXML
    private TextField fieldA;
    @FXML
    private TextField fieldM;
    @FXML
    private TextField fieldC;
    @FXML
    private TextField semilla;
    @FXML
    private ListView<Double> listView1;
    @FXML
    private CheckBox chkMetodo;

    public FXMLController() {
        this.parametroA = new SimpleIntegerProperty(0);
        this.parametroM = new SimpleIntegerProperty(0);
        this.parametroC = new SimpleIntegerProperty(0);
        this.semillaParam = new SimpleIntegerProperty(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fieldA.textProperty().bindBidirectional(this.parametroA, new NumberStringConverter());
        fieldM.textProperty().bindBidirectional(this.parametroM, new NumberStringConverter());
        fieldC.textProperty().bindBidirectional(this.parametroC, new NumberStringConverter());
        semilla.textProperty().bindBidirectional(this.semillaParam, new NumberStringConverter());
    }

    @FXML
    private void generarPorConsola(ActionEvent event) {

        if (ValidarCamposNumericos()) {
            if (ValidarNoNegativo()) {
                listView1.getItems().clear();
                listaNumeros.clear();
                c=20;
                cargarSerie();
                listView1.setItems(cargarLista());                     
            } else {
                JOptionPane.showMessageDialog(null, "ERROR, INGRESE BIEN LOS DATOS, SOLO VALORES POSITIVOS");
                limpiarCampos();
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERROR, INGRESE BIEN LOS DATOS, SOLO VALORES NUMERICOS");
            limpiarCampos();
        }

    }
    
    @FXML
    private void cambiarMetodo(ActionEvent event){
        if (chkMetodo.isSelected()){
            fieldC.setText("0");
            fieldC.setEditable(false);
        }
        else{
            fieldC.setEditable(true);
        }
    }
    private void cargarSerie() {
        IGenerador generador = new GeneradorUniforme(this.semillaParam.get(), this.parametroA.get(), this.parametroC.get(), this.parametroM.get());
        for (int i = 0; i < 1000; i++) {
            listaNumeros.add(Round.truncate(generador.nextDouble(), 4));
        }      
    }
    
    private ObservableList<Double> cargarLista(){
        ObservableList<Double> primerosVeinte = FXCollections.observableArrayList();
        for (int i = 0; i < 20; i++) {
            primerosVeinte.add(listaNumeros.get(i));         
        }
        return primerosVeinte;
    }
    @FXML
    private void siguienteNumero(ActionEvent event){  
        if (listaNumeros.size() > 0){
            JOptionPane.showMessageDialog(null,"Valor numero " + (c +1) + ": " + listaNumeros.get(c));
            c++;
        }
        
        else{
            JOptionPane.showMessageDialog(null, "No hay valores en la serie");       
        }
    }

    private void limpiarCampos() {
        fieldA.setText("0");
        fieldM.setText("0");
        fieldC.setText("0");
        semilla.setText("0");
        listView1.getItems().clear();
    }

    private boolean ValidarCamposNumericos() {
        return Validaciones.isNumeric(fieldA.getText())
                && Validaciones.isNumeric(fieldM.getText()) && Validaciones.isNumeric(fieldC.getText()) 
                && Validaciones.isNumeric(semilla.getText());
       
    }

    private boolean ValidarNoNegativo() {
        return (Validaciones.isPositive(Integer.parseInt(fieldA.getText()))
                && Validaciones.isPositive(Integer.parseInt(fieldM.getText()))
                && Integer.parseInt(fieldC.getText()) >= 0) && Validaciones.isPositive(Integer.parseInt(semilla.getText()));

    }

 
}
