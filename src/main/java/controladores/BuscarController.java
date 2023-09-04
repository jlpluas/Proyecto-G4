/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import ec.edu.espol.proyectog4.App;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class BuscarController implements Initializable , Serializable{

    @FXML
    private ComboBox<String> tipoV;
    @FXML
    private TextField rmin;
    @FXML
    private TextField amin;
    @FXML
    private TextField pmin;
    @FXML
    private TextField pmax;
    @FXML
    private TextField amax;
    @FXML
    private TextField rmax;
    @FXML
    private Button buscar;
    
    static String tipoVehiculo;
    static int recorridomin;
    static int recorridomax;
    static int añomin;
    static int añomax;
    static int preciomin;
    static int preciomax;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipoV.getItems().addAll("Auto","Camioneta","Motocicleta");
    }    
    
    public void darvalores(){
        recorridomin=0;
        if(!rmin.getText().equals(""))
            recorridomin =  Integer.parseInt(rmin.getText());
        añomin=0;
        if(!amin.getText().equals(""))
            añomin = Integer.parseInt(amin.getText());
        preciomin=0;
        if(!pmin.getText().equals(""))
            preciomin = Integer.parseInt(pmin.getText());
        recorridomax=Integer.MAX_VALUE;
        if(!rmax.getText().equals(""))
            recorridomax = Integer.parseInt(rmax.getText());
        añomax=Integer.MAX_VALUE;
        if(!amax.getText().equals(""))
            añomax = Integer.parseInt(amax.getText());
        preciomax=Integer.MAX_VALUE;
        if(!pmax.getText().equals(""))
            preciomax = Integer.parseInt(pmax.getText());

        tipoVehiculo= tipoV.getSelectionModel().getSelectedItem();
        
        
    }

    @FXML
    private void buscar(ActionEvent event) throws IOException {
        darvalores();
        App.setRoot("filtrado");
    }

    @FXML
    private void regresar(MouseEvent event) throws IOException {
        App.setRoot("menu");
    }

}
