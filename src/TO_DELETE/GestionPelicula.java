package TO_DELETE;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.ConectorBD;
import model.Pelicula;

public class GestionPelicula {

 // Método para insertar una película en la tabla
 public void insertarPelicula(Pelicula pelicula) throws SQLException  {
	    String query = ""
	    		+ "INSERT INTO Peliculas (titulo, director, anio_lanzamiento, genero, duracion) "
	    		+ "VALUES (?, ?, ?, ?, ?)";
	    
	    	PreparedStatement preparedStatement = ConectorBD.getConexion().prepareStatement(query); 
	        preparedStatement.setString(1, pelicula.getTitulo());
	        preparedStatement.setString(2, pelicula.getDirector());
	        preparedStatement.setInt(3, pelicula.getAnioLanzamiento());
	        preparedStatement.setString(4, pelicula.getGenero());
	        preparedStatement.setInt(5, pelicula.getDuracion());
	        preparedStatement.executeUpdate();
	    
}
 public void consultarTodasLasPeliculas() throws SQLException {
     
     String query = "SELECT * FROM Peliculas";
     Statement statement = ConectorBD.getConexion().createStatement();
     ResultSet resultSet = statement.executeQuery(query); 
         while (resultSet.next()) {
             System.out.println("Título: " + resultSet.getString("titulo") +
                     ", Director: " + resultSet.getString("director") +
                     ", Año: " + resultSet.getInt("anio_lanzamiento") +
                     ", Género: " + resultSet.getString("genero") +
                     ", Duración: " + resultSet.getInt("duracion") + " minutos");
         }
     
 }
     
// Método para mostrar películas por género
public void mostrarPeliculasPorGenero(String genero) throws SQLException {
 System.out.println("\n--- Mostrar Películas por Género ---");
 System.out.print("Ingresa el género: ");


 String query = "SELECT * FROM Peliculas WHERE genero like ?";
 // Opción 2
 // String query = "SELECT * FROM Peliculas WHERE genero LIKE CONCAT('%', ?, '%')";
 PreparedStatement preparedStatement = ConectorBD.getConexion().prepareStatement(query); 
 preparedStatement.setString(1, "%"+genero+"%");
 // Opción 2
 // preparedStatement.setString(1,genero);
 ResultSet resultSet = preparedStatement.executeQuery();

     if (!resultSet.isBeforeFirst()) {
         System.out.println("No se encontraron películas para el género: " + genero);
     } else {
         while (resultSet.next()) {
             System.out.println("Título: " + resultSet.getString("titulo") +
                     ", Director: " + resultSet.getString("director") +
                     ", Año: " + resultSet.getInt("anio_lanzamiento") +
                     ", Duración: " + resultSet.getInt("duracion") + " minutos");
         }
     }
 
}


}
