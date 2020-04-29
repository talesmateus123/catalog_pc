/** 
 * This is the class "UserService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.pml.domain.Client;

public abstract class AbstractEmailService implements EmailService {	
	
	@Value("${default.sender}")
	private String sender;
	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendNewPasswordEmail(Client client, String newPassword) {
		SimpleMailMessage simpleMailMessage = prepareNewPasswordEmail(client, newPassword);
		sendEmail(simpleMailMessage);
	}
	
	protected SimpleMailMessage prepareSimpleMailMessage(Client object) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage(); 
		simpleMailMessage.setTo(object.getEmail());
		simpleMailMessage.setFrom(sender);
		simpleMailMessage.setSubject("Test");
		simpleMailMessage.setSentDate(new Date (System.currentTimeMillis()));
		simpleMailMessage.setText(object.toString());
		return simpleMailMessage;
	}
	
	protected SimpleMailMessage prepareNewPasswordEmail(Client object, String newPassword) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage(); 
		simpleMailMessage.setTo(object.getEmail());
		simpleMailMessage.setFrom(sender);
		simpleMailMessage.setSubject("New password solicitation");
		simpleMailMessage.setSentDate(new Date (System.currentTimeMillis()));
		simpleMailMessage.setText("Your new password is: " + newPassword);
		return simpleMailMessage;
	}
	
	protected String htmlFromTemplateNewPassword(Client client, String newPassword) {
		Context context = new Context();
		context.setVariable("client", client);
		context.setVariable("newPassword", newPassword);
		return this.templateEngine.process("email/newPasswordSolicitation", context);
	}
	
	@Override
	public void sendNewPasswordHtmlEmail(Client client, String newPassword) {
		try {
			MimeMessage mimeMessage = prepareMimeMessageNewPasswordEmail(client, newPassword);
			sendHtmlEmail(mimeMessage);
		}
		catch(MessagingException e) {
			sendNewPasswordEmail(client, newPassword);
		}
	}

	protected MimeMessage prepareMimeMessageNewPasswordEmail(Client client, String newPassword) throws MessagingException {
		MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setTo(client.getEmail());
		mimeMessageHelper.setFrom(sender);
		mimeMessageHelper.setSubject("Solicitação de nova senha");
		mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
		mimeMessageHelper.setText(htmlFromTemplateNewPassword(client, newPassword), true);
		return mimeMessage;
	}
	
	
}
