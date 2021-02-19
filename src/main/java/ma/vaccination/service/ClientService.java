package ma.vaccination.service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import ma.vaccination.beans.VaccinageBean;
import ma.vaccination.beans.datevaccinsBean;
import ma.vaccination.dao.ClientDAO;
import ma.vaccination.entities.Client;
import ma.vaccination.entities.Rendez_vous;

@Service
public class ClientService {

	@Autowired
	ClientDAO clientdao;


	@Autowired
	JavaMailSender javaMailSender;


	public void sendEmail111(String to, String topic) {

		// create a string of all characters
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

		// create random string builder
		StringBuilder sb = new StringBuilder();

		// create an object of Random class
		Random random = new Random();

		for (int i = 0; i < 7; i++) {

			int index = random.nextInt(alphabet.length());

			char randomChar = alphabet.charAt(index);

			sb.append(randomChar);
		}

		String randomString = sb.toString();
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setFrom("gestionvaccination.lsi2@gmail.com");
		sm.setTo(to);
		sm.setSubject(topic);
		sm.setText(randomString);

		javaMailSender.send(sm);
		;
	}
	@CrossOrigin
	public Client Login(String email, String password) {

		Client clt = clientdao.findByEmailPassword(email, password);
		return clt;
	}

	public void sendEmail(String to, String body, String topic) {

		MimeMessage sm = javaMailSender.createMimeMessage();

		boolean multipart = true;

		MimeMessageHelper helper;

		try {
			helper = new MimeMessageHelper(sm, multipart, "utf-8");

			sm.setContent(body, "text/html");
			helper.setTo(to);
			helper.setTo(to);

			helper.setSubject(topic);
			javaMailSender.send(sm);
		}

		catch (MessagingException e) {

			e.printStackTrace();
		}

	}
	@CrossOrigin
	public String Registration(String nom, String email) {

		Client cl = clientdao.findByEmail(email);

		if (cl != null)
			return "invalid";

		else {

			String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
			StringBuilder sb = new StringBuilder();
			Random random = new Random();

			for (int i = 0; i < 7; i++) {

				int index = random.nextInt(alphabet.length());

				char randomChar = alphabet.charAt(index);

				sb.append(randomChar);
			}

			String randomString = sb.toString();

			sendEmail(email, "<h3>Bonjour cher utilisateur </h3>" + nom
					+ "voici le code de vérification de votre Email  : " + randomString, "Email vérification ");

			return randomString;
		}
	}
	@CrossOrigin
	public void Deleteuser(String nom, String prenom,String email, String password, String code) {
         	Client t = new Client(nom, prenom, email, password, null, 0, null, null,null, null);
			clientdao.save(t);
	} 
	@CrossOrigin
	public void updateuser(String email,String sex, int age, String tel, String ville, Rendez_vous rendez_vous) {
		Client cl = clientdao.findByEmail(email);
		cl.setSex(sex);
		cl.setAge(age);
		cl.setTel(tel);
		cl.setVille(ville);
		List<Rendez_vous> rendez_vous_id= cl.getRendez_vous_id();	
		rendez_vous_id.add(rendez_vous);
		cl.setRendez_vous_id(rendez_vous_id);
		clientdao.save(cl);
		
		
	}
	
	public List<VaccinageBean> nmbrevaccinage() {
		List<VaccinageBean>vcabs = new ArrayList<VaccinageBean>();
		VaccinageBean vcab;
		List<Client> clts = clientdao.findAll();
		String age1="<25ans";
		String age2="entre 25ans et 50ans";
		String age3=">50ans";
		int age1nmbre=0;
		int age2nmbre=0;
		int age3nmbre=0;
		for(Client clt : clts) {
			if(clt.getRendez_vous_id()!= null)
			  for (Rendez_vous rv :clt.getRendez_vous_id()) {
				  
				  if(rv.getValide()==1 && rv.getDose()==2)
					 if(clt.getAge()<25)
						 age1nmbre++;
				  if(clt.getAge()>25 && clt.getAge()<50)
						 age2nmbre++;
				  if(clt.getAge()>50)
						 age3nmbre++;
			  }
			    
		  } 
		vcab=new VaccinageBean(age1, age1nmbre);
		vcabs.add(vcab);
		vcab=new VaccinageBean(age2, age2nmbre);
		vcabs.add(vcab);
		vcab=new VaccinageBean(age3, age3nmbre);
		vcabs.add(vcab);
		return vcabs;
	}
	
	
	
	
	
}
