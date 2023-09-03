/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import ec.edu.espol.proyectog4.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.Oferta;
import modelo.Usuario;
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
    public void enviarOferta(MouseEvent event) throws IOException {
        Oferta of = new Oferta(Float.parseFloat(ofertaIn.getText()), "s", FiltradoController.getVhSelec());
        ArrayList<Oferta> ofertas = Oferta.readListFromFileSer("oferta.ser");
        ofertas.add(of);
        Oferta.saveListToFileSer("oferta.ser", ofertas);
        App.setRoot("filtrado");
}
    
    public void datosVeh(Vehiculo v){
        lInfo.setText(FiltradoController.getVhSelec().toString());
        img.setImage(new Image("img/"+ "     "));
        
        
    }
}
