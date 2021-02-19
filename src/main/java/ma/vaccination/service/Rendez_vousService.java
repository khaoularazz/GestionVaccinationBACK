package ma.vaccination.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import com.lowagie.text.DocumentException;

import ma.vaccination.dao.Centre_VaccinationDAO;
import ma.vaccination.dao.ClientDAO;
import ma.vaccination.dao.Rendez_vousDAO;
import ma.vaccination.entities.Centre_Vaccination;
import ma.vaccination.entities.Client;
import ma.vaccination.beans.Rendez_vousBean;
import ma.vaccination.beans.datevaccinsBean;
import ma.vaccination.entities.Rendez_vous;
@Service
public class Rendez_vousService {
	@Autowired
	Rendez_vousDAO rendez_vousdao;
	@Autowired
	ClientDAO clientdao;
	@Autowired
	Centre_VaccinationDAO centredao;
	@Autowired
	private JavaMailSender mailSender;
	@CrossOrigin
	public int creerRendez_vous(String email,String centreName,String ville, String date,String sex,String tel,int age) throws Exception {
		int dose=0;
		int n=0;
		int d=0;
		List<Rendez_vous> rdvs=new ArrayList<Rendez_vous>()	;
		List<Rendez_vous> rdvsnonvalidé=new ArrayList<Rendez_vous>()	;
		Client clt= clientdao.findByEmail(email);

		if(clt.getRendez_vous_id()!=null)
		rdvs =clt.getRendez_vous_id();
		
		if(rdvs!=null) {
			for (Rendez_vous rd: rdvs)
				if(rd.getValide()==0)
					rdvsnonvalidé.add(rd);
			if(rdvsnonvalidé.size()>1) {
			Date date1= new SimpleDateFormat("dd/MM/yyy").parse(rdvsnonvalidé.get(rdvsnonvalidé.size()-1).getDate());
			
			if(date1.compareTo(new Date())<0) {
				return 1;
			}}
		}
		
		
		
		for(Rendez_vous rend :rdvs) {
			if(rend.getValide()==1)
				n++;
		}
		
		if(n>=2) return 2;
		else  {
			clt.setVille(ville);
			clt.setAge(age);
			clt.setSex(sex);
			clt.setTel(tel);
			
		dose= n+1;
		Rendez_vous rendezvous = new Rendez_vous(date, dose,0);
		rendez_vousdao.save(rendezvous);
		rdvs.add(rendezvous);
		clt.setRendez_vous_id(rdvs);
		clientdao.save(clt);
		System.out.println("xxxxxxxxxx"+ville);
		System.out.println("ssssssssssssssssss"+centreName);
		Centre_Vaccination centre= centredao.findByVilleNom(ville, centreName) ;
		
		 rdvs=new ArrayList<Rendez_vous>()	;
		if(centre.getRendez_vous_id()!=null)
			rdvs=centre.getRendez_vous_id();
		rdvs.add(rendezvous);
		centre.setRendez_vous_id(rdvs);
		centredao.save(centre);
		generatePdfFromHtml(parseThymeleafTemplate(clt.getNom() , clt.getPrenom() ,clt.getSex() ,clt.getAge(),clt.getVille() ,date,rendezvous.getId() ,clt.getTel() ,rendezvous.getDose(),centreName ));
		//generatePdfFromHtml(parseThymeleafTemplate("tt" ,"tt","tt" ,11,"tt","tt","tt" ,"tt",4,"tt"));
		
		sendMailWithAttachment(email);
	
	    return 0;}
	    
	      
	}
	
	public String parseThymeleafTemplate(String nom , String prenom ,String sex ,int age ,String ville ,String date ,String num ,String tel ,int ndose,String ctre ) {
	    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode(TemplateMode.HTML);

	    TemplateEngine templateEngine = new TemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver);
        String dose=null;
        if(ndose==1)dose="Premiere Dose";
        else dose="Deuxieme Dose";
	    Context context = new Context();
	    context.setVariable("nom", nom);
	    context.setVariable("prenom", prenom);
	    context.setVariable("tel", tel);
	    context.setVariable("ville", ville);
	    context.setVariable("date", date);
	    context.setVariable("age", age);
	    context.setVariable("num", num);
	    context.setVariable("sex", sex);
	    context.setVariable("cv", ctre);
	    context.setVariable("vaccin", dose);
	    return templateEngine.process("templates/thymeleaf_template", context);
	}
	
	
	public void generatePdfFromHtml(String html) throws Exception {
	    String outputFolder = System.getProperty("user.home") + File.separator + "rendez_vous.pdf";
	    OutputStream outputStream = new FileOutputStream(outputFolder);

	    ITextRenderer renderer = new ITextRenderer();
	    renderer.setDocumentFromString(html);
	    renderer.layout();
	    renderer.createPDF(outputStream);
	    outputStream.close();
	}
	
	 
	public void sendMailWithAttachment(String to ) 
	{
	    MimeMessagePreparator preparator = new MimeMessagePreparator() 
	    {
	        public void prepare(MimeMessage mimeMessage) throws Exception 
	        {
	            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
	            mimeMessage.setFrom(new InternetAddress("gestionvaccination.lsi2@gmail.com"));
	  
	            String path1 = "/C:/Users/pc/rendez_vous.pdf";
	                       
	            FileSystemResource file1 = new FileSystemResource(new File(path1));
	        
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	            helper.addAttachment("rendez_vous.pdf", file1);
	             helper.setText("", true);
	        }
	    };
	     
	    try {
	        mailSender.send(preparator);
	    }
	    catch (MailException ex) {
	        // simply log it and go on...
	    	
	        System.err.println(ex.getMessage());
	    }
	}
	
	public List<Rendez_vousBean> getRendez_vous(String centreName , String ville ){
		Centre_Vaccination centre= centredao.findByVilleNom(ville, centreName) ;
		List<Rendez_vous> rdvs= centre.getRendez_vous_id();
		List<Rendez_vous> rdvs1= new ArrayList<Rendez_vous>();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Rendez_vousBean rvb;
		Client client= new Client() ;
		List<Client> clients=clientdao.findAll();
		List<Rendez_vousBean> rvbs=new ArrayList<Rendez_vousBean>();
		String date= df.format(new Date());
		for(Rendez_vous rdv :rdvs) {
			if( rdv.getDate().equalsIgnoreCase(date) && rdv.getValide()==0)
				//rdvs1.add(rdv);
			for(Client clt :clients) {
				System.out.println(clt.getRendez_vous_id());
				
				System.out.println(rdv);
				if(clt.getRendez_vous_id()!=null ) 
					for(Rendez_vous r  : clt.getRendez_vous_id() ) 
						if(r.getId().equalsIgnoreCase(rdv.getId())) {
					rvb= new Rendez_vousBean(rdv.getId(),clt.getNom(), clt.getPrenom(), clt.getSex(), clt.getAge(),clt.getTel(), rdv.getDose());
					rvbs.add(rvb)	;
					break ;
				}	
			}
			
		}
		//List<Client> clients=clientdao.findAll();
		
		return rvbs ;
		
	}
	
	public void ValiderRendezvous(String id) {
	Rendez_vous rdv= rendez_vousdao.findById(id).orElse(null);
	rdv.setValide(1);
	rendez_vousdao.save(rdv);
	List<Client> clients=clientdao.findAll();
	List<Centre_Vaccination> ctrev=centredao.findAll();
	for(Client clt :clients) {
		if(clt.getRendez_vous_id()!=null ) 
			for(int i=0; i<clt.getRendez_vous_id().size() ;i++ ) 
				if(clt.getRendez_vous_id().get(i).getId().equalsIgnoreCase(rdv.getId())) {
					clt.getRendez_vous_id().get(i).setValide(1);
					clientdao.save(clt);
			    break ;
		}	
	}
	
	for(Centre_Vaccination ctr :ctrev) {
		if(ctr.getRendez_vous_id()!=null ) 
			for(int i=0; i<ctr.getRendez_vous_id().size() ;i++ ) 
				if(ctr.getRendez_vous_id().get(i).getId().equalsIgnoreCase(rdv.getId())) {
					ctr.getRendez_vous_id().get(i).setValide(1);
					centredao.save(ctr);
			    break ;
		}	
	}
	
	}
	
	
	
	
	
	public List<datevaccinsBean>  getvaccinsdate(){
		String today;
		String byestrday;
		String yestrday;
		int dose1today=0;
		int dose1yest=0;
		int dose1byest=0;
		int dose2today=0;
		int dose2yest=0;
		int dose2byest=0;
		  Calendar calendar = Calendar.getInstance();
		  today = new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
		  System.out.println("Today : " + today);
		  calendar.add(Calendar.DATE, -1);
		  yestrday = new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
		  System.out.println("yestrday: " + yestrday);
		  calendar.add(Calendar.DATE, -1);
		  byestrday = new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
		  List<Rendez_vous> rdvs= new ArrayList<Rendez_vous>();
		  List<datevaccinsBean> vaccinsdate = new ArrayList<datevaccinsBean>();
		  datevaccinsBean dvbtoday;
		  datevaccinsBean dvbbyest;
		  datevaccinsBean dvbyest;
		//  List<Rendez_vous> rdvsdose1 = new ArrayList<Rendez_vous>();
		//  List<Rendez_vous> rdvsdose2 = new ArrayList<Rendez_vous>();
		 
		  dose2today=rendez_vousdao.findBy(1, 2,today).size();
		   dose2yest=rendez_vousdao.findBy(1, 2,yestrday).size();
		   dose2byest=rendez_vousdao.findBy(1, 2,byestrday).size();
		   
		  List<Client>clts =clientdao.findAll();
		  for(Client clt : clts) {
			  if(clt.getRendez_vous_id()!=null)
			  for (Rendez_vous rv :clt.getRendez_vous_id()) {
				  if(rv.getValide()==1)
					  rdvs.add(rv);}
			  System.out.println(rdvs);
			  if(rdvs!=null && rdvs.get(rdvs.size()-1).getDose()==1 && rdvs.get(rdvs.size()-1).getDate().equalsIgnoreCase(yestrday) ) 
				  dose1yest++;		
			  
			  if(rdvs!=null && rdvs.get(rdvs.size()-1).getDose()==1 && rdvs.get(rdvs.size()-1).getDate().equalsIgnoreCase(today) ) 
				  dose1today++;	
			  
			  if(rdvs!=null && rdvs.get(rdvs.size()-1).getDose()==1 && rdvs.get(rdvs.size()-1).getDate().equalsIgnoreCase(byestrday) ) 
				  dose1byest++;	
		  
			  
		  }  dvbtoday=new datevaccinsBean(today, dose1today, dose2today);
			  dvbbyest=new datevaccinsBean(byestrday, dose1byest, dose2byest);
			  dvbyest=new datevaccinsBean(yestrday, dose1yest, dose2yest);
			  vaccinsdate.add(dvbbyest);
			  vaccinsdate.add(dvbyest);			 
			  vaccinsdate.add(dvbtoday);
			  
			return  vaccinsdate;
		
	}
	
	public List<Integer> nmbretotalvaccines(){
		List<Integer> nmbretotalvaccines=new ArrayList<Integer>();
		 List<Rendez_vous> rdvs= new ArrayList<Rendez_vous>();
		 int nbrerdvs2=rendez_vousdao.findBy(1, 2).size();
		 int nbrerdvs1=0;
		 List<Client> clts = clientdao.findAll();
		 for(Client clt : clts) {
				if(clt.getRendez_vous_id()!= null)
				  for (Rendez_vous rv :clt.getRendez_vous_id()) {
					  if(rv.getValide()==1)
						  rdvs.add(rv);
				  }
				  if(rdvs!=null && rdvs.get(rdvs.size()-1).getDose()==1 ) 
					  nbrerdvs1++;	
				    
			  } 
		 nmbretotalvaccines.add(nbrerdvs1);
		 nmbretotalvaccines.add(nbrerdvs2);
		return nmbretotalvaccines;
	}
	
}
