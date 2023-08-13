/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectog4;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class RegistrosController implements Initializable {

    @FXML
    private TextField txtfieldNombre;
    @FXML
    private TextField txtfieldApellido;
    @FXML
    private TextField txtfieldOrganizacion;
    @FXML
    private TextField txtfieldCorreo;
    @FXML
    private TextField txtfieldContraseña;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registrar(MouseEvent event) throws IOException {
        Usuario us= new Usuario(0,txtfieldNombre.getText(),txtfieldApellido.getText(),txtfieldOrganizacion.getText(),txtfieldCorreo.getText(),txtfieldContraseña.getText());
        ArrayList<Usuario> usuarios=Usuario.readListFromFileSer("usuarios.ser");
        usuarios.add(us);
        Usuario.saveListToFileSer("usuarios.ser", usuarios);
        
        App.setRoot("ingreso");
    }
    
    
}
