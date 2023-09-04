/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import ec.edu.espol.proyectog4.App;
import ec.edu.espol.util.Util;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.Oferta;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AceptarOController implements Initializable, Serializable {

    @FXML
    private VBox vboferta;
    @FXML
    private VBox vbbotones;
    @FXML
    private TextField placa;
    @FXML
    private VBox vbcorreo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void mostrarOfertas(){
        ArrayList<Oferta> ofertas=filtrarporusuario(Oferta.readListFromFileSer("oferta.ser"),IngresoController.usuarioing);
        ArrayList<Oferta> lstofertas= filtrarporplaca(ofertas,placa.getText());
        Collections.sort(lstofertas,Collections.reverseOrder());
        for (Oferta oferta:lstofertas){
            Label precio=new Label(String.valueOf(oferta.getPrecio_oferta()));
            Label correo=new Label(oferta.getCorreo());
            vboferta.getChildren().add(precio); 
            vbcorreo.getChildren().add(correo);
            vboferta.setSpacing(9.3); vbcorreo.setSpacing(9.3);
            Button aceptar= new Button("Aceptar Oferta");
            aceptar.setId("btnaceptar");
            
            aceptar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    Util.enviarCorreo(oferta.getCorreo(),"OFERTA ACEPTADA", "Ha sido aceptada su oferta de $"+oferta.getPrecio_oferta()+" por el vehiculo: "+ oferta.getVehiculo().toString());
                }
            });
            
            vbbotones.getChildren().add(aceptar);
        }
            
    }
    
    
    public ArrayList<Oferta> filtrarporusuario(ArrayList<Oferta> ofertas ,Usuario usuario){
        ArrayList<Oferta> lstofertas= new ArrayList<>();
        for(Oferta oferta:ofertas){
            if (oferta.getVehiculo().getUsuario().getCorreo_electronico().equals(usuario.getCorreo_electronico()))
                lstofertas.add(oferta);   
        }
        return lstofertas;
    }
    public ArrayList<Oferta> filtrarporplaca(ArrayList<Oferta> ofertas ,String placa){
        ArrayList<Oferta> lstofertas= new ArrayList<>();
        for(Oferta oferta:ofertas){
            if (oferta.getVehiculo().getPlaca().equals(placa))
                lstofertas.add(oferta);
        }
        return lstofertas;
    }

    @FXML
    private void regresar(MouseEvent event) throws IOException {
        App.setRoot("Menu");
    }

    @FXML
    private void buscar(MouseEvent event) {
        mostrarOfertas();
    }
}
