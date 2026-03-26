package view;

import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;
import model.*;
import util.TotalManagerEuskalEncounter;

public class ViewEuskalEncounter {

	private Scanner sc = new Scanner(System.in);
	private TotalManagerEuskalEncounter totalManager;

	public ViewEuskalEncounter(TotalManagerEuskalEncounter totalManagerEuskalEncounter) {
		this.totalManager = totalManagerEuskalEncounter;
	}

	// métodos privados de validación

	private int readInt(int min, int max) {
		while (true) {
			try {
				int numero = Integer.parseInt(sc.nextLine());
				if (numero < min || numero > max) {
					throw new IllegalArgumentException("Rango permitido: " + min + " - " + max);
				}
				return numero;
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage() + ". Inténtalo de nuevo:");
			}
		}
	}

	private String readString(String message, int maxLength) {
		while (true) {
			try {
				System.out.print(message);
				String texto = sc.nextLine();

				if (texto.trim().isEmpty()) {
					throw new Exception("La entrada no puede estar vacía.");
				}
				if (texto.length() > maxLength) {
					throw new Exception("Longitud máxima permitida: " + maxLength);
				}
				if (!texto.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ 0-9\\- .:,;]+")) {
					throw new Exception("Contiene caracteres especiales no permitidos.");
				}
				return texto;
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage() + ". Reintenta:");
			}
		}
	}

	private String readEmail(String message, int maxLength) {
		while (true) {
			try {
				System.out.print(message + " (ejemplo: usuario@dominio.com): ");
				String texto = sc.nextLine();

				if (!texto.matches("^[\\w.-]+@([\\w-]+\\.)+[a-zA-Z]{2,}$")) {
					throw new Exception("Formato de correo electrónico no válido.");
				}
				if (texto.length() > maxLength) {
					throw new Exception("Correo electrónico demasiado largo.");
				}
				return texto;
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage() + ". Reintenta:");
			}
		}
	}

	private String readDni() {
		while (true) {
			try {
				System.out.print("Escribe el DNI (8 números + 1 letra): ");
				String dni = sc.nextLine();

				if (!dni.matches("^[0-9]{8}[A-Za-z]$")) {
					throw new Exception("Formato de DNI incorrecto.");
				}
				return dni;
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage() + ". Reintenta:");
			}
		}
	}

	// menús principales

	public void mainMenu() {
		int choice;
		do {
			System.out.println("\n========================================");
			System.out.println(" SISTEMA DE GESTIÓN DE EUSKAL ENCOUNTER");
			System.out.println("========================================");
			System.out.println("1. Acceso Usuario (Modo Consulta)");
			System.out.println("2. Acceso Administrador (Modo Edición)");
			System.out.println("0. Salir");
			System.out.print("\nOpción (0-2): ");

			choice = readInt(0, 2);
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
			System.out.println("2. Ver Eventos");
			System.out.println("3. Ver Invitados");
			System.out.println("0. Volver");
			System.out.print("Opción (0-3): ");
			option = readInt(0, 3);

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
			System.out.println("\n--- PANEL DE ADMINISTRADOR ---");
			System.out.println("1. Gestionar Usuarios");
			System.out.println("2. Gestionar Encuentros");
			System.out.println("3. Gestionar Eventos");
			System.out.println("4. Gestionar Invitados");
			System.out.println("5. Leer fichero log");
			System.out.println("0. Volver");
			System.out.print("Seleccione (0-5): ");

			entity = readInt(0, 5);
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
		System.out.println("2. Listar todos");
		System.out.println("3. Actualizar");
		System.out.println("4. Eliminar");
		System.out.println("5. Buscar por identificador");
		System.out.println("0. Volver");
		System.out.print("Acción (0-5): ");
		return readInt(0, 5);
	}

	// gestión de entidades

	private void manageEvents() {
		int action;
		do {
			action = showActionMenu("Eventos");
			if (action == 0)
				break;

			try {
				switch (action) {
				case 1 -> {
					System.out.println("Seleccione Tipo:");
					System.out.println("1. Conferencia");
					System.out.println("2. Taller Práctico");
					System.out.println("3. Presentación de Proyecto");
					System.out.println("4. Mesa Redonda");
					System.out.println("5. General");
					int type = readInt(1, 5);
					System.out.println(totalManager.createEvent(requestEventData(0, type)));
				}
				case 2 -> System.out.println(totalManager.listEvents());
				case 3 -> {
					System.out.print("Identificador a modificar: ");
					int id = readInt(1, 99999);
					System.out.println("Nuevo tipo:");
					System.out.println("1. Conferencia");
					System.out.println("2. Taller Práctico");
					System.out.println("3. Presentación de Proyecto");
					System.out.println("4. Mesa Redonda");
					System.out.println("5. General");
					int type = readInt(1, 5);
					System.out.println(totalManager.updateEvent(requestEventData(id, type)));
				}
				case 4 -> {
					System.out.print("Identificador a eliminar: ");
					System.out.println(totalManager.deleteEvent(readInt(1, 99999)));
				}
				case 5 -> {
					System.out.print("Introduce el ID del evento: ");
					System.out.println(totalManager.getEventById(readInt(1, 99999)));
				}
				}
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		} while (action != 0);
	}

	private void manageEncounters() {
		int action;
		do {
			action = showActionMenu("Encuentros");
			if (action == 0)
				break;
			try {
				switch (action) {
				case 1 -> {
					String loc = readString("Ubicación: ", 150);
					Date start = null;
					while (start == null) {
						try {
							start = Date.valueOf(readString("Fecha Inicio (YYYY-MM-DD): ", 10));
						} catch (Exception e) {
							System.out.println("Formato de fecha inválido.");
						}
					}
					Date end = null;
					while (end == null) {
						try {
							end = Date.valueOf(readString("Fecha Fin (YYYY-MM-DD): ", 10));
						} catch (Exception e) {
							System.out.println("Formato de fecha inválido.");
						}
					}
					System.out.println(totalManager.createEncounter(loc, start, end));
				}
				case 2 -> System.out.println(totalManager.listEncounters());
				case 3 -> {
					System.out.print("Identificador del encuentro: ");
					int id = readInt(1, 9999);
					String loc = readString("Nueva Ubicación: ", 150);
					Date dS = null;
					while (dS == null) {
						try {
							dS = Date.valueOf(readString("Nuevo Inicio (YYYY-MM-DD): ", 10));
						} catch (Exception e) {
							System.out.println("Formato inválido.");
						}
					}
					Date dE = null;
					while (dE == null) {
						try {
							dE = Date.valueOf(readString("Nuevo Fin (YYYY-MM-DD): ", 10));
						} catch (Exception e) {
							System.out.println("Formato inválido.");
						}
					}
					System.out.println(totalManager.updateEncounter(new Encounter(id, loc, dS, dE)));
				}
				case 4 -> {
					System.out.print("Identificador a borrar: ");
					System.out.println(totalManager.deleteEncounter(readInt(1, 99999)));
				}
				case 5 -> {
					System.out.print("Introduce el código del encuentro: ");
					System.out.println(totalManager.getEncounterById(readInt(1, 99999)));
				}
				}
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		} while (action != 0);
	}

	private void manageGuests() {
		int action;
		do {
			action = showActionMenu("Invitados");
			if (action == 0)
				break;
			try {
				switch (action) {
				case 1 -> System.out.println(totalManager.createGuest(readString("Usuario: ", 50),
						readString("Nombre: ", 100), readString("Apellidos: ", 150), readString("Teléfono: ", 15),
						readString("Carrera: ", 200), readEmail("Correo Electrónico", 150),
						readString("Contraseña: ", 255)));
				case 2 -> System.out.println(totalManager.listGuests());
				case 3 -> System.out.println(totalManager.updateGuest(new Guest(readString("Usuario: ", 50),
						readString("Nombre: ", 100), readString("Apellidos: ", 150), readString("Teléfono: ", 15),
						readString("Carrera: ", 200), readEmail("Correo Electrónico", 150),
						readString("Contraseña: ", 255))));
				case 4 -> System.out.println(totalManager.deleteGuest(readString("Usuario a borrar: ", 50)));
				case 5 -> {
					System.out.print("Introduce el nombre de usuario: ");
					System.out.println(totalManager.getGuestByUsername(readString("Usuario: ", 50)));
				}
				}
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		} while (action != 0);
	}

	private void manageUsers() {
		int action;
		do {
			action = showActionMenu("Usuarios");
			if (action == 0)
				break;
			try {
				switch (action) {
				case 1 -> System.out.println(totalManager.createUser(readDni(), readString("Nombre: ", 100),
						readString("Apellido: ", 150), readEmail("Correo Electrónico", 150)));
				case 2 -> System.out.println(totalManager.listUsers());
				case 3 -> System.out.println(totalManager.updateUser(new User(readDni(), readString("Nombre: ", 100),
						readString("Apellido: ", 150), readEmail("Correo Electrónico", 150))));
				case 4 -> System.out.println(totalManager.deleteUser(readDni()));
				case 5 -> {
					System.out.println(totalManager.getUserByDni(readDni()));
				}
				}
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		} while (action != 0);
	}

	private Event requestEventData(int id, int typeChoice) {
		String title = readString("Título: ", 100);
		String loc = readString("Ubicación: ", 150);
		String desc = readString("Descripción: ", 255);

		Date dS = null;
		while (dS == null) {
			try {
				dS = Date.valueOf(readString("Fecha Inicio (YYYY-MM-DD): ", 10));
			} catch (IllegalArgumentException e) {
				System.out.println("Error en formato de fecha. Use YYYY-MM-DD.");
			}
		}

		Date dE = null;
		while (dE == null) {
			try {
				dE = Date.valueOf(readString("Fecha Fin (YYYY-MM-DD): ", 10));
			} catch (IllegalArgumentException e) {
				System.out.println("Error en formato de fecha. Use YYYY-MM-DD.");
			}
		}

		Time hS = null;
		while (hS == null) {
			try {
				hS = Time.valueOf(readString("Hora Inicio (HH:MM:SS): ", 8));
			} catch (IllegalArgumentException e) {
				System.out.println("Error en formato de hora. Use HH:MM:SS.");
			}
		}

		Time hE = null;
		while (hE == null) {
			try {
				hE = Time.valueOf(readString("Hora Fin (HH:MM:SS): ", 8));
			} catch (IllegalArgumentException e) {
				System.out.println("Error en formato de hora. Use HH:MM:SS.");
			}
		}

		System.out.print("Identificador del Encuentro Relacionado: ");
		int fk = readInt(1, 999999);

		return switch (typeChoice) {
		case 1 -> new KeynoteSpeech(id, title, loc, desc, dS, dE, hS, hE, fk, readString("Tipo Conferencia: ", 100));
		case 2 -> new PracticalWorkshop(id, title, loc, desc, dS, dE, hS, hE, fk, readInt(1, 999));
		case 3 -> new ProjectPresentation(id, title, loc, desc, dS, dE, hS, hE, fk, readString("Tipo Proyecto: ", 100),
				readString("Descripción larga: ", 500));
		case 4 -> new RoundTable(id, title, loc, desc, dS, dE, hS, hE, fk, readInt(1, 999));
		default -> new Event(id, title, loc, desc, dS, dE, hS, hE, fk);
		};
	}
}