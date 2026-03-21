//13/02/2026 12:16
package util;
<<<<<<< Updated upstream
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import model.Event;
=======

import java.sql.*;
import java.util.ArrayList;
import db.DatabaseConnector;
import model.*;

>>>>>>> Stashed changes
public class ManagerEvents {
   private static ArrayList<Event> listaEventos = new ArrayList<>();
   public String crearEvento(
           Event e
   ) {
       listaEventos.add(e);
       return e.getClass().getSimpleName() + " creado con éxito. ID: " + e.getId();
   }
   public String listarEventos() {
   	if (listaEventos.isEmpty()) {
           return "No hay eventos por ahora";
       }
      
       String listaString = "";
       for(Event e : listaEventos) {
       	listaString += "\n" + e.toString();
       }
       return listaString;
   }
   public String actualizarEvento(
           int id,
           String titulo,
           String ubicacion,
           String descripcion,
           LocalDate fechaInicio,
           LocalDate fechaFin,
           LocalTime horarioInicio,
           LocalTime horarioFin,
           String codigoEncuentro
   ) {
   	if (listaEventos.isEmpty()) {
			
			return "La lista de eventos está vacía";
			
		}
   	
       for (Event evento : listaEventos) {
           if (evento.getId() == id) {
               evento.setTitulo(titulo);
               evento.setUbicacion(ubicacion);
               evento.setDescripcion(descripcion);
               evento.setFechaInicio(fechaInicio);
               evento.setFechaFin(fechaFin);
               evento.setHorarioInicio(horarioInicio);
               evento.setHorarioFin(horarioFin);
               evento.setCodigoEncuentro(codigoEncuentro);
               return "Evento " + id + " actualizado.";
           }
       }
       return "No se encontró el ID " + id;
   }
   public Event buscarPorID(int id) {
       Event encontrado = listaEventos.get(id);
       return encontrado;
  }
  
   public String buscarEvento(Event e) {
      
       int id = listaEventos.indexOf(e);
       return e.getTitulo() + " tiene el ID: " + id + "en la lista de eventos";
      
   }
   public String eliminarEvento(int id) {
       if (listaEventos.removeIf(e -> e.getId() == id)) {
           return "Eliminado con éxito";
       }
       return "No encontrado";
   }
   public int getCantidadEventos() {
       return listaEventos.size();
   }
}



<<<<<<< Updated upstream
=======
    public void loadEvents() throws SQLException {
        eventList.clear();
        String query = "SELECT e.*, c.TIPO_DE_CONFERENCIA, t.NUMERO_TALLER, " +
                       "p.TIPO_DE_PROYECTO, p.DESCRIPCION_PROYECTO, m.NUMERO_CONFERENCIA " +
                       "FROM evento e " +
                       "LEFT JOIN conferencia_magistral c ON e.ID = c.ID " +
                       "LEFT JOIN talleres_practicos t ON e.ID = t.ID " +
                       "LEFT JOIN presentacion_de_proyecto p ON e.ID = p.ID " +
                       "LEFT JOIN mesa_redonda m ON e.ID = m.ID";
        
        Connection con = DatabaseConnector.getConexion();
        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                int id = rs.getInt("ID");
                String title = rs.getString("TITULO");
                String loc = rs.getString("UBICACION");
                String desc = rs.getString("DESCRIPCION");
                Date dStart = rs.getDate("FECHA_INICIO");
                Date dEnd = rs.getDate("FECHA_FIN");
                Time hStart = rs.getTime("HORARIO_INICIO");
                Time hEnd = rs.getTime("HORARIO_FIN");
                int eCode = rs.getInt("ENCUENTRO_CODIGO");

                if (rs.getString("TIPO_DE_CONFERENCIA") != null) {
                    eventList.add(new KeynoteSpeech(id, title, loc, desc, dStart, dEnd, hStart, hEnd, eCode, rs.getString("TIPO_DE_CONFERENCIA")));
                } else if (rs.getObject("NUMERO_TALLER") != null) {
                    eventList.add(new PracticalWorkshop(id, title, loc, desc, dStart, dEnd, hStart, hEnd, eCode, rs.getInt("NUMERO_TALLER")));
                } else if (rs.getString("TIPO_DE_PROYECTO") != null) {
                    eventList.add(new ProjectPresentation(id, title, loc, desc, dStart, dEnd, hStart, hEnd, eCode, rs.getString("TIPO_DE_PROYECTO"), rs.getString("DESCRIPCION_PROYECTO")));
                } else if (rs.getObject("NUMERO_CONFERENCIA") != null) {
                    eventList.add(new RoundTable(id, title, loc, desc, dStart, dEnd, hStart, hEnd, eCode, rs.getInt("NUMERO_CONFERENCIA")));
                } else {
                    eventList.add(new Event(id, title, loc, desc, dStart, dEnd, hStart, hEnd, eCode));
                }
            }
        }
    }

    public String createEvent(Event e) throws SQLException {
        String queryBase = "INSERT INTO evento (TITULO, UBICACION, DESCRIPCION, FECHA_INICIO, FECHA_FIN, HORARIO_INICIO, HORARIO_FIN, ENCUENTRO_CODIGO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = DatabaseConnector.getConexion();
        
        try {
            con.setAutoCommit(false);

            try (PreparedStatement ps = con.prepareStatement(queryBase, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, e.getTitle());
                ps.setString(2, e.getLocation());
                ps.setString(3, e.getDescription());
                ps.setDate(4, e.getDateStart());
                ps.setDate(5, e.getDateEnd());
                ps.setTime(6, e.getHourStart());
                ps.setTime(7, e.getHourEnd());
                ps.setInt(8, e.getEncounterCode());
                
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int newId = rs.getInt(1);
                        insertSubtype(con, newId, e); 
                    }
                }
                
                con.commit(); 
                loadEvents();
                return "Evento '" + e.getTitle() + "' creado con éxito.";
            } catch (SQLException ex) {
                con.rollback(); 
                return "Error SQL al insertar: " + ex.getMessage();
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            return "Error de conexión o transacción: " + ex.getMessage();
        }
    }

    private void insertSubtype(Connection con, int id, Event e) throws SQLException {
        String query = "";
        if (e instanceof KeynoteSpeech k) {
            query = "INSERT INTO conferencia_magistral (ID, TIPO_DE_CONFERENCIA) VALUES (?, ?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, id);
                ps.setString(2, k.getSpeechType());
                ps.executeUpdate();
            }
        } else if (e instanceof PracticalWorkshop w) {
            query = "INSERT INTO talleres_practicos (ID, NUMERO_TALLER) VALUES (?, ?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, id);
                ps.setInt(2, w.getWorkshopNumber());
                ps.executeUpdate();
            }
        } else if (e instanceof ProjectPresentation p) {
            query = "INSERT INTO presentacion_de_proyecto (ID, TIPO_DE_PROYECTO, DESCRIPCION_PROYECTO) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, id);
                ps.setString(2, p.getProjectType());
                ps.setString(3, p.getProjectDescription());
                ps.executeUpdate();
            }
        } else if (e instanceof RoundTable r) {
            query = "INSERT INTO mesa_redonda (ID, NUMERO_CONFERENCIA) VALUES (?, ?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, id);
                ps.setInt(2, r.getConferenceNumber());
                ps.executeUpdate();
            }
        }
    }

    public String updateEvent(Event e) throws SQLException {
        String queryBase = "UPDATE evento SET TITULO=?, UBICACION=?, DESCRIPCION=?, FECHA_INICIO=?, FECHA_FIN=?, HORARIO_INICIO=?, HORARIO_FIN=?, ENCUENTRO_CODIGO=? WHERE ID=?";
        Connection con = DatabaseConnector.getConexion();
        
        try {
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement(queryBase)) {
                ps.setString(1, e.getTitle());
                ps.setString(2, e.getLocation());
                ps.setString(3, e.getDescription());
                ps.setDate(4, e.getDateStart());
                ps.setDate(5, e.getDateEnd());
                ps.setTime(6, e.getHourStart());
                ps.setTime(7, e.getHourEnd());
                ps.setInt(8, e.getEncounterCode());
                ps.setInt(9, e.getId()); 
                
                if (ps.executeUpdate() > 0) {
                    updateSubtype(con, e);
                    con.commit();
                    loadEvents();
                    return "Evento actualizado con éxito.";
                }
            } catch (SQLException ex) {
                con.rollback();
                return "Error al actualizar subtipo: " + ex.getMessage();
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            return "Error de base de datos: " + ex.getMessage();
        }
        return "No se encontró el evento.";
    }

    private void updateSubtype(Connection con, Event e) throws SQLException {
        if (e instanceof KeynoteSpeech k) {
            String q = "UPDATE conferencia_magistral SET TIPO_DE_CONFERENCIA=? WHERE ID=?";
            try (PreparedStatement ps = con.prepareStatement(q)) { ps.setString(1, k.getSpeechType()); ps.setInt(2, k.getId()); ps.executeUpdate(); }
        } else if (e instanceof PracticalWorkshop w) {
            String q = "UPDATE talleres_practicos SET NUMERO_TALLER=? WHERE ID=?";
            try (PreparedStatement ps = con.prepareStatement(q)) { ps.setInt(1, w.getWorkshopNumber()); ps.setInt(2, w.getId()); ps.executeUpdate(); }
        } else if (e instanceof ProjectPresentation p) {
            String q = "UPDATE presentacion_de_proyecto SET TIPO_DE_PROYECTO=?, DESCRIPCION_PROYECTO=? WHERE ID=?";
            try (PreparedStatement ps = con.prepareStatement(q)) { 
                ps.setString(1, p.getProjectType()); 
                ps.setString(2, p.getProjectDescription()); 
                ps.setInt(3, p.getId()); 
                ps.executeUpdate(); 
            }
        } else if (e instanceof RoundTable r) {
            String q = "UPDATE mesa_redonda SET NUMERO_CONFERENCIA=? WHERE ID=?";
            try (PreparedStatement ps = con.prepareStatement(q)) { ps.setInt(1, r.getConferenceNumber()); ps.setInt(2, r.getId()); ps.executeUpdate(); }
        }
    }

    public String listEvents() {
        if (eventList.isEmpty()) return "No hay eventos registrados.";
        StringBuilder sb = new StringBuilder();
        for (Event e : eventList) sb.append(e.toString()).append("\n");
        return sb.toString();
    }

    public String deleteEvent(int id) throws SQLException {
        String query = "DELETE FROM evento WHERE ID = ?";
        Connection con = DatabaseConnector.getConexion();
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                loadEvents();
                return "Evento eliminado.";
            }
        }
        return "Error: No encontrado.";
    }
}
>>>>>>> Stashed changes
