package modelo;

import controlador.Alerta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private Connection Conexion;

    public Connection obtenerConexion() {
        try {
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost/sisdenn?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            return Conexion;
        } catch (SQLException e) {
            Alerta.errorInicio("¡Error de conexión!", "Ocurrió un error al establecer conexión con la base de datos.", e.getMessage(), Alerta.BASE_DE_DATOS_NO_CONECTADA());
            return null;
        }
    }
}
