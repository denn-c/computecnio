package modelo;

import controlador.Alerta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecuperarContrasenia {

    ConexionBD conexionBD = new ConexionBD();
    Connection conexion = conexionBD.obtenerConexion();

    String[] datosUsuario = new String[2];

    public int verificarUsuario(Usuarios usuario) {
        String sql = "SELECT userName, email FROM user WHERE userName =?";

        try {
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setString(1, usuario.getUsuario());
            ResultSet conjuntoResultante = sentenciaPreparada.executeQuery();
            if (conjuntoResultante.next()) {
                datosUsuario[0] = conjuntoResultante.getString(1);
                datosUsuario[1] = conjuntoResultante.getString(2);
                return 1;
            }
            return -1;
        } catch (SQLException e) {
            Alerta.error("Error de consulta MySQL", "Ocurrió un error al comprobar sus datos en la base de datos", e.getMessage(), Alerta.SQL());
            return 0;
        }
    }

    public String[] obtenerDatosUsuario() {
        return datosUsuario;
    }
}
