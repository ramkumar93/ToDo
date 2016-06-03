package pojo;

import java.io.StringWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;



public class ReminderMail {

	public void content(String name,String email,String listname,String content,String date, String time) {
		 VelocityEngine ve = new VelocityEngine();
		 System.out.println("after ve");
	        try {
				//ve.init();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		System.out.println("above prop");
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
		System.out.println("outside try");
		try{
		Message message = new MimeMessage(session);
		Multipart multipart = new MimeMultipart();
		MimeBodyPart htmlPart = new MimeBodyPart();
		message.setFrom(new InternetAddress("no-reply@todospring.appspotmail.com", "ToDo Scheduled"));
		message.addRecipient(Message.RecipientType.TO,
                 new InternetAddress(email, name));
		message.setSubject("ToDo Schedule Created");

		VelocityContext context = new VelocityContext();
        context.put("name", name);
        context.put("date", date);
        context.put("listname", listname);
        context.put("content", content);
        context.put("time", time);
        System.out.println("above template");
        Template t = ve.getTemplate( "vm/content.vm" );
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        System.out.println(writer.toString());
		//String text = VelocityEngineUtils.mergeTemplateIntoString(
          //      velocityEngine, "registration-confirmation.vm", (Map) context);
		htmlPart.setContent(writer.toString(),"text/html");
		multipart.addBodyPart(htmlPart);
		message.setContent(multipart);
			Transport.send(message);
		
		//resp.getWriter().println("sucessfully sent..");
		}catch(Exception e){
			
		}
	}
	
	
	
	
	public void list(String name,String email,String listname,String date) {
		 VelocityEngine ve = new VelocityEngine();
		 System.out.println("after ve");
	        try {
				//ve.init();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		System.out.println("above prop");
		Properties props = new Properties();
       Session session = Session.getDefaultInstance(props, null);
		System.out.println("outside try");
		try{
		Message message = new MimeMessage(session);
		Multipart multipart = new MimeMultipart();
		MimeBodyPart htmlPart = new MimeBodyPart();
		message.setFrom(new InternetAddress("no-reply@todospring.appspotmail.com", "ToDo List"));
		message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(email, name));
		message.setSubject("ToDo List Reminder");

		VelocityContext context = new VelocityContext();
       context.put("name", name);
       context.put("date", date);
       context.put("listname", listname);
       
       System.out.println("above template");
       Template t = ve.getTemplate( "vm/list.vm" );
       StringWriter writer = new StringWriter();
       t.merge(context, writer);
       System.out.println(writer.toString());
		//String text = VelocityEngineUtils.mergeTemplateIntoString(
         //      velocityEngine, "registration-confirmation.vm", (Map) context);
		htmlPart.setContent(writer.toString(),"text/html");
		multipart.addBodyPart(htmlPart);
		message.setContent(multipart);
			Transport.send(message);
		
		//resp.getWriter().println("sucessfully sent..");
		}catch(Exception e){
			
		}
	}
	
	public void cron(String name,String email,String date,String details,String time) {
		
		VelocityEngine ve = new VelocityEngine();
		 System.out.println("after ve");
	        try {
				//ve.init();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		System.out.println("above prop");
		Properties props = new Properties();
     Session session = Session.getDefaultInstance(props, null);
		System.out.println("outside try");
		try{
		Message message = new MimeMessage(session);
		Multipart multipart = new MimeMultipart();
		MimeBodyPart htmlPart = new MimeBodyPart();
		message.setFrom(new InternetAddress("no-reply@todospring.appspotmail.com", "ToDo Reminder"));
		message.addRecipient(Message.RecipientType.TO,
		                new InternetAddress(email, name));
		message.setSubject("Your Scheduled task on Today");
		System.out.println("above velocity context");
		VelocityContext context = new VelocityContext();
     context.put("name", name);
     context.put("date", date);
     context.put("details", details);
     context.put("time", time);
     
     Template t = ve.getTemplate( "vm/scheduled_task.vm" );
     StringWriter writer = new StringWriter();
     t.merge(context, writer);
     System.out.println(writer.toString());
		htmlPart.setContent(writer.toString(),"text/html");
		multipart.addBodyPart(htmlPart);
		message.setContent(multipart);
			Transport.send(message);
		
		
		System.out.println("Cron completed sucessfully");
		}catch(Exception e){
			
		}
			
	}
	
public void queue(String name,String email,String date,String details,String time) {
		
		VelocityEngine ve = new VelocityEngine();
		 System.out.println("after ve");
	        try {
				//ve.init();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		System.out.println("above prop");
		Properties props = new Properties();
     Session session = Session.getDefaultInstance(props, null);
		System.out.println("outside try");
		try{
		Message message = new MimeMessage(session);
		Multipart multipart = new MimeMultipart();
		MimeBodyPart htmlPart = new MimeBodyPart();
		message.setFrom(new InternetAddress("no-reply@todospring.appspotmail.com", "ToDo Schedule in next 5 mins"));
		message.addRecipient(Message.RecipientType.TO,
		                new InternetAddress(email, name));
		message.setSubject("Task for you in next 5 minutes");
		System.out.println("above velocity context 5 mins");
		VelocityContext context = new VelocityContext();
     context.put("name", name);
     context.put("date", date);
     context.put("details", details);
     context.put("time", time);
     
     Template t = ve.getTemplate( "vm/schedule_in_future.vm" );
     StringWriter writer = new StringWriter();
     t.merge(context, writer);
     System.out.println(writer.toString());
		htmlPart.setContent(writer.toString(),"text/html");
		multipart.addBodyPart(htmlPart);
		message.setContent(multipart);
			Transport.send(message);
		
		
		System.out.println("queue completed sucessfully");
		}catch(Exception e){
			
		}
			
	}
}
