/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.simulacion;

import colas.Prueba;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import utils.Round;

/**
 * FXML Controller class
 *
 * @author Octavio
 */
public class ColasController implements Initializable {

    @FXML
    private TextField txt_n;
    @FXML
    private TextField txt_desde;
    @FXML
    private TextField txt_hasta;
    @FXML
    private Button btn_simular;
    @FXML
    private ListView<String> list1;
    private ObservableList<String> vectorEstado = FXCollections.observableArrayList();
    @FXML
    private TextField promedio_Carniceria;
    @FXML
    private TextField promedio_Fiambreria;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void run() {
        if (validarNoNegativo()) {
            if (validardesdeHasta()) {
                reset();
                Prueba simulacion = new Prueba();
                int limite_lineas = Integer.parseInt(txt_n.getText()); // Cantidad de lineas.
                int mostrar_desde = Integer.parseInt(txt_desde.getText());
                int mostrar_hasta = Integer.parseInt(txt_hasta.getText());
                String cad = "";
                for (int n = 0; n < limite_lineas; n++) {
                    simulacion.linea();
                    if (n >= mostrar_desde && n < mostrar_hasta) {
                        cad = simulacion.getEstado();
                        vectorEstado.add(cad);
                    }
                }
                list1.getItems().addAll(vectorEstado);
                promedio_Carniceria.setText(String.valueOf(Round.truncate(simulacion.getPromedioCarniceria(), 5)));
                promedio_Fiambreria.setText(String.valueOf(Round.truncate(simulacion.getPromedioFiambreria(), 5)));
                System.out.println("Fin de Simulacion.");

            } else {
                JOptionPane.showMessageDialog(null, "ERROR, DESDE ES MENOR QUE HASTA Y VISCEVERSA");
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERROR, NO INGRESE DATOS NEGATIVOS");
        }
    }

    private void reset() {
        list1.getItems().clear();
        vectorEstado.clear();
    }

    private boolean validardesdeHasta() {
        return Integer.parseInt(txt_desde.getText()) < Integer.parseInt(txt_hasta.getText());
    }

    private boolean validarNoNegativo() {
        return Integer.parseInt(txt_desde.getText()) >= 0 && Integer.parseInt(txt_hasta.getText()) >= 0 && Integer.parseInt(txt_n.getText()) > 0;
    }

    private void limpiarCampos() {
        txt_desde.setText("0");
        txt_hasta.setText("0");
        txt_n.setText("1");
    }
}
