/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectog4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class BuscarController implements Initializable {

    @FXML
    private ComboBox<String> tipoV;
    @FXML
    private TextField rmin;
    @FXML
    private TextField amin;
    @FXML
    private TextField pmin;
    @FXML
    private TextField pmax;
    @FXML
    private TextField amax;
    @FXML
    private TextField rmax;
    @FXML
    private Button buscar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipoV.getItems().addAll("Auto","Camioneta","Motocicleta");
    }    
    
}
