package view;

import java.time.LocalDate;
import java.time.LocalTime;
import model.Event;
import model.KeynoteSpeech;
import model.PracticalWorkshop;
import model.ProjectPresentation;
import model.RoundTable;
import util.InputOutputChecks;
import util.TotalManagerEuskalEncounter;

public class ViewEuskalEncounter {

    private InputOutputChecks checksInputOutput = new InputOutputChecks();
    private TotalManagerEuskalEncounter managerTotal = new TotalManagerEuskalEncounter();

    public ViewEuskalEncounter(TotalManagerEuskalEncounter totalManagerEuskalEncounter) {
        this.managerTotal = totalManagerEuskalEncounter;
    }

    public void mainMenu() {
        System.out.println("#########################################");
        System.out.println("#      BIENVENIDO AL GESTOR DE EVENTOS  #");
        System.out.println("#########################################");
        int choice;

        do {
            System.out.println("\n1. Entrar como Usuario");
            System.out.println("2. Entrar como Administrador");
            System.out.println("3. Salir del Programa");
            System.out.print("Seleccione su perfil: ");
            
            choice = checksInputOutput.getInt(1, 3);

            switch (choice) {
                case 1 -> {
                    System.out.println("\nAccediendo como USUARIO...");
                    menuUser();
                }
                case 2 -> {
                    System.out.println("\nAccediendo como ADMINISTRADOR...");
                    menuAdmin();
                }
                case 3 -> System.out.println("\nGracias por usar el sistema. ¡Hasta pronto!");
            }
        } while (choice != 3);
    }

    private void menuUser() {
        int opcion;
        do {
            System.out.println("\n===========================");
            System.out.println("      MENU DE USUARIO      ");
            System.out.println("===========================");
            System.out.println("1. Ver Encuentros");
            System.out.println("2. Ver Eventos");
            System.out.println("3. Ver Invitados");
            System.out.println("4. Contacto");
            System.out.println("5. Cerrar Sesión");
            System.out.print("Opción: ");
            opcion = checksInputOutput.getInt(1, 5);

            switch (opcion) {
                case 1 -> System.out.println(managerTotal.getGestionEncuentros().listarEncuentros());
                case 2 -> System.out.println(managerTotal.getGestionEventos().listarEventos());
                case 3 -> System.out.println(managerTotal.getGestionInvitados().listarInvitados());
                case 4 -> System.out.println("Contacto: admin@eventos.com");
                case 5 -> System.out.println("Cerrando sesión...");
            }
        } while (opcion != 5);
    }

    private void menuAdmin() {
        int opcionPrincipal;
        do {
            System.out.println("\n===========================");
            System.out.println("   PANEL DE ADMINISTRADOR  ");
            System.out.println("===========================");
            System.out.println("1. Gestionar Atendientes");
            System.out.println("2. Gestionar Encuentros");
            System.out.println("3. Gestionar Eventos");
            System.out.println("4. Gestionar Invitados");
            System.out.println("5. Volver");
            System.out.print("Seleccione entidad: ");

            opcionPrincipal = checksInputOutput.getInt(1, 5);
            if (opcionPrincipal != 5) {
                String entidad = obtenerNombreEntidad(opcionPrincipal);
                int accion = mostrarMenuAcciones(entidad);
                if (accion != 5) {
                    ejecutarAccion(opcionPrincipal, accion);
                }
            }
        } while (opcionPrincipal != 5);
    }

    private String obtenerNombreEntidad(int opcion) {
        return switch (opcion) {
            case 1 -> "atendiente";
            case 2 -> "encuentro";
            case 3 -> "evento";
            case 4 -> "invitado";
            default -> "";
        };
    }

    private int mostrarMenuAcciones(String gestion) {
        System.out.println("\nPANEL DE GESTION: " + gestion.toUpperCase());
        System.out.println("1. Crear registro");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.println("5. Volver");
        return checksInputOutput.getInt(1, 5);
    }

    private void ejecutarAccion(int entidad, int accion) {
        switch (entidad) {
            case 1 -> gestionarAtendientes(accion);
            case 2 -> gestionarEncuentros(accion);
            case 3 -> gestionarEventos(accion);
            case 4 -> gestionarInvitados(accion);
        }
    }

    private void gestionarAtendientes(int accion) {
        switch (accion) {
            case 1 -> System.out.println(managerTotal.getGestionAtendientes().crearAtendiente(
                checksInputOutput.getString("DNI: ", 9), checksInputOutput.getString("Pass: ", 20),
                checksInputOutput.getString("Nombre: ", 50), checksInputOutput.getString("Apellidos: ", 50),
                checksInputOutput.leerEmail("Email: ", 100)));
            case 2 -> System.out.println(managerTotal.getGestionAtendientes().listarAtendientes());
            case 3 -> System.out.println(managerTotal.getGestionAtendientes().actualizarAtendiente(
                checksInputOutput.getString("DNI actual: ", 9), checksInputOutput.getString("Nueva Pass: ", 20),
                checksInputOutput.getString("Nuevo Nombre: ", 50), checksInputOutput.getString("Nuevos Apellidos: ", 50),
                checksInputOutput.leerEmail("Nuevo Email: ", 100)));
            case 4 -> System.out.println(managerTotal.getGestionAtendientes().eliminarAtendiente(
                checksInputOutput.getString("DNI a borrar: ", 9)));
        }
    }

    private void gestionarEncuentros(int accion) {
        switch (accion) {
            case 1 -> System.out.println(managerTotal.getGestionEncuentros().crearEncuentro(
                checksInputOutput.getString("Nombre: ", 100), checksInputOutput.getString("Lugar: ", 100),
                checksInputOutput.getDate("Inicio", "dd/MM/yyyy"), checksInputOutput.getDate("Fin", "dd/MM/yyyy")));
            case 2 -> System.out.println(managerTotal.getGestionEncuentros().listarEncuentros());
            case 4 -> System.out.println(managerTotal.getGestionEncuentros().eliminarEncuentro(
                checksInputOutput.getInt(0, managerTotal.getGestionEncuentros().getCantidadEncuentros())));
        }
    }

    private void gestionarEventos(int accion) {
        switch (accion) {
            case 1 -> {
                int tipo = seleccionarTipoEvento();
                if (tipo != 5) {
                    Event nuevo = capturarDatosEvento(tipo);
                    if (nuevo != null) System.out.println(managerTotal.getGestionEventos().crearEvento(nuevo));
                }
            }
            case 2 -> System.out.println(managerTotal.getGestionEventos().listarEventos());
            case 4 -> System.out.println(managerTotal.getGestionEventos().eliminarEvento(checksInputOutput.getInt(0, 9999)));
        }
    }

    private void gestionarInvitados(int accion) {
        switch (accion) {
            case 1 -> System.out.println(managerTotal.getGestionInvitados().crearInvitado(
                checksInputOutput.getString("Nick: ", 30), checksInputOutput.getString("Apellido 1: ", 50),
                checksInputOutput.getString("Apellido 2: ", 50), checksInputOutput.getString("Tel: ", 15),
                checksInputOutput.getString("Estudios: ", 50), checksInputOutput.leerEmail("Email: ", 100),
                checksInputOutput.getString("Pass: ", 20)));
            case 2 -> System.out.println(managerTotal.getGestionInvitados().listarInvitados());
            case 4 -> System.out.println(managerTotal.getGestionInvitados().eliminarInvitado(
                checksInputOutput.getString("Nick a borrar: ", 30)));
        }
    }

    private int seleccionarTipoEvento() {
        System.out.println("\n1. Conferencia | 2. Mesa Redonda | 3. Proyecto | 4. Taller | 5. Cancelar");
        return checksInputOutput.getInt(1, 5);
    }

    private Event capturarDatosEvento(int tipo) {
        String tit = checksInputOutput.getString("Título: ", 100);
        String ubi = checksInputOutput.getString("Ubicación: ", 100);
        String des = checksInputOutput.getString("Descripción: ", 200);
        LocalDate fI = checksInputOutput.getDate("Fecha Inicio", "dd/MM/yyyy");
        LocalDate fF = checksInputOutput.getDate("Fecha Fin", "dd/MM/yyyy");
        LocalTime hI = checksInputOutput.leerHora("Hora Inicio", "HH:mm");
        LocalTime hF = checksInputOutput.leerHora("Hora Fin", "HH:mm");
        String cod = checksInputOutput.getString("Código (5 chars): ", 5);

        return switch (tipo) {
            case 1 -> new KeynoteSpeech(tit, ubi, des, fI, fF, hI, hF, cod, checksInputOutput.getString("Temática: ", 50));
            case 2 -> new RoundTable(tit, ubi, des, fI, fF, hI, hF, cod, checksInputOutput.getInt(1, 100));
            case 3 -> new ProjectPresentation(tit, ubi, des, fI, fF, hI, hF, cod, checksInputOutput.getString("Tipo: ", 50), checksInputOutput.getString("Detalles: ", 200));
            case 4 -> new PracticalWorkshop(tit, ubi, des, fI, fF, hI, hF, cod, checksInputOutput.getInt(1, 100));
            default -> null;
        };
    }
}