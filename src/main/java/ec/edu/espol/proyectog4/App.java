package ec.edu.espol.proyectog4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {
    

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("ingreso"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
//        Usuario us1= new Usuario(1,"Joselyn","Barzola","espol","ljbarzol@espol.edu.ec","Joselyn16"); 
//        Usuario us2= new Usuario(2,"Isaac","Criollo", "espol","isaac@espol.edu.ec","Isaac");
//        ArrayList<Usuario> lstusuarios= new ArrayList<>();
//        lstusuarios.add(us1);
//        lstusuarios.add(us2);
//        Usuario.saveListToFileSer("usuarios.ser", lstusuarios);
        

    }

}