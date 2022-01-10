package controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.SVGPath;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;

import modelo.CambiarEscena;
import modelo.CifradoContrasenia;
import modelo.IniciarSesion;
import modelo.MostrarContrasenia;
import modelo.RecuperarContrasenia;
import modelo.Usuarios;

import org.controlsfx.control.textfield.TextFields;

public class IniciarSesionControlador implements Initializable {

    public static FlowPane flowPanePrincipalEstatico;
    private static String[] datosUsuario = new String[2];
    private static String estiloTema;

    @FXML
    private FlowPane flowPanePrincipal;

    @FXML
    private TextField textFieldUsuario;

    @FXML
    private SVGPath svgPathErrorUsuario;

    @FXML
    private TextField textFieldContrasenia2;

    @FXML
    private PasswordField passwordFieldContrasenia1;

    @FXML
    private SVGPath svgPathErrorContrasenia;
    
    @FXML
    private ToggleButton toggleButtonMostrarContrasenia;

    @FXML
    private Button buttonRegistrarse;

    @Override
    public void initialize(URL ubicacion, ResourceBundle recursos) {

        if (estiloTema == null) {
            estiloTema = "recursos/css/estilos-modo-oscuro.css";
        }

        flowPanePrincipal.getStylesheets().add(estiloTema);
        flowPanePrincipalEstatico = flowPanePrincipal;

        IniciarSesion iniciarSesion = new IniciarSesion();
        ObservableList<String> usuario = iniciarSesion.obtenerUsuario();
        TextFields.bindAutoCompletion(textFieldUsuario, usuario);

    }

    @FXML
    void MostrarContrasenia() {
        MostrarContrasenia mostrarContrasenia = new MostrarContrasenia(passwordFieldContrasenia1, textFieldContrasenia2, toggleButtonMostrarContrasenia);
        mostrarContrasenia.mostrar();
    }

    @FXML
    void sincronizarContrasenia(KeyEvent evento) {
        MostrarContrasenia mostrarContrasenia = new MostrarContrasenia(passwordFieldContrasenia1, textFieldContrasenia2, evento);
        mostrarContrasenia.sincronizar();
    }

    @FXML
    void ocultarError(KeyEvent evento) {

        if (evento.getSource().equals(textFieldUsuario)) {
            if (svgPathErrorUsuario.isVisible()) {
                ocultarError(textFieldUsuario, svgPathErrorUsuario);
            }
        } else if (evento.getSource().equals(passwordFieldContrasenia1) || evento.getSource().equals(textFieldContrasenia2)) {
            if (svgPathErrorContrasenia.isVisible()) {
                ocultarError(textFieldContrasenia2, svgPathErrorContrasenia);
                ocultarError(passwordFieldContrasenia1);
            }
        }
    }

    @FXML
    void iniciarSesion() {
        if (textFieldUsuario.getText().isEmpty()) {
            mostrarError(textFieldUsuario, svgPathErrorUsuario);
        } else if (passwordFieldContrasenia1.getText().isEmpty()) {
            mostrarError(textFieldContrasenia2, svgPathErrorContrasenia);
            mostrarError(passwordFieldContrasenia1);
        } else {
            IniciarSesion iniciarSesion = new IniciarSesion();
            Usuarios usuarios = new Usuarios();
            String contrasenia = CifradoContrasenia.sha1(passwordFieldContrasenia1.getText());
            usuarios.setUsuario(textFieldUsuario.getText());
            usuarios.setContrasenia(contrasenia);
            int respueta = iniciarSesion.verificarInicio(usuarios);
            if (respueta == 1) {
                new CambiarEscena(getClass().getResource("/vista/sistema.fxml"), buttonRegistrarse);
            } else if (respueta == -1) {
                Alerta.error("Acceso denegado", "Los datos que ingresaste no son correctos, o usted no esta registrado", "Verifique que su nombre de usuario y contraseña sean los correctos, en caso de que usted no este registrado en el sistema comuníquese con el administrador del sistema para proceder con su registro", Alerta.ACCESO_DENEGADO());
            }
        }
    }

    @FXML
    void recuperarContrasenia() {

        if (textFieldUsuario.getText().isEmpty()) {
            mostrarError(textFieldUsuario, svgPathErrorUsuario);
        } else {
            Usuarios usuarios = new Usuarios();
            usuarios.setUsuario(textFieldUsuario.getText());
            RecuperarContrasenia recuperarContrasenia = new RecuperarContrasenia();
            if (recuperarContrasenia.verificarUsuario(usuarios) == 1) {
                datosUsuario = recuperarContrasenia.obtenerDatosUsuario();
                new CambiarEscena(getClass().getResource("/vista/recuperarContrasenia.fxml"));
                agregarDesenfoque();

            } else if (recuperarContrasenia.verificarUsuario(usuarios) == -1) {
                Alerta.error("El usuario no existe", "El usuario que ingresaste no esta registrado en el sistema", "Verifique que el usuario que ingresaste se valido, caso contrario su cuenta de usuario fue eliminado por el administrador, ponte en contacto con el administrador", Alerta.ACCESO_DENEGADO());
            }
        }
    }

    @FXML
    void registarse() {
        new CambiarEscena(getClass().getResource("/vista/registrar.fxml"), buttonRegistrarse);
    }

    public static String[] obtenerDatosUsuario() {
        return datosUsuario;
    }

    private void mostrarError(TextField textField, SVGPath svgPath) {
        textField.getStylesheets().add("recursos/css/estilos-validacion.css");
        textField.setPromptText("Por favor rellene este campo");
        textField.requestFocus();
        svgPath.setVisible(true);
    }

    private void mostrarError(PasswordField passwordField) {
        passwordField.getStylesheets().add("recursos/css/estilos-validacion.css");
        passwordField.setPromptText("Por favor rellene este campo");
        passwordField.requestFocus();
    }

    private void ocultarError(TextField textField, SVGPath svgPath) {
        textField.getStylesheets().clear();
        textField.setPromptText("Usuario");
        svgPath.setVisible(false);
    }

    private void ocultarError(PasswordField passwordField) {
        passwordField.getStylesheets().clear();
        passwordField.setPromptText("Contraseña");
    }

    public static void agregarDesenfoque() {
        GaussianBlur DesenfoqueGaussiano = new GaussianBlur(20);
        flowPanePrincipalEstatico.setEffect(DesenfoqueGaussiano);
    }

    public static void quitarDesenfoque() {
        flowPanePrincipalEstatico.setEffect(null);
    }

    public static void establecerEstilo(String estilos) {
        estiloTema = estilos;
    }

}
