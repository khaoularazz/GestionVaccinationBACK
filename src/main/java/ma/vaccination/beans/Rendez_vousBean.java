package ma.vaccination.beans;

public class Rendez_vousBean {
	private String id;
	private String nom ;
	private String prenom;
	private String sex;
	private int age;
	private String tel;
	private int dose ;
	public Rendez_vousBean(String id, String nom, String prenom, String sex, int age, String tel, int dose) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.sex = sex;
		this.age = age;
		this.tel = tel;
		this.dose = dose;
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
	public int getDose() {
		return dose;
	}
	public void setDose(int dose) {
		this.dose = dose;
	}
	
	

}
