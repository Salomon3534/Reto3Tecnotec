package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Encounter {
<<<<<<< Updated upstream
	
	private int id;
	private String location;
	private String name;
	private LocalDate dateStart;
	private LocalDate dateEnd;
	
	//eventos que este encuentro engloba
	private ArrayList<Event> eventsInEncounterList;

	//constructor
	public Encounter(int id, String lugar, String nombre, LocalDate dateStart, LocalDate dateEnd,
			ArrayList<Event> eventsInEncounterList) {
		super();
		this.id = id;
		this.location = lugar;
		this.name = nombre;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.eventsInEncounterList = eventsInEncounterList;
	}
=======

    private int code;
    private Date dateStart;
    private Date dateEnd;
    private String location;
    private ArrayList<Event> eventsInEncounterList;

    public Encounter(int code, Date dateStart, Date dateEnd, String location) {
        this.code = code;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.location = location;
    }

    public int getCode() {
        return code;
    }
>>>>>>> Stashed changes

    public void setCode(int code) {
        this.code = code;
    }

    public Date getDateStart() {
        return dateStart;
    }

<<<<<<< Updated upstream
	public String getLugar() {
		return location;
	}

	public void setLugar(String lugar) {
		this.location = lugar;
	}

	public String getNombre() {
		return name;
	}

	public void setNombre(String nombre) {
		this.name = nombre;
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

	public ArrayList<Event> getEventsInEncounterList() {
		return eventsInEncounterList;
	}

	public void setEventsInEncounterList(ArrayList<Event> eventsInEncounterList) {
		this.eventsInEncounterList = eventsInEncounterList;
	}

	
	@Override
	public String toString() {
		return "Encounter [id=" + id + ", location=" + location + ", name=" + name + ", dateStart=" + dateStart
				+ ", dateEnd=" + dateEnd + ", eventsInEncounterList=" + eventsInEncounterList + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateEnd, dateStart, eventsInEncounterList, id, location, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Encounter other = (Encounter) obj;
		return Objects.equals(dateEnd, other.dateEnd) && Objects.equals(dateStart, other.dateStart)
				&& Objects.equals(eventsInEncounterList, other.eventsInEncounterList) && id == other.id
				&& Objects.equals(location, other.location) && Objects.equals(name, other.name);
	}
	
	}
=======
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Event> getEventsInEncounterList() {
        return eventsInEncounterList;
    }

    public void setEventsInEncounterList(ArrayList<Event> eventsInEncounterList) {
        this.eventsInEncounterList = eventsInEncounterList;
    }

    @Override
    public String toString() {
        int eventsCount = (eventsInEncounterList != null) ? eventsInEncounterList.size() : 0;
        return "\n**************************************************\n" +
               "               ENCUENTRO #" + code + "\n" +
               "**************************************************\n" +
               " > CÓDIGO (ID):   " + code + "\n" +
               " > LUGAR:         " + location + "\n" +
               " > FECHAS:        " + dateStart + " al " + dateEnd + "\n" +
               " > NUM. EVENTOS:  " + eventsCount + "\n" +
               "**************************************************";
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, dateEnd, dateStart, location);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Encounter other = (Encounter) obj;
        return code == other.code;
    }
}
>>>>>>> Stashed changes
