
package model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Event {
	
   private int id;
   
   private String title;
   private String location;
   private String description;
   
   private Date dateStart;
   private Date dateEnd;
   
   private Time hourStart;
   private Time hourEnd;
   
   private String encounterCode; //no se para que sirve esto

   public Event(int id, String title, String location, String description, Date dateStart, Date dateEnd,
		Time hourStart, Time hourEnd, String encounterCode) {
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

   public int getId() {
	return id;
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

   public Time getHourEnd() {
	return hourEnd;
   }

   public void setHourEnd(Time hourEnd) {
	this.hourEnd = hourEnd;
   }

   public String getEncounterCode() {
	return encounterCode;
   }

   public void setEncounterCode(String encounterCode) {
	this.encounterCode = encounterCode;
   }

   @Override
   public String toString() {
	return "Event [id=" + id + ", title=" + title + ", location=" + location + ", descryption=" + description
			+ ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", hourStart=" + hourStart + ", hourEnd=" + hourEnd
			+ ", encounterCode=" + encounterCode + "]";
   }

   @Override
   public int hashCode() {
	return Objects.hash(dateEnd, dateStart, description, encounterCode, hourEnd, hourStart, id, location, title);
   }

   @Override
   public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Event other = (Event) obj;
	return Objects.equals(dateEnd, other.dateEnd) && Objects.equals(dateStart, other.dateStart)
			&& Objects.equals(description, other.description) && Objects.equals(encounterCode, other.encounterCode)
			&& Objects.equals(hourEnd, other.hourEnd) && Objects.equals(hourStart, other.hourStart) && id == other.id
			&& Objects.equals(location, other.location) && Objects.equals(title, other.title);
   }
   
   
   }
