package ma.vaccination.beans;

import java.util.Date;

public class datevaccinsBean {
	private String date;
	private int nmbredose1;
	private int nmbredose2;
	public datevaccinsBean(String date, int nmbredose1, int nmbredose2) {
		super();
		this.date = date;
		this.nmbredose1 = nmbredose1;
		this.nmbredose2 = nmbredose2;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getNmbredose1() {
		return nmbredose1;
	}
	public void setNmbredose1(int nmbredose1) {
		this.nmbredose1 = nmbredose1;
	}
	public int getNmbredose2() {
		return nmbredose2;
	}
	public void setNmbredose2(int nmbredose2) {
		this.nmbredose2 = nmbredose2;
	}
	
}
