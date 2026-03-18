//11/02/2026 14:16
package model;
import java.time.LocalDate;
import java.time.LocalTime;
public class Evento {
   private int id;
   private String titulo;
   private String ubicacion;
   private String descripcion;
   private LocalDate fechaInicio;
   private LocalDate fechaFin;
   private LocalTime horarioInicio;
   private LocalTime horarioFin;
   private String codigoEncuentro;
   private static int eventosCreados;
   
   //CONSTRUCTORES
  
   public Evento() {
       eventosCreados++;
       this.id = eventosCreados;
   }
   
   public Evento(String titulo, String ubicacion, String descripcion, LocalDate fechaInicio, LocalDate fechaFin,
           LocalTime horarioInicio, LocalTime horarioFin, String codigoEncuentro) {
       this();
       this.titulo = titulo;
       this.ubicacion = ubicacion;
       this.descripcion = descripcion;
       this.fechaInicio = fechaInicio;
       this.fechaFin = fechaFin;
       this.horarioInicio = horarioInicio;
       this.horarioFin = horarioFin;
       this.codigoEncuentro = codigoEncuentro;
   }
   
   //SETTERS
   public void setId(int id) {
       this.id = id;
   }
   public void setTitulo(String titulo) {
       this.titulo = titulo;
   }
   public void setUbicacion(String ubicacion) {
       this.ubicacion = ubicacion;
   }
   public void setDescripcion(String descripcion) {
       this.descripcion = descripcion;
   }
   public void setFechaInicio(LocalDate fechaInicio) {
       this.fechaInicio = fechaInicio;
   }
   public void setFechaFin(LocalDate fechaFin) {
       this.fechaFin = fechaFin;
   }
   public void setHorarioInicio(LocalTime horarioInicio) {
       this.horarioInicio = horarioInicio;
   }
   public void setHorarioFin(LocalTime horarioFin) {
       this.horarioFin = horarioFin;
   }
   public void setCodigoEncuentro(String codigoEncuentro) {
       this.codigoEncuentro = codigoEncuentro;
   }
   
   //GETTERS
  
   public int getId() {
       return id;
   }
   public String getTitulo() {
       return titulo;
   }
   public String getUbicacion() {
       return ubicacion;
   }
   public String getDescripcion() {
       return descripcion;
   }
   public LocalDate getFechaInicio() {
       return fechaInicio;
   }
   public LocalDate getFechaFin() {
       return fechaFin;
   }
   public LocalTime getHorarioInicio() {
       return horarioInicio;
   }
   public LocalTime getHorarioFin() {
       return horarioFin;
   }
   public String getCodigoEncuentro() {
       return codigoEncuentro;
   }
   public static int getEventosCreados() {
       return eventosCreados;
   }
   
   //TOSTRING
   @Override
   public String toString() {
       return "EVENTO " + this.getTitulo()
               + "\n  Ubicación: '" + this.getUbicacion() + '\'' + "\n  Fecha: '" + this.getFechaInicio() + "-"
               + this.getFechaFin() + '\'' + "\n  Horario: '" + this.getHorarioInicio() + "-" + this.getHorarioFin()
               + '\'' + "\n  Descripción: '" + this.getDescripcion() + '\'';
   }
   //EQUALS
   @Override
   public boolean equals(Object obj) {
       if (this == obj)
           return true;
       if (obj == null || getClass() != obj.getClass())
           return false;
       Evento otro = (Evento) obj;
       return this.id == otro.id;
   }
}



