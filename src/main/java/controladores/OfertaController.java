/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.Vehiculo;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class OfertaController implements Initializable {

    @FXML
    private TextField ofertaIn;
    @FXML
    private ImageView img;
    @FXML
    private VBox vInfo;
    
    private Label lInfo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    @FXML
    private void enviarOferta(MouseEvent event) {
        
    }
    
    public void datosVeh(Vehiculo v){
        lInfo.setText(v.toString());
        img.setImage(new Image("img/"+ "     "));
    }
}
