package ma.vaccination.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import ma.vaccination.dao.Certificat_VaccinationDAO;
import ma.vaccination.dao.ClientDAO;
import ma.vaccination.entities.Certificat_Vaccination;
import ma.vaccination.entities.Client;
import ma.vaccination.entities.Rendez_vous;
@Service
public class Certificat_VaccinationService {
	@Autowired
	Certificat_VaccinationDAO certificatdao;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	ClientDAO clientdao;
	public int getCertificat(String email) {
	
		Client clt= clientdao.findByEmail(email);
		 List<Rendez_vous> rdvs= new ArrayList<Rendez_vous>();
		 Certificat_Vaccination certificat=null;
		 List<Certificat_Vaccination> cers= certificatdao.findAll();
		 for(Certificat_Vaccination cv :cers ) {
			 if(cv.getId_client().getId().equalsIgnoreCase(clt.getId()))
				 certificat=cv;
				 
		 }
		
		if(certificat==null) {
			if(clt.getRendez_vous_id()== null)
				return 0;
			else 
				  for (Rendez_vous rv :clt.getRendez_vous_id()) {
					  if(rv.getValide()==1)
						  rdvs.add(rv);
				  }
				  if((rdvs!=null && rdvs.get(rdvs.size()-1).getDose()==1)||rdvs==null ) 
					 return 0;
				  else if(rdvs!=null && rdvs.get(rdvs.size()-1).getDose()==2)
				    return 1;
				  return 0;
		}
		else return 2;
		
	}
	
	public void demanderCertificat(String email) throws Exception {
		Client clt= clientdao.findByEmail(email);
		String datedose1=null;
		String datedose2=null;
		for (Rendez_vous rv :clt.getRendez_vous_id()) {
			  if(rv.getValide()==1 && rv.getDose()==1)
				  datedose1=rv.getDate();
			  if(rv.getValide()==1 && rv.getDose()==2)
				  datedose2=rv.getDate();
		  }
		Certificat_Vaccination certificat_Vaccination= new Certificat_Vaccination(datedose1, datedose2, clt);
		certificatdao.save(certificat_Vaccination);
		generatePdfFromHtml(parseThymeleafTemplate(clt.getNom() , clt.getPrenom() ,clt.getSex() ,clt.getAge() ,clt.getTel(),datedose1,datedose2));
		//generatePdfFromHtml(parseThymeleafTemplate("tt" ,"tt","tt" ,11,"tt","tt","tt" ,"tt",4,"tt"));
		
		sendMailWithAttachment(email);
	}
	
	
	
	
	
	public String parseThymeleafTemplate(String nom , String prenom ,String sex ,int age  ,String tel ,String datedose1 ,String datedose2  ) {
	    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode(TemplateMode.HTML);
	    TemplateEngine templateEngine = new TemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver);
	    
	    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String datecert= df.format(new Date());
	    Context context = new Context();
	    context.setVariable("nom", nom);
	    context.setVariable("prenom", prenom);
	    context.setVariable("tel", tel);
	    context.setVariable("age", age);
	    context.setVariable("sex", sex);
	    context.setVariable("datedose1", datedose1);
	    context.setVariable("datedose2", datedose2);
	    context.setVariable("datecert", datecert);
	    return templateEngine.process("templates/certificat", context);
	}
	
	public void generatePdfFromHtml(String html) throws Exception {
	    String outputFolder = System.getProperty("user.home") + File.separator + "certificat_vaccination.pdf";
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
	  
	            String path1 = "/C:/Users/pc/certificat_vaccination.pdf";
	                       
	            FileSystemResource file1 = new FileSystemResource(new File(path1));
	        
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	            helper.addAttachment("certificat_vaccination.pdf", file1);
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
	

}
