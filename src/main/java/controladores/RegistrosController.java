/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import ec.edu.espol.proyectog4.App;
import ec.edu.espol.proyectog4.App;
import modelo.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class RegistrosController implements Initializable, Serializable {

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
//        launch();
    }    

    @FXML
    private void registrar(MouseEvent event) throws IOException {
        Usuario us= new Usuario(0,txtfieldNombre.getText(),txtfieldApellido.getText(),txtfieldOrganizacion.getText(),txtfieldCorreo.getText(),txtfieldContraseña.getText());
        ArrayList<Usuario> usuarios=Usuario.readListFromFileSer("usuarios.ser");
        
        ArrayList<String> correos= new ArrayList<>();
        for (Usuario u:usuarios)
            correos.add(u.getCorreo_electronico());
        System.out.println(correos);
        if (correos.contains(txtfieldCorreo.getText())){
            Alert alerta= new Alert(AlertType.ERROR);
            alerta.setContentText("Correo ya existente");
            alerta.show();
            txtfieldCorreo.clear();
        } else {
            usuarios.add(us);
            Usuario.saveListToFileSer("usuarios.ser", usuarios);

            App.setRoot("ingreso");
        }
    }
    
    
}
