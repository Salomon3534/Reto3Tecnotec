package model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class RoundTable extends Event {

    private int conferenceNumber;

    public RoundTable(int id, String title, String location, String description, Date dateStart,
                      Date dateEnd, Time hourStart, Time hourEnd, int eCode, int numConf) {
        super(id, title, location, description, dateStart, dateEnd, hourStart, hourEnd, eCode);
        this.conferenceNumber = numConf;
    }

    public int getConferenceNumber() {
        return conferenceNumber;
    }

    public void setConferenceNumber(int tableType) {
        this.conferenceNumber = tableType;
    }

    @Override
    public String toString() {
        return "\n**************************************************\n" +
               "           MESA REDONDA: " + getTitle().toUpperCase() + "\n" +
               "**************************************************\n" +
               " > ID EVENTO:  " + getId() + "\n" +
               " > TIPO:       " + conferenceNumber + "\n" +
               " > LUGAR:      " + getLocation() + "\n" +
               " > FECHA:      " + getDateStart() + " al " + getDateEnd() + "\n" +
               " > HORARIO:    " + getHourStart() + " - " + getHourEnd() + "\n" +
               " > ENCUENTRO:  " + getEncounterCode() + "\n" +
               " > DESCRIPCIÓN: " + getDescription() + "\n" +
               "**************************************************";
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(conferenceNumber);
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
		RoundTable other = (RoundTable) obj;
		return conferenceNumber == other.conferenceNumber;
	}
}