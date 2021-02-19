package ma.vaccination.beans;

import java.util.List;

public class ReclamationBean {
	String id ;
	String nom ; 
	String prenom ;
	List<String> effets ;

	
	public ReclamationBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReclamationBean( String id , String nom, String prenom, List<String> ef) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.effets = ef;
		this.id = id ;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getEffets() {
		return effets;
	}

	public void setEffets(List<String> effets) {
		this.effets = effets;
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

	public List<String> getEf() {
		return effets;
	}

	public void setEf(List<String> ef) {
		this.effets = ef;
	}
}
