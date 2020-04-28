/** 
 * This is the class "UserService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.pml.domain.Client;

public abstract class AbstractEmailService implements EmailService {	
	
	@Value("${default.sender}")
	private String sender;
	
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
	
	
}
