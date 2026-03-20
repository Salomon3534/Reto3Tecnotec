//v 13/02/2026 12:12
package util;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Encounter;
public class ManagerEncounters {
	
	private static ArrayList<Encounter> listaEncuentros = new ArrayList<>();
	
	//crear encuentr
	public String crearEncuentro(String nombre, String lugar, LocalDate fechaInicio, LocalDate fechaFin) {
		
		Encounter nuevoEncuentro = new Encounter(fechaInicio, fechaFin, lugar, nombre);
		ManagerEncounters.listaEncuentros.add(nuevoEncuentro);
		return "Encuentro con edición: " + listaEncuentros.size() + " creado con éxito.";
		
	}
	
	//listar encuentro
	public String listarEncuentros() {
		if (listaEncuentros.isEmpty()) {
           return "No hay encuentros por ahora";
       }
      
       String listaString = "";
       for(Encounter e : listaEncuentros) {
       	listaString += "\n" + e.toString();
       }
       return listaString;
	}
	
	//actualizar encuentro
	public String actualizarEncuentro(String nombre, String lugar, LocalDate fechaInicio, LocalDate fechaFin, int id) {
		
		if (listaEncuentros.isEmpty()) {
			
			return "La lista de encuentros está vacía";
			
		}
		for (Encounter encuentro : listaEncuentros) {
			
			if (encuentro.getId() == id) {
				
				encuentro.setNombre(nombre);
				encuentro.setLugar(lugar);
				encuentro.setDateStart(fechaInicio);
				encuentro.setDateEnd(fechaFin);
				return "El encuentro se ha actualizado con éxito";
			}
		}
		return "El encuentro no se ha encontrado";
	}
	
	//eliminar encuentro
	public String eliminarEncuentro(int id) {
		
		if (listaEncuentros.isEmpty()) {
			
			return "La lista de encuentros esta vacia";
		}
		Encounter eliminarEncuentro = null;
		for (Encounter encuentro : listaEncuentros) {
			
			if (encuentro.getId() == id) {
				
				eliminarEncuentro = encuentro;
			}
		}
		
		if (eliminarEncuentro != null) {
			
			listaEncuentros.remove(eliminarEncuentro);
			return "\n El evento se ha eliminado con exito";
			
		}
		return "\n El evento no se ha encontrado";
	}
	
	//conteo
	public int getCantidadEncuentros() {
		return listaEncuentros.size();
	}
}


