module ec.edu.espol.proyectog4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.proyectog4 to javafx.fxml;
    exports ec.edu.espol.proyectog4;
}
