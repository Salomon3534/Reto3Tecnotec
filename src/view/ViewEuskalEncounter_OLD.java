package view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import app.Main.MenuAdmin;
import app.Main.MenuUsuario;
import app.Main.OpcionesAdmin;
import app.Main.OpcionesElecionUsuarioAdmin;
import app.Main.OpcionesUsuario;
import model.Event;
import model.KeynoteSpeech;
import model.PracticalWorkshop;
import model.ProjectPresentation;
import model.RoundTable;
import util.ManagerAttendants;
import util.ManagerEncounters;
import util.ManagerEvents;
import util.ManagerGuests;
import util.TotalManagerEuskalEncounter;
import util.InputOutputChecks;

public class ViewEuskalEncounter_OLD {

	static InputOutputChecks checks = new InputOutputChecks();
    static ManagerEncounters gestionEncuentros = new ManagerEncounters();
    static ManagerEvents gestionEventos = new ManagerEvents();
    static ManagerGuests gestionInvitados = new ManagerGuests();
    static ManagerAttendants gestionAtendientes = new ManagerAttendants();
    static Scanner sc = new Scanner(System.in);

    static TotalManagerEuskalEncounter tm = new TotalManagerEuskalEncounter();
    public ViewEuskalEncounter_OLD(TotalManagerEuskalEncounter totalManagerEuskalEncounter) {
		this.tm = totalManagerEuskalEncounter;
	}

	public void mainMenu() {
        System.out.println("#########################################");
        System.out.println("#      BIENVENIDO AL GESTOR DE EVENTOS  #");
        System.out.println("#########################################");

        MenuUsuario menuUsuario = new MenuUsuario();
        MenuAdmin menuAdmin = new MenuAdmin();
        int opcion;
        
        do {
            OpcionesElecionUsuarioAdmin.mostrar();
            System.out.print("Seleccione su perfil: ");
            opcion = checks.getInt(1, 3);
            
            switch (opcion) {
                case 1 -> {
                    System.out.println("\nAccediendo como USUARIO...");
                    menuUsuario.menuU();
                }
                case 2 -> {
                    System.out.println("\nAccediendo como ADMINISTRADOR...");
                    menuAdmin.menuA();
                }
                case 3 -> System.out.println("\nGracias por usar el sistema. ¡Hasta pronto!");
            }
        } while (opcion != 3);
    }

    public static class MenuUsuario {
        public void menuU() {
            int opcion;
            do {
                OpcionesUsuario.mostrar();
                System.out.print("Opción: ");
                opcion = checks.getInt(1, 5);
                
                System.out.println(); // Salto de línea estético
                switch (opcion) {
                    case 1 -> {
                        System.out.println("--- LISTADO DE ENCUENTROS ---");
                        System.out.println(gestionEncuentros.listarEncuentros());
                    }
                    case 2 -> {
                        System.out.println("--- CALENDARIO DE EVENTOS ---");
                        System.out.println(gestionEventos.listarEventos());
                    }
                    case 3 -> {
                        System.out.println("--- LISTA DE INVITADOS ---");
                        System.out.println(gestionInvitados.listarInvitados());
                    }
                    case 4 -> System.out.println("Sección de contacto: admin@eventos.com");
                    case 5 -> System.out.println("Cerrando sesión de usuario...");
                }
            } while (opcion != 5);
        }
    }

    public static class MenuAdmin {
        
        public void menuA() {
            int opcionMenuAcciones = 0;
            int opcionPrincipal = 0;

            do {
                OpcionesAdmin.mostrar();
                System.out.print("Seleccione una entidad a gestionar: ");

                // comprobacion de opcion disponible
                opcionPrincipal = checks.getInt(1, 5);
                
                if (opcionPrincipal != 5) {
                    String entidad = obtenerNombreEntidad(opcionPrincipal);
                    opcionMenuAcciones = acciones(entidad);
                    
                    if (opcionMenuAcciones != 5) {
                        ejecutarAccion(opcionPrincipal, opcionMenuAcciones);
                    }
                }
            } while (opcionPrincipal != 5);
            System.out.println("Saliendo del panel de administración...");
        }

        // OBTENCION DE NOMBRE DEL APARTADO SELECCIONADO
        private static String obtenerNombreEntidad(int opcion) {
            return switch (opcion) {
                case 1 -> "atendiente";
                case 2 -> "encuentro";
                case 3 -> "evento";
                case 4 -> "invitado";
                default -> "";
            };
        }

        // ACCION EJECUTADA DE APARTADO ELEGIDO
        private static void ejecutarAccion(int entidad, int accion) {
            int idElegido;

            switch (entidad) {
                case 1 -> { // gestion atendientes
                    switch (accion) {
                        case 1 -> {
                            System.out.println("\n--- REGISTRAR NUEVO ATENDIENTE ---");
                            System.out.println(gestionAtendientes.crearAtendiente(
                                checks.getString("Ingrese DNI: ", 9), 
                                checks.getString("Asigne una Contraseña: ", 20),
                                checks.getString("Nombre: ", 50), 
                                checks.getString("Apellidos: ", 50),
                                checks.leerEmail("Correo Electrónico: ", 100)));
                        }

                        case 2 -> {
                            System.out.println("\n--- BASE DE DATOS DE ATENDIENTES ---");
                            System.out.println(gestionAtendientes.listarAtendientes());
                        }

                        case 3 -> {
                            System.out.println("\n--- ACTUALIZAR DATOS DE ATENDIENTE ---");
                            System.out.println("Introduce los nuevos datos:");
                            System.out.println(gestionAtendientes.actualizarAtendiente(
                                checks.getString("DNI del usuario a modificar: ", 9), 
                                checks.getString("Nueva Contraseña: ", 20),
                                checks.getString("Nuevo Nombre: ", 50), 
                                checks.getString("Nuevos Apellidos: ", 50),
                                checks.leerEmail("Nuevo Email: ", 100)));
                        }

                        case 4 -> {
                            System.out.println("\n--- ELIMINAR ATENDIENTE ---");
                            System.out.println(gestionAtendientes.eliminarAtendiente(
                                checks.getString("Ingrese el DNI del atendiente a borrar: ", 9)));
                        }
                    }
                }
                case 2 -> { // gestion encuentros
                    switch (accion) {
                        case 1 -> {
                            System.out.println("\n--- PROGRAMAR NUEVO ENCUENTRO ---");
                            System.out.println(gestionEncuentros.crearEncuentro(
                                checks.getString("Nombre del encuentro: ", 100), 
                                checks.getString("Lugar / Sede: ", 100),
                                checks.getDate("Fecha de inicio", "dd/MM/yyyy"), 
                                checks.getDate("Fecha de finalización", "dd/MM/yyyy")));
                        }

                        case 2 -> {
                            System.out.println("\n--- LISTADO GLOBAL DE ENCUENTROS ---");
                            System.out.println(gestionEncuentros.listarEncuentros());
                        }

                        case 3 -> {
                            System.out.println("\n--- MODIFICAR ENCUENTRO ---");
                            System.out.println("Primero, identifique el encuentro.");
                            idElegido = checks.getInt(0, gestionEncuentros.getCantidadEncuentros());
                            System.out.println("Ahora introduzca los nuevos datos:");
                            System.out.println(gestionEncuentros.actualizarEncuentro(
                                    checks.getString("Nuevo Nombre: ", 100), 
                                    checks.getString("Nuevo Lugar: ", 100),
                                    checks.getDate("Nueva Fecha inicio", "dd/MM/yyyy"), 
                                    checks.getDate("Nueva Fecha fin", "dd/MM/yyyy"),
                                    idElegido));
                        }

                        case 4 -> {
                            System.out.println("\n--- CANCELAR ENCUENTRO ---");
                            System.out.print("Ingrese el ID del encuentro a eliminar: ");
                            idElegido = checks.getInt(0, gestionEncuentros.getCantidadEncuentros());
                            System.out.println(gestionEncuentros.eliminarEncuentro(idElegido));
                        }
                    }
                }
                case 3 -> { // gestion eventos
                    switch (accion) {
                        case 1 -> { // Crear evento
                            int tipoEvento = listaTipoEvento();
                            if (tipoEvento != 5) {
                                Event nuevoEvento = capturarDatosEvento(tipoEvento);
                                if (nuevoEvento != null) {
                                    System.out.println("Guardando evento en el sistema...");
                                    System.out.println(gestionEventos.crearEvento(nuevoEvento));
                                } else {
                                    System.out.println("Error: No se pudo generar el evento.");
                                }
                            }
                        }
                        case 2 -> {
                            System.out.println("\n--- AGENDA DE EVENTOS ---");
                            System.out.println(gestionEventos.listarEventos());
                        }
                        
                        case 3 -> { // Modificar evento
                            System.out.println("\n--- MODIFICAR EVENTO EXISTENTE ---");
                            System.out.print("Ingrese el ID numérico del evento: ");
                            // Nota: Asumo que tienes un método getCantidadEventos() o usas un rango amplio
                            Event e = gestionEventos.buscarPorID(checks.getInt(0, 9999));
                            
                            if (e != null) {
                                System.out.println("Evento encontrado: " + e.getClass().getSimpleName());
                                System.out.println("Por favor, reingrese los datos actualizados:");
                                
                                int tipoDetectado = 0;
                                if (e instanceof KeynoteSpeech) tipoDetectado = 1;
                                else if (e instanceof RoundTable) tipoDetectado = 2;
                                else if (e instanceof ProjectPresentation) tipoDetectado = 3;
                                else if (e instanceof PracticalWorkshop) tipoDetectado = 4;

                                Event eventoModificado = capturarDatosEvento(tipoDetectado);
                                // Simulación de actualización
                                System.out.println("Datos capturados para actualización: " + eventoModificado);
                            } else {
                                System.out.println("No existe ningún evento con ese ID.");
                            }
                        }
                        case 4 -> {
                            System.out.println("\n--- BORRAR EVENTO ---");
                            System.out.print("Ingrese el ID del evento a eliminar: ");
                            System.out.println(gestionEventos.eliminarEvento(checks.getInt(0, 999)));
                        }
                    }
                }
                case 4 -> { // gestion invitados
                    switch (accion) {
                        case 1 -> {
                            System.out.println("\n--- REGISTRAR INVITADO ---");
                            System.out.println(gestionInvitados.crearInvitado(
                                checks.getString("Usuario (Nick): ", 30), 
                                checks.getString("Primer Apellido: ", 50),
                                checks.getString("Segundo Apellido: ", 50), 
                                checks.getString("Teléfono de contacto: ", 15),
                                checks.getString("Carrera/Estudios: ", 50), 
                                checks.leerEmail("Correo personal: ", 100),
                                checks.getString("Contraseña temporal: ", 20)));
                        }

                        case 2 -> {
                            System.out.println("\n--- LISTA DE INVITADOS ---");
                            System.out.println(gestionInvitados.listarInvitados());
                        }

                        case 3 -> {
                            System.out.println("\n--- ACTUALIZAR INVITADO ---");
                            System.out.println(gestionInvitados.actualizarInvitado(
                                checks.getString("Usuario a buscar: ", 30), 
                                checks.getString("Nuevo Apellido 1: ", 50),
                                checks.getString("Nuevo Apellido 2: ", 50), 
                                checks.getString("Nuevo Teléfono: ", 15),
                                checks.getString("Nueva Carrera: ", 50), 
                                checks.leerEmail("Nuevo Email: ", 100),
                                checks.getString("Nueva Contraseña: ", 20)));
                        }

                        case 4 -> {
                            System.out.println("\n--- DAR DE BAJA INVITADO ---");
                            System.out.println(gestionInvitados.eliminarInvitado(
                                checks.getString("Ingrese el Usuario (Nick) a eliminar: ", 30)));
                        }
                    }
                }
            }
        }

        // METODO AUXILIAR PARA NO REPETIR CODIGO EN EVENTOS
        private static Event capturarDatosEvento(int tipo) {
            System.out.println("\nRELLENE LA FICHA DEL EVENTO:");
            System.out.println("--------------------------------");
            String titulo = checks.getString("Título del Evento: ", 100);
            String ubicacion = checks.getString("Ubicación (Aula/Sala): ", 100);
            String descripcion = checks.getString("Breve descripción: ", 200);
            LocalDate fIni = checks.getDate("Fecha de Inicio", "dd/MM/yyyy");
            LocalDate fFin = checks.getDate("Fecha de Finalización", "dd/MM/yyyy");
            LocalTime hIni = checks.leerHora("Hora de Inicio", "HH:mm");
            LocalTime hFin = checks.leerHora("Hora de Finalización", "HH:mm");
            String codigo = checks.getString("Código ID único (5 chars): ", 5);

            return switch (tipo) {
                case 1 -> new KeynoteSpeech(titulo, ubicacion, descripcion, fIni, fFin, hIni, hFin, codigo, 
                        checks.getString("Temática de la conferencia: ", 50));
                case 2 -> new RoundTable(titulo, ubicacion, descripcion, fIni, fFin, hIni, hFin, codigo, 
                        checks.getInt(1, 100)); // Pide el número entero para N. Conferencia
                case 3 -> new ProjectPresentation(titulo, ubicacion, descripcion, fIni, fFin, hIni, hFin, codigo, 
                        checks.getString("Tipo de proyecto: ", 50), 
                        checks.getString("Detalles del proyecto: ", 200));
                case 4 -> new PracticalWorkshop(titulo, ubicacion, descripcion, fIni, fFin, hIni, hFin, codigo, 
                        checks.getInt(1, 100)); // Pide el número entero para N. Taller
                default -> null;
            };
        }

        private static int acciones(String gestion) {
            System.out.println("\nPANEL DE GESTION: " + gestion.toUpperCase());
            System.out.println("=================================");
            System.out.println("1. Crear nuevo registro");
            System.out.println("2. Listar todos");
            System.out.println("3. Actualizar existente");
            System.out.println("4. Eliminar");
            System.out.println("5. Volver al menú principal");
            System.out.println("=================================");
            return checks.getInt(1, 5);
        }

        private static int listaTipoEvento() {
            System.out.println("\nSELECCIONA EL TIPO DE EVENTO");
            System.out.println("---------------------------------");
            System.out.println("1. Conferencia Magistral");
            System.out.println("2. Mesa Redonda");
            System.out.println("3. Presentación de Proyecto");
            System.out.println("4. Taller Práctico");
            System.out.println("5. Cancelar");
            return checks.getInt(1, 5);
        }
    }

    private static class OpcionesUsuario {
        static void mostrar() {
            System.out.println("\n===========================");
            System.out.println("      MENU DE USUARIO      ");
            System.out.println("===========================");
            System.out.println("1. Ver Encuentros");
            System.out.println("2. Ver Eventos");
            System.out.println("3. Ver Invitados");
            System.out.println("4. Contacto");
            System.out.println("5. Cerrar Sesión");
        }
    }

    private static class OpcionesAdmin {
        static void mostrar() {
            System.out.println("\n===========================");
            System.out.println("   PANEL DE ADMINISTRADOR  ");
            System.out.println("===========================");
            System.out.println("1. Gestionar Atendientes");
            System.out.println("2. Gestionar Encuentros");
            System.out.println("3. Gestionar Eventos");
            System.out.println("4. Gestionar Invitados");
            System.out.println("5. Cerrar Sesión");
        }
    }

    private static class OpcionesElecionUsuarioAdmin {
        static void mostrar() {
            System.out.println("\n-----------------------------");
            System.out.println("   INICIO DE SESION   ");
            System.out.println("-----------------------------");
            System.out.println("1. Entrar como Usuario");
            System.out.println("2. Entrar como Administrador");
            System.out.println("3. Salir del Programa");
        }
    }
}
