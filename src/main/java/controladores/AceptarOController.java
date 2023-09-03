/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import modelo.Oferta;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AceptarOController implements Initializable {

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
        ArrayList<Oferta> ofertas=filtrarporusuario(Oferta.ofertasVehiculos,IngresoController.usuarioing);
        ArrayList<Oferta> lstofertas= filtrarporplaca(ofertas,placa.getText());
        for (Oferta oferta:lstofertas){
            Label precio=new Label(String.valueOf(oferta.getPrecio_oferta()));
            Label correo=new Label(oferta.getCorreo());
            vboferta.getChildren().add(precio); 
            vbcorreo.getChildren().add(correo);
            vbbotones.getChildren().add(new Button("Aceptar Oferta"));
            
        }
    }
    
    
    public ArrayList<Oferta> filtrarporusuario(ArrayList<Oferta> ofertas ,Usuario usuario){
        ArrayList<Oferta> lstofertas= new ArrayList<>();
        for(Oferta oferta:ofertas){
            if (oferta.getVehiculo().getVendedor().getCorreo_electronico().equals(usuario.getCorreo_electronico()))
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
}
