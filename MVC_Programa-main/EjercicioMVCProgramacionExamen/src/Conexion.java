

import java.sql.*;

/**
 *
 * @author raul.de1
 */
public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/concesionario";
    private static final String USER = "root";
    private static final String PASS = ""; //Tu contraseña aquí
    
    //Constructor privado para evitar instancias
    private Conexion() {}
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
        }
    }