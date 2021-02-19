package ma.vaccination.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="effets_secondaires")
public class Effet_Secondaire {
	@Id
	private String id;
	private String text;
    private List <Reclamation> reclamation_id;
	public Effet_Secondaire(String text, List<Reclamation> reclamation_id) {
		super();
		this.text = text;
		this.reclamation_id = reclamation_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<Reclamation> getReclamation_id() {
		return reclamation_id;
	}
	public void setReclamation_id(List<Reclamation> reclamation_id) {
		this.reclamation_id = reclamation_id;
	}
    
}
