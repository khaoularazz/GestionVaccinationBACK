package ma.vaccination.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "certificat_vaccination")
public class Certificat_Vaccination {
	@Id
	private String id;
	private String datedose1;
	private String datedose2;
	private Client id_client;
	public Certificat_Vaccination(String datedose1, String datedose2, Client id_client) {
		super();
		this.datedose1 = datedose1;
		this.datedose2 = datedose2;
		this.id_client = id_client;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDatedose1() {
		return datedose1;
	}
	public void setDatedose1(String datedose1) {
		datedose1 = datedose1;
	}
	public String getDatedose2() {
		return datedose2;
	}
	public void setDatedose2(String datedose2) {
		datedose2 = datedose2;
	}
	public Client getId_client() {
		return id_client;
	}
	public void setId_client(Client id_client) {
		this.id_client = id_client;
	}
	
}
