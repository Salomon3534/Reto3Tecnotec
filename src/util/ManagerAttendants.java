//v 13/02/2026 12:09
package util;

import java.util.ArrayList;
import model.Attendant;


public class ManagerAttendants {
    private static ArrayList<Attendant> listaAtendientes = new ArrayList<>();

    // crear atendiente
    public String crearAtendiente(String dni, String contraseña, String nombre, String apellidos, String email) {
        Attendant nuevoAtendiente = new Attendant(dni, contraseña, nombre, apellidos, email);
        listaAtendientes.add(nuevoAtendiente);
        return  "Atendiente con DNI: " + dni + " agregado con éxito.";
    }

    // listar atendientes
    public String listarAtendientes() {
        if (listaAtendientes.isEmpty()) {
            return "No hay atendientes por ahora";
        }
        
        String listaString = "";
        for(Attendant a : listaAtendientes) {
            listaString += "\n" + a.toString();
        }
        return listaString;
    }

    // actualizar atendientes
    public String actualizarAtendiente(String dni, String contraseña, String nombre, String apellidos, String email) {
        if (listaAtendientes.isEmpty()) {

            return "La lista de atendientes está vacía";
        }
        for (Attendant atendiente : listaAtendientes) {

            if (atendiente.getDni().equals(dni)) {

                atendiente.setContraseña(contraseña);
                atendiente.setNombre(nombre);
                atendiente.setApellidos(apellidos);
                atendiente.setEmail(email);

                return "El atendiente se ha actualizado con éxito";
            
            }
        }
        return "El atendiente no se ha encontrado";
    }

    // eliminar atendiente
    public String eliminarAtendiente(String dni) {

        if (listaAtendientes.isEmpty()) {

            return "La lista de atendientes está vacía";
        }
        Attendant atendienteEliminar = null;
        for (Attendant atendiente : listaAtendientes) {

            if (atendiente.getDni().equals(dni)) {

                atendienteEliminar = atendiente;
                break;
            }
        }
        if (atendienteEliminar != null) {

            listaAtendientes.remove(atendienteEliminar);
            return "El atendiente se ha eliminado con éxito";
        }
        return "El atendiente no se ha encontrado";
    }

    // conteo
    public int getCantidadAtendientes() {
        return listaAtendientes.size();
        
        
        
    }
}



