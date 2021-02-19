package ma.vaccination.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import ma.vaccination.beans.ReclamationBean;
import ma.vaccination.dao.ClientDAO;
import ma.vaccination.dao.ReclamationDAO;
import ma.vaccination.entities.Client;
import ma.vaccination.entities.Reclamation;
@Service
public class ReclamationService {
	 @Autowired
	 JavaMailSender javaMailSender;
	 
	@Autowired
	private ReclamationDAO reclamationdao;
	
	@Autowired
	private ClientDAO clientdao;
	
	public void creerreclamation (String email , List<String> effets) {
		
		Client cl = clientdao.findByEmail(email) ;
		System.out.println(cl.getEmail());
		Reclamation rec = new Reclamation  ( effets , cl ) ;
		
		reclamationdao.save(rec) ;
		
		
	}
	
	
	public ReclamationBean getreclamationbyid (String id) {
		
	ReclamationBean rb = new ReclamationBean();
	rb.setEffets(reclamationdao.findById(id).orElse(null).getEffets_id());
	return	rb ;
		
	
	}
	
	
	
    public void sendEmailReclamation( String body , String id ) {
	
	 Client cl = reclamationdao.findById(id).orElse(null).getId_client();
	String email = cl.getEmail();
	
		SimpleMailMessage sm= new SimpleMailMessage();
		sm.setFrom("kbibi5696@gmail.com");
		sm.setTo(email);
		sm.setSubject("le résultat du traitement de la réclamation");
		sm.setText(body);
		javaMailSender.send(sm);
		System.out.println("sisi");
		
		
			//Reclamation rec = reclamationdao.findById(id).orElse(null);
			reclamationdao.deleteById(id);
		
	}
}
