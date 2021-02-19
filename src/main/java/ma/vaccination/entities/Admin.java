package ma.vaccination.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "admin")
public class Admin {
	
	@Id
	private String id;
	private String nom;
	private String prenom;
	private String email;
	private String password;
	private String ville;
	private String centrevaccination;
	
	public Admin(String nom, String prenom, String email, String password, String ville, String centrevaccination) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.ville = ville;
		this.centrevaccination = centrevaccination;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCentrevaccination() {
		return centrevaccination;
	}
	public void setCentrevaccination(String centrevaccination) {
		this.centrevaccination = centrevaccination;
	}
	

}
