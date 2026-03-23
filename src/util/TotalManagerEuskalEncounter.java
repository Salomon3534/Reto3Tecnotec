package util;

import java.sql.Date;
import java.sql.SQLException;
import model.*;

public class TotalManagerEuskalEncounter {

	private ManagerEncounters managerEncounters = new ManagerEncounters();
	private ManagerEvents managerEvents = new ManagerEvents();
	private ManagerGuests managerGuests = new ManagerGuests();
	private ManagerUsers managerUsers = new ManagerUsers();
	private Logger logger = new Logger();

	public TotalManagerEuskalEncounter() throws SQLException {
		logger.writeLog("SISTEMA - Managers instanciados y listos.");
	}

	public String showLogFile() {
		return logger.readLog();
	}

	// ENCUENTROS
	public String createEncounter(String location, Date dateStart, Date dateEnd) {
		try {
			String result = managerEncounters.createEncounter(location, dateStart, dateEnd);
			logger.writeLog("CREATE ENCUENTRO - Ubicación: " + location + " | " + result);
			return result;
		} catch (SQLException e) {
			logger.writeLog("ERROR SQL (createEncounter): " + e.getMessage());
			return "Error al crear encuentro en la base de datos.";
		}
	}

	public String listEncounters() {
		return managerEncounters.listEncounters();
	}

	public String updateEncounter(Encounter e) {
		try {
			String result = managerEncounters.updateEncounter(e);
			logger.writeLog("UPDATE ENCUENTRO - Código: " + e.getCode() + " | " + result);
			return result;
		} catch (SQLException ex) {
			logger.writeLog("ERROR SQL (updateEncounter): " + ex.getMessage());
			return "Error al actualizar el encuentro.";
		}
	}

	public String deleteEncounter(int code) {
		try {
			String result = managerEncounters.deleteEncounter(code);
			logger.writeLog("DELETE ENCUENTRO - Código: " + code + " | " + result);
			return result;
		} catch (SQLException e) {
			logger.writeLog("ERROR SQL (deleteEncounter): " + e.getMessage());
			return "Error al eliminar el encuentro.";
		}
	}

	// EVENTOS
	public String createEvent(Event e) {
		try {
			String result = managerEvents.createEvent(e);
			logger.writeLog("CREATE EVENTO - Título: " + e.getTitle() + " | " + result);
			return result;
		} catch (SQLException ex) {
			logger.writeLog("ERROR SQL (createEvent): " + ex.getMessage());
			return "Error al crear el evento.";
		}
	}

	public String listEvents() {
		return managerEvents.listEvents();
	}

	public String updateEvent(Event e) {
		try {
			String result = managerEvents.updateEvent(e);
			logger.writeLog("UPDATE EVENTO - ID: " + e.getId() + " | " + result);
			return result;
		} catch (SQLException ex) {
			logger.writeLog("ERROR SQL (updateEvent): " + ex.getMessage());
			return "Error al actualizar el evento.";
		}
	}

	public String deleteEvent(int id) {
		try {
			String result = managerEvents.deleteEvent(id);
			logger.writeLog("DELETE EVENTO - ID: " + id + " | " + result);
			return result;
		} catch (SQLException e) {
			logger.writeLog("ERROR SQL (deleteEvent): " + e.getMessage());
			return "Error al eliminar el evento.";
		}
	}

	// INVITADOS
	public String createGuest(String user, String name, String last, String tel, String career, String email,
			String pass) {
		try {
			String result = managerGuests.createGuest(user, name, last, tel, career, email, pass);
			logger.writeLog("CREATE INVITADO - Usuario: " + user + " | " + result);
			return result;
		} catch (SQLException e) {
			logger.writeLog("ERROR SQL (createGuest): " + e.getMessage());
			return "Error al registrar invitado.";
		}
	}

	public String listGuests() {
		return managerGuests.listGuests();
	}

	public String updateGuest(Guest g) {
		try {
			String result = managerGuests.updateGuest(g);
			logger.writeLog("UPDATE INVITADO - Usuario: " + g.getUsername() + " | " + result);
			return result;
		} catch (SQLException e) {
			logger.writeLog("ERROR SQL (updateGuest): " + e.getMessage());
			return "Error al actualizar invitado.";
		}
	}

	public String deleteGuest(String username) {
		try {
			String result = managerGuests.deleteGuest(username);
			logger.writeLog("DELETE INVITADO - Usuario: " + username + " | " + result);
			return result;
		} catch (SQLException e) {
			logger.writeLog("ERROR SQL (deleteGuest): " + e.getMessage());
			return "Error al eliminar invitado.";
		}
	}

	// USUARIOS
	public String createUser(String dni, String name, String last, String email) {
		try {
			String result = managerUsers.createUser(dni, name, last, email);
			logger.writeLog("CREATE USUARIO - DNI: " + dni + " | " + result);
			return result;
		} catch (SQLException e) {
			logger.writeLog("ERROR SQL (createUser): " + e.getMessage());
			return "Error al registrar usuario.";
		}
	}

	public String listUsers() {
		return managerUsers.listUsers();
	}

	public String updateUser(User u) {
		try {
			String result = managerUsers.updateUser(u);
			logger.writeLog("UPDATE USUARIO - DNI: " + u.getDni() + " | " + result);
			return result;
		} catch (SQLException e) {
			logger.writeLog("ERROR SQL (updateUser): " + e.getMessage());
			return "Error al actualizar usuario.";
		}
	}

	public String deleteUser(String dni) {
		try {
			String result = managerUsers.deleteUser(dni);
			logger.writeLog("DELETE USUARIO - DNI: " + dni + " | " + result);
			return result;
		} catch (SQLException e) {
			logger.writeLog("ERROR SQL (deleteUser): " + e.getMessage());
			return "Error al eliminar usuario.";
		}
	}
}