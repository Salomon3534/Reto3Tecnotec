package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import db.DatabaseConnector;
import model.Guest;

public class ManagerGuests {

    private ArrayList<Guest> guestList = new ArrayList<>();

    // constructor para inicializar la lista cargando datos de la bd
    public ManagerGuests() throws SQLException {
        loadGuests();
    }

    // carga todos los registros de la tabla guests en el arraylist local
    public void loadGuests() throws SQLException {
        guestList.clear();
        String query = "SELECT * FROM Guests";
        
        try (PreparedStatement preparedStatement = DatabaseConnector.getConexion().prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
                // mapeo exacto con los atributos del modelo guest
                Guest guest = new Guest(
                    resultSet.getString("username"),
                    resultSet.getString("name"),
                    resultSet.getString("surnames"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("career"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
                );
                guestList.add(guest);
            }
        }
    }

    // inserta un nuevo invitado y actualiza la lista local
    public String createGuest(String username, String name, String surnames, String phoneNumber, String career, String email, String password) throws SQLException {
        String query = "INSERT INTO Guests (username, name, surnames, phoneNumber, career, email, password) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement preparedStatement = DatabaseConnector.getConexion().prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, surnames);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, career);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, password);
            
            preparedStatement.executeUpdate();
        }
        
        loadGuests();
        return "invitado '" + username + "' creado y lista actualizada.";
    }

    // devuelve la representacion en texto de todos los invitados locales
    public String listGuests() {
        if (guestList.isEmpty()) {
            return "no hay invitados registrados.";
        }
        
        StringBuilder sb = new StringBuilder();
        for (Guest g : guestList) {
            sb.append("\n").append(g.toString());
        }
        return sb.toString();
    }

    // actualiza un invitado usando el objeto guest y sus getters
    public String updateGuest(Guest guest) throws SQLException {
        String query = "UPDATE Guests SET name = ?, surnames = ?, phoneNumber = ?, career = ?, email = ?, password = ? WHERE username = ?";
        
        try (PreparedStatement preparedStatement = DatabaseConnector.getConexion().prepareStatement(query)) {
            preparedStatement.setString(1, guest.getName());
            preparedStatement.setString(2, guest.getSurnames());
            preparedStatement.setString(3, guest.getPhoneNumber());
            preparedStatement.setString(4, guest.getCareer());
            preparedStatement.setString(5, guest.getEmail());
            preparedStatement.setString(6, guest.getPassword());
            preparedStatement.setString(7, guest.getUsername());
            
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                loadGuests();
                return "invitado actualizado correctamente.";
            }
        }
        return "no se encontro el invitado para actualizar.";
    }

    // elimina un invitado recibiendo el objeto completo
    public String deleteGuest(Guest guest) throws SQLException {
        String query = "DELETE FROM Guests WHERE username = ?";
        
        try (PreparedStatement preparedStatement = DatabaseConnector.getConexion().prepareStatement(query)) {
            preparedStatement.setString(1, guest.getUsername());
            
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                loadGuests();
                return "invitado eliminado y lista sincronizada.";
            }
        }
        return "invitado no encontrado.";
    }

    // retorna la cantidad de invitados cargados en memoria
    public int getGuestCount() {
        return guestList.size();
    }
}