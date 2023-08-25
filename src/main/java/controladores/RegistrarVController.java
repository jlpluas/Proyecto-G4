/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import ec.edu.espol.proyectog4.App;
import modelo.Auto;
import modelo.Vehiculo;
import modelo.Camioneta;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class RegistrarVController implements Initializable {

    @FXML
    private ComboBox<String> Vbox_tipoV;
    @FXML
    private HBox h;
    @FXML
    private VBox v1;
    @FXML
    private VBox v2;
    
    private ArrayList<String> datosIngresados = new ArrayList<>();
    private Vehiculo vehiculoActual;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Vbox_tipoV.getItems().addAll("Auto","Camioneta","Motocicleta");
    }    
    
    @FXML
    private void filtrar(ActionEvent event) {
        v1.getChildren().clear();
        v2.getChildren().clear();
        datosIngresados.clear();

        String opcion = Vbox_tipoV.getSelectionModel().getSelectedItem();
        if (opcion.equals("Auto")) {
            ingresarAuto();
            vehiculoActual = new Auto(); 
        } else if (opcion.equals("Camioneta")) {
            ingresarCamioneta();
            vehiculoActual = new Camioneta(); 
        } else if (opcion.equals("Motocicleta")) {
            ingresarMotocicleta();
            vehiculoActual = new Vehiculo(); 
        }
    }
    
    public void ingresarAuto(){
        v1.getChildren().addAll(new Label("Placa: "),new Label("Marca: "),new Label("Modelo: "),new Label("Tipo de Motor: "),new Label("Año: "),new Label("Recorrido: "),new Label("Color: "),new Label("Tipo de Combustible: "),new Label("Precio: "),new Label("Vidrios: "),new Label("Transmision: "));
        for (int v=0; v<v1.getChildren().size();v++)
            v1.getChildren().get(v).setStyle("-fx-font-size: 16px;"); 
        for (int i = 0; i < 11; i++) {
            TextField textField = new TextField();
            v2.getChildren().add(textField);
            datosIngresados.add(""); 
        }
    }
    
    public void ingresarCamioneta(){
        v1.getChildren().addAll(new Label("Placa: "),new Label("Marca: "),new Label("Modelo: "),new Label("Tipo de Motor: "),new Label("Año: "),new Label("Recorrido: "),new Label("Color: "),new Label("Tipo de Combustible: "),new Label("Precio: "),new Label("Traccion: "));
        for (int v=0; v<v1.getChildren().size();v++)
            v1.getChildren().get(v).setStyle("-fx-font-size: 16px;");            

        for (int i = 0; i < 10; i++) {
            TextField textField = new TextField();
            v2.getChildren().add(textField);
            datosIngresados.add(""); 
        }
    }

    public void ingresarMotocicleta(){
        v1.getChildren().addAll(new Label("Placa: "),new Label("Marca: "),new Label("Modelo: "),new Label("Tipo de Motor: "),new Label("Año: "),new Label("Recorrido: "),new Label("Color: "),new Label("Tipo de Combustible: "),new Label("Precio: "));
        for (int v=0; v<v1.getChildren().size();v++){
            v1.getChildren().get(v).setStyle("-fx-font-size: 16px;");            
        }
        
        for (int i = 0; i < 9; i++) {
            TextField textField = new TextField();
            textField.setId("campo_" + i); 
            v2.getChildren().add(textField);
            datosIngresados.add(""); 
        }
    }
    
    private void guardarDatos() {
        
        for (int i = 0; i < v2.getChildren().size(); i++) {
            TextField textField = (TextField) v2.getChildren().get(i);
            String texto = textField.getText();
            datosIngresados.set(i, texto); 
        }
        vehiculoActual.setPlaca(datosIngresados.get(0));
        vehiculoActual.setMarca(datosIngresados.get(1));
        vehiculoActual.setModelo(datosIngresados.get(2));
        vehiculoActual.setTipo_motor(datosIngresados.get(3));
        vehiculoActual.setAño(Integer.parseInt(datosIngresados.get(4)));
        vehiculoActual.setRecorrido(Integer.parseInt(datosIngresados.get(5)));
        vehiculoActual.setColor(datosIngresados.get(6));
        vehiculoActual.setTipo_comb(datosIngresados.get(7));
        vehiculoActual.setPrecio(Integer.parseInt(datosIngresados.get(8)));
                
        if (vehiculoActual instanceof Auto) {
            Auto auto = (Auto) vehiculoActual;
            auto.setVidrios(datosIngresados.get(9));
            auto.setTransmision(datosIngresados.get(10));
            
        } else if (vehiculoActual instanceof Camioneta) {
            Camioneta camioneta = (Camioneta) vehiculoActual;
            camioneta.setTraccion(datosIngresados.get(9));
        } else if (vehiculoActual instanceof Vehiculo) {
            Vehiculo moto = vehiculoActual;
        }
    }
   
    @FXML
    private void registrar(ActionEvent event) throws IOException {
        guardarDatos();
        
        ArrayList<Vehiculo> vehiculos= Vehiculo.readListFromFileSer("vehiculos.ser");
        ArrayList<String> lstplacas= new ArrayList<>();
        for (Vehiculo v: vehiculos){
            lstplacas.add(v.getPlaca());
        }
        
        if (lstplacas.contains(vehiculoActual.getPlaca())){
            Alert alerta= new Alert(AlertType.ERROR);
            alerta.setContentText("Vehiculo ya registrado");
            alerta.show();
//            App.setRoot("registrarV");            
        }else{
            vehiculos.add(vehiculoActual); 
            Vehiculo.saveListToFileSer("vehiculos.ser", vehiculos);
            App.setRoot("menu");
        }
        datosIngresados.clear();
    }

    @FXML
    private void regresar(MouseEvent event) throws IOException {
        App.setRoot("menu");
    }
}
