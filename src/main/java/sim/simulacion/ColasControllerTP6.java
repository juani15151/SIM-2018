/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.simulacion;

import colas.Prueba;
import colas.VectorEstado;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.Round;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author juani
 */
public class ColasControllerTP6 implements Initializable {

    @FXML
    private TextField txt_n;
    @FXML
    private TextField txt_desde;
    @FXML
    private TextField txt_hasta;
    @FXML
    private Button btn_simular;
    @FXML
    private TextField promedio_Carniceria;
    @FXML
    private TextField promedio_Fiambreria;
    @FXML
    private TableView<VectorEstado> tablaEstado;
    @FXML
    private TableView<VectorEstado> tablaColas;

    private final ObservableList<VectorEstado> filas = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<VectorEstado, ?> colCompuesta;

        // Bindear columnas de la tablaEstado con valores del vector.
        ObservableList<TableColumn<VectorEstado, ?>> columnas1 = tablaEstado.getColumns();

        addColumn(columnas1, "Reloj", "reloj");
        colCompuesta = addColumn(columnas1, "Llegada", null);
        addColumn(colCompuesta.getColumns(), "Prox. Llegada", "proximaLlegada");
        colCompuesta = addColumn(columnas1, "Carniceria", null);
        addColumn(colCompuesta.getColumns(), "Estado", "carniceriaEstado");
        addColumn(colCompuesta.getColumns(), "T. Cola", "carniceriaTamañoCola");
        addColumn(colCompuesta.getColumns(), "Prox. Fin. At.", "carniceriaFinAtencion");
        addColumn(colCompuesta.getColumns(), "Prox. Interrupcion", "proximaInterrupcion");
        addColumn(colCompuesta.getColumns(), "Ac. t. Espera", "carniceriaTiempoEsperaAcumulado");
        addColumn(colCompuesta.getColumns(), "Clientes At.", "carniceriaClientesIniciados");

        colCompuesta = addColumn(columnas1, "Fiambreria", null);
        addColumn(colCompuesta.getColumns(), "Estado", "fiambreriaEstado");
        addColumn(colCompuesta.getColumns(), "T. Cola", "fiambreriaTamañoCola");
        addColumn(colCompuesta.getColumns(), "Prox. Fin. At.", "fiambreriaFinAtencion");
        addColumn(colCompuesta.getColumns(), "Ac. t. Espera", "fiambreriaTiempoEsperaAcumulado");
        addColumn(colCompuesta.getColumns(), "Clientes At.", "fiambreriaClientesIniciados");

        // Bindear columnas de la tablaColas con valores del vector.
        ObservableList<TableColumn<VectorEstado, ?>> columnas2 = tablaColas.getColumns();


        addColumn(columnas2, "Reloj", "reloj");
        colCompuesta = addColumn(columnas2, "Cola Carniceria", null);
        addColumn(colCompuesta.getColumns(), "Estado", "carniceriaEstadoCliente1");
        addColumn(colCompuesta.getColumns(), "H Inicio Espera", "carniceriaHoraCliente1");
        addColumn(colCompuesta.getColumns(), "Estado", "carniceriaEstadoCliente2");
        addColumn(colCompuesta.getColumns(), "H Inicio Espera", "carniceriaHoraCliente2");
        addColumn(colCompuesta.getColumns(), "Estado", "carniceriaEstadoClienteN");
        addColumn(colCompuesta.getColumns(), "H Inicio Espera", "carniceriaHoraClienteN");
        colCompuesta = addColumn(columnas2, "Cola Fiambreria", null);
        addColumn(colCompuesta.getColumns(), "Estado", "fiambreriaEstadoCliente1");
        addColumn(colCompuesta.getColumns(), "H Inicio Espera", "fiambreriaHoraCliente1");
        addColumn(colCompuesta.getColumns(), "Estado", "fiambreriaEstadoCliente2");
        addColumn(colCompuesta.getColumns(), "H Inicio Espera", "fiambreriaHoraCliente2");
        addColumn(colCompuesta.getColumns(), "Estado", "fiambreriaEstadoClienteN");
        addColumn(colCompuesta.getColumns(), "H Inicio Espera", "fiambreriaHoraClienteN");


        // Bindear las filas.
        tablaEstado.setItems(filas);
        tablaColas.setItems(filas);


        // Bindear los listener para el cambio de fila.
        tablaEstado.getSelectionModel().selectedIndexProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable observable) {
                int index = ((ReadOnlyIntegerProperty) observable).get();
                tablaColas.getSelectionModel().select(index);
                try {
                    tablaColas.scrollTo(index - 4);
                } catch (Exception e) {
                    tablaColas.scrollTo(index);
                }
            }
        });

    }


    private TableColumn<VectorEstado, ?> addColumn(ObservableList<TableColumn<VectorEstado, ?>> columnas, String label, String propertyName) {
        TableColumn col = new TableColumn(label);
        if (propertyName != null) {
            col.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        }
        columnas.add(col);
        return col;
    }

    @FXML
    private void run(ActionEvent event) {
        if (!validarParametros()) {
            return;
        }
        reset();
        Prueba simulacion = new Prueba();
        int limite_lineas = Integer.parseInt(txt_n.getText()); // Cantidad de lineas.
        int mostrar_desde = Integer.parseInt(txt_desde.getText());
        int mostrar_hasta = Integer.parseInt(txt_hasta.getText());
        String cad = "";
        for (int n = 0; n < limite_lineas; n++) {
            if (n >= mostrar_desde && n < mostrar_hasta) {
                filas.add(simulacion.getEstado());
            }
            simulacion.linea();
        }
        promedio_Carniceria.setText(String.valueOf(Round.truncate(simulacion.getPromedioCarniceria(), 5)));
        promedio_Fiambreria.setText(String.valueOf(Round.truncate(simulacion.getPromedioFiambreria(), 5)));
        System.out.println("Fin de Simulacion.");
    }

    private void reset() {
        filas.clear();
    }

    private boolean validarParametros() {
        boolean valido = true;
        if (!validarNoNegativo()) {
            JOptionPane.showMessageDialog(null, "ERROR, NO INGRESE DATOS NEGATIVOS");
            valido = false;
        }
        if (!validardesdeHasta()) {
            JOptionPane.showMessageDialog(null, "ERROR, DESDE ES MENOR QUE HASTA Y VISCEVERSA");
            valido = false;
        }
        return valido;
    }

    private boolean validardesdeHasta() {
        return Integer.parseInt(txt_desde.getText()) < Integer.parseInt(txt_hasta.getText());
    }

    private boolean validarNoNegativo() {
        return Integer.parseInt(txt_desde.getText()) >= 0 && Integer.parseInt(txt_hasta.getText()) >= 0 && Integer.parseInt(txt_n.getText()) > 0;
    }

}
