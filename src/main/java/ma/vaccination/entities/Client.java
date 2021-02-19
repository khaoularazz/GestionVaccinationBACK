package ma.vaccination.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "client")
public class Client {
	
	@Id
	private String id;
	private String nom;
	private String prenom;
	private String email;
	private String password;
	private String sex;
	private int age;
	private String tel;
	private String ville;
	private String code_ver ;
	private List<Rendez_vous> rendez_vous_id;

	
	public Client(String nom, String prenom,  String email, String password,String sex, int age, String tel, String ville,
			String code_ver, List<Rendez_vous> rendez_vous_id) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.sex = sex;
		this.age = age;
		this.tel = tel;
		this.ville = ville;
		this.email = email;
		this.password = password;
		this.code_ver = code_ver;
		this.rendez_vous_id = rendez_vous_id;
	}
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
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
	public String getCode_ver() {
		return code_ver;
	}
	public void setCode_ver(String code_ver) {
		this.code_ver = code_ver;
	}
	public List<Rendez_vous> getRendez_vous_id() {
		return rendez_vous_id;
	}
	public void setRendez_vous_id(List<Rendez_vous> rendez_vous_id) {
		this.rendez_vous_id = rendez_vous_id;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password="
				+ password + ", sex=" + sex + ", age=" + age + ", tel=" + tel + ", ville=" + ville + ", code_ver="
				+ code_ver + ", rendez_vous_id=" + rendez_vous_id + "]";
	}
	

}
