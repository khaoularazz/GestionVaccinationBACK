package ma.vaccination.beans;

public class villeVaccinBeans {
	
	private String ville;
	private int nmbrevaccin;
	public villeVaccinBeans(String ville, int nmbrevaccin) {
		super();
		this.ville = ville;
		this.nmbrevaccin = nmbrevaccin;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public int getNmbrevaccin() {
		return nmbrevaccin;
	}
	public void setNmbrevaccin(int nmbrevaccin) {
		this.nmbrevaccin = nmbrevaccin;
	}
	

}
