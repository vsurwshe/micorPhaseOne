package org.service.apiService;

import org.domain.component.EmailModule;
import org.springframework.stereotype.Service;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

	public String sendEmail(EmailModule email) throws Exception {
		String result = null;
		try {
			Transport transport = this.setEmailSession().getTransport("smtp");
			transport.connect("smtp.gmail.com", "v2018y@gmail.com", "Vany2018ju");
			transport.sendMessage(this.setEmailMessage(email), this.setEmailMessage(email).getAllRecipients());
			result = "Your Email Has Been Send";
		} catch (MessagingException e) {
			LogService.setLogger("Error: " + e.getMessage());
			throw new Exception("Error: " + e.getMessage());
		}
		return result;
	}

	// This method setting email properties
	private Properties setEmailProps() {
		Properties props = null;
		try {
			props = System.getProperties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "456");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.fallback", "false");
		} catch (Exception e) {
			LogService.setLogger("Error: " + e.getMessage());
		}
		return props;
	}

	// This method setting email session
	private Session setEmailSession() {
		Session mailSession = null;
		try {
			mailSession = Session.getDefaultInstance(this.setEmailProps(), null);
			mailSession.setDebug(true);
		} catch (Exception e) {
			LogService.setLogger("Error: " + e.getMessage());
		}
		return mailSession;
	}

	// This method Setting email message
	private Message setEmailMessage(EmailModule email) throws Exception {
		Message mailMessage = null;
		try {
			mailMessage = new MimeMessage(this.setEmailSession());
			mailMessage.setFrom(new InternetAddress("v2018y@gmail.com"));
			mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getToAddress()));
			mailMessage.setContent(email.getBody(), "text/html");
			mailMessage.setSubject(email.getSubject());
		} catch (Exception e) {
			LogService.setLogger("Error: " + e.getMessage());
			throw new Exception("Error: " + e.getMessage());
		}
		return mailMessage;
	}
}
