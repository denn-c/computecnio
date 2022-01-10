package modelo;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;

public class MostrarContrasenia {
    
    private final PasswordField contrasenia1;
    private final TextField contrasenia2;
    private ToggleButton mostrarContrasenia;
    private KeyEvent evento;

    public MostrarContrasenia(PasswordField contrasenia1, TextField contrasenia2, ToggleButton mostrarContrasenia) {
        this.contrasenia1 = contrasenia1;
        this.contrasenia2 = contrasenia2;
        this.mostrarContrasenia = mostrarContrasenia;
    }

    public MostrarContrasenia(PasswordField contrasenia1, TextField contrasenia2, KeyEvent evento) {
        this.contrasenia1 = contrasenia1;
        this.contrasenia2 = contrasenia2;
        this.evento = evento;
    }

    public void mostrar() {
        if (mostrarContrasenia.isSelected()) {
            contrasenia2.setText(contrasenia1.getText());
            contrasenia1.setVisible(false);
            contrasenia2.setVisible(true);
            contrasenia2.requestFocus();
            contrasenia2.selectEnd();
        } else {
            contrasenia1.setText(contrasenia2.getText());
            contrasenia2.setVisible(false);
            contrasenia1.setVisible(true);
            contrasenia1.requestFocus();
            contrasenia1.selectEnd();
        }
    }

    public void sincronizar() {
        if (evento.getSource().equals(contrasenia1)) {
            contrasenia2.setText(contrasenia1.getText());
        } else if (evento.getSource().equals(contrasenia2)) {
            contrasenia1.setText(contrasenia2.getText());
        }
    }
}
