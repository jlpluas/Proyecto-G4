/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import ec.edu.espol.proyectog4.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class MenuController implements Initializable {

    @FXML
    private Button registrarVehiculo;
    @FXML
    private Button irperfil;

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
    private void verperfil(MouseEvent event) throws IOException{
        App.setRoot("usuarioinfo");
    }

    @FXML
    private void OfertarV(ActionEvent event) throws IOException {
        App.setRoot("buscar");
    }

    @FXML
    private void AceptarO(ActionEvent event) throws IOException {
        App.setRoot("aceptarO");
    }
    
}
