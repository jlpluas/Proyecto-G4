/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import ec.edu.espol.proyectog4.App;
import java.io.File;
import modelo.Auto;
import modelo.Vehiculo;
import modelo.Camioneta;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.*;
import javafx.stage.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class RegistrarVController implements Initializable, Serializable {

    @FXML
    private ComboBox<String> Vbox_tipoV;
    @FXML
    private HBox h;
    @FXML
    private VBox v1;
    @FXML
    private VBox v2;
    @FXML
    Button subirImagen=new Button("Subir Imagen");
    @FXML
    TextField imagenRuta=new TextField();
    
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

        v1.getChildren().addAll(new Label("Placa: "), new Label("Marca: "), new Label("Modelo: "), new Label("Tipo de Motor: "), new Label("Año: "), new Label("Recorrido: "), new Label("Color: "), new Label("Tipo de Combustible: "), new Label("Precio: "), new Label("Vidrios: "), new Label("Transmision: "));
        v1.getChildren().addAll(subirImagen);
        subirImagen();

        for (int v = 0; v < v1.getChildren().size(); v++) {
            v1.getChildren().get(v).setStyle("-fx-font-size: 16px;");
        }
        for (int i = 0; i < 11; i++) {
            TextField textField = new TextField();
            v2.getChildren().add(textField);
            datosIngresados.add("");
        }

        v2.getChildren().add(imagenRuta);
        datosIngresados.add("");
    }

    public void ingresarCamioneta() {
        v1.getChildren().addAll(new Label("Placa: "), new Label("Marca: "), new Label("Modelo: "), new Label("Tipo de Motor: "), new Label("Año: "), new Label("Recorrido: "), new Label("Color: "), new Label("Tipo de Combustible: "), new Label("Precio: "), new Label("Traccion: "));
        v1.getChildren().addAll(subirImagen);
        subirImagen();

        for (int v = 0; v < v1.getChildren().size(); v++) {
            v1.getChildren().get(v).setStyle("-fx-font-size: 16px;");
        }

        for (int i = 0; i < 10; i++) {
            TextField textField = new TextField();
            v2.getChildren().add(textField);
            datosIngresados.add("");
        }
        v2.getChildren().add(imagenRuta);
        datosIngresados.add("");
    }

    public void ingresarMotocicleta() {
        v1.getChildren().addAll(new Label("Placa: "), new Label("Marca: "), new Label("Modelo: "), new Label("Tipo de Motor: "), new Label("Año: "), new Label("Recorrido: "), new Label("Color: "), new Label("Tipo de Combustible: "), new Label("Precio: "));
        v1.getChildren().addAll(subirImagen);
        subirImagen();

        for (int v = 0; v < v1.getChildren().size(); v++) {
            v1.getChildren().get(v).setStyle("-fx-font-size: 16px;");
        }

        for (int i = 0; i < 9; i++) {
            TextField textField = new TextField();
            textField.setId("campo_" + i);
            v2.getChildren().add(textField);
            datosIngresados.add("");
        }
        v2.getChildren().add(imagenRuta);
        datosIngresados.add("");
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
            vehiculoActual.setColor(datosIngresados.get(6));
            vehiculoActual.setTipo_comb(datosIngresados.get(7));
            vehiculoActual.setUsuario(IngresoController.usuarioing);
            try {
                vehiculoActual.setAño(Integer.parseInt(datosIngresados.get(4)));
            } catch (NumberFormatException nf) {
                Alert alerta = new Alert(AlertType.ERROR);
                alerta.setContentText("Ingrese el año en números enteros");
                alerta.show();
            }
            try {
                vehiculoActual.setRecorrido(Integer.parseInt(datosIngresados.get(5)));
            } catch (NumberFormatException nf) {
                Alert alerta = new Alert(AlertType.ERROR);
                alerta.setContentText("Ingrese el recorrido en números enteros");
                alerta.show();
            }
            try {
                vehiculoActual.setPrecio(Integer.parseInt(datosIngresados.get(8)));
            } catch (NumberFormatException nf) {
                Alert alerta = new Alert(AlertType.ERROR);
                alerta.setContentText("Ingrese el precio en números enteros");
                alerta.show();
            }

            if (datosIngresados.size() == 10) {
                vehiculoActual.setImagen(datosIngresados.get(9));
                Vehiculo moto = vehiculoActual;
            }
            if (datosIngresados.size() == 12) {
                Auto auto = (Auto) vehiculoActual;
                auto.setVidrios(datosIngresados.get(9));
                auto.setTransmision(datosIngresados.get(10));
                vehiculoActual.setImagen(datosIngresados.get(11));

            }
            if (datosIngresados.size() == 11) {
                Camioneta camioneta = (Camioneta) vehiculoActual;
                camioneta.setTraccion(datosIngresados.get(9));
                vehiculoActual.setImagen(datosIngresados.get(10));
            }
        

               
    }
   
    @FXML
    private void registrar(ActionEvent event) throws IOException {
        
        try{
            guardarDatos();
            ArrayList<Vehiculo> vehiculos = Vehiculo.readListFromFileSer("vehiculos.ser");
            ArrayList<String> lstplacas = new ArrayList<>();
            for (Vehiculo v : vehiculos) {
                lstplacas.add(v.getPlaca());
            }

            if (lstplacas.contains(vehiculoActual.getPlaca())) {
                Alert alerta = new Alert(AlertType.ERROR);
                alerta.setContentText("Vehiculo ya registrado");
                alerta.show();
//            App.setRoot("registrarV");            
            } else {
                vehiculos.add(vehiculoActual);
                Vehiculo.saveListToFileSer("vehiculos.ser", vehiculos);
                App.setRoot("menu");
            }
            datosIngresados.clear();
        } catch(IndexOutOfBoundsException rt){
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setContentText("Debe llenar todos los campos");
            alerta.show();           
        } 
        

    }

    @FXML
    private void regresar(MouseEvent event) throws IOException {
        App.setRoot("menu");
    }
    

    
private void subirImagen() {
    subirImagen.setOnAction(eh -> {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.dir")));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagen", "*.png", "*.jpeg", "*.jpg"));
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            String imageName = selectedFile.getName();
            try {
                File destination = new File(imageName); 
                Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                imagenRuta.setText(imageName);
            } catch (IOException ex) {
                System.out.println("Error al copiar la imagen: " + ex.getMessage());
            }
        } else {
            System.out.println("Archivo no válido");
        }
    });
}

}
