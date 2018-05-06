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

public class FXMLController implements Initializable {

    
    @FXML
    private ListView<Double> listView1;
    @FXML
    private CheckBox chk_uniforme;
    @FXML
    private CheckBox chk_exp;
    @FXML
    private CheckBox chk_normbox;
    @FXML
    private CheckBox chk_normconv;
    @FXML
    private CheckBox chk_poisson;
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
    

    public FXMLController() {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void generarPorConsola(ActionEvent event) {

//        if (ValidarCamposNumericos()) {
//            if (ValidarNoNegativo()) {
                
                listView1.getItems().clear();
                listaNumeros.clear();
                elegirGenerador();
                listView1.setItems(listaNumeros);                     
//            } else {
//                JOptionPane.showMessageDialog(null, "ERROR, INGRESE BIEN LOS DATOS, SOLO VALORES POSITIVOS");
//                limpiarCampos();
            }
//        } else {
//            JOptionPane.showMessageDialog(null, "ERROR, INGRESE BIEN LOS DATOS, SOLO VALORES NUMERICOS");
//            limpiarCampos();
//        }

//    }
    
    
    private void elegirGenerador() {
       if(chk_uniforme.isSelected()){
        IGenerador generador = new GeneradorUniformePersonalizado(Integer.parseInt(txt_a.getText()), Integer.parseInt(txt_b.getText()));
        cargarSerie(generador);
       }
       if(chk_normbox.isSelected()){
        IGenerador generador = new GeneradorNormal(Double.parseDouble(txt_varianza.getText()), Double.parseDouble(txt_media.getText()));
        cargarSerie(generador);
       }
       if(chk_normconv.isSelected()){
       IGenerador generador = new GeneradorNormalConvolucion(Double.parseDouble(txt_varianza.getText()), Double.parseDouble(txt_media.getText()));
        cargarSerie(generador);
       }
       if(chk_poisson.isSelected()){
         IGenerador generador = new GeneradorPoisson(Double.parseDouble(txt_media.getText()));
        cargarSerie(generador);
       }
       if(chk_exp.isSelected()){
            IGenerador generador = new GeneradorExponencial(Double.parseDouble(txt_media.getText()));
            cargarSerie(generador);
       }
   
   
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

 

    private void limpiarCampos() {
       
    }

//    private boolean ValidarCamposNumericos() {
//      
//    }
//
//    private boolean ValidarNoNegativo() {
//      
//    }

 
}
