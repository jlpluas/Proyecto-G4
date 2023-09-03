/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import ec.edu.espol.proyectog4.App;
import modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author P4321
 */
public class IngresoController{

    @FXML
    private Button btnRegistrarse;
    @FXML
    private TextField txtfieldUsuario;
    @FXML
    private TextField txtfieldContraseña;
    @FXML
    private Button btnIngresar;
    
    static Usuario usuarioing;
    
    static String correo;
    static String contraseña;

    public static String getCorreo() {
        return correo;
    }

    public static String getContraseña() {
        return contraseña;
    }


    
    @FXML
    private void registrarse(ActionEvent event) throws IOException {
        App.setRoot("registros");
    }

    @FXML
    private void ingresar(ActionEvent event) throws IOException {
        String usuario = txtfieldUsuario.getText();
        String contraseña = txtfieldContraseña.getText(); 
        
        usuario = txtfieldUsuario.getText();
        contraseña = txtfieldContraseña.getText();
                
        ArrayList<Usuario> usuarios=Usuario.readListFromFileSer("usuarios.ser");
        ArrayList<String> correos= new ArrayList<>();
        ArrayList<String> contraseñas= new ArrayList<>();
        for (Usuario us:usuarios){
            correos.add(us.getCorreo_electronico());
            contraseñas.add(us.getClave());
        }
        
        if (correos.contains(usuario)&& contraseñas.contains(contraseña)){
            mostrarAlertaC();
            App.setRoot("menu");
            usuarioing(usuarios);
        } else 
            mostrarAlertaI();
    }
  
    private void mostrarAlertaC(){
        Alert alerta= new Alert(AlertType.INFORMATION);
        alerta.setContentText("Usuario correcto");
        alerta.show();
    }    
    
    private void mostrarAlertaI(){
        Alert alerta= new Alert(AlertType.ERROR);
        alerta.setContentText("Usuario incorrecto");
        alerta.show();
    }    
    
    private void usuarioing(ArrayList<Usuario> listau){
        for (Usuario usuario:listau){
            if (usuario.getCorreo_electronico().equals(txtfieldUsuario.getText()))
                usuarioing=usuario;
        
        }
    
    }
    
    static void actualizarUsuarios(){
        ArrayList<Usuario> usuarios =Usuario.readListFromFileSer("usuarios.ser");
        ArrayList<Usuario> nusuarios= new ArrayList<>();
        for (Usuario usuario:usuarios){
            if(usuario.getCorreo_electronico().equals(usuarioing.getCorreo_electronico()))
                nusuarios.add(usuarioing);
            else 
                nusuarios.add(usuario);
        }
         Usuario.saveListToFileSer("usuarios.ser", nusuarios);
    }
    
}
