package view;

import java.sql.Date;
import java.sql.Time;
import model.*;
import util.InputOutputChecks;
import util.TotalManagerEuskalEncounter;

public class ViewEuskalEncounter {

	private InputOutputChecks checksInputOutput = new InputOutputChecks();
	private TotalManagerEuskalEncounter totalManager;

	public ViewEuskalEncounter(TotalManagerEuskalEncounter totalManagerEuskalEncounter) {
		this.totalManager = totalManagerEuskalEncounter;
	}

	public void mainMenu() {
		int choice;
		do {
			System.out.println("\n========================================");
			System.out.println(" SISTEMA DE GESTIÓN DE EUSKAL ENCOUNTER");
			System.out.println("========================================");
			System.out.println(" 1. Acceso Usuario (Modo Consulta)");
			System.out.println(" 2. Acceso Administrador (Modo Edición)");
			System.out.println(" 0. Salir");
			System.out.print("\nOpción (0-2): ");

			choice = checksInputOutput.getInt(0, 2);
			if (choice == 1)
				userMenu();
			else if (choice == 2)
				adminMenu();
		} while (choice != 0);

		System.out.println("\nSaliendo del sistema...");
	}

	private void userMenu() {
		int option;
		do {
			System.out.println("\n--- VISTA DE USUARIO ---");
			System.out.println("1. Ver Encuentros");
			System.out.println("2. Ver Eventos (Todos)");
			System.out.println("3. Ver Invitados");
			System.out.println("0. Volver");
			System.out.print("Opción (0-3): ");
			option = checksInputOutput.getInt(0, 3);

			if (option == 1)
				System.out.println(totalManager.listEncounters());
			else if (option == 2)
				System.out.println(totalManager.listEvents());
			else if (option == 3)
				System.out.println(totalManager.listGuests());
		} while (option != 0);
	}

	private void adminMenu() {
		int entity;
		do {
			System.out.println("\n===========================");
			System.out.println("   PANEL DE ADMINISTRADOR  ");
			System.out.println("===========================");
			System.out.println("1. Gestionar Usuarios");
			System.out.println("2. Gestionar Encuentros");
			System.out.println("3. Gestionar Eventos");
			System.out.println("4. Gestionar Invitados");
			System.out.println("5. Leer fichero log (historial de acciones)");
			System.out.println("0. Volver");
			System.out.print("Seleccione acción (0-5): ");

			entity = checksInputOutput.getInt(0, 5);

			switch (entity) {
			case 1 -> manageUsers();
			case 2 -> manageEncounters();
			case 3 -> manageEvents();
			case 4 -> manageGuests();
			case 5 -> System.out.println(totalManager.showLogFile());
			}
		} while (entity != 0);
	}

	private int showActionMenu(String title) {
		System.out.println("\n>> GESTIÓN DE " + title.toUpperCase());
		System.out.println("1. Crear");
		System.out.println("2. Listar");
		System.out.println("3. Actualizar");
		System.out.println("4. Eliminar");
		System.out.println("0. Volver al Panel de Administrador");
		System.out.print("Acción (0-4): ");
		return checksInputOutput.getInt(0, 4);
	}

	// EVENTOS
	private void manageEvents() {
		int action;
		do {
			action = showActionMenu("Eventos");
			if (action == 0)
				break;

			try {
				switch (action) {
				case 1 -> {
					System.out.println("\n--- SELECCIONAR TIPO DE EVENTO ---");
					System.out.println("1. Conferencia Magistral");
					System.out.println("2. Taller Práctico");
					System.out.println("3. Presentación");
					System.out.println("4. Mesa Redonda");
					System.out.println("5. General");
					int type = checksInputOutput.getInt(1, 5);
					System.out.println(totalManager.createEvent(requestEventData(0, type)));
				}
				case 2 -> System.out.println(totalManager.listEvents());
				case 3 -> {
					System.out.println(totalManager.listEvents());
					System.out.print("\nID del evento a modificar (número entero): ");
					int id = checksInputOutput.getInt(1, 99999);

					System.out.println("Seleccione el nuevo tipo/clase:");
					System.out.println("1. Conferencia Magistral");
					System.out.println("2. Taller Práctico");
					System.out.println("3. Presentación");
					System.out.println("4. Mesa Redonda");
					System.out.println("5. General");

					int type = checksInputOutput.getInt(1, 5);
					System.out.println(totalManager.updateEvent(requestEventData(id, type)));
				}
				case 4 -> {
					System.out.println(totalManager.listEvents());
					System.out.print("ID del evento a ELIMINAR (número entero): ");
					int id = checksInputOutput.getInt(1, 99999);
					System.out.println(totalManager.deleteEvent(id));
				}
				}
			} catch (Exception e) {
				System.err.println("\nError en gestión de eventos: " + e.getMessage());
			}
		} while (action != 0);
	}

	private Event requestEventData(int id, int typeChoice) {

		System.out.print("Título (máx 100 caracteres): ");
		String title = checksInputOutput.getString("", 100);

		System.out.print("Ubicación (máx 150 caracteres): ");
		String loc = checksInputOutput.getString("", 150);

		System.out.print("Descripción (máx 255 caracteres): ");
		String desc = checksInputOutput.getString("", 255);

		System.out.print("Fecha Inicio (formato YYYY-MM-DD, ej: 2026-03-22): ");
		Date dS = Date.valueOf(checksInputOutput.getString("", 10));

		System.out.print("Fecha Fin (formato YYYY-MM-DD, ej: 2026-03-25): ");
		Date dE = Date.valueOf(checksInputOutput.getString("", 10));

		System.out.print("Hora Inicio (formato HH:MM:SS, ej: 14:30:00): ");
		Time hS = Time.valueOf(checksInputOutput.getString("", 8));

		System.out.print("Hora Fin (formato HH:MM:SS, ej: 18:00:00): ");
		Time hE = Time.valueOf(checksInputOutput.getString("", 8));

		System.out.print("Código Encuentro (FK, número entero): ");
		int fk = checksInputOutput.getInt(1, 999999);

		return switch (typeChoice) {
		case 1 -> {
			System.out.print("Tipo de Conferencia (texto): ");
			yield new KeynoteSpeech(id, title, loc, desc, dS, dE, hS, hE, fk, checksInputOutput.getString("", 100));
		}
		case 2 -> {
			System.out.print("Número de Taller (entero): ");
			yield new PracticalWorkshop(id, title, loc, desc, dS, dE, hS, hE, fk, checksInputOutput.getInt(1, 999));
		}
		case 3 -> {
			System.out.print("Tipo de Proyecto (texto): ");
			String tp = checksInputOutput.getString("", 100);
			System.out.print("Descripción Detallada (máx 500 caracteres): ");
			String dp = checksInputOutput.getString("", 500);
			yield new ProjectPresentation(id, title, loc, desc, dS, dE, hS, hE, fk, tp, dp);
		}
		case 4 -> {
			System.out.print("Número de Conferencia (entero): ");
			yield new RoundTable(id, title, loc, desc, dS, dE, hS, hE, fk, checksInputOutput.getInt(1, 999));
		}
		default -> new Event(id, title, loc, desc, dS, dE, hS, hE, fk);
		};
	}

	// ENCUENTROS
	private void manageEncounters() {
		int action;
		do {
			action = showActionMenu("Encuentros");
			if (action == 0)
				break;

			try {
				switch (action) {
				case 1 -> {
					System.out.print("Ubicación (máx 150 caracteres): ");
					String loc = checksInputOutput.getString("", 150);

					System.out.print("Fecha Inicio (YYYY-MM-DD, ej: 2026-03-22): ");
					Date start = Date.valueOf(checksInputOutput.getString("", 10));

					System.out.print("Fecha Fin (YYYY-MM-DD, ej: 2026-03-25): ");
					Date end = Date.valueOf(checksInputOutput.getString("", 10));

					System.out.println(totalManager.createEncounter(loc, start, end));
				}
				case 2 -> System.out.println(totalManager.listEncounters());
				case 3 -> {
					System.out.println(totalManager.listEncounters());
					System.out.println(totalManager.updateEncounter(getEncounterData()));
				}
				case 4 -> {
					System.out.println(totalManager.listEncounters());
					System.out.print("ID del encuentro a ELIMINAR (entero): ");
					System.out.println(totalManager.deleteEncounter(checksInputOutput.getInt(1, 99999)));
				}
				}
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		} while (action != 0);
	}

	private Encounter getEncounterData() {
		System.out.print("ID del encuentro (entero): ");
		int id = checksInputOutput.getInt(1, 9999);

		System.out.print("Nueva Ubicación (máx 150 caracteres): ");
		String loc = checksInputOutput.getString("", 150);

		System.out.print("Nueva Fecha Inicio (YYYY-MM-DD): ");
		Date dS = Date.valueOf(checksInputOutput.getString("", 10));

		System.out.print("Nueva Fecha Fin (YYYY-MM-DD): ");
		Date dE = Date.valueOf(checksInputOutput.getString("", 10));

		return new Encounter(id, loc, dS, dE);
	}

	// INVITADOS
	private void manageGuests() {
		int action;
		do {
			action = showActionMenu("Invitados");
			if (action == 0)
				break;

			switch (action) {
			case 1 -> {
				System.out.print("Username (máx 50 caracteres): ");
				String u = checksInputOutput.getString("", 50);

				System.out.print("Nombre (máx 100 caracteres): ");
				String n = checksInputOutput.getString("", 100);

				System.out.print("Apellidos (máx 150 caracteres): ");
				String l = checksInputOutput.getString("", 150);

				System.out.print("Teléfono (solo números, máx 15 dígitos): ");
				String t = checksInputOutput.getString("", 15);

				System.out.print("Carrera (texto): ");
				String c = checksInputOutput.getString("", 200);

				System.out.print("Email (ej: usuario@email.com): ");
				String e = checksInputOutput.getEmail("", 150);

				System.out.print("Password (máx 255 caracteres): ");
				String p = checksInputOutput.getString("", 255);

				System.out.println(totalManager.createGuest(u, n, l, t, c, e, p));
			}
			case 2 -> System.out.println(totalManager.listGuests());
			case 3 -> {
				System.out.println(totalManager.listGuests());
				System.out.println(totalManager.updateGuest(getGuestData()));
			}
			case 4 -> {
				System.out.println(totalManager.listGuests());
				System.out.print("Username a eliminar: ");
				System.out.println(totalManager.deleteGuest(checksInputOutput.getString("", 50)));
			}
			}
		} while (action != 0);
	}

	private Guest getGuestData() {
		System.out.print("Username actual: ");
		String u = checksInputOutput.getString("", 50);

		System.out.print("Nombre: ");
		String n = checksInputOutput.getString("", 100);

		System.out.print("Apellidos: ");
		String l = checksInputOutput.getString("", 150);

		System.out.print("Teléfono (solo números): ");
		String t = checksInputOutput.getString("", 15);

		System.out.print("Carrera: ");
		String c = checksInputOutput.getString("", 200);

		System.out.print("Email (ej: usuario@email.com): ");
		String e = checksInputOutput.getEmail("", 150);

		System.out.print("Password: ");
		String p = checksInputOutput.getString("", 255);

		return new Guest(u, n, l, t, c, e, p);
	}

	// USUARIOS
	private void manageUsers() {
		int action;
		do {
			action = showActionMenu("Usuarios");
			if (action == 0)
				break;

			switch (action) {
			case 1 -> {
				System.out.print("DNI (formato: 12345678A): ");
				String d = checksInputOutput.getString("", 9);

				System.out.print("Nombre: ");
				String n = checksInputOutput.getString("", 100);

				System.out.print("Apellido: ");
				String l = checksInputOutput.getString("", 150);

				System.out.print("Email (ej: usuario@email.com): ");
				String e = checksInputOutput.getEmail("", 150);

				System.out.println(totalManager.createUser(d, n, l, e));
			}
			case 2 -> System.out.println(totalManager.listUsers());
			case 3 -> {
				System.out.println(totalManager.listUsers());
				System.out.println(totalManager.updateUser(getUserData()));
			}
			case 4 -> {
				System.out.println(totalManager.listUsers());
				System.out.print("DNI a eliminar: ");
				System.out.println(totalManager.deleteUser(checksInputOutput.getString("", 20)));
			}
			}
		} while (action != 0);
	}

	private User getUserData() {
		System.out.print("DNI actual (formato 12345678A): ");
		String d = checksInputOutput.getString("", 20);

		System.out.print("Nombre: ");
		String n = checksInputOutput.getString("", 100);

		System.out.print("Apellido: ");
		String l = checksInputOutput.getString("", 150);

		System.out.print("Email (ej: usuario@email.com): ");
		String e = checksInputOutput.getEmail("", 150);

		return new User(d, n, l, e);
	}
}