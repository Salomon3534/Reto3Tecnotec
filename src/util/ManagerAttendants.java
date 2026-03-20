package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import db.DatabaseConnector;
import model.Attendant;

public class ManagerAttendants {

    private ArrayList<Attendant> attendantsList = new ArrayList<>();

    public ManagerAttendants() throws SQLException {
        loadAttendants();
    }

    public void loadAttendants() throws SQLException {
        attendantsList.clear();
        String query = "SELECT * FROM USUARIO";
        try (PreparedStatement ps = DatabaseConnector.getConexion().prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                attendantsList.add(new Attendant(
                    rs.getString("DNI"), 
                    rs.getString("NOMBRE"),
                    rs.getString("APELLIDO"), 
                    rs.getString("EMAIL")
                ));
            }
        }
    }

    public String createAttendant(String dni, String name, String surname, String email) throws SQLException {
        String query = "INSERT INTO USUARIO VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = DatabaseConnector.getConexion().prepareStatement(query)) {
            ps.setString(1, dni); ps.setString(2, name); ps.setString(3, surname);
            ps.setString(6, email);
            ps.executeUpdate();
        }
        loadAttendants();
        return "¡Usuario '" + name + "' creado con éxito!";
    }

    public String listAttendants() {
        if (attendantsList.isEmpty()) return "No hay usuarios registrados.";
        StringBuilder sb = new StringBuilder();
        for (Attendant a : attendantsList) sb.append(a.toString());
        return sb.toString();
    }

    public String updateAttendants(Attendant a) throws SQLException {
        String query = "UPDATE USUARIO SET DNI=?, NOMBRE=?, APELLIDO=?, EMAIL=? WHERE DNI=?";
        try (PreparedStatement ps = DatabaseConnector.getConexion().prepareStatement(query)) {
            ps.setString(1, a.getDni());
            ps.setString(2, a.getName());
            ps.setString(3, a.getSurname());
            ps.setString(4, a.getEmail());
            ps.setString(5, a.getDni());
            if (ps.executeUpdate() > 0) {
                loadAttendants();
                return "Datos de '" + a.getName() + "' actualizados correctamente.";
            }
        }
        return "Error: El usuario no existe.";
    }

    public String deleteAttendant(String dni) throws SQLException {
        String query = "DELETE FROM USUARIO WHERE DNI = ?";
        try (PreparedStatement ps = DatabaseConnector.getConexion().prepareStatement(query)) {
            ps.setString(1, dni);
            if (ps.executeUpdate() > 0) {
                loadAttendants();
                return "El usuario con DNI: '" + dni + "' se ha eliminado correctamente.";
            }
        }
        return "Error: No se encontró al usuario con el dni'" + dni + "'.";
    }
}