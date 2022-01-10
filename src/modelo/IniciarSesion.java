package modelo;

import controlador.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class IniciarSesion {

    ConexionBD conexionBD = new ConexionBD();
    Connection conexion = conexionBD.obtenerConexion();

    public ObservableList<String> obtenerUsuario() {
        ObservableList<String> usuarios = FXCollections.observableArrayList();

        String sql = "SELECT userName FROM user";

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet conjuntoResultante = sentencia.executeQuery(sql);
            while (conjuntoResultante.next()) {
                usuarios.add(conjuntoResultante.getString(1));
            }
        } catch (SQLException ignored) {
        }
        return usuarios;
    }

    public int verificarInicio(Usuarios usuario) {
        String sql = "SELECT iduser, idUserType, nameLastName, email, userName, password FROM user where userName = ?";

        try {
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setString(1, usuario.getUsuario());
            ResultSet conjuntoResultante = sentenciaPreparada.executeQuery();
            if (conjuntoResultante.next()) {
                if (usuario.getContrasenia().equals(conjuntoResultante.getString(6))) {
                    usuario.setId(conjuntoResultante.getInt(1));
                    usuario.setNombres_apellidos(conjuntoResultante.getString(3));
                    usuario.setTipo_usuario(conjuntoResultante.getInt(2));
                    return 1;
                }
                return -1;
            }
            return -1;
        } catch (SQLException e) {
            Alerta.error("Error de consulta MySQL", "Ocurrió un error al comprobar sus datos en la base de datos", e.getMessage(), Alerta.SQL());
            return 0;
        }

    }
}
