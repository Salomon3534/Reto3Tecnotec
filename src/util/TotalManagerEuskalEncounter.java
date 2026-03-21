package util;

import java.sql.Date;
import java.sql.SQLException;
import model.*;

public class TotalManagerEuskalEncounter {

    private ManagerEncounters managerEncounters;
    private ManagerEvents managerEvents;
    private ManagerGuests managerGuests;
    private ManagerAttendants managerAttendants;
    private Logger logger = new Logger();

    public TotalManagerEuskalEncounter() {
        try {
            this.managerEncounters = new ManagerEncounters();
            this.managerEvents = new ManagerEvents();
            this.managerGuests = new ManagerGuests();
            this.managerAttendants = new ManagerAttendants();
        } catch (SQLException e) {
            System.err.println("ERROR CRÍTICO: No se pudo conectar a la base de datos.");
            logger.writeLog("FATAL ERROR - Inicialización fallida: " + e.getMessage());
        }
    }

    public String showLogFile() {
        return logger.readLog();
    }

    // --- ENCUENTROS ---
    public String createEncounter(Date dateStart, Date dateEnd, String location) {
        try {
            String result = managerEncounters.createEncounter(dateStart, dateEnd, location);
            logger.writeLog("CREATE ENCOUNTER - Ubicación: " + location + " | " + result);
            return result;
        } catch (SQLException e) {
            logger.writeLog("ERROR SQL (createEncounter): " + e.getMessage());
            return "Error al crear encuentro en DB.";
        }
    }

    public String listEncounters() {
        return managerEncounters.listEncounters();
    }

    public String updateEncounter(Encounter e) {
        try {
            String result = managerEncounters.updateEncounter(e);
            logger.writeLog("UPDATE ENCOUNTER - Código: " + e.getCode() + " | " + result);
            return result;
        } catch (SQLException ex) {
            logger.writeLog("ERROR SQL (updateEncounter): " + ex.getMessage());
            return "Error al actualizar encuentro.";
        }
    }

    public String deleteEncounter(int code) {
        try {
            String result = managerEncounters.deleteEncounter(code);
            logger.writeLog("DELETE ENCOUNTER - Código: " + code + " | " + result);
            return result;
        } catch (SQLException e) {
            logger.writeLog("ERROR SQL (deleteEncounter): " + e.getMessage());
            return "Error al eliminar encuentro.";
        }
    }

    // --- EVENTOS ---
    public String createEvent(Event e) {
        try {
            String result = managerEvents.createEvent(e);
            logger.writeLog("CREATE EVENT - Título: " + e.getTitle() + " | " + result);
            return result;
        } catch (SQLException ex) {
            logger.writeLog("ERROR SQL (createEvent): " + ex.getMessage());
            return "Error al crear evento.";
        }
    }

    public String listEvents() {
        return managerEvents.listEvents();
    }

    public String updateEvent(Event e) {
        try {
            String result = managerEvents.updateEvent(e);
            logger.writeLog("UPDATE EVENT - ID: " + e.getId() + " | " + result);
            return result;
        } catch (SQLException ex) {
            logger.writeLog("ERROR SQL (updateEvent): " + ex.getMessage());
            return "Error al actualizar evento.";
        }
    }

    public String deleteEvent(int id) {
        try {
            String result = managerEvents.deleteEvent(id);
            logger.writeLog("DELETE EVENT - ID: " + id + " | " + result);
            return result;
        } catch (SQLException e) {
            logger.writeLog("ERROR SQL (deleteEvent): " + e.getMessage());
            return "Error al eliminar evento.";
        }
    }

    // --- INVITADOS ---
    public String createGuest(String user, String name, String last, String tel, String career, String email, String pass) {
        try {
            String result = managerGuests.createGuest(user, name, last, tel, career, email, pass);
            logger.writeLog("CREATE GUEST - Usuario: " + user + " | " + result);
            return result;
        } catch (SQLException e) {
            logger.writeLog("ERROR SQL (createGuest): " + e.getMessage());
            return "Error al crear invitado.";
        }
    }

    public String listGuests() {
        return managerGuests.listGuests();
    }

    public String updateGuest(Guest g) {
        try {
            String result = managerGuests.updateGuest(g);
            logger.writeLog("UPDATE GUEST - Usuario: " + g.getUsername() + " | " + result);
            return result;
        } catch (SQLException e) {
            logger.writeLog("ERROR SQL (updateGuest): " + e.getMessage());
            return "Error al actualizar invitado.";
        }
    }

    public String deleteGuest(String username) {
        try {
            String result = managerGuests.deleteGuest(username);
            logger.writeLog("DELETE GUEST - Usuario: " + username + " | " + result);
            return result;
        } catch (SQLException e) {
            logger.writeLog("ERROR SQL (deleteGuest): " + e.getMessage());
            return "Error al eliminar invitado.";
        }
    }

    // --- ATENDIENTES ---
    public String createAttendant(String dni, String name, String last, String email) {
        try {
            String result = managerAttendants.createAttendant(dni, name, last, email);
            logger.writeLog("CREATE ATTENDANT - DNI: " + dni + " | " + result);
            return result;
        } catch (SQLException e) {
            logger.writeLog("ERROR SQL (createAttendant): " + e.getMessage());
            return "Error al crear atendiente.";
        }
    }

    public String listAttendants() {
        return managerAttendants.listAttendants();
    }

    public String updateAttendant(Attendant a) {
        try {
            String result = managerAttendants.updateAttendant(a);
            logger.writeLog("UPDATE ATTENDANT - DNI: " + a.getDni() + " | " + result);
            return result;
        } catch (SQLException e) {
            logger.writeLog("ERROR SQL (updateAttendant): " + e.getMessage());
            return "Error al actualizar atendiente.";
        }
    }

    public String deleteAttendant(String dni) {
        try {
            String result = managerAttendants.deleteAttendant(dni);
            logger.writeLog("DELETE ATTENDANT - DNI: " + dni + " | " + result);
            return result;
        } catch (SQLException e) {
            logger.writeLog("ERROR SQL (deleteAttendant): " + e.getMessage());
            return "Error al eliminar atendiente.";
        }
    }
}