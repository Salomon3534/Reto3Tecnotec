package util;

import java.sql.SQLException;
import model.Guest;

public class TotalManagerEuskalEncounter {
	
	private ManagerAttendants matt;
	private ManagerEncounters menc;
	private ManagerEvents mevn;
	private ManagerGuests mgue;

	// inicializacion de todos los gestores
	public TotalManagerEuskalEncounter() throws SQLException {
		this.matt = new ManagerAttendants();
		this.menc = new ManagerEncounters();
		this.mevn = new ManagerEvents();
		this.mgue = new ManagerGuests();
	}
	
	// metodos especificos para la gestion de invitados
	public String createGuest(String username, String name, String surnames, String phoneNumber, String career, String email, String password) throws SQLException {
		return mgue.createGuest(username, name, surnames, phoneNumber, career, email, password);
	}
	
	public String listGuests() {
		return mgue.listGuests();
	}

	// actualiza un invitado pasando el objeto completo
	public String updateGuest(Guest guest) throws SQLException {
		return mgue.updateGuest(guest);
	}
	
	// elimina un invitado pasando el objeto completo
	public String deleteGuest(Guest guest) throws SQLException {
		return mgue.deleteGuest(guest);
	}
}