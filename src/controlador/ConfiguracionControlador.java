package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class ConfiguracionControlador {

    @FXML
    private Button buttonAtras;

    @FXML
    private ToggleButton toggleButtonCambiarTema;

    @FXML
    void CambiarTema() {

        if (toggleButtonCambiarTema.isSelected()) {
            toggleButtonCambiarTema.setText("Modo Oscuro");
            Alerta.establecerEstilo("/recursos/css/estilos-modo-claro.css");
            RegistrarControlador.establecerEstilo("/recursos/css/estilos-modo-claro.css");
            IniciarSesionControlador.establecerEstilo("/recursos/css/estilos-modo-claro.css");
//            RecoverPasswordController.setStyle("/recursos/css/styles-light.css");


        } else {
            toggleButtonCambiarTema.setText("Modo Claro");
            Alerta.establecerEstilo("/recursos/css/estilos-modo-oscuro.css");
            RegistrarControlador.establecerEstilo("/recursos/css/estilos-modo-oscuro.css");
            IniciarSesionControlador.establecerEstilo("/recursos/css/estilos-modo-oscuro.css");
//            RecoverPasswordController.setStyle("/recursos/css/styles-dark.css");
        }
    }

    @FXML
    void irAtras() {
        Stage escenario = (Stage) buttonAtras.getScene().getWindow();
        escenario.close();
    }
}
