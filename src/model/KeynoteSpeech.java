package model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class KeynoteSpeech extends Event {

    private String speechType;

    public KeynoteSpeech(int id, String title, String location, String description, Date dateStart,
                         Date dateEnd, Time hourStart, Time hourEnd, int eCode, String speechType) {
        super(id, title, location, description, dateStart, dateEnd, hourStart, hourEnd, eCode);
        this.speechType = speechType;
    }

    public String getSpeechType() {
        return speechType;
    }

    public void setSpeechType(String speechType) {
        this.speechType = speechType;
    }

    @Override
    public String toString() {
        return "\n**************************************************\n" +
               "          KEYNOTE: " + getTitle().toUpperCase() + "\n" +
               "**************************************************\n" +
               " > ID EVENTO:  " + getId() + "\n" +
               " > TIPO:       " + speechType + "\n" +
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
		result = prime * result + Objects.hash(speechType);
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
		KeynoteSpeech other = (KeynoteSpeech) obj;
		return Objects.equals(speechType, other.speechType);
	}

    
}