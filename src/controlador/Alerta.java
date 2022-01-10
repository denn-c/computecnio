package controlador;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Modality;
import modelo.CambiarEscena;

public class Alerta implements Initializable {

    private static String mensaje;
    private static String detalles;
    private static String estiloTema;
    private static String estiloTemaTipo;
    private static Boolean mostrarBotonDetalles;
    private static String iconoTipo;
    private static String titulo;

    @FXML
    private FlowPane flowPanePrincipal;

    @FXML
    private AnchorPane anchorPaneContenedorPrimario;

    @FXML
    private VBox vBoxContenedorSecundario;

    @FXML
    private SVGPath svgPathIcono;
    
    @FXML
    private Label labelTitutlo;
    
    @FXML
    private Label labelDescripcion;

    @FXML
    private AnchorPane anchorPaneMargen;
    
    @FXML
    private AnchorPane anchorPaneContenedorDetalles;
    
    @FXML
    private TextArea textAreaDetalles;

    @FXML
    private AnchorPane anchorPaneContenedorBotonDetalles;
    
    @FXML
    private ToggleButton ToggleButtonMostarDetalles;
    
    @FXML
    private SVGPath svgPathMostrarDetalles;
    
    @FXML
    private Button buttonCerrar;

    @Override
    public void initialize(URL ubicacion, ResourceBundle recursos) {
        labelDescripcion.setText(mensaje);
        textAreaDetalles.setText(detalles);
        if (estiloTema == null) {
            estiloTema = "recursos/css/estilos-modo-oscuro.css";
        }
        flowPanePrincipal.getStylesheets().add(estiloTema);
        flowPanePrincipal.getStylesheets().add(estiloTemaTipo);
        anchorPaneContenedorBotonDetalles.setVisible(mostrarBotonDetalles);
        svgPathIcono.setContent(iconoTipo);
        labelTitutlo.setText(titulo);
    }

    @FXML
    public void cerrar() {
        Stage escenario = (Stage) buttonCerrar.getScene().getWindow();
        if (!escenario.getModality().equals(Modality.NONE)) {
            IniciarSesionControlador.quitarDesenfoque();
            RegistrarControlador.quitarDesenfoque();
        }
        escenario.close();
    }

    @FXML
    void verDetalles() {
        Stage escenario = (Stage) flowPanePrincipal.getScene().getWindow();
        RotateTransition Rotartransicion = new RotateTransition(Duration.millis(500), svgPathMostrarDetalles);
        if (ToggleButtonMostarDetalles.isSelected()) {
            if (escenario.getModality().equals(Modality.NONE)) {
                escenario.setHeight(380);
            } else {
                escenario.setHeight(330);
            }
            flowPanePrincipal.setPrefHeight(320);
            anchorPaneContenedorPrimario.setPrefHeight(310);
            vBoxContenedorSecundario.setPrefHeight(310);
            anchorPaneMargen.setPrefHeight(140);
            anchorPaneContenedorDetalles.setPrefHeight(130);
            textAreaDetalles.setPrefHeight(130);
            ToggleButtonMostarDetalles.setText("Ocultar detalles");
        } else {
            textAreaDetalles.setPrefHeight(0);
            anchorPaneContenedorDetalles.setPrefHeight(0);
            anchorPaneMargen.setPrefHeight(0);
            vBoxContenedorSecundario.setPrefHeight(170);
            anchorPaneContenedorPrimario.setPrefHeight(170);
            flowPanePrincipal.setPrefHeight(200);
            if (escenario.getModality().equals(Modality.NONE)) {
                escenario.setHeight(240);
            } else {
                escenario.setHeight(200);
            }
            ToggleButtonMostarDetalles.setText("Mostrar detalles");
        }
        Rotartransicion.setByAngle(180);
        Rotartransicion.play();

    }

    public static String BASE_DE_DATOS_NO_CONECTADA() {
        return "M10.5 0.1875C4.804688 0.1875 0.1875 2.34375 0.1875 5C0.1875 7.65625 4.804688 9.8125 10.5 9.8125C16.195313 9.8125 20.8125 7.65625 20.8125 5C20.8125 2.34375 16.195313 0.1875 10.5 0.1875 Z M 0.1875 6.28125L0.1875 10C0.1875 12.660156 4.804688 14.8125 10.5 14.8125C16.195313 14.8125 20.8125 12.660156 20.8125 10L20.8125 6.28125C20.8125 8.941406 16.195313 11.09375 10.5 11.09375C4.804688 11.09375 0.1875 8.941406 0.1875 6.28125 Z M 0.1875 11.28125L0.1875 15C0.1875 17.660156 4.804688 19.8125 10.5 19.8125C12.070313 19.8125 13.546875 19.636719 14.875 19.34375C14.609375 18.96875 14.46875 18.535156 14.46875 18.0625C14.46875 17.460938 14.703125 16.886719 15.125 16.46875L16.375 15.25C14.710938 15.789063 12.683594 16.09375 10.5 16.09375C4.804688 16.09375 0.1875 13.941406 0.1875 11.28125 Z M 20.8125 11.28125C20.8125 12.511719 19.816406 13.617188 18.1875 14.46875C18.742188 14.5 19.265625 14.734375 19.65625 15.125L20.5625 16.03125C20.71875 15.695313 20.8125 15.355469 20.8125 15 Z M 18.0625 16.0625C17.898438 16.0625 17.75 16.125 17.625 16.25L16.25 17.625C16 17.871094 16 18.25 16.25 18.5L18.75 21L16.25 23.5C16 23.746094 16 24.125 16.25 24.375L17.625 25.75C17.871094 26 18.25 26 18.5 25.75L21 23.25L23.5 25.75C23.746094 26 24.125 26 24.375 25.75L25.75 24.375C26 24.125 26 23.746094 25.75 23.5L23.25 21L25.75 18.5C26 18.253906 26 17.871094 25.75 17.625L24.375 16.25C24.125 16 23.746094 16 23.5 16.25L21 18.75L18.5 16.25C18.375 16.125 18.226563 16.0625 18.0625 16.0625 Z M 0.1875 16.28125L0.1875 20C0.1875 22.660156 4.804688 24.8125 10.5 24.8125C11.929688 24.8125 13.292969 24.683594 14.53125 24.4375C14.492188 24.277344 14.46875 24.105469 14.46875 23.9375C14.46875 23.335938 14.703125 22.761719 15.125 22.34375L16.46875 21L15.875 20.375C14.3125 20.820313 12.464844 21.09375 10.5 21.09375C4.804688 21.09375 0.1875 18.941406 0.1875 16.28125Z";
    }

    public static String ACCESO_DENEGADO() {
        return "M15 2.0019531C10.758 2.0019531 9 4.7229531 9 8.0019531C9 9.1059531 9.5273437 10.214844 9.5273438 10.214844C9.3153438 10.336844 8.9666875 10.724109 9.0546875 11.412109C9.2186875 12.695109 9.7749062 13.021828 10.128906 13.048828C10.263906 14.245828 11.55 15.777 12 16L12 18C11 21 3 19 3 26L14.523438 26C14.190437 25.06 14 24.054 14 23C14 19.461 16.047578 16.4085 19.017578 14.9375C19.426578 14.3675 19.801094 13.665828 19.871094 13.048828C20.225094 13.021828 20.781312 12.695109 20.945312 11.412109C21.033313 10.723109 20.684656 10.336844 20.472656 10.214844C20.472656 10.214844 21 9.2129531 21 8.0019531C21 5.5739531 20.047 3.5019531 18 3.5019531C18 3.5019531 17.289 2.0019531 15 2.0019531 z M 23 16C19.134 16 16 19.134 16 23C16 26.866 19.134 30 23 30C26.866 30 30 26.866 30 23C30 19.134 26.866 16 23 16 z M 21 20C21.25575 20 21.511531 20.097469 21.707031 20.292969L23 21.585938L24.292969 20.292969C24.683969 19.901969 25.316031 19.901969 25.707031 20.292969C26.098031 20.683969 26.098031 21.316031 25.707031 21.707031L24.414062 23L25.707031 24.292969C26.098031 24.683969 26.098031 25.316031 25.707031 25.707031C25.512031 25.902031 25.256 26 25 26C24.744 26 24.487969 25.902031 24.292969 25.707031L23 24.414062L21.707031 25.707031C21.512031 25.902031 21.256 26 21 26C20.744 26 20.487969 25.902031 20.292969 25.707031C19.901969 25.316031 19.901969 24.683969 20.292969 24.292969L21.585938 23L20.292969 21.707031C19.901969 21.316031 19.901969 20.683969 20.292969 20.292969C20.488469 20.097469 20.74425 20 21 20 z";
    }

    public static String ACCESO_CONCEDIDO() {
        return "M15 3.0019531C10.758 3.0019531 9 5.7229531 9 9.0019531C9 10.105953 9.5273437 11.214844 9.5273438 11.214844C9.3153438 11.336844 8.9666875 11.724109 9.0546875 12.412109C9.2186875 13.695109 9.7749062 14.021828 10.128906 14.048828C10.263906 15.245828 11.55 16.777 12 17L12 19C11 22 3 20 3 27L14.947266 27C14.346266 25.794 14 24.439 14 23C14 19.186 16.376563 15.933047 19.726562 14.623047C19.795562 14.427047 19.850094 14.233828 19.871094 14.048828C20.225094 14.021828 20.781312 13.695109 20.945312 12.412109C21.033313 11.723109 20.684656 11.336844 20.472656 11.214844C20.472656 11.214844 21 10.212953 21 9.0019531C21 6.5739531 20.047 4.5019531 18 4.5019531C18 4.5019531 17.289 3.0019531 15 3.0019531 z M 23 16C19.134 16 16 19.134 16 23C16 26.866 19.134 30 23 30C26.866 30 30 26.866 30 23C30 19.134 26.866 16 23 16 z M 26 20C26.25575 20 26.511531 20.097469 26.707031 20.292969C27.098031 20.683969 27.098031 21.316031 26.707031 21.707031L22.707031 25.707031C22.512031 25.902031 22.256 26 22 26C21.744 26 21.487969 25.902031 21.292969 25.707031L19.292969 23.707031C18.901969 23.316031 18.901969 22.683969 19.292969 22.292969C19.683969 21.901969 20.316031 21.901969 20.707031 22.292969L22 23.585938L25.292969 20.292969C25.488469 20.097469 25.74425 20 26 20 z";
    }

    public static String SQL() {
        return "M6 4C4.895 4 4 4.895 4 6L4 24C4 25.105 4.895 26 6 26L24 26C25.105 26 26 25.105 26 24L26 6C26 4.895 25.105 4 24 4L6 4 z M 15.121094 10.888672C16.605094 10.888672 17.183594 12.515922 17.183594 14.419922C17.183594 16.253922 16.762547 17.326547 16.060547 17.685547L16.060547 17.730469C16.519547 17.934469 17.0075 18.091047 17.4375 18.248047L17.039062 19.111328C16.464063 18.876328 15.768641 18.525672 15.306641 18.263672C15.165641 18.187672 15.063766 18.132813 15.009766 18.132812C13.725766 18.132812 12.892578 16.874047 12.892578 14.498047C12.892578 12.391047 13.694094 10.888672 15.121094 10.888672 z M 10.189453 10.908203C10.645453 10.908203 11.057094 11.044016 11.246094 11.166016L11.039062 12.128906C10.847062 12.002906 10.546062 11.886719 10.164062 11.886719C9.5270625 11.886719 9.2265625 12.320141 9.2265625 12.744141C9.2265625 13.293141 9.5075156 13.542813 10.228516 14.007812C11.127516 14.589813 11.486328 15.275156 11.486328 16.035156C11.485328 17.302156 10.573594 18.111328 9.3085938 18.111328C8.7765937 18.111328 8.2463906 17.962406 8.0253906 17.816406L8.2148438 16.851562C8.4788437 17.010562 8.9375469 17.134766 9.3105469 17.134766C10.000547 17.134766 10.378906 16.730344 10.378906 16.152344C10.378906 15.580344 10.030172 15.258375 9.4511719 14.859375C8.6691719 14.357375 8.1171875 13.653672 8.1171875 12.888672C8.1171875 11.782672 8.8684531 10.908203 10.189453 10.908203 z M 19 10.972656L20.119141 10.972656L20.119141 17.099609L22.011719 17.099609L22.011719 18.048828L19 18.048828L19 10.972656 z M 15.058594 11.865234C14.334594 11.865234 14.050641 13.145328 14.056641 14.486328C14.044641 15.904328 14.305594 17.15625 15.058594 17.15625C15.778594 17.15625 16.019531 15.90575 16.019531 14.46875C16.019531 13.13575 15.798594 11.865234 15.058594 11.865234 z";
    }

    public static void error(String titulo_error, String mensaje_error, String detalles_error, String icono_error) {
        mostrarBotonDetalles = true;
        IniciarSesionControlador.agregarDesenfoque();
       RegistrarControlador.agregarDesenfoque();
        estiloTemaTipo = "recursos/css/estilos-error.css";
        iconoTipo = icono_error;
        titulo = titulo_error;
        mensaje = mensaje_error;
        detalles = detalles_error;
        new CambiarEscena(Alerta.class.getResource("/vista/alerta.fxml"));
    }

    public static void errorInicio(String titulo_error, String mensaje_error, String detalles_error, String icono_error) {
        mostrarBotonDetalles = true;
        estiloTemaTipo = "recursos/css/estilos-error.css";
        iconoTipo = icono_error;
        titulo = titulo_error;
        mensaje = mensaje_error;
        detalles = detalles_error;
        new CambiarEscena("/vista/alerta.fxml");
    }

    public static void exitoso(String titulo_exitoso, String exitoso_exitoso, String icono_exitoso) {
        mostrarBotonDetalles = false;
        IniciarSesionControlador.agregarDesenfoque();
        RegistrarControlador.agregarDesenfoque();
        titulo = titulo_exitoso;
        estiloTemaTipo = "recursos/css/estilos-exitoso.css";
        iconoTipo = icono_exitoso;
        mensaje = exitoso_exitoso;
        new CambiarEscena(Alerta.class.getResource("/vista/alerta.fxml"));
    }

    public static void establecerEstilo(String estilos) {
        estiloTema = estilos;
    }
}
