package controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;


public class RecuperarContraseniaControlador implements Initializable {

   private String correo_destinatario;
    private String usuario_destinatario;
    private static String estiloTema;

    @FXML
    private FlowPane flowPanePrincipal;

    @FXML
    private Label labelCorreo;

    @FXML
    private Button buttonCancelar;

    @FXML
    private ImageView imageViewPerfil;

    @Override
    public void initialize(URL ubicacion, ResourceBundle recursos) {

        if (estiloTema == null) {
            estiloTema = "recursos/css/estilos-modo-oscuro.css";
        }
        flowPanePrincipal.getStylesheets().add(estiloTema);

        Rectangle rentangulo = new Rectangle(100, 120);
        rentangulo.setArcWidth(20);
        rentangulo.setArcHeight(20);
        imageViewPerfil.setClip(rentangulo);

        usuario_destinatario = IniciarSesionControlador.obtenerDatosUsuario()[0];
        correo_destinatario = IniciarSesionControlador.obtenerDatosUsuario()[1];
        labelCorreo.setText(labelCorreo.getText() + correo_destinatario);
    }

    @FXML
    void cancelar() {
        Stage escenario = (Stage) buttonCancelar.getScene().getWindow();
        IniciarSesionControlador.quitarDesenfoque();
        escenario.close();
    }

    @FXML
    void continuar() {

        Random aleatorio = new Random();
        int codigo_aleatorio = aleatorio.nextInt(999999);
        String host = "smtp.gmail.com";
        String correo_remitente = "dennyschuyma@gmail.com";
        String contrasenia_remitente = "tukalukita_3000";
        String correo_receptor = correo_destinatario;
        String asunto = codigo_aleatorio + " es el código de recuperación de tu cuenta Computecnio";
        String texto = "Hola, " + usuario_destinatario + ":\n" +
                "Hemos recibido una solicitud para modificar la contraseña del sistema computecnio.\n" +
                "Introduce el siguiente código para restablecer la contraseña: <h1>" + codigo_aleatorio+"</h1>";
      
        Properties propiedades = System.getProperties();
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.socketFactory.port", "465");
        propiedades.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.port", "465");

        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        Session sesion = Session.getDefaultInstance(propiedades, null);
        sesion.setDebug(false);

        Message mensaje = new MimeMessage(sesion);
        try {
            mensaje.setFrom(new InternetAddress(correo_remitente));
            InternetAddress[] direccion = {new InternetAddress(correo_receptor)};
            mensaje.setRecipients(Message.RecipientType.TO, direccion);
            mensaje.setSubject(asunto);
            mensaje.setContent(texto, "text/html");
            Transport transporte = sesion.getTransport("smtp");
            transporte.connect(host, correo_remitente, contrasenia_remitente);
            transporte.sendMessage(mensaje, mensaje.getAllRecipients());
            transporte.close();
            Alerta.exitoso("Código enviado", "Revise su corre electrónico asociado a esta cuenta hemos enviado un correo de recuperación", Alerta.ACCESO_CONCEDIDO());
        } catch (MessagingException e) {
            Alerta.error("Error al enviar", "no se pudo enviar el código de recuperación", e.getMessage(), Alerta.ACCESO_DENEGADO());
        }
    }

    public static void establecerEstilo(String estilos) {
        estiloTema = estilos;
    }
}
