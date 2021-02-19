package ma.vaccination.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collection = "rendez_vous")
public class Rendez_vous {
	@Id
	private String id ;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private String date;
	private int dose ;
	private int valide;

	public Rendez_vous(String date, int dose, int valide) {
		super();
		this.date = date;
		this.dose = dose;
		this.valide = valide;
	}
	
	public int getValide() {
		return valide;
	}
	public void setValide(int valide) {
		this.valide = valide;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getDose() {
		return dose;
	}
	public void setDose(int dose) {
		this.dose = dose;
	}

	@Override
	public String toString() {
		return "Rendez_vous [id=" + id + ", date=" + date + ", dose=" + dose + ", valide=" + valide + "]";
	};
	
}
