package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Event {
<<<<<<< Updated upstream
	
   private int id;
   
   private String title;
   private String location;
   private String description;
   
   private LocalDate dateStart;
   private LocalDate dateEnd;
   
   private LocalTime hourStart;
   private LocalTime hourEnd;
   
   private String encounterCode; //no se para que sirve esto

   public Event(int id, String title, String location, String descryption, LocalDate dateStart, LocalDate dateEnd,
		LocalTime hourStart, LocalTime hourEnd, String encounterCode) {
	this.id = id;
	this.title = title;
	this.location = location;
	this.description = descryption;
	this.dateStart = dateStart;
	this.dateEnd = dateEnd;
	this.hourStart = hourStart;
	this.hourEnd = hourEnd;
	this.encounterCode = encounterCode;
   }
=======

    private int id;
    private String title;
    private String location;
    private String description;
    private Date dateStart;
    private Date dateEnd;
    private Time hourStart;
    private Time hourEnd;
    private int encounterCode;
>>>>>>> Stashed changes

    public Event(int id, String title, String location, String description, Date dateStart, Date dateEnd,
                 Time hourStart, Time hourEnd, int encounterCode) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.description = description;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.hourStart = hourStart;
        this.hourEnd = hourEnd;
        this.encounterCode = encounterCode;
    }

<<<<<<< Updated upstream
   public void setId(int id) {
	this.id = id;
   }

   public String getTitle() {
	return title;
   }
=======
    public int getId() {
        return id;
    }
>>>>>>> Stashed changes

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

<<<<<<< Updated upstream
   public void setDescription(String descryption) {
	this.description = descryption;
   }

   public LocalDate getDateStart() {
	return dateStart;
   }

   public void setDateStart(LocalDate dateStart) {
	this.dateStart = dateStart;
   }

   public LocalDate getDateEnd() {
	return dateEnd;
   }

   public void setDateEnd(LocalDate dateEnd) {
	this.dateEnd = dateEnd;
   }

   public LocalTime getHourStart() {
	return hourStart;
   }

   public void setHourStart(LocalTime hourStart) {
	this.hourStart = hourStart;
   }

   public LocalTime getHourEnd() {
	return hourEnd;
   }

   public void setHourEnd(LocalTime hourEnd) {
	this.hourEnd = hourEnd;
   }
=======
    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Time getHourStart() {
        return hourStart;
    }

    public void setHourStart(Time hourStart) {
        this.hourStart = hourStart;
    }
>>>>>>> Stashed changes

    public Time getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(Time hourEnd) {
        this.hourEnd = hourEnd;
    }

    public int getEncounterCode() {
        return encounterCode;
    }

    public void setEncounterCode(int encounterCode) {
        this.encounterCode = encounterCode;
    }

    @Override
    public String toString() {
        return "\n**************************************************\n" +
               "                EVENTO: " + (title != null ? title.toUpperCase() : "N/A") + "\n" +
               "**************************************************\n" +
               " > ID EVENTO:    " + id + "\n" +
               " > LUGAR:        " + location + "\n" +
               " > FECHAS:       " + dateStart + " al " + dateEnd + "\n" +
               " > HORARIO:      " + hourStart + " - " + hourEnd + "\n" +
               " > ENCUENTRO:    " + encounterCode + "\n" +
               " > DESCRIPCIÓN:  " + description + "\n" +
               "**************************************************";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Generalmente, el ID es suficiente para identificar la entidad
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Event other = (Event) obj;
        return id == other.id; // Simplificado: la igualdad se basa en el ID único
    }
}