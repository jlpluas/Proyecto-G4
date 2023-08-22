/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectog4;

import modelo.Auto;
import modelo.Vehiculo;
import modelo.Camioneta;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FiltradoController implements Initializable {

    @FXML
    private TableView<Vehiculo> tabla;
    @FXML
    private TableColumn<Vehiculo, Integer> idColumn;
    @FXML
    private TableColumn<Vehiculo, String> placaColumn;
    @FXML
    private TableColumn<Vehiculo, String> marcaColumn;
    @FXML
    private TableColumn<Vehiculo, String> modeloColumn;
    @FXML
    private TableColumn<Vehiculo, String> tipoMotorColumn;
    @FXML
    private TableColumn<Vehiculo, Integer> añoColumn;
    @FXML
    private TableColumn<Vehiculo, Integer> recorridoColumn;
    @FXML
    private TableColumn<Vehiculo, String> colorColumn;
    @FXML
    private TableColumn<Vehiculo, String> tipoCombColumn;
    @FXML
    private TableColumn<Vehiculo, Integer> precioColumn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        presentar();
    }    

    public void presentar(){
        ObservableList<Vehiculo> observableVehiculos = FXCollections.observableArrayList(filtrar());
        tabla.getItems().clear();
        tabla.setItems(observableVehiculos);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        placaColumn.setCellValueFactory(new PropertyValueFactory<>("placa"));
        marcaColumn.setCellValueFactory(new PropertyValueFactory<>("marca"));
        modeloColumn.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tipoMotorColumn.setCellValueFactory(new PropertyValueFactory<>("tipo_motor"));
        añoColumn.setCellValueFactory(new PropertyValueFactory<>("año"));
        recorridoColumn.setCellValueFactory(new PropertyValueFactory<>("recorrido"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        tipoCombColumn.setCellValueFactory(new PropertyValueFactory<>("tipo_comb"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
    }
       
    public ArrayList<Vehiculo> filtrar(){
        ArrayList<Vehiculo> vehiculos = Vehiculo.readListFromFileSer("vehiculos.ser");
        ArrayList <Vehiculo> lstvehiculosp =filtrarParametros(vehiculos);    
        String tipoV= BuscarController.tipoVehiculo;
        ArrayList<Vehiculo> lstfiltrados=filtrarVehiculo(tipoV,lstvehiculosp);
        return lstfiltrados;
    }
    
    public ArrayList<Vehiculo> filtrarVehiculo(String tipo, ArrayList<Vehiculo> vehiculos){
        ArrayList<Vehiculo> autos=new ArrayList<>();
        ArrayList<Vehiculo> camionetas=new ArrayList<>();
        ArrayList<Vehiculo> motocicletas=new ArrayList<>();
        for (Vehiculo v: vehiculos){
            if (v instanceof Auto)
                autos.add(v);
            else if (v instanceof Camioneta)
                camionetas.add(v);
            else if (v instanceof Vehiculo)
                motocicletas.add(v);               
        }
        if (tipo.equals("Auto"))
            return autos;
        else if(tipo.equals("Camioneta"))
            return camionetas;
        else if(tipo.equals("Motocicleta"))
            return motocicletas;
        return null;
    }
    
    public ArrayList<Vehiculo> filtrarParametros(ArrayList<Vehiculo> vehiculos){
        ArrayList<Vehiculo> lstfinal= new ArrayList<>();
        for(Vehiculo v:vehiculos){
            if((BuscarController.recorridomax>=v.getRecorrido() && v.getRecorrido()>=BuscarController.recorridomin) && (BuscarController.añomax>=v.getAño()&& v.getAño()>=BuscarController.añomin) && (BuscarController.preciomax >=v.getPrecio() && v.getPrecio()>=BuscarController.preciomin))
                lstfinal.add(v);
        }
    
    return lstfinal;
    }

}
