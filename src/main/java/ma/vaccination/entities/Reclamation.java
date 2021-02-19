package ma.vaccination.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reclamation")
public class Reclamation {
	
	@Id
	private String id ;
	private  List<String> effets_id;
	private Client id_client;
	public Reclamation( List<String> effets_id, Client id_client) {
		super();
		
		this.effets_id = effets_id;
		this.id_client = id_client;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getEffets_id() {
		return effets_id;
	}
	public void setEffets_id(List<String> effets_id) {
		this.effets_id = effets_id;
	}
	public Client getId_client() {
		return id_client;
	}
	public void setId_client(Client id_client) {
		this.id_client = id_client;
	}
	
}
