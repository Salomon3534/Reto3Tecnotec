//13/02/2026 12:16
package util;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import model.Event;
public class ManagerEvents {
   private static ArrayList<Event> listaEventos = new ArrayList<>();
   public String crearEvento(
           Event e
   ) {
       listaEventos.add(e);
       return e.getClass().getSimpleName() + " creado con éxito. ID: " + e.getId();
   }
   public String listarEventos() {
   	if (listaEventos.isEmpty()) {
           return "No hay eventos por ahora";
       }
      
       String listaString = "";
       for(Event e : listaEventos) {
       	listaString += "\n" + e.toString();
       }
       return listaString;
   }
   public String actualizarEvento(
           int id,
           String titulo,
           String ubicacion,
           String descripcion,
           LocalDate fechaInicio,
           LocalDate fechaFin,
           LocalTime horarioInicio,
           LocalTime horarioFin,
           String codigoEncuentro
   ) {
   	if (listaEventos.isEmpty()) {
			
			return "La lista de eventos está vacía";
			
		}
   	
       for (Event evento : listaEventos) {
           if (evento.getId() == id) {
               evento.setTitulo(titulo);
               evento.setUbicacion(ubicacion);
               evento.setDescripcion(descripcion);
               evento.setFechaInicio(fechaInicio);
               evento.setFechaFin(fechaFin);
               evento.setHorarioInicio(horarioInicio);
               evento.setHorarioFin(horarioFin);
               evento.setCodigoEncuentro(codigoEncuentro);
               return "Evento " + id + " actualizado.";
           }
       }
       return "No se encontró el ID " + id;
   }
   public Event buscarPorID(int id) {
       Event encontrado = listaEventos.get(id);
       return encontrado;
  }
  
   public String buscarEvento(Event e) {
      
       int id = listaEventos.indexOf(e);
       return e.getTitulo() + " tiene el ID: " + id + "en la lista de eventos";
      
   }
   public String eliminarEvento(int id) {
       if (listaEventos.removeIf(e -> e.getId() == id)) {
           return "Eliminado con éxito";
       }
       return "No encontrado";
   }
   public int getCantidadEventos() {
       return listaEventos.size();
   }
}



