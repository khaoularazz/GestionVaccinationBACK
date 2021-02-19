package ma.vaccination.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "centre_vaccination")
public class Centre_Vaccination {
	@Id
	private String id ;
    private String nom;
    private String ville;
    private List<Rendez_vous> rendez_vous_id;
	public Centre_Vaccination( String nom, String ville, List<Rendez_vous> rendez_vous_id) {
		super();
		
		this.nom = nom;
		this.ville = ville;
		this.rendez_vous_id = rendez_vous_id;
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
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public List<Rendez_vous> getRendez_vous_id() {
		return rendez_vous_id;
	}
	public void setRendez_vous_id(List<Rendez_vous> rendez_vous_id) {
		this.rendez_vous_id = rendez_vous_id;
	}
    
}
