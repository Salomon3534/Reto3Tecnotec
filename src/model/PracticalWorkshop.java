package model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class PracticalWorkshop extends Event {

    private int workshopNumber;

    public PracticalWorkshop(int id, String title, String location, String description, Date dateStart,
                               Date dateEnd, Time hourStart, Time hourEnd, int eCode, 
                               int workshopNumber) {
        super(id, title, location, description, dateStart, dateEnd, hourStart, hourEnd, eCode);
        this.workshopNumber = workshopNumber;
    }

    // Getters y Setters
    public int getWorkshopNumber() {
        return workshopNumber;
    }

    public void setWorkshopNumber(int workshopNumber) {
        this.workshopNumber = workshopNumber;
    }

    @Override
    public String toString() {
        return "\n**************************************************\n" +
               "          TALLER PRÁCTICO: " + getTitle().toUpperCase() + "\n" +
               "**************************************************\n" +
               " > ID EVENTO:      " + getId() + "\n" +
               " > NÚMERO TALLER:  " + workshopNumber + "\n" +
               " > LUGAR:          " + getLocation() + "\n" +
               " > FECHA:          " + getDateStart() + " al " + getDateEnd() + "\n" +
               " > HORARIO:        " + getHourStart() + " - " + getHourEnd() + "\n" +
               " > ENCUENTRO:      " + getEncounterCode() + "\n" +
               " > DESCRIPCIÓN:    " + getDescription() + "\n" +
               "**************************************************";
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(workshopNumber);
		return result;
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
		PracticalWorkshop other = (PracticalWorkshop) obj;
		return workshopNumber == other.workshopNumber;
	}

    
}