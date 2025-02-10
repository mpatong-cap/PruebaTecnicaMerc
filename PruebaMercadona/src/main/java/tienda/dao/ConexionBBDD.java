package tienda.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {

    private static final String URL = "jdbc:mysql://localhost:3306/pruebamercadona"; // Cambia tu_base_datos por el nombre de tu base de datos
    private static final String USERNAME = "root"; // Cambia root por tu usuario de base de datos
    private static final String PASSWORD = "root"; // Cambia password por tu contraseña

    static {
        try {
            // Registrar el Driver de MySQL (o el driver de la base de datos que estés utilizando)
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener una conexión a la base de datos
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
