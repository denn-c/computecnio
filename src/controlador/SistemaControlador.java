package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import modelo.CambiarEscena;

public class SistemaControlador{   
    @FXML
    private Button buttonAtras;

    @FXML
    void irAtras() {
        new CambiarEscena(getClass().getResource("/vista/iniciarSesion.fxml"),buttonAtras);
    }

    @FXML
    void irConfiguracion() {
        new CambiarEscena(getClass().getResource("/vista/configuracion.fxml"));
    }
}
