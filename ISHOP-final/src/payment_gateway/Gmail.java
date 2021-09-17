package payment_gateway;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class Mailer{  
    public static boolean send(String from,String password,String to,String sub,String msg){  
    	System.out.println("inside send");
          //Get properties object    
          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");  
          
          
          
          //get Session   
          Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {    
	           protected PasswordAuthentication getPasswordAuthentication() {    
	        	   return new PasswordAuthentication(from,password);  
	           }    
          });    
          System.out.println("outside try");
          //compose message    
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(sub);    
           message.setText(msg);
           
           //send message  
           Transport.send(message);    
           System.out.println("message sent successfully"); 
           return true;
          } catch (MessagingException e) {
        	  System.out.println(e);
        	  //throw new RuntimeException(e);
        	  return false;
          }        
    }  
}

public class Gmail {
		
	public boolean Gmail(String from, String to, String password, String title, String message) {   
		System.out.println("entering gmail");
		return Mailer.send(from,password,to,title,message);  
		     //change from mail, password , to mail, title and message 
		}    
}


