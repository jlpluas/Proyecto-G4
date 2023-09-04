/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import ec.edu.espol.proyectog4.App;
import java.io.IOException;
import java.io.Serializable;
import modelo.Auto;
import modelo.Vehiculo;
import modelo.Camioneta;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FiltradoController implements Initializable , Serializable{

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
    private static Vehiculo vhSelec;

    public static Vehiculo getVhSelec() {
        return vhSelec;
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        presentar();
        tabla.setRowFactory(tv -> {
            TableRow<Vehiculo> r= new TableRow<>();
            r.setOnMouseClicked(event ->{
                if(!r.isEmpty() && event.getButton()== MouseButton.PRIMARY && event.getClickCount()==2){
                    vhSelec=r.getItem();
                    try {
                        App.setRoot("oferta");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    //Vehiculo vehiculoSel= r.getItem();
                    //abrirOferta(vehiculoSel);
                }
            });
            return r;
        });
    }  
    
//    private void abrirOferta(Vehiculo v){
//        try{
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("oferta.fxml"));
//            Parent root= loader.load();
//            OfertaController ofC= loader.getController();
//            ofC.datosVeh(v);
//            FiltradoController flC= new FiltradoController();
//            Stage st= new Stage();
//            st.setScene(new Scene(root));
//            st.show();
//            
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }

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

    @FXML
    private void regresar(MouseEvent event) throws IOException {
        App.setRoot("buscar");
    }

}
