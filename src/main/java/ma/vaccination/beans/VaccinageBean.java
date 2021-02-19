package ma.vaccination.beans;

public class VaccinageBean {
	private String age;
	private int nmbrevaccin;
	public VaccinageBean(String age, int nmbrevaccin) {
		super();
		this.age = age;
		this.nmbrevaccin = nmbrevaccin;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public int getNmbrevaccin() {
		return nmbrevaccin;
	}
	public void setNmbrevaccin(int nmbrevaccin) {
		this.nmbrevaccin = nmbrevaccin;
	}
	

}
