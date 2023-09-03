
module ec.edu.espol.proyectog4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.mail;

    opens ec.edu.espol.proyectog4 to javafx.fxml;
    exports ec.edu.espol.proyectog4;
    opens modelo to javafx.fxml;
    exports modelo;
    opens controladores to javafx.fxml;
    exports controladores;
    
}
