//v 13/02/2026 12:03
package util;

import java.util.ArrayList;
import model.Guest;

public class ManagerGuests {

    private static ArrayList<Guest> listaInvitados = new ArrayList<>();

    //crear invitado
    public String crearInvitado(
            String nombreUsuario,
            String primerApellido,
            String segundoApellido,
            String telefono,
            String carrera,
            String correo,
            String contrasena
    ) {
        Guest nuevoInvitado = new Guest(
                nombreUsuario,
                primerApellido,
                segundoApellido,
                telefono,
                carrera,
                correo,
                contrasena
        );

        listaInvitados.add(nuevoInvitado);

        return "Invitado con nombre de usuario: " + nombreUsuario + " creado con éxito.";
    }

    //listar invitados
    public String listarInvitados() {
        if (listaInvitados.isEmpty()) {
            return "No hay invitados por ahora";
        }
        
        String listaString = "";
        for(Guest i : listaInvitados) {
            listaString += "\n" + i.toString();
        }
        return listaString;
    }

    //actualizar invitado
    public String actualizarInvitado(
            String nombreUsuario,
            String primerApellido,
            String segundoApellido,
            String telefono,
            String carrera,
            String correo,
            String contrasena
    ) {
        if (listaInvitados.isEmpty()) {
            
            return "La lista de invitados está vacía.";
            
        }

        for (Guest invitado : listaInvitados) {
            if (invitado.getNombreUsuario().equals(nombreUsuario)) {

                invitado.setPrimerApellido(primerApellido);
                invitado.setSegundoApellido(segundoApellido);
                invitado.setTelefono(telefono);
                invitado.setCarrera(carrera);
                invitado.setCorreo(correo);
                invitado.setContrasena(contrasena);

                return "El invitado se ha actualizado con éxito.";
               
            }
            
        }
        return "No se ha encontrado el usuario.";
    }

    //eliminar invitado
    public String eliminarInvitado(String nombreUsuario) {
        if (listaInvitados.isEmpty()) {
            return "La lista de invitados está vacía.";
        }

        
        boolean eliminado = listaInvitados.removeIf(invitado -> 
            invitado.getNombreUsuario().equals(nombreUsuario)
        );

        if (eliminado) {
            return "El invitado se ha eliminado con éxito.";
        }

        return "El invitado no se ha encontrado.";
    }

    //conteo
    public int getCantidadInvitados() {
        return listaInvitados.size();
    }
}



