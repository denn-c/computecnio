package modelo;

import controlador.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Registrar {

    ConexionBD conexionBD = new ConexionBD();
    Connection conexion = conexionBD.obtenerConexion();

    public ObservableList<String> cargarTipoUsuario() {

        ObservableList<String> tipo_usuario = FXCollections.observableArrayList();
        String sql = "SELECT name FROM user_type";

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet conjuntoResultante = sentencia.executeQuery(sql);
            while (conjuntoResultante.next()) {
                tipo_usuario.add(conjuntoResultante.getString(1));
            }
        } catch (SQLException e) {
            Alerta.error("Error de consulta MySQL", "Ocurrió un error al cargar los tipos de usuario de la base de datos", e.getMessage(), Alerta.SQL());
        }
        return tipo_usuario;
    }

    public boolean registrar(Usuarios usuario) {
        String sql = "INSERT INTO user (idUserType,nameLastName, email, userName, password, phone) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setInt(1, usuario.getTipo_usuario());
            sentenciaPreparada.setString(2, usuario.getNombres_apellidos());
            sentenciaPreparada.setString(3, usuario.getCorreo());
            sentenciaPreparada.setString(4, usuario.getUsuario());
            sentenciaPreparada.setString(5, usuario.getContrasenia());
            sentenciaPreparada.setInt(6, usuario.getTelefono());
            sentenciaPreparada.execute();
            return true;
        } catch (SQLException e) {
            Alerta.error("Error de registro MySQL", "Ocurrió un error al ingresar los datos de usuario de la base de datos", e.getMessage(), Alerta.SQL());
        }
        return false;
    }
}
