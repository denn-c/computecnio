package modelo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class CambiarEscena {
    
    public CambiarEscena(String recurso) {
        try {
            Parent raiz = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(recurso)));
            Stage escenario = new Stage();
            Scene escena = new Scene(raiz);
            escenario.setScene(escena);
            escenario.setResizable(false);
            escenario.show();
        }catch (IOException ignored){
        }
    }

    public CambiarEscena(URL ubicacion) {
        try {
            Parent raiz = FXMLLoader.load(Objects.requireNonNull(ubicacion));
            Stage escenario = new Stage();
            Scene escena = new Scene(raiz);
            escenario.setScene(escena);
            escena.setFill(Color.TRANSPARENT);
            escenario.initStyle(StageStyle.TRANSPARENT);
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.show();
        } catch (IOException ignored) {
        }
    }

    public CambiarEscena(URL ubicacion, Button boton) {
        try {
            Parent raiz = FXMLLoader.load(ubicacion);
            Stage escenario = (Stage) boton.getScene().getWindow();
            escenario.setScene(new Scene(raiz));
            escenario.show();
        } catch (IOException ignored) {
        }
    }
    
}
