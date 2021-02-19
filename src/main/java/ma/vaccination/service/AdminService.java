package ma.vaccination.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import ma.vaccination.beans.ReclamationBean;
import ma.vaccination.dao.AdminDAO;
import ma.vaccination.dao.Centre_VaccinationDAO;
import ma.vaccination.dao.ClientDAO;
import ma.vaccination.dao.ReclamationDAO;
import ma.vaccination.entities.Admin;
import ma.vaccination.entities.Centre_Vaccination;
import ma.vaccination.entities.Client;
import ma.vaccination.entities.Reclamation;
import ma.vaccination.entities.Rendez_vous;
@Service
public class AdminService {
	@Autowired
	AdminDAO admindao;
	@Autowired
	private ClientDAO clientdao;
	
	@Autowired
	private Centre_VaccinationDAO  centredao;
	
	@Autowired
	private ReclamationDAO  reclamationdao;
	@CrossOrigin
	public Admin LoginAdmin(String email, String password) {
	Admin admin = admindao.findByEmailPassword(email, password);
		return admin;
	}
	
	public List<ReclamationBean> listereclamations (String ville , String centre ){
		

	Centre_Vaccination ct = centredao.findByNomVille(centre, ville) ;
	List<Rendez_vous> rv = ct.getRendez_vous_id();
	List<Client> cl = clientdao.findByVille(ville) ;
	List<Client> clcentre = new ArrayList<Client>() ;
	List<Reclamation> rec = new ArrayList<Reclamation>() ;
	List<ReclamationBean> reclamations = new ArrayList<ReclamationBean>() ;
	ReclamationBean rb ;
	
	
	
		for( Client c :cl) {
			
			for (Rendez_vous rr : c.getRendez_vous_id())
				for (Rendez_vous r  :rv ) 
					if(rr.getId().equalsIgnoreCase(r.getId()) ) {clcentre.add(c);}
				
					if(clcentre!=null && clcentre.size()>0 &&reclamationdao.findByclient(c)!=null) {
				rb = new ReclamationBean (reclamationdao.findByclient(clcentre.get(0)).getId() ,c.getNom() , c.getPrenom() ,reclamationdao.findByclient(c).getEffets_id() ) ;
				 
				reclamations.add(rb) ;}
			
			
			
			}
		
		

	return reclamations ;
	
	
	
	}

	
	
	

}
