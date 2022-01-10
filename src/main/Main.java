package main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.ConexionBD;

public class Main extends Application {

    @Override
    public void start(Stage primerEscenario) throws IOException {

        ConexionBD conexionBD = new ConexionBD();
        if (conexionBD.obtenerConexion() != null) {
            Parent raiz = FXMLLoader.load(getClass().getResource("/vista/iniciarSesion.fxml"));
            primerEscenario.setTitle("Hello World");
            primerEscenario.setScene(new Scene(raiz));
            primerEscenario.setMinWidth(450);
            primerEscenario.setMinHeight(600);
            primerEscenario.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
