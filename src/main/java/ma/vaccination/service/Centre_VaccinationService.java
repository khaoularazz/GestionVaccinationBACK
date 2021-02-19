package ma.vaccination.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import ma.vaccination.beans.villeVaccinBeans;
import ma.vaccination.dao.Centre_VaccinationDAO;
import ma.vaccination.entities.Centre_Vaccination;
import ma.vaccination.entities.Rendez_vous;

@Service
public class Centre_VaccinationService {

	@Autowired
	Centre_VaccinationDAO cent_vacDAO;

	@CrossOrigin
	public List<Centre_Vaccination> CentresVacVille(String ville, String date) {
		List<Centre_Vaccination> centres = cent_vacDAO.findALLByVille(ville);
		int nmbr = 0;
		List<Centre_Vaccination> centresvac = new ArrayList<Centre_Vaccination>();
		List<Rendez_vous> rvs = new ArrayList<Rendez_vous>();
		for (Centre_Vaccination cv : centres) {
			if (cv.getRendez_vous_id() == null)
				centresvac.add(cv);
			else {
				for (Rendez_vous rv : cv.getRendez_vous_id()) {
					if (rv.getDate().equalsIgnoreCase(date)) {
						nmbr++;

					}}
					if (nmbr < 30) {
						centresvac.add(cv);
					
					nmbr = 0;
				}
			}
		}

		return centresvac;
	}

	public List<villeVaccinBeans> villenmbrevaccin() {

		List<Centre_Vaccination> cntrville ;
		List<Rendez_vous> rendezvousville = new ArrayList<Rendez_vous>();
		List<villeVaccinBeans> vvbs = new ArrayList<villeVaccinBeans>();
		villeVaccinBeans vvb;
		cntrville = cent_vacDAO.findALLByVille("agadir");
		rendezvousville = new ArrayList<Rendez_vous>();
		

		for (Centre_Vaccination ctr : cntrville) {
			if (ctr.getRendez_vous_id() != null)
				for (Rendez_vous rv : ctr.getRendez_vous_id()) {
					if (rv.getDose() == 2 && rv.getValide() == 1)
						rendezvousville.add(rv);
				}
		}

		  vvb=new villeVaccinBeans("agadir", rendezvousville.size());
	       vvbs.add(vvb);
			cntrville = cent_vacDAO.findALLByVille("al_hoceima");
			rendezvousville = new ArrayList<Rendez_vous>();
			
		for (Centre_Vaccination ctr : cntrville) {
			if (ctr.getRendez_vous_id() != null)
				for (Rendez_vous rv : ctr.getRendez_vous_id()) {
					if (rv.getDose() == 2 && rv.getValide() == 1)
						rendezvousville.add(rv);
				}
		}

		  vvb=new villeVaccinBeans("al_hoceima", rendezvousville.size());
	       vvbs.add(vvb);
			cntrville = cent_vacDAO.findALLByVille("assilah");
			rendezvousville = new ArrayList<Rendez_vous>();

		for (Centre_Vaccination ctr : cntrville) {
			if (ctr.getRendez_vous_id() != null)
				for (Rendez_vous rv : ctr.getRendez_vous_id()) {
					if (rv.getDose() == 2 && rv.getValide() == 1)
						rendezvousville.add(rv);
				}
		}

		  vvb=new villeVaccinBeans("assilah", rendezvousville.size());
	       vvbs.add(vvb);
			cntrville = cent_vacDAO.findALLByVille("beni Mellal");
			rendezvousville = new ArrayList<Rendez_vous>();

		for (Centre_Vaccination ctr : cntrville) {
			if (ctr.getRendez_vous_id() != null)
				for (Rendez_vous rv : ctr.getRendez_vous_id()) {
					if (rv.getDose() == 2 && rv.getValide() == 1)
						rendezvousville.add(rv);
				}
		}

		  vvb=new villeVaccinBeans("beni Mellal", rendezvousville.size());
	       vvbs.add(vvb);
			cntrville = cent_vacDAO.findALLByVille("casablanca");
			rendezvousville = new ArrayList<Rendez_vous>();

		for (Centre_Vaccination ctr : cntrville) {
			if (ctr.getRendez_vous_id() != null)
				for (Rendez_vous rv : ctr.getRendez_vous_id()) {
					if (rv.getDose() == 2 && rv.getValide() == 1)
						rendezvousville.add(rv);
				}
		}

		  vvb=new villeVaccinBeans("casablanca", rendezvousville.size());
	       vvbs.add(vvb);
			cntrville = cent_vacDAO.findALLByVille("chefchaouen");
			rendezvousville = new ArrayList<Rendez_vous>();

		for (Centre_Vaccination ctr : cntrville) {
			if (ctr.getRendez_vous_id() != null)
				for (Rendez_vous rv : ctr.getRendez_vous_id()) {
					if (rv.getDose() == 2 && rv.getValide() == 1)
						rendezvousville.add(rv);
				}
		}

		  vvb=new villeVaccinBeans("chefchaouen", rendezvousville.size());
	       vvbs.add(vvb);
			cntrville = cent_vacDAO.findALLByVille("el_jadida");
			rendezvousville = new ArrayList<Rendez_vous>();

		for (Centre_Vaccination ctr : cntrville) {
			if (ctr.getRendez_vous_id() != null)
				for (Rendez_vous rv : ctr.getRendez_vous_id()) {
					if (rv.getDose() == 2 && rv.getValide() == 1)
						rendezvousville.add(rv);
				}
		}

		  vvb=new villeVaccinBeans("el_jadida", rendezvousville.size());
	       vvbs.add(vvb);
			cntrville = cent_vacDAO.findALLByVille("fes");
			rendezvousville = new ArrayList<Rendez_vous>();

		for (Centre_Vaccination ctr : cntrville) {
			if (ctr.getRendez_vous_id() != null)
				for (Rendez_vous rv : ctr.getRendez_vous_id()) {
					if (rv.getDose() == 2 && rv.getValide() == 1)
						rendezvousville.add(rv);
				}
		}

		  vvb=new villeVaccinBeans("fes", rendezvousville.size());
	       vvbs.add(vvb);
			cntrville = cent_vacDAO.findALLByVille("fnideq");
			rendezvousville = new ArrayList<Rendez_vous>();

		for (Centre_Vaccination ctr : cntrville) {
			if (ctr.getRendez_vous_id() != null)
				for (Rendez_vous rv : ctr.getRendez_vous_id()) {
					if (rv.getDose() == 2 && rv.getValide() == 1)
						rendezvousville.add(rv);
				}
		}

		  vvb=new villeVaccinBeans("fnideq", rendezvousville.size());
	       vvbs.add(vvb);
			cntrville = cent_vacDAO.findALLByVille("ifrane");
			rendezvousville = new ArrayList<Rendez_vous>();

		for (Centre_Vaccination ctr : cntrville) {
			if (ctr.getRendez_vous_id() != null)
				for (Rendez_vous rv : ctr.getRendez_vous_id()) {
					if (rv.getDose() == 2 && rv.getValide() == 1)
						rendezvousville.add(rv);
				}
		}

		  vvb=new villeVaccinBeans("ifrane", rendezvousville.size());
	       vvbs.add(vvb);
			cntrville = cent_vacDAO.findALLByVille("kenitra");
			rendezvousville = new ArrayList<Rendez_vous>();

		for (Centre_Vaccination ctr : cntrville) {
			if (ctr.getRendez_vous_id() != null)
				for (Rendez_vous rv : ctr.getRendez_vous_id()) {
					if (rv.getDose() == 2 && rv.getValide() == 1)
						rendezvousville.add(rv);
				}
		}

		  vvb=new villeVaccinBeans("kenitra", rendezvousville.size());
	       vvbs.add(vvb);
			cntrville = cent_vacDAO.findALLByVille("marrakech");
			rendezvousville = new ArrayList<Rendez_vous>();

		for (Centre_Vaccination ctr : cntrville) {
			if (ctr.getRendez_vous_id() != null)
				for (Rendez_vous rv : ctr.getRendez_vous_id()) {
					if (rv.getDose() == 2 && rv.getValide() == 1)
						rendezvousville.add(rv);
				}
		}
		
		
		  vvb=new villeVaccinBeans("marrakech", rendezvousville.size());
	       vvbs.add(vvb);
			cntrville = cent_vacDAO.findALLByVille("oujda");
			rendezvousville = new ArrayList<Rendez_vous>();

		for (Centre_Vaccination ctr : cntrville) {
			if (ctr.getRendez_vous_id() != null)
				for (Rendez_vous rv : ctr.getRendez_vous_id()) {
					if (rv.getDose() == 2 && rv.getValide() == 1)
						rendezvousville.add(rv);
				}
		}

		  vvb=new villeVaccinBeans("oujda", rendezvousville.size());
	       vvbs.add(vvb);
			cntrville = cent_vacDAO.findALLByVille("rabat");
			rendezvousville = new ArrayList<Rendez_vous>();

		for (Centre_Vaccination ctr : cntrville) {
			if (ctr.getRendez_vous_id() != null)
				for (Rendez_vous rv : ctr.getRendez_vous_id()) {
					if (rv.getDose() == 2 && rv.getValide() == 1)
						rendezvousville.add(rv);
				}
		}

		  vvb=new villeVaccinBeans("rabat", rendezvousville.size());
	       vvbs.add(vvb);
	       cntrville = cent_vacDAO.findALLByVille("tanger");
			rendezvousville = new ArrayList<Rendez_vous>();
			
	   	for (Centre_Vaccination ctr : cntrville) {
			if (ctr.getRendez_vous_id() != null)
				for (Rendez_vous rv : ctr.getRendez_vous_id()) {
					if (rv.getDose() == 2 && rv.getValide() == 1)
						rendezvousville.add(rv);
				}
		}

		  vvb=new villeVaccinBeans("tanger", rendezvousville.size());
	       vvbs.add(vvb);
	       cntrville = cent_vacDAO.findALLByVille("tetouan");
			rendezvousville = new ArrayList<Rendez_vous>();
	       
	   	for (Centre_Vaccination ctr : cntrville) {
			if (ctr.getRendez_vous_id() != null)
				for (Rendez_vous rv : ctr.getRendez_vous_id()) {
					if (rv.getDose() == 2 && rv.getValide() == 1)
						rendezvousville.add(rv);
				}
		}

		  vvb=new villeVaccinBeans("tetouan", rendezvousville.size());
	       vvbs.add(vvb);
return vvbs;
	}

}
