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


<<<<<<< Updated upstream
=======
    public ManagerEncounters() throws SQLException {
        loadEncounters();
    }

    public void loadEncounters() throws SQLException {
        encountersList.clear();
        String query = "SELECT * FROM ENCUENTRO";
        try (PreparedStatement ps = DatabaseConnector.getConexion().prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                encountersList.add(new Encounter(
                    rs.getInt("CODIGO"), 
                    rs.getDate("FECHA_INICIO"),
                    rs.getDate("FECHA_FIN"), 
                    rs.getString("UBICACION")
                ));
            }
        }
    }

    // No pedimos 'code' porque la DB lo genera solo (AI)
    public String createEncounter(Date dateStart, Date dateEnd, String location) throws SQLException {
        String query = "INSERT INTO ENCUENTRO (FECHA_INICIO, FECHA_FIN, UBICACION) VALUES (?, ?, ?)";
        try (PreparedStatement ps = DatabaseConnector.getConexion().prepareStatement(query)) {
            ps.setDate(1, dateStart); 
            ps.setDate(2, dateEnd);
            ps.setString(3, location);
            ps.executeUpdate();
        }
        loadEncounters();
        return "¡Nuevo encuentro registrado correctamente!";
    }

    public String listEncounters() {
        if (encountersList.isEmpty()) return "No hay encuentros registrados.";
        StringBuilder sb = new StringBuilder();
        for (Encounter e : encountersList) sb.append(e.toString());
        return sb.toString();
    }

    public String updateEncounter(Encounter e) throws SQLException {
        String query = "UPDATE ENCUENTRO SET FECHA_INICIO=?, FECHA_FIN=?, UBICACION=? WHERE CODIGO=?";
        try (PreparedStatement ps = DatabaseConnector.getConexion().prepareStatement(query)) {
            ps.setDate(1, e.getDateStart());
            ps.setDate(2, e.getDateEnd());
            ps.setString(3, e.getLocation());
            ps.setInt(4, e.getCode());
            
            if (ps.executeUpdate() > 0) {
                loadEncounters();
                return "Encuentro #" + e.getCode() + " actualizado.";
            }
        }
        return "Error: No se encontró el encuentro.";
    }

    public String deleteEncounter(int code) throws SQLException {
        String query = "DELETE FROM ENCUENTRO WHERE CODIGO = ?";
        try (PreparedStatement ps = DatabaseConnector.getConexion().prepareStatement(query)) {
            ps.setInt(1, code);
            if (ps.executeUpdate() > 0) {
                loadEncounters();
                return "Encuentro #" + code + " eliminado.";
            }
        }
        return "Error: No se encontró el encuentro.";
    }
}
>>>>>>> Stashed changes
