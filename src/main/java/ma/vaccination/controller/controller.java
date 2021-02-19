package ma.vaccination.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.vaccination.entities.Client;
import ma.vaccination.entities.Rendez_vous;
import ma.vaccination.beans.ReclamationBean;
import ma.vaccination.beans.Rendez_vousBean;
import ma.vaccination.beans.VaccinageBean;
import ma.vaccination.beans.datevaccinsBean;
import ma.vaccination.beans.villeVaccinBeans;
import ma.vaccination.entities.Admin;
import ma.vaccination.entities.Centre_Vaccination;
import ma.vaccination.service.ClientService;
import ma.vaccination.service.ReclamationService;
import ma.vaccination.service.Rendez_vousService;
import ma.vaccination.service.AdminService;
import ma.vaccination.service.Centre_VaccinationService;
import ma.vaccination.service.Certificat_VaccinationService;
@RestController
@CrossOrigin
public class controller {
    	@Autowired
		private ClientService clientservice;
    	@Autowired
		private AdminService adminservice;
    	@Autowired
		private Rendez_vousService rendezvousservice;
    	@Autowired
    	private Centre_VaccinationService centreservice;
    	@Autowired
    	private ReclamationService reclamationservice;

    	@Autowired
    	private Centre_VaccinationService Centre_Vaccinationservice;
    	@Autowired
    	private Certificat_VaccinationService certificatservice;
    	@CrossOrigin
    	@PostMapping(value="/login/Client")
    	public Client Login(@RequestParam("email")String email,@RequestParam("password") String password){
    	return clientservice.Login(email, password);
    	}
    	
    	@CrossOrigin
    	@PostMapping(value="/login/Admin")
    	public Admin LoginAdmin(@RequestParam("email")String email,@RequestParam("password") String password){
    	return adminservice.LoginAdmin(email, password);
    	}
    	@CrossOrigin
    	@PostMapping(value="/registration")
    	public Map sendemail(@RequestParam("nom")String nom,@RequestParam("email")String email){
    	return Collections.singletonMap("response", clientservice.Registration( nom,    email ));
    	}
    	@CrossOrigin
    	@PostMapping(value="/emailverification")
    	public void verifemail(@RequestParam("nom")String nom,@RequestParam("prenom") String prenom,@RequestParam("email")String email,@RequestParam("password") String password,@RequestParam("code")String code){
    	clientservice.Deleteuser(nom, prenom , email,  password, code);
    	}
    	
    	@CrossOrigin
    	@PostMapping(value="/CentresVaccinationDisponibles")
    	public List<Centre_Vaccination> CentresVacVille(@RequestParam("ville")String ville , @RequestParam("date")String date){
    	return Centre_Vaccinationservice.CentresVacVille( ville ,  date);
    	}
    	@CrossOrigin
    	@PostMapping(value="/creerRendezVous")
    	public Map CreateRendezVous(@RequestParam("email")String email,@RequestParam("centrenom")String centrenom,@RequestParam("ville")String ville,@RequestParam("sex") String sex,@RequestParam("tel") String tel,@RequestParam("age") String age,@RequestParam("date") String date) throws Exception{
    	int a=Integer.parseInt(age);
    	return Collections.singletonMap("response", rendezvousservice.creerRendez_vous( email,centrenom, ville, date,sex ,tel, a));
    	}
    	
    	@CrossOrigin
    	@PostMapping(value="/getRendezVous")
    	public List<Rendez_vousBean> getRendezVous (@RequestParam("centrnom")String centrnom,@RequestParam("ville")String ville){
    	
    	return  rendezvousservice.getRendez_vous(centrnom, ville);
    	}
    	
    	@CrossOrigin
    	@PostMapping(value="/validerRendezVous")
    	public void validerRendezVous (@RequestParam("id")String id){
    	
    	rendezvousservice.ValiderRendezvous(id);
    	}
    	@CrossOrigin
    	@GetMapping(value="/villenmbrevaccin")
    	public List<villeVaccinBeans> villenmbrevaccin() {
    	return Centre_Vaccinationservice.villenmbrevaccin();
    	}
    	
    	@CrossOrigin
    	@GetMapping(value="/vaccinsdate")
    	public List<datevaccinsBean>  getvaccinsdate() {
    	return rendezvousservice.getvaccinsdate();
    	}
    	@CrossOrigin
    	@GetMapping(value="/nmbrevaccinage")
    	public List<VaccinageBean> nmbrevaccinage() {
    	return clientservice.nmbrevaccinage();
    	}
    	@CrossOrigin
    	@GetMapping(value="/nmbretotalvaccines")
    	public Map nmbretotalvaccines() {
    	return Collections.singletonMap("response",rendezvousservice.nmbretotalvaccines());
    	}
    	
    	@CrossOrigin
    	@PostMapping(value="/getCertificat")
    	public Map getCertificat (@RequestParam("email")String email){
    	
    		return Collections.singletonMap("response",certificatservice.getCertificat(email));
    	}
    	@CrossOrigin
    	@PostMapping(value="/demanderCertificat")
    	public void demanderCertificat (@RequestParam("email")String email) throws Exception{
    	
    	certificatservice.demanderCertificat(email);
    	}	
    	
    	@CrossOrigin
    	@PostMapping(value = "/getreclamation")
    	public ReclamationBean getreclamation(@RequestParam("id") String id) {
    		return reclamationservice.getreclamationbyid(id);
    	}

    	@CrossOrigin
    	@PostMapping(value = "/traitementreclamation")
    	public void TraitementRec(@RequestParam("id") String id, @RequestParam("body") String body) {
    		reclamationservice.sendEmailReclamation(body, id);
    	}
    	@CrossOrigin
    	@PostMapping(value = "/reclamation")
    	public void reclamation(@RequestParam("email") String email, @RequestParam("evenements") List<String> evenements) {
    		reclamationservice.creerreclamation(email, evenements);
    	}

    	@CrossOrigin
    	@PostMapping(value = "/reclamationlist")
    	public List<ReclamationBean> reclamations(@RequestParam("ville") String ville,
    			@RequestParam("centrevaccination") String centrevaccination) {
    		return adminservice.listereclamations(ville, centrevaccination);

    	}

} 
