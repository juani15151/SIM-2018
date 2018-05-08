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
import generadores.*;
import javafx.scene.control.ComboBox;

public class FXMLController implements Initializable {

    
    @FXML
    private ListView<Double> listView1;
    @FXML
    private TextField txt_media;
    @FXML
    private TextField txt_a;
    @FXML
    private TextField txt_b;
    @FXML
    private TextField txt_varianza;
    @FXML
    private TextField txt_n;
    private ObservableList<Double> listaNumeros = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> cmb_distribuciones;
    

    public FXMLController() {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmb_distribuciones.getItems().add("Uniforme");
        cmb_distribuciones.getItems().add("Normal Box Muller");
        cmb_distribuciones.getItems().add("Normal Convolucion");
        cmb_distribuciones.getItems().add("Exponencial Negativa");
        cmb_distribuciones.getItems().add("Poisson");
    }

    @FXML
    private void generarPorConsola(ActionEvent event) {

        if(controlIntervalosUniforme()){
        if (ValidarCamposNumericos()) {
            if (ValidarNoNegativo()) {
                
            
                listView1.getItems().clear();
                listaNumeros.clear();
                elegirGenerador();
                listView1.setItems(listaNumeros);                     
            } else {
                JOptionPane.showMessageDialog(null, "ERROR, INGRESE BIEN LOS DATOS, LA CANTIDAD ES POSITIVA");
                limpiarCampos();
                
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERROR, INGRESE BIEN LOS DATOS, SOLO VALORES NUMERICOS");
            limpiarCampos();
        }

    }
        else{
            JOptionPane.showMessageDialog(null, "ERROR, INGRESE BIEN LOS INTERVALOS EN LA UNIFORME");
            limpiarCampos();
        }
    }
    
    
    private void elegirGenerador() {
       if(cmb_distribuciones.getValue().equals(cmb_distribuciones.getItems().get(0))){
        IGenerador generador = new GeneradorUniformePersonalizado(Integer.parseInt(txt_a.getText()), Integer.parseInt(txt_b.getText()));
        limpiarCamposUniforme();
        cargarSerie(generador);
       }
       if(cmb_distribuciones.getValue().equals(cmb_distribuciones.getItems().get(1))){
        IGenerador generador = new GeneradorNormal(Double.parseDouble(txt_varianza.getText()), Double.parseDouble(txt_media.getText()));
        cargarSerie(generador);
       }
       if(cmb_distribuciones.getValue().equals(cmb_distribuciones.getItems().get(2))){
       IGenerador generador = new GeneradorNormalConvolucion(Double.parseDouble(txt_varianza.getText()), Double.parseDouble(txt_media.getText()));
        cargarSerie(generador);
       }
       if(cmb_distribuciones.getValue().equals(cmb_distribuciones.getItems().get(4))){
         IGenerador generador = new GeneradorPoisson(Double.parseDouble(txt_media.getText()));
        cargarSerie(generador);
       }
       if(cmb_distribuciones.getValue().equals(cmb_distribuciones.getItems().get(3))){
            IGenerador generador = new GeneradorExponencial(Double.parseDouble(txt_media.getText()));
            cargarSerie(generador);
       }
   
   
    }
    
    private boolean controlIntervalosUniforme(){
        return Integer.parseInt(txt_a.getText()) <= Integer.parseInt(txt_b.getText());
    }
    
    private void cargarSerie(IGenerador generador){
        int n = Integer.parseInt(txt_n.getText());
        if(generador == null){
            JOptionPane.showMessageDialog(null, "Porfavor seleccione un generador");
        }
            else{
            for (int i = 0; i < n; i++) {
            listaNumeros.add(Round.truncate(generador.nextDouble(), 4));
                    }
        }
        
    }
    @FXML
    private void cambiarItem(ActionEvent event){
        if(cmb_distribuciones.getValue().equals(cmb_distribuciones.getItems().get(0))){
        activarCamposUniforme();
        limpiarCamposUniforme();
       }
       if(cmb_distribuciones.getValue().equals(cmb_distribuciones.getItems().get(1))){
           activarCamposNormal();
           limpiarCamposNormal();
       }
       if(cmb_distribuciones.getValue().equals(cmb_distribuciones.getItems().get(2))){
           activarCamposNormal();
           limpiarCamposNormal();
       }
       if(cmb_distribuciones.getValue().equals(cmb_distribuciones.getItems().get(4))){
        activarCamposPoissonExp();
        limpiarCamposPoissonExp();
       }
       if(cmb_distribuciones.getValue().equals(cmb_distribuciones.getItems().get(3))){
           activarCamposPoissonExp();
            limpiarCamposPoissonExp();
       }
    }
    private void limpiarCamposUniforme() {
        txt_media.setDisable(true);
        txt_varianza.setDisable(true);
    }
    
    private void limpiarCamposNormal(){
        txt_a.setDisable(true);
        txt_b.setDisable(true);
        txt_a.setText("0");
        txt_b.setText("0");
    }

    private void limpiarCamposPoissonExp(){
        txt_a.setDisable(true);
        txt_b.setDisable(true);
        txt_varianza.setDisable(true);
        txt_a.setText("0");
        txt_b.setText("0");
        txt_varianza.setText("0");
        
    }
    
    private void activarCamposUniforme(){
        txt_a.setDisable(false);
        txt_b.setDisable(false);
        txt_varianza.setText("0");
        txt_media.setText("0");
    }
    
    private void activarCamposPoissonExp(){
        txt_media.setDisable(false);
        txt_a.setText("0");
        txt_b.setText("0");
    }
    
    private void activarCamposNormal(){
        txt_media.setDisable(false);
        txt_varianza.setDisable(false);
        txt_a.setText("0");
        txt_b.setText("0");
    }
    
    private static boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    
    private boolean ValidarCamposNumericos() {
      return isNumeric(txt_n.getText()) && isNumeric(txt_a.getText()) && isNumeric(txt_b.getText()) && 
              isNumeric(txt_media.getText()) && isNumeric(txt_varianza.getText()) ;
    }

    private boolean ValidarNoNegativo() {
      return Integer.parseInt(txt_n.getText()) >= 0;
    }

    private void limpiarCampos(){
        txt_a.setText("0");
        txt_b.setText("0");
        txt_n.setText("0");
        txt_varianza.setText("0");
        txt_media.setText("0");
    }
 
}
