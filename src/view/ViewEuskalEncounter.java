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
            if (choice == 1) userMenu();
            else if (choice == 2) adminMenu();
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
            
            if (option == 1) System.out.println(totalManager.listEncounters());
            else if (option == 2) System.out.println(totalManager.listEvents());
            else if (option == 3) System.out.println(totalManager.listGuests());
        } while (option != 0);
    }

    private void adminMenu() {
        int entity;
        do {
            System.out.println("\n===========================");
            System.out.println("   PANEL DE ADMINISTRADOR  ");
            System.out.println("===========================");
            System.out.println("1. Gestionar Atendientes");
            System.out.println("2. Gestionar Encuentros");
            System.out.println("3. Gestionar Eventos");
            System.out.println("4. Gestionar Invitados");
            System.out.println("5. Leer fichero log.txt");
            System.out.println("0. Volver");
            System.out.print("Seleccione entidad (0-5): ");

            entity = checksInputOutput.getInt(0, 5);
            
            if (entity == 5) {
                System.out.println(totalManager.showLogFile());
            } else {
                switch (entity) {
                    case 1 -> manageAttendants(showActionMenu("Atendientes"));
                    case 2 -> manageEncounters(showActionMenu("Encuentros"));
                    case 3 -> manageEvents(showActionMenu("Eventos"));
                    case 4 -> manageGuests(showActionMenu("Invitados"));
                }
            }
        } while (entity != 0);
    }

    private int showActionMenu(String title) {
        System.out.println("\n>> GESTIÓN DE " + title.toUpperCase());
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.println("0. Volver");
        System.out.print("Acción (0-4): ");
        return checksInputOutput.getInt(0, 4);
    }

    private void manageEvents(int action) {
        if (action == 0) return;
        do {
            try {
                switch (action) {
                    case 1 -> {
                        System.out.println("\n--- SELECCIONAR TIPO DE EVENTO ---");
                        System.out.println("1. Conferencia Magistral");
                        System.out.println("2. Taller Práctico");
                        System.out.println("3. Presentación de Proyecto");
                        System.out.println("4. Mesa Redonda");
                        System.out.println("5. Evento General");
                        int type = checksInputOutput.getInt(1, 5);
                        System.out.println(totalManager.createEvent(requestEventData(0, type)));
                    }
                    case 2 -> System.out.println(totalManager.listEvents());
                    case 3 -> {
                        System.out.print("\nID del evento a modificar (número entero): ");
                        int id = checksInputOutput.getInt(1, 99999);
                        System.out.println("Seleccione el nuevo tipo/clase para este ID:");
                        System.out.println("1. Conferencia magistral | 2. Taller practico | 3. Presentacion | 4. Mesa redonda | 5. General");
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
            } catch (IllegalArgumentException e) {
                System.err.println("\nError de formato: " + e.getMessage());
            }
            if (action != 0) action = showActionMenu("Eventos");
        } while (action != 0);
    }

    private Event requestEventData(int id, int typeChoice) {
        System.out.println("\n--- DATOS DEL EVENTO ---");
        System.out.print("Título: ");
        String title = checksInputOutput.getString("", 100);
        System.out.print("Ubicación: ");
        String loc = checksInputOutput.getString("", 150);
        System.out.print("Descripción General: ");
        String desc = checksInputOutput.getString("", 255);
        System.out.print("Fecha Inicio (AAAA-MM-DD): ");
        Date dS = Date.valueOf(checksInputOutput.getString("", 10));
        System.out.print("Fecha Fin (AAAA-MM-DD): ");
        Date dE = Date.valueOf(checksInputOutput.getString("", 10));
        System.out.print("Hora Inicio (HH:MM:SS): ");
        Time hS = Time.valueOf(checksInputOutput.getString("", 8));
        System.out.print("Hora Fin (HH:MM:SS): ");
        Time hE = Time.valueOf(checksInputOutput.getString("", 8));
        System.out.print("Código Encuentro: ");
        int fk = checksInputOutput.getInt(1, 9999999);

        return switch (typeChoice) {
            case 1 -> {
                System.out.print("Tipo de Conferencia: ");
                yield new KeynoteSpeech(id, title, loc, desc, dS, dE, hS, hE, fk, checksInputOutput.getString("", 100));
            }
            case 2 -> {
                System.out.print("Número de Taller (int): ");
                yield new PracticalWorkshop(id, title, loc, desc, dS, dE, hS, hE, fk, checksInputOutput.getInt(1, 999));
            }
            case 3 -> {
                System.out.print("Tipo de Proyecto: ");
                String tp = checksInputOutput.getString("", 100);
                System.out.print("Descripción Detallada del Proyecto: ");
                String dp = checksInputOutput.getString("", 500);
                yield new ProjectPresentation(id, title, loc, desc, dS, dE, hS, hE, fk, tp, dp);
            }
            case 4 -> {
                System.out.print("Número de Conferencia (int): ");
                yield new RoundTable(id, title, loc, desc, dS, dE, hS, hE, fk, checksInputOutput.getInt(1, 999));
            }
            default -> new Event(id, title, loc, desc, dS, dE, hS, hE, fk);
        };
    }

    private void manageEncounters(int action) {
        if (action == 0) return;
        do {
            try {
                switch (action) {
                    case 1 -> {
                        System.out.println("\n--- NUEVO ENCUENTRO ---");
                        System.out.print("Fecha Inicio (Formato AAAA-MM-DD): ");
                        Date start = Date.valueOf(checksInputOutput.getString("", 10));
                        System.out.print("Fecha Fin (Formato AAAA-MM-DD): ");
                        Date end = Date.valueOf(checksInputOutput.getString("", 10));
                        System.out.print("Ubicación General (Ej: BEC Bilbao): ");
                        String loc = checksInputOutput.getString("", 150);
                        System.out.println(totalManager.createEncounter(start, end, loc));
                    }
                    case 2 -> System.out.println(totalManager.listEncounters());
                    case 3 -> System.out.println(totalManager.updateEncounter(getEncounterData()));
                    case 4 -> {
                        System.out.print("ID del encuentro a ELIMINAR (número entero): ");
                        int code = checksInputOutput.getInt(1, 99999);
                        System.out.println(totalManager.deleteEncounter(code));
                    }
                }
            } catch (Exception e) { System.err.println("Error: " + e.getMessage()); }
            if (action != 0) action = showActionMenu("Encuentros");
        } while (action != 0);
    }

    private void manageGuests(int action) {
        if (action == 0) return;
        do {
            switch (action) {
                case 1 -> {
                    System.out.println("\n--- NUEVO INVITADO ---");
                    System.out.print("Username (Nombre de usuario único): "); String u = checksInputOutput.getString("", 50);
                    System.out.print("Nombre: "); String n = checksInputOutput.getString("", 100);
                    System.out.print("Apellidos: "); String l = checksInputOutput.getString("", 150);
                    System.out.print("Teléfono (Máx. 15 dígitos): "); String t = checksInputOutput.getString("", 15);
                    System.out.print("Carrera/Especialidad: "); String c = checksInputOutput.getString("", 200);
                    System.out.print("Email (Formato usuario@dominio.com): "); String e = checksInputOutput.leerEmail("", 150);
                    System.out.print("Password (Contraseña de acceso): "); String p = checksInputOutput.getString("", 255);
                    System.out.println(totalManager.createGuest(u, n, l, t, c, e, p));
                }
                case 2 -> System.out.println(totalManager.listGuests());
                case 3 -> System.out.println(totalManager.updateGuest(getGuestData()));
                case 4 -> {
                    System.out.print("Username del invitado a ELIMINAR: ");
                    System.out.println(totalManager.deleteGuest(checksInputOutput.getString("", 50)));
                }
            }
            if (action != 0) action = showActionMenu("Invitados");
        } while (action != 0);
    }

    private void manageAttendants(int action) {
        if (action == 0) return;
        do {
            switch (action) {
                case 1 -> {
                    System.out.println("\n--- NUEVO ATENDIENTE ---");
                    System.out.print("DNI (8 números y 1 letra): "); String d = checksInputOutput.getString("", 9);
                    System.out.print("Nombre: "); String n = checksInputOutput.getString("", 100);
                    System.out.print("Apellido: "); String l = checksInputOutput.getString("", 150);
                    System.out.print("Email (Formato usuario@dominio.com): "); String e = checksInputOutput.leerEmail("", 150);
                    System.out.println(totalManager.createAttendant(d, n, l, e));
                }
                case 2 -> System.out.println(totalManager.listAttendants());
                case 3 -> System.out.println(totalManager.updateAttendant(getAttendantData()));
                case 4 -> {
                    System.out.print("DNI del atendiente a ELIMINAR: ");
                    System.out.println(totalManager.deleteAttendant(checksInputOutput.getString("", 20)));
                }
            }
            if (action != 0) action = showActionMenu("Atendientes");
        } while (action != 0);
    }

    private Encounter getEncounterData() {
        System.out.print("CÓDIGO (ID) del encuentro a modificar: ");
        int id = checksInputOutput.getInt(1, 9999);
        System.out.print("Nueva Fecha Inicio (AAAA-MM-DD): "); Date dS = Date.valueOf(checksInputOutput.getString("", 10));
        System.out.print("Nueva Fecha Fin (AAAA-MM-DD): "); Date dE = Date.valueOf(checksInputOutput.getString("", 10));
        System.out.print("Nueva Ubicación: "); String loc = checksInputOutput.getString("", 150);
        return new Encounter(id, dS, dE, loc);
    }

    private Guest getGuestData() {
        System.out.print("USERNAME del invitado a modificar: "); String u = checksInputOutput.getString("", 50);
        System.out.print("Nuevo Nombre: "); String n = checksInputOutput.getString("", 100);
        System.out.print("Nuevos Apellidos: "); String l = checksInputOutput.getString("", 150);
        System.out.print("Nuevo Teléfono: "); String t = checksInputOutput.getString("", 15);
        System.out.print("Nueva Carrera: "); String c = checksInputOutput.getString("", 200);
        System.out.print("Nuevo Email: "); String e = checksInputOutput.leerEmail("", 150);
        System.out.print("Nueva Password: "); String p = checksInputOutput.getString("", 255);
        return new Guest(u, n, l, t, c, e, p);
    }

    private Attendant getAttendantData() {
        System.out.print("DNI del atendiente a modificar: "); String d = checksInputOutput.getString("", 20);
        System.out.print("Nuevo Nombre: "); String n = checksInputOutput.getString("", 100);
        System.out.print("Nuevo Apellido: "); String l = checksInputOutput.getString("", 150);
        System.out.print("Nuevo Email: "); String e = checksInputOutput.leerEmail("", 150);
        return new Attendant(d, n, l, e);
    }
}