package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;
import modelo.CifradoContrasenia;
import modelo.MostrarContrasenia;
import modelo.Registrar;
import modelo.Usuarios;

public class RegistrarControlador implements Initializable{
    
    private static String estiloTema;
    private static FlowPane flowPanePrincipalEstatico;
    private static final String[] datosUsuario = new String[2];

    @FXML
    private FlowPane flowPanePrincipal;

    @FXML
    private TextField textFieldNombresApelllidos;

    @FXML
    private TextField textFieldCorreo;

    @FXML
    private TextField textFieldUsuario;

    @FXML
    private TextField textFieldContrasenia1;

    @FXML
    private PasswordField passwordFieldContrasenia1;

    @FXML
    private TextField textFieldContrasenia2;

    @FXML
    private PasswordField passwordFieldContrasenia2;

    @FXML
    private TextField textFieldTelefono;

    @FXML
    private ToggleButton toggleButtonMostrarContrasenia1;

    @FXML
    private ToggleButton toggleButtonMostrarContrasenia2;

    @FXML
    private ComboBox<String> comboBoxTipoUsuario;

    @Override
    public void initialize(URL ubicacion, ResourceBundle recursos) {
        Registrar registrar = new Registrar();
        comboBoxTipoUsuario.setItems(registrar.cargarTipoUsuario());
        if (estiloTema == null) {
            estiloTema = "recursos/css/estilos-modo-oscuro.css";
        }
        flowPanePrincipal.getStylesheets().add(estiloTema);
        flowPanePrincipalEstatico = flowPanePrincipal;
    }

    @FXML
    void MostrarContrasenia(ActionEvent evento) {
        if (evento.getSource().equals(toggleButtonMostrarContrasenia1)) {
            MostrarContrasenia mostrarContrasenia = new MostrarContrasenia(passwordFieldContrasenia1, textFieldContrasenia1, toggleButtonMostrarContrasenia1);
            mostrarContrasenia.mostrar();
        } else if (evento.getSource().equals(toggleButtonMostrarContrasenia2)) {
            MostrarContrasenia mostrarContrasenia = new MostrarContrasenia(passwordFieldContrasenia2, textFieldContrasenia2, toggleButtonMostrarContrasenia2);
            mostrarContrasenia.mostrar();
        }
    }

    @FXML
    void sincronizarContrasenia(KeyEvent evento) {
        if (evento.getSource().equals(passwordFieldContrasenia1) || evento.getSource().equals(textFieldContrasenia1)) {
            MostrarContrasenia mostrarContrasenia = new MostrarContrasenia(passwordFieldContrasenia1, textFieldContrasenia1, evento);
            mostrarContrasenia.sincronizar();
        } else if (evento.getSource().equals(passwordFieldContrasenia2) || evento.getSource().equals(textFieldContrasenia2)) {
            MostrarContrasenia mostrarContrasenia = new MostrarContrasenia(passwordFieldContrasenia2, textFieldContrasenia2, evento);
            mostrarContrasenia.sincronizar();
        }
    }

    @FXML
    void Registrar() {

        Registrar registrar = new Registrar();
        Usuarios usuario = new Usuarios();

        String contrasenia = passwordFieldContrasenia1.getText();
        String contraseniaCifrada = CifradoContrasenia.sha1(contrasenia);

        usuario.setTipo_usuario(1);
        usuario.setNombres_apellidos(textFieldNombresApelllidos.getText());
        usuario.setCorreo(textFieldCorreo.getText());
        usuario.setUsuario(textFieldUsuario.getText());
        usuario.setContrasenia(contraseniaCifrada);
        String telefono = textFieldTelefono.getText();
        if(telefono.isEmpty()){
            usuario.setTelefono(00000000);
        }else{
            usuario.setTelefono(Integer.parseInt(telefono));
        }
        if (registrar.registrar(usuario)) {
            Alerta.exitoso("Registro Exitoso", "Su registro re realizo de forma exitosa, ya puedes iniciar sesión", Alerta.ACCESO_CONCEDIDO());
        }
    }
    public static void agregarDesenfoque() {
        GaussianBlur DesenfoqueGaussiano = new GaussianBlur(20);
        if (flowPanePrincipalEstatico !=null){
            flowPanePrincipalEstatico.setEffect(DesenfoqueGaussiano);
        }

    }

    public static void quitarDesenfoque() {
        if (flowPanePrincipalEstatico !=null){
            flowPanePrincipalEstatico.setEffect(null);
        }
    }

    public static void establecerEstilo(String estilos) {
        estiloTema = estilos;
    }
    
}
