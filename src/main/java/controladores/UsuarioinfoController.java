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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        Stage newStage = new Stage();
        newStage.setTitle("Cambio de contraseña");
        StackPane sp = new StackPane();
        TextField cActual=new TextField();
        TextField cNueva=new TextField();       
        HBox hb1= new HBox(new Label("Contraseña Actual"), cActual);
        HBox hb2= new HBox(new Label("Contraseña Nueva"), cNueva);
        Button cambiar = new Button("Cambiar");
        cambiar.setOnAction(e -> {try {
            comprobar(cActual.getText(),cNueva.getText(),newStage);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
});
        
        VBox vb= new VBox();         
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(hb1,hb2,cambiar);

        sp.getChildren().add(vb);
        
        Scene scene = new Scene(sp, 300, 200);
        newStage.setScene(scene);
        newStage.show();
        
        
    }

    private void comprobar(String cA, String cN, Stage st) throws IOException {
        if (cA.equals(IngresoController.usuarioing.getClave())){
            IngresoController.usuarioing.setClave(cN);
            IngresoController.actualizarUsuarios();
            App.setRoot("Menu");
            st.close();
        } else {
            Alert alerta= new Alert(AlertType.ERROR);
            alerta.setContentText("Contraseña Actual Incorrecta ");
            alerta.show();
        } 
    }
}
