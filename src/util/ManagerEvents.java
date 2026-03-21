package util;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import db.DatabaseConnector;
import model.Event;

public class ManagerEvents {

    private ArrayList<Event> eventList = new ArrayList<>();

    public ManagerEvents() throws SQLException {
        loadEvents();
    }

    public void loadEvents() throws SQLException {
        eventList.clear();
        String query = "SELECT ID, TITULO, UBICACION, DESCRIPCION, FECHA_INICIO, FECHA_FIN, HORARIO_INICIO, HORARIO_FIN, ENCUENTRO_CODIGO FROM EVENTO";
        try (PreparedStatement ps = DatabaseConnector.getConexion().prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                eventList.add(new Event(
                    rs.getInt("ID"), 
                    rs.getString("TITULO"),
                    rs.getString("UBICACION"), 
                    rs.getString("DESCRIPCION"),
                    rs.getDate("FECHA_INICIO"), 
                    rs.getDate("FECHA_FIN"),
                    rs.getTime("HORARIO_INICIO"),
                    rs.getTime("HORARIO_FIN"),
                    rs.getString("ENCUENTRO_CODIGO")
                ));
            }
        }
    }

    public String createEvent(String title, String location, String description, Date dateStart, Date dateEnd,
    		Time hourStart, Time hourEnd, String encounterCode) throws SQLException {
        String query = "INSERT INTO EVENTO (TITULO , UBICACION, DESCRIPCION, FECHA_INICIO, FECHA_FIN, HORARIO_INICIO, HORARIO_FIN, ENCUENTRO_CODIGO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = DatabaseConnector.getConexion().prepareStatement(query)) {
            ps.setString(1, title); ps.setString(2, location); ps.setString(3, description);
            ps.setDate(4, dateStart); ps.setDate(5, dateEnd); ps.setTime(6, hourStart); ps.setTime(7, hourEnd); ps.setString(8, encounterCode);
            ps.executeUpdate();
        }
        loadEvents();
        return "¡Evento '" + title + "' creado con éxito!";
    }

    public String listEvents() {
        if (eventList.isEmpty()) return "No hay eventos registrados.";
        StringBuilder sb = new StringBuilder();
        for (Event e : eventList) sb.append(e.toString());
        return sb.toString();
    }

    public String updateEvents(Event e) throws SQLException {
        String query = "UPDATE EVENTO SET TITULO=?, UBICACION=?, DESCRIPCION=?, FECHA_INICIO=?, FECHA_FIN=?, HORARIO_INICIO=?, HORARIO_FIN=?, ENCUENTRO_CODIGO=? WHERE ID=?";
        try (PreparedStatement ps = DatabaseConnector.getConexion().prepareStatement(query)) {
            ps.setString(1, e.getTitle()); ps.setString(2, e.getLocation());
            ps.setString(3, e.getDescription()); ps.setDate(4, e.getDateStart());
            ps.setDate(5, e.getDateEnd()); ps.setTime(6, e.getHourStart());
            ps.setTime(7, e.getHourEnd()); ps.setString(8, e.getEncounterCode()); ps.setInt(9, e.getId());
            if (ps.executeUpdate() > 0) {
                loadEvents();
                return "Datos de '" + e.getTitle() + "' actualizados correctamente.";
            }
        }
        return "Error: El evento no existe.";
    }

    public String deleteEvent(int id) throws SQLException {
        String query = "DELETE FROM EVENTO WHERE ID = ?";
        try (PreparedStatement ps = DatabaseConnector.getConexion().prepareStatement(query)) {
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                loadEvents();
                return "Evento con id '" + id + "' eliminado correctamente.";
            }
        }
        return "Error: No se encontró el evento con id '" + id + "'.";
    }
}