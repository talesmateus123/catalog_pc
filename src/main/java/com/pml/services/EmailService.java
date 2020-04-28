/** 
 * This is the class "UserService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import org.springframework.mail.SimpleMailMessage;

import com.pml.domain.Client;

public interface EmailService {	
	
	void sendNewPasswordEmail(Client client, String newPass);
	
	void sendEmail(SimpleMailMessage msg);
	
	
}
