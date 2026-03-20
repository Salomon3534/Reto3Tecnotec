package view;

public class ViewEuskalEncounter {

	public void menuUserTypeChoice() {
        System.out.println("Que tipo de usuario eres?");

        MenuUsuario menuUsuario = new MenuUsuario();
        MenuAdmin menuAdmin = new MenuAdmin();
        int opcion;
        
        do {
            OpcionesElecionUsuarioAdmin.mostrar();
            System.out.print("Seleccione su perfil: ");
            opcion = checks.leerEntero(1, 3);
            
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
}
