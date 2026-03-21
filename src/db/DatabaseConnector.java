package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnector {
    
    private static Connection conexion;
    
    public static Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            conectar();
        }
        return conexion;
    }

    public static void setConexion(Connection conexion) {
        DatabaseConnector.conexion = conexion;
    }
   
    public static void conectar() throws SQLException {
        try {
            // cargamos el driver de mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // establecemos la conexion con los datos de bd					BBDD				USER		PASSWORD
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/euskalencounter", "root", "1DAW3");
            
            System.out.println("conexion establecida con exito.");
            
        } catch (ClassNotFoundException e) {
            System.err.println("error: no se encontro el driver jdbc.");
            throw new SQLException(e);
        } catch (SQLException e) {
            System.err.println("error: no se pudo conectar a la base de datos.");
            throw e;
        }
    }

    // cierra la conexion
    public static void cerrarConexion() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
            System.out.println("conexion cerrada.");
        }
    }
}