
package model;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
public class ConferenciaMagistral extends Evento {
	
   private String tipoConferencia;
   private static int conferenciasCreadas;
   
   public ConferenciaMagistral() {
       super();
       conferenciasCreadas++;
   }
   public ConferenciaMagistral(String titulo, String ubicacion, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, LocalTime horaInicio, LocalTime horaFin, String codigo, String tipoConferencia) {
       super(titulo, ubicacion, descripcion, fechaInicio, fechaFin, horaInicio, horaFin, codigo);
       this.tipoConferencia = tipoConferencia;
       conferenciasCreadas++;
   }
   
   //GETTERS
   public String getTipoConferencia() {
       return tipoConferencia;
   }
   public static int getConferenciasCreadas() {
       return conferenciasCreadas;
   }
  
   //SETTERS
   public void setTipoConferencia(String tipoConferencia) {
       this.tipoConferencia = tipoConferencia;
   }
   
   //TOSTRING
   @Override
   public String toString() {
       return super.toString() + "\n Tipo conferencia: '" + this.getTipoConferencia() + '\'';
   }
   @Override
   public int hashCode() {
	return Objects.hash(tipoConferencia);
   }
   
   @Override
   public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (!super.equals(obj))
		return false;
	if (getClass() != obj.getClass())
		return false;
	ConferenciaMagistral other = (ConferenciaMagistral) obj;
	return Objects.equals(tipoConferencia, other.tipoConferencia);
   };
   
   
}



