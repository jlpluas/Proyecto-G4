/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectog4;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class MenuController implements Initializable {

    @FXML
    private Button registrarVehiculo;
    @FXML
    private Button buscarVehiculo;
    @FXML
    private Button ofertarVehiculo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registrarV(ActionEvent event) throws IOException {
        App.setRoot("registrarV"); 
    }

    @FXML
    private void buscarV(ActionEvent event)throws IOException {
        App.setRoot("buscarV");
    }

    @FXML
    private void ofertarV(ActionEvent event) throws IOException{
        App.setRoot("ofertarV");
    }
    
}
