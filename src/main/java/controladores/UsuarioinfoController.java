/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author romiy
 */
public class UsuarioinfoController implements Initializable {

    @FXML
    private VBox vbox1;
    @FXML
    private VBox vbox2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        presentarusuario();
    }    
    
    public void presentarusuario(){
        vbox1.getChildren().addAll(new Label("Nombre: "),new Label("Apellido: "),new Label("Organizacion: "),new Label("Correo: "));
        vbox2.getChildren().addAll(new Label(IngresoController.usuarioing.getNombres()),new Label(IngresoController.usuarioing.getApellidos()),new Label(IngresoController.usuarioing.getOrganizacion()),new Label(IngresoController.usuarioing.getCorreo_electronico()));      
        vbox1.setSpacing(15.); vbox2.setSpacing(15.);
        
        for (int v=0; v<vbox1.getChildren().size();v++)
            vbox1.getChildren().get(v).setStyle("-fx-font-size: 25px;"); 
        for (int v=0; v<vbox2.getChildren().size();v++)
            vbox2.getChildren().get(v).setStyle("-fx-font-size: 25px;"); 
    }

    @FXML
    private void CambiarC(ActionEvent event) {
        
    }

}
