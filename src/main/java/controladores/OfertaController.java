/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import ec.edu.espol.proyectog4.App;
import ec.edu.espol.util.Util;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.Oferta;
import modelo.Usuario;
import modelo.Vehiculo;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class OfertaController implements Initializable, Serializable{

    @FXML
    private TextField ofertaIn;
    @FXML
    private ImageView img;
    @FXML
    private VBox vInfo;
    @FXML
    private Label lInfo= new Label();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lInfo.setText(FiltradoController.getVhSelec().toString());
        vInfo.getChildren().add(lInfo);
//        System.out.println(FiltradoController.getVhSelec().toString());
        img.setImage(new Image("img/" + FiltradoController.getVhSelec().getImagen()));

    }    

    @FXML
    public void enviarOferta(MouseEvent event) throws IOException {
        float valor = 0;
        if (!ofertaIn.getText().equals("")) {
            valor = Float.parseFloat(ofertaIn.getText());
        }
        if (valor != 0) {
        Oferta of = new Oferta(Float.parseFloat(ofertaIn.getText()), IngresoController.usuarioing.getCorreo_electronico(), FiltradoController.getVhSelec());
        ArrayList<Oferta> ofertas = Oferta.readListFromFileSer("oferta.ser");
        ofertas.add(of);
        Oferta.saveListToFileSer("oferta.ser", ofertas);
//        System.out.println(FiltradoController.vhSelec.getUsuario());
//        Util.enviarCorreo(FiltradoController.vhSelec.getUsuario().getCorreo_electronico(),"OFERTA RECIBIDA","Ha recibido la oferta de $"+of.getPrecio_oferta()+" por su vehiculo: "+ FiltradoController.vhSelec.toString());
        
        App.setRoot("filtrado");
        }
        else if(valor==0){
            Alert a = new Alert(Alert.AlertType.ERROR, "Debe llenar el campo de la oferta");
            a.show();
        }
        
}
    


    @FXML
    private void regresar(MouseEvent event) throws IOException {
        App.setRoot("filtrado");
    }
}
