package sim.simulacion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import montecarlo.SobreVentaPasajes;
import montecarlo.generadoresPasajeros.*;
import montecarlo.generadoresPasajeros.IGeneradorPasajeros;
import utils.Round;

/**
 * FXML Controller class
 *
 * @author juani
 */
public class MontecarloController implements Initializable {

    
    @FXML
    private TextField txt_n;
    @FXML
    private TextField txt_desde;
    @FXML
    private TextField txt_hasta;
    @FXML
    private TextField txt_promedio;
    @FXML
    private ComboBox<String> cmb_generadores;
    @FXML
    private Button btn_simular;
    @FXML
    private ListView<String> list_Vector;
     private ObservableList<String> vectorEstado = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmb_generadores.getItems().add("31");
        cmb_generadores.getItems().add("32");
        cmb_generadores.getItems().add("33");
        cmb_generadores.getItems().add("34");
        
    }    
    
    
    /**
     * Retorna el generador que desea utilizar el usuario.
     * @return 
     */
    private IGeneradorPasajeros getGenerador(){
        if(cmb_generadores.getItems().get(0).equals(cmb_generadores.getValue())){
            return new Generador31Reservas();
        }
        if(cmb_generadores.getItems().get(1).equals(cmb_generadores.getValue())){
            return new Generador32Reservas();
        }
        if(cmb_generadores.getItems().get(2).equals(cmb_generadores.getValue())){
            return new Generador33Reservas();
        }
        if(cmb_generadores.getItems().get(3).equals(cmb_generadores.getValue())){
            return new Generador34Reservas();
        }
        return null;
    }
    
    @FXML
    public void run(){
        try{
        if(validarNoNegativo()){
            if(validardesdeHasta()){
        reset();
        SobreVentaPasajes simulacion = new SobreVentaPasajes(getGenerador());
        int n = Integer.parseInt(txt_n.getText());
        String cad = "";
        for (; n > 0; n--) {
        simulacion.iterar();
        if(simulacion.getReloj() >= Integer.parseInt(txt_desde.getText()) && simulacion.getReloj() <= Integer.parseInt(txt_hasta.getText()) ){
          cad = "Reloj: " + simulacion.getReloj() + " - RND: " + Round.truncate(simulacion.getRndCantidadPasajeros(), 2) + " - Cantidad Pasajeros: " + Round.truncate(simulacion.getCantidadPasajeros(),2) + " - Utilidad: " + Round.truncate(simulacion.getUtilidad(),2) + " - Utilidad Acumulada: " + Round.truncate(simulacion.getUtilidadAcumulada(), 2);   
          vectorEstado.add(cad);
        }     
    }
        list_Vector.getItems().addAll(vectorEstado); 
        txt_promedio.setText(String.valueOf(Round.truncate(simulacion.utilidadPromedio(), 2)));

        }
        else{
        JOptionPane.showMessageDialog(null, "ERROR, DESDE ES MENOR QUE HASTA Y VISCEVERSA");
        }
        }
        else{
            
            JOptionPane.showMessageDialog(null, "ERROR, NO INGRESE DATOS NEGATIVOS");
        }
        }
        catch(NullPointerException ex){
            JOptionPane.showMessageDialog(null, "ERROR, SELECCIONE UN VALOR EN EL COMBO");
        }
    }
  
    
    
    
    /**
     * Reinicia la tabla y todos los controles necesarios para el inicio de una
     * simulacion.
     */
    private void reset(){
        list_Vector.getItems().clear();
        vectorEstado.clear();
    }
    
   private boolean validardesdeHasta(){
       return Integer.parseInt(txt_desde.getText()) < Integer.parseInt(txt_hasta.getText());
   }
   
   private boolean validarNoNegativo(){
       return Integer.parseInt(txt_desde.getText()) >= 0 && Integer.parseInt(txt_hasta.getText()) >= 0 && Integer.parseInt(txt_n.getText()) > 0;
   }
   
   private boolean validarSeleccionCombo(){
       return cmb_generadores.getValue() != "";
   }
   
   private void limpiarCampos(){
       txt_desde.setText("0");
       txt_hasta.setText("0");
       txt_n.setText("1");
   }
}
